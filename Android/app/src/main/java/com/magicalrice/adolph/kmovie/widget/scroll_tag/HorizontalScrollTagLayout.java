package com.magicalrice.adolph.kmovie.widget.scroll_tag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adolph on 2018/4/24.
 */

public class HorizontalScrollTagLayout extends HorizontalScrollView implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private LinearLayout parentLayout;
    private int selectPosition = 0, lastPosition = -1;
    private onScrollSelectTagListener listener;
    private boolean isTagClick;
    private ArrayList<Integer> currentItemWidth;
    private int scrollLength = 0;
    private Paint mPaint;

    public HorizontalScrollTagLayout(Context context) {
        this(context, null);
    }

    public HorizontalScrollTagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScrollTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        currentItemWidth = new ArrayList<>();
        parentLayout = new LinearLayout(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parentLayout.setLayoutParams(params);
        parentLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(parentLayout);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(76);
    }

    public void setParam(ViewPager viewPager, List<String> tagList,int choosePosition) {
        this.viewPager = viewPager;
        initHeaderTags(tagList,choosePosition);
        viewPager.addOnPageChangeListener(this);
    }

    private void initHeaderTags(List<String> tagList,int choosePosition) {
        if (parentLayout == null) {
            throw new NullPointerException("parentLayout must be initialized.");
        }
        parentLayout.removeAllViews();
        currentItemWidth.clear();
        for (int i = 0; i < tagList.size(); i++) {
            TextView textView = new TextView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setPadding((int) ScreenUtils.dp2px(getContext(), 10), (int) ScreenUtils.dp2px(getContext(), 5), (int) ScreenUtils.dp2px(getContext(), 10), (int) ScreenUtils.dp2px(getContext(), 5));
            params.gravity = Gravity.CENTER_VERTICAL;
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setText(tagList.get(i));
            textView.setTextSize(15);
            textView.setTextColor(getResources().getColor(R.color.white_1));
            textView.setAlpha(0.3f);
            int finalI = i;
            textView.setOnClickListener(v -> {
                isTagClick = true;
                selectItem(finalI);
            });
            parentLayout.addView(textView);
            if (i == 0) {
                currentItemWidth.add((int) textView.getPaint().measureText(tagList.get(i)) + (int) ScreenUtils.dp2px(getContext(), 20));
            } else {
                currentItemWidth.add((int) textView.getPaint().measureText(tagList.get(i)) + (int) ScreenUtils.dp2px(getContext(), 20) + currentItemWidth.get(i - 1));
            }
        }
        lastPosition = -1;
        updateSelectItem();
        invalidate();
        if (choosePosition != -1) {
            selectItem(choosePosition);
        } else {
            selectItem(0);
        }
    }

    public void selectItem(int position) {
        selectNewItem(position);
        if (viewPager != null) {
            viewPager.setCurrentItem(position, true);
        }
    }

    private void selectNewItem(int position) {
        lastPosition = selectPosition;
        selectPosition = position;
        updateSelectItem();
        if (lastPosition != selectPosition) {
            updateLastItem();
        }
        if (listener != null) {
            listener.onSelectTag(position);
        }
    }

    private void updateSelectItem() {
        if (parentLayout != null) {
            TextView tv = (TextView) parentLayout.getChildAt(selectPosition);
            if (tv != null) {
                tv.setAlpha(0.5f);
                tv.setTypeface(Typeface.DEFAULT_BOLD);
                int dx = 0;
                int halfLayoutWidth = getMeasuredWidth() / 2;
                if (currentItemWidth.get(selectPosition) < scrollLength + halfLayoutWidth) {
                    dx = currentItemWidth.get(selectPosition) - scrollLength - halfLayoutWidth - tv.getWidth() / 2;
//                    LUtils.e("小位置%d,半屏宽%d,半tv宽度%d,滚动距离%d", itemWidth.get(selectPosition), halfLayoutWidth, tv.getWidth() / 2,scrollLength);
                } else {
                    dx = currentItemWidth.get(selectPosition) - scrollLength - halfLayoutWidth - tv.getWidth() / 2;
//                    LUtils.e("大位置%d,半屏宽%d,半tv宽度%d,滚动距离%d", itemWidth.get(selectPosition), halfLayoutWidth, tv.getWidth() / 2,scrollLength);
                }
                smoothScrollBy(dx, 0);
            }
        }
    }

    private void updateLastItem() {
        if (parentLayout != null) {
            TextView tv = (TextView) parentLayout.getChildAt(lastPosition);
            if (tv != null) {
                tv.setAlpha(0.3f);
                tv.setTypeface(Typeface.DEFAULT);
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        scrollLength = l;
        if (listener != null) {
            if (getScrollX() == 0) {
                listener.onScrollTop(true);
                listener.onScrollBottom(false);
            } else if (getScrollX() + getWidth() - getPaddingLeft() - getPaddingRight() == getChildAt(0).getWidth()) {
                listener.onScrollTop(false);
                listener.onScrollBottom(true);
            } else {
                listener.onScrollTop(false);
                listener.onScrollBottom(false);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (parentLayout != null && parentLayout.getChildAt(selectPosition) != null && currentItemWidth.size() > 0) {
            RectF rectF = new RectF(0, getMeasuredHeight() - ScreenUtils.dp2px(getContext(), 13), currentItemWidth.get(currentItemWidth.size() - 1), getMeasuredHeight() - ScreenUtils.dp2px(getContext(), 10));
            canvas.drawRoundRect(rectF, ScreenUtils.dp2px(getContext(), 1), ScreenUtils.dp2px(getContext(), 1), mPaint);
        }
    }

    public void addOnScrollSelectTagListener(onScrollSelectTagListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (!isTagClick) {
            selectNewItem(position);
        }
        isTagClick = false;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void detachListener() {
        if (viewPager != null) {
            viewPager.removeOnPageChangeListener(this);
        }
    }
}
