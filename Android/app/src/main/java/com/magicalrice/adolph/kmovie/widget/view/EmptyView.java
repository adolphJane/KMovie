package com.magicalrice.adolph.kmovie.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicalrice.adolph.kmovie.R;

public class EmptyView extends RelativeLayout{
    private TextView textView;

    public EmptyView(Context context) {
        this(context,null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_empty,this,true);
        textView = findViewById(R.id.txt_empty);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
