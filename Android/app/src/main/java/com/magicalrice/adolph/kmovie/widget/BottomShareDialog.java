package com.magicalrice.adolph.kmovie.widget;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.data.entities.AppInfo;
import com.magicalrice.adolph.kmovie.utils.Utils;
import com.magicalrice.adolph.kmovie.widget.adapter.AppInfoAdapter;

import java.util.ArrayList;

public class BottomShareDialog extends BottomSheetDialogFragment {
    private BottomSheetBehavior mBehavior;
    private ArrayList<AppInfo> appInfos;
    private RecyclerView mRecyclerView;
    private AppInfoAdapter adapter;
    private Intent shareIntent;
    private String movieName;
    private String moviePoster;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPhotoError();
        movieName = getArguments().getString("title", "empty");
        moviePoster = getArguments().getString("imgPoster", "empty");
        shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.fragment_dialog_share, null);
        mRecyclerView = view.findViewById(R.id.list_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        dialog.setContentView(view);
        ((View) view.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setState(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        appInfos = Utils.getShareAppList(getContext(), shareIntent);
        adapter = new AppInfoAdapter(appInfos);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickLitener(new AppInfoAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(AppInfo info, View view, int position) {
                Utils.shareMsg(info,movieName,moviePoster,getActivity());
            }

            @Override
            public void onItemLongClick(AppInfo info, View view, int position) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + info.getPkgName()));
                startActivity(intent);
            }
        });
        return dialog;
    }



    private void initPhotoError() {
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
