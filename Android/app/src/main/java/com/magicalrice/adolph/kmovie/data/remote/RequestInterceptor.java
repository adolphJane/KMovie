package com.magicalrice.adolph.kmovie.data.remote;

import com.magicalrice.adolph.kmovie.data.enumerations.AuthenticationType;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Adolph on 2018/2/26.
 */

/**
 * {@link Interceptor} to add the API key query parameter and if available session information. As it modifies the URL
 * and may retry requests, ensure this is added as an application interceptor (never a network interceptor), otherwise
 * caching will be broken and requests will fail.
 */
public class RequestInterceptor implements Interceptor {

    private final Tmdb tmdb;

    public RequestInterceptor(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return handleIntercept(chain,tmdb);
    }

    public Response handleIntercept(Chain chain,Tmdb tmdb) throws IOException {
        Request originalRequest = chain.request();
        //不拦截其他域名的请求
        if (!ApiConstants.API_HOST.equals(originalRequest.url().host())) {
            return chain.proceed(originalRequest);
        }
        //为API Key参数
        HttpUrl.Builder builder = originalRequest.url().newBuilder();
        builder.setEncodedQueryParameter(ApiConstants.PARAM_API_KEY,ApiConstants.API_KEY);

        //检查路径
        AuthenticationType type = null;
        List<String> pathSegments = originalRequest.url().pathSegments();
        if ((pathSegments.size() >= 2 && pathSegments.get(1).equals("account")) || pathSegments.get(pathSegments.size() - 1).equals("account_states")) {
            type = AuthenticationType.ACCOUNT;
        } else if (pathSegments.get(pathSegments.size() - 1).equals("rating") || !originalRequest.method().equals("GET")) {
            type = determineAuthenticationType(builder,tmdb);
        }
        addSessionToQuery(builder,type,tmdb);
        builder.removeAllEncodedQueryParameters("authentication");

        if (type != null) {
            builder.fragment(type.toString());
        }

        Request.Builder newBuilder = originalRequest.newBuilder();
        newBuilder.url(builder.build());
        Response response = chain.proceed(newBuilder.build());

        if (!response.isSuccessful()) {
            String retryHeader = response.header("Retry-After");
            if (retryHeader != null) {
                try {
                    Integer retry = Integer.parseInt(retryHeader);
                    Thread.sleep((int) ((retry + 0.5) * 1000));
                    return handleIntercept(chain,tmdb);
                } catch (NumberFormatException |InterruptedException e) {

                }
            }
        }
        return response;
    }

    private void addSessionToQuery(HttpUrl.Builder urlBuilder,AuthenticationType type,Tmdb tmdb) {
        if (type == AuthenticationType.GUEST) {
            urlBuilder.addEncodedQueryParameter(ApiConstants.PARAM_GUEST_SESSION_ID,tmdb.getGuestSessionId());
        } else if (type == AuthenticationType.ACCOUNT) {
            urlBuilder.addEncodedQueryParameter(ApiConstants.PARAM_SESSION_ID,tmdb.getSessionId());
        }
    }

    public static AuthenticationType determineAuthenticationType(HttpUrl.Builder builder,Tmdb tmdb) {
        HttpUrl url = builder.build();
        String authParm = url.queryParameter("authentication");
        AuthenticationType type = AuthenticationType.get(url.fragment());

        if (type == null) {
            if (authParm != null) {
                if (authParm.equals("account")) {
                    type = AuthenticationType.ACCOUNT;
                } else {
                    type = AuthenticationType.GUEST;
                }
            } else {
                if (tmdb.hasAccountSession()) {
                    type = AuthenticationType.ACCOUNT;
                } else if (tmdb.hasGuestSession()) {
                    type = AuthenticationType.GUEST;
                } else {
                    type = null;
                }
            }
        }
        return type;
    }
}
