package com.ashlikun.loadswitch;

import android.view.View;

/**
 * 作者　　: 李坤
 * 创建时间: 17:08 Administrator
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：布局切换时的监听
 */

public abstract class OnLoadLayoutListener {
    public abstract void setRetryEvent(View retryView, ContextData data);

    public abstract void setLoadingEvent(View loadingView, ContextData data);

    public abstract void setEmptyEvent(View emptyView, ContextData data);

    public int generateLoadingLayoutId() {
        return LoadSwitchService.NO_LAYOUT_ID;
    }

    public int generateRetryLayoutId() {
        return LoadSwitchService.NO_LAYOUT_ID;
    }

    public int generateEmptyLayoutId() {
        return LoadSwitchService.NO_LAYOUT_ID;
    }

    public View generateLoadingLayout() {
        return null;
    }

    public View generateRetryLayout() {
        return null;
    }

    public View generateEmptyLayout() {
        return null;
    }

    public boolean isSetLoadingLayout() {
        if (generateLoadingLayoutId() != LoadSwitchService.NO_LAYOUT_ID || generateLoadingLayout() != null)
            return true;
        return false;
    }

    public boolean isSetRetryLayout() {
        if (generateRetryLayoutId() != LoadSwitchService.NO_LAYOUT_ID || generateRetryLayout() != null)
            return true;
        return false;
    }

    public boolean isSetEmptyLayout() {
        if (generateEmptyLayoutId() != LoadSwitchService.NO_LAYOUT_ID || generateEmptyLayout() != null)
            return true;
        return false;
    }


}