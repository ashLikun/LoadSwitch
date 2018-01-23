package com.ashlikun.loadswitch;

import android.view.View;

/**
 * 作者　　: 李坤
 * 创建时间: 17:08 Administrator
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：布局切换时的监听
 */
public interface OnLoadLayoutListener {

    void setRetryEvent(View retryView, ContextData data);

    void setLoadingEvent(View loadingView, ContextData data);

    void setEmptyEvent(View emptyView, ContextData data);
}