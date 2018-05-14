package com.magicalrice.adolph.kmovie.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adolph on 2018/4/12.
 */

public class SpUtils {
    private SharedPreferences sharedPreferences;
    private static SpUtils instance;
    /**
     * 保存在手机里面的名字
     */
    public static final String FILE_NAME = "shared_data";
    private static SharedPreferences.Editor editor;

    public SpUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SpUtils getInstance(Context context) {
        if (instance == null) {
            instance = new SpUtils(context);
        }
        return instance;
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public void put(String key, Object object) {
        if (object != null) {
            if (object instanceof String) {
                editor.putString(key, (String) object);
            } else if (object instanceof Integer) {
                editor.putInt(key, (Integer) object);
            } else if (object instanceof Boolean) {
                editor.putBoolean(key, (Boolean) object);
            } else if (object instanceof Float) {
                editor.putFloat(key, (Float) object);
            } else if (object instanceof Long) {
                editor.putLong(key, (Long) object);
            } else if (object instanceof List) {
                if (((List) object).size() >= 0) {
                    Gson gson = new Gson();
                    String str = gson.toJson(object);
                    editor.putString(key, str);
                    editor.commit();
                }
            } else {
                editor.putString(key, object.toString());
            }
            editor.apply();
        }
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           键的值
     * @param defaultObject 默认值
     * @return
     */

    public <T> Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else if (defaultObject instanceof List) {
            List<T> datalist= new ArrayList<T>();
            String str = sharedPreferences.getString(key,null);
            if (str == null) {
                return null;
            }
            Gson gson = new Gson();
            datalist = gson.fromJson(str,new TypeToken<List<T>>(){}.getType());
            return datalist;
        } else {
            return sharedPreferences.getString(key, null);
        }

    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public void remove(String key) {
        editor.remove(key).apply();
    }

    /**
     * 清除所有的数据
     */
    public void clear() {
        editor.clear().apply();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}
