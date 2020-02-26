package com.magicalrice.adolph.kmovie.widget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.data.entities.AppInfo;

import java.util.ArrayList;

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder>{
    private ArrayList<AppInfo> mAppList;
    private OnItemClickLitener mOnItemClickLitener;

    public AppInfoAdapter(ArrayList<AppInfo> data) {
        this.mAppList = data;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app_share,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(mAppList.get(position).getAppName());
        holder.img.setBackground(mAppList.get(position).getAppIcon());

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(v -> {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemClick(mAppList.get(pos),holder.itemView,pos);
            });

            holder.itemView.setOnLongClickListener(v -> {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemLongClick(mAppList.get(pos),holder.itemView,pos);
                return false;
            });
        }
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }

    public interface OnItemClickLitener {
        void onItemClick(AppInfo info, View view, int position);

        void onItemLongClick(AppInfo info, View view, int position);
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;

        ViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.text_list_item);
            img = view.findViewById(R.id.img_list_item);
        }
    }
}
