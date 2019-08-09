package com.ashlikun.loadswitch;

import android.view.ViewGroup;
import android.widget.FrameLayout;

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

    //真正的切换布局
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


    protected LoadSwitchService(TargetContext targetContext, LoadSwitch.Builder builder, OnLoadLayoutListener listener) {
        //开始替换布局
        mLoadingAndRetryLayout = new LoadSwitchLayou(targetContext.getContext());
        mLoadingAndRetryLayout.setListener(listener);
        mLoadingAndRetryLayout.setBuilder(builder);
        ViewGroup.LayoutParams lp = targetContext.getOldContent().getLayoutParams();
        FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        targetContext.getOldContent().setLayoutParams(lp2);
        targetContext.getParentView().addView(mLoadingAndRetryLayout, targetContext.getChildIndex(), lp);
        mLoadingAndRetryLayout.addOtherView(targetContext.getOldContent());
        showContent();
        loadingCanShow = true;
    }


    public void showLoading(ContextData data) {
        if (loadingCanShow) {
            mLoadingAndRetryLayout.showLoading(data);
        }
    }

    public void showRetry(ContextData data) {
        mLoadingAndRetryLayout.showRetry(data);
        loadingCanShow = true;
    }

    public void showContent() {
        mLoadingAndRetryLayout.showContent();
        if (loadingShowOne) {
            loadingCanShow = false;
        }
    }

    public void showEmpty(ContextData data) {
        mLoadingAndRetryLayout.showEmpty(data);
        loadingCanShow = true;
    }


    public LoadSwitchLayou getLoadingAndRetryLayout() {
        return mLoadingAndRetryLayout;
    }
}
