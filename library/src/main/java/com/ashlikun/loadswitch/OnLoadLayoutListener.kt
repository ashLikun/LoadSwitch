package com.ashlikun.loadswitch

import android.view.View

/**
 * @author　　: 李坤
 * 创建时间: 2022/4/30 17:04
 * 邮箱　　：496546144@qq.com
 *
 * 功能介绍：布局切换时的监听
 */
interface OnLoadLayoutListener {
    fun setRetryEvent(retryView: View, data: ContextData)
    fun setLoadingEvent(loadingView: View, data: ContextData)
    fun setEmptyEvent(emptyView: View, data: ContextData)
}