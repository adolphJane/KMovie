package com.magicalrice.adolph.kmovie.utils;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Adolph on 2018/2/27.
 */

public class AnimatorUtil {
    private static final TimeInterpolator sDefaultInterpolator = new AccelerateDecelerateInterpolator();

    public static AnimatorBuilder createAnimator() {
        return new AnimatorBuilder();
    }

    public static AnimatorBuilder createAnimator(TimeInterpolator interpolator) {
        return new AnimatorBuilder(interpolator);
    }

    /**
     * View是否在屏幕上可见
     * @param view
     * @return
     */
    public static boolean isVisibleOnScreen(View view) {
        if (view == null)
            return false;
        return view.getWindowVisibility() == View.VISIBLE && view.getVisibility() == View.VISIBLE && view.isShown();
    }

    public static class AnimatorBuilder {
        private View mView;
        private static final long DEFAULT_DURATION = 300;
        private AnimatorSet mSet;
        private AnimatorSet.Builder mBuilder;
        private TimeInterpolator mInterpolator;
        private boolean mIsPlaying = false;

        private int mRepeatCount = 0;
        private int mCurrentCount = 0;
        private boolean mIsCanceled;
        private boolean mHasInitThenAnim = false;   //是否已经初始化then动画

        private ScheduledExecutorService mRepeatSchedule;

        private ArrayList<Animator> mThenAnimator = new ArrayList<>();

        public AnimatorBuilder() {
            this(sDefaultInterpolator);
        }

        public AnimatorBuilder(TimeInterpolator interpolator) {
            mRepeatCount = 0;
            mSet = new AnimatorSet();
            mInterpolator = interpolator;
        }

        public AnimatorBuilder setRepeatCount(int count) {
            mRepeatCount = count;
            return this;
        }

        public int getRepeatCount() {
            return mRepeatCount;
        }

        public AnimatorBuilder play(View view,String propertyName,float... values) {
            return play(view,DEFAULT_DURATION,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder play(View view,long duration,String propertyName,float... values) {
            return play(view,duration,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder play(View view,long duration,AnimatorListener listener,String propertyName,float... values) {
            return play(view,duration,listener,propertyName,values);
        }

        public AnimatorBuilder play(Animator animator) {
            mBuilder = mSet.play(animator);
            return this;
        }

        public AnimatorBuilder with(View view,String propertyName,float... values) {
            return with(view,DEFAULT_DURATION,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder with(View view,long duration,String propertyName,float... values) {
            return with(view,duration,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder with(View view,long duration,AnimatorListener listener,String propertyName,float... values) {
            return with(view,duration,listener,propertyName,values);
        }

        public AnimatorBuilder with(Animator animator) {
            mBuilder = mBuilder.with(animator);
            return this;
        }

        public AnimatorBuilder before(View view,String propertyName,float... values) {
            return before(view,DEFAULT_DURATION,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder before(View view,long duration,String propertyName,float... values) {
            return before(view,duration,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder before(View view,long duration,AnimatorListener listener,String propertyName,float... values) {
            return before(view,duration,listener,propertyName,values);
        }

        public AnimatorBuilder before(Animator animator) {
            mBuilder = mBuilder.before(animator);
            return this;
        }

        public AnimatorBuilder after(View view,String propertyName,float... values) {
            return after(view,DEFAULT_DURATION,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder after(View view,long duration,String propertyName,float... values) {
            return after(view,duration,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder after(View view,long duration,AnimatorListener listener,String propertyName,float... values) {
            return after(view,duration,listener,propertyName,values);
        }

        public AnimatorBuilder after(Animator animator) {
            mBuilder = mBuilder.after(animator);
            return this;
        }

        public AnimatorBuilder then(View view,String propertyName,float... values) {
            return then(view,DEFAULT_DURATION,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder then(View view,long duration,String propertyName,float... values) {
            return then(view,duration,null,mInterpolator,propertyName,values);
        }

        public AnimatorBuilder then(View view,long duration,AnimatorListener listener,String propertyName,float... values) {
            return then(view,duration,listener,propertyName,values);
        }

        public AnimatorBuilder then(Animator animator) {
            mThenAnimator.add(animator);
            return this;
        }

        public AnimatorBuilder play(View view, long duration, AnimatorListener listener,TimeInterpolator interpolator,String propertyName,float... values) {
            if (mIsPlaying)
                throw new RuntimeException("AnimatorBuilder.play()方法只能调用一次");
            if (view == null)
                throw new RuntimeException("View 不能为null");

            mIsPlaying = true;
            mView = view;

            ObjectAnimator animator = ObjectAnimator.ofFloat(view,propertyName,values).setDuration(duration);
            animator.setInterpolator(interpolator);
            if (listener != null)
                animator.addListener(listener);
            mThenAnimator.clear();
            mBuilder = mSet.play(animator);
            return this;
        }

        public AnimatorBuilder with(View view,long duration,AnimatorListener listener,TimeInterpolator interpolator,String propertyName,float... values) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view,propertyName,values).setDuration(duration);
            animator.setInterpolator(interpolator);
            if (listener != null)
                animator.addListener(listener);
            mBuilder = mBuilder.with(animator);
            return this;
        }

        public AnimatorBuilder before(View view,long duration,AnimatorListener listener,TimeInterpolator interpolator,String propertyName,float... values) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view,propertyName,values).setDuration(duration);
            animator.setInterpolator(interpolator);
            if (listener != null)
                animator.addListener(listener);
            mBuilder = mBuilder.before(animator);
            return this;
        }

        public AnimatorBuilder after(View view,long duration,AnimatorListener listener,TimeInterpolator interpolator,String propertyName,float... values) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view,propertyName,values).setDuration(duration);
            animator.setInterpolator(interpolator);
            if (listener != null)
                animator.addListener(listener);
            mBuilder = mBuilder.after(animator);
            return this;
        }

        public AnimatorBuilder then(View view,long duration,AnimatorListener listener,TimeInterpolator interpolator,String propertyName,float... values) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view,propertyName,values).setDuration(duration);
            animator.setInterpolator(interpolator);
            if (listener != null)
                animator.addListener(listener);
            then(animator);
            return this;
        }

        private void shutDownRepeat(){
            if (mRepeatSchedule != null) {
                try {
                    mRepeatSchedule.shutdownNow();
                    mRepeatSchedule = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public AnimatorBuilder addListener(AnimatorListener listener) {
            mSet.addListener(listener);
            return this;
        }

        public ArrayList<AnimatorListener> getListeners() {
            return mSet.getListeners();
        }

        public void removeListener(AnimatorListener listener) {
            mSet.removeListener(listener);
        }

        public void removeAllListeners() {
            mSet.removeAllListeners();
        }

        public void cancel() {
            mIsCanceled = true;
            shutDownRepeat();
            mSet.cancel();
            mCurrentCount = Integer.MAX_VALUE;
        }

        public void start() {
            beforeStart();
            mSet.start();
        }

        public void start(long duration) {
            beforeStart();
            mSet.setDuration(duration);
            mSet.start();
        }

        public void startDelay(long duration) {
            beforeStart();
            mSet.setStartDelay(duration);
            mSet.start();
        }

        private void beforeStart() {
            mIsCanceled = false;
            handleRepeat();
            if (mHasInitThenAnim)
                return;

            mHasInitThenAnim = true;
            if (mThenAnimator.size() > 0) {
                AnimatorSet set = new AnimatorSet();
                set.playSequentially(mThenAnimator);
                mBuilder.before(set);
            }
        }

        private void handleRepeat() {
            shutDownRepeat();
            mCurrentCount = 0;
            if (mRepeatCount == 0)
                return;
            mSet.addListener(new AnimatorListenerAdapter() {
                long mStart,mEnd;

                @Override
                public void onAnimationStart(Animator animation, boolean isReverse) {
                    mStart = System.currentTimeMillis();        //记录第一次执行所需的时间，以此确定调度器执行动画的间隔时间
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mEnd = System.currentTimeMillis();
                    mSet.removeListener(this);

                     mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                         @Override
                         public void onViewAttachedToWindow(View v) {

                         }

                         @Override
                         public void onViewDetachedFromWindow(View v) {
                            cancel();
                         }
                     });

                     repeat();
                }

                private void repeat() {
                    if (mView == null || mIsCanceled) {
                        shutDownRepeat();
                        return;
                    }

                    mRepeatSchedule = Executors.newSingleThreadScheduledExecutor();
                    mRepeatSchedule.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            if (mIsCanceled) {
                                shutDownRepeat();
                                return;
                            }

                            //不可见，暂停调度
                            if (!isVisibleOnScreen(mView)) {
                                shutDownRepeat();
                                mView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                    @Override
                                    public boolean onPreDraw() {
                                        //再次可见，则重新开启调度
                                        if (isVisibleOnScreen(mView)) {
                                            mView.getViewTreeObserver().removeOnPreDrawListener(this);
                                            repeat();
                                        }
                                        return true;
                                    }
                                });
                                return;
                            }

                            mView.post(()->{
                                mSet.cancel();
                                mSet.start();
                            });

                            if (mRepeatCount > 0) {
                                mCurrentCount++;
                                if (mCurrentCount == mRepeatCount) {
                                    shutDownRepeat();
                                }
                            }
                        }
                    },0,mEnd - mStart, TimeUnit.MILLISECONDS);
                }
            });
        }
    }
}
