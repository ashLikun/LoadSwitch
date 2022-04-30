package com.ashlikun.loadswitch

/**
 * 作者　　: 李坤
 * 创建时间: 17:09 Administrator
 * 邮箱　　：496546144@qq.com
 *
 * 功能介绍：按钮点击时的回调
 */
interface OnLoadSwitchClick {
    //数据加载失败点击重新加载
    fun onRetryClick(data: ContextData)

    //数据为空点击重新加载
    fun onEmptyClick(data: ContextData)
}