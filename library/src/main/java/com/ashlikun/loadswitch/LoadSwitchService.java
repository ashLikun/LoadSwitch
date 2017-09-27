package com.ashlikun.loadswitch;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者　　: 李坤
 * 创建时间: 17:07 Administrator
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：初始化
 * LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_load_empty;
 * LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_load_retry;
 * LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_load_loading;
 */

public class LoadSwitchService {
    public static final int NO_LAYOUT_ID = 0;
    public static int BASE_LOADING_LAYOUT_ID = R.layout.base_load_loading;
    public static int BASE_RETRY_LAYOUT_ID = R.layout.base_load_retry;
    public static int BASE_EMPTY_LAYOUT_ID = R.layout.base_load_empty;

    private LoadSwitchLayou mLoadingAndRetryLayout;
    // 如果成功了那么Start方法就不会显示加载中的布局
    private boolean loadingCanShow = true;
    //标记是否只显示加载中一次
    private boolean loadingShowOne = true;

    public boolean isLoadingCanShow() {
        return loadingCanShow;
    }

    public void setLoadingCanShow(boolean loadingCanShow) {
        this.loadingCanShow = loadingCanShow;
    }

    public void setLoadingShowOne(boolean loadingShowOne) {
        this.loadingShowOne = loadingShowOne;
    }

    public OnLoadLayoutListener DEFAULT_LISTENER = new OnLoadLayoutListener() {
        @Override
        public void setRetryEvent(View retryView, ContextData data) {

        }

        @Override
        public void setLoadingEvent(View loadingView, ContextData data) {

        }

        @Override
        public void setEmptyEvent(View emptyView, ContextData data) {

        }
    };

    public static LoadSwitchService generate(Object activityOrFragment, OnLoadLayoutListener listener) {
        if (activityOrFragment != null)
            return new LoadSwitchService(activityOrFragment, listener);
        return null;
    }

    private LoadSwitchService(Object activityOrFragmentOrView, OnLoadLayoutListener listener) {
        if (listener == null) listener = DEFAULT_LISTENER;

        ViewGroup contentParent = null;
        Context context;
        if (activityOrFragmentOrView instanceof Activity) {
            Activity activity = (Activity) activityOrFragmentOrView;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (activityOrFragmentOrView instanceof Fragment) {
            Fragment fragment = (Fragment) activityOrFragmentOrView;
            context = fragment.getActivity();
            contentParent = (ViewGroup) (fragment.getView().getParent());
        } else if (activityOrFragmentOrView instanceof View) {
            View view = (View) activityOrFragmentOrView;
            contentParent = (ViewGroup) (view.getParent());
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("the argument's type must be Fragment or Activity: init(context)");
        }
        int childCount = contentParent.getChildCount();
        //get contentParent
        int index = 0;
        View oldContent;
        if (activityOrFragmentOrView instanceof View) {
            oldContent = (View) activityOrFragmentOrView;
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldContent) {
                    index = i;
                    break;
                }
            }
        } else {
            oldContent = contentParent.getChildAt(0);
        }
        contentParent.removeView(oldContent);
        //setup content layout
        LoadSwitchLayou loadingAndRetryLayout = new LoadSwitchLayou(context);
        loadingAndRetryLayout.setLoadingAndRetryListener(listener);
        ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
        contentParent.addView(loadingAndRetryLayout, index, lp);
        loadingAndRetryLayout.setContentView(oldContent);
        // setup loading,retry,empty layout
        setupLoadingLayout(listener, loadingAndRetryLayout);
        setupRetryLayout(listener, loadingAndRetryLayout);
        setupEmptyLayout(listener, loadingAndRetryLayout);
        //callback
        mLoadingAndRetryLayout = loadingAndRetryLayout;
        showContent();
        loadingCanShow = true;
    }

    private void setupEmptyLayout(OnLoadLayoutListener listener, LoadSwitchLayou loadingAndRetryLayout) {
        if (listener.isSetEmptyLayout()) {
            int layoutId = listener.generateEmptyLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                loadingAndRetryLayout.setEmptyView(layoutId);
            } else {
                loadingAndRetryLayout.setEmptyView(listener.generateEmptyLayout());
            }
        } else {
            if (BASE_EMPTY_LAYOUT_ID != NO_LAYOUT_ID)
                loadingAndRetryLayout.setEmptyView(BASE_EMPTY_LAYOUT_ID);
        }
    }

    private void setupLoadingLayout(OnLoadLayoutListener listener, LoadSwitchLayou loadingAndRetryLayout) {
        if (listener.isSetLoadingLayout()) {
            int layoutId = listener.generateLoadingLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                loadingAndRetryLayout.setLoadingView(layoutId);
            } else {
                loadingAndRetryLayout.setLoadingView(listener.generateLoadingLayout());
            }
        } else {
            if (BASE_LOADING_LAYOUT_ID != NO_LAYOUT_ID)
                loadingAndRetryLayout.setLoadingView(BASE_LOADING_LAYOUT_ID);
        }
    }

    private void setupRetryLayout(OnLoadLayoutListener listener, LoadSwitchLayou loadingAndRetryLayout) {
        if (listener.isSetRetryLayout()) {
            int layoutId = listener.generateRetryLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                loadingAndRetryLayout.setLoadingView(layoutId);
            } else {
                loadingAndRetryLayout.setLoadingView(listener.generateRetryLayout());
            }
        } else {
            if (BASE_RETRY_LAYOUT_ID != NO_LAYOUT_ID)
                loadingAndRetryLayout.setRetryView(BASE_RETRY_LAYOUT_ID);
        }
    }


    public void showLoading(ContextData data) {
        if (loadingCanShow)
            mLoadingAndRetryLayout.showLoading(data);
    }

    public void showRetry(ContextData data) {
        mLoadingAndRetryLayout.showRetry(data);
        loadingCanShow = true;
    }

    public void showContent() {
        mLoadingAndRetryLayout.showContent();
        if (loadingShowOne)
            loadingCanShow = false;
    }

    public void showEmpty(ContextData data) {
        mLoadingAndRetryLayout.showEmpty(data);
        loadingCanShow = true;
    }


    public LoadSwitchLayou getmLoadingAndRetryLayout() {
        return mLoadingAndRetryLayout;
    }
}
