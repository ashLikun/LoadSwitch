package com.ashlikun.loadswitch

/**
 * 作者　　: 李坤
 * 创建时间: 17:07 Administrator
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：初始化
 * LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_load_empty;
 * LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_load_retry;
 * LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_load_loading;
 */
class LoadSwitchService(targetContext: TargetContext, builder: LoadSwitch, listener: OnLoadLayoutListener) {
    //真正的切换布局
    val loadingAndRetryLayout: LoadSwitchLayou = LoadSwitchLayou(targetContext.context)

    // 如果成功了那么Start方法就不会显示加载中的布局
    var isLoadingCanShow = true

    //标记是否只显示加载中一次
    private var loadingShowOne = true

    init {
        //开始替换布局
        loadingAndRetryLayout.listener = listener
        loadingAndRetryLayout.builder = builder
        loadingAndRetryLayout.targetContext = targetContext
        loadingAndRetryLayout.id = R.id.LoadSwitch_Main_Id
        loadingAndRetryLayout.init()
        showContent()
        isLoadingCanShow = true
    }

    fun setLoadingShowOne(loadingShowOne: Boolean) {
        this.loadingShowOne = loadingShowOne
    }

    fun showLoading(data: ContextData) {
        if (isLoadingCanShow) {
            loadingAndRetryLayout.showLoading(data)
        }
    }

    fun showRetry(data: ContextData) {
        loadingAndRetryLayout.showRetry(data)
        isLoadingCanShow = true
    }

    fun showContent() {
        loadingAndRetryLayout.showContent()
        if (loadingShowOne) {
            isLoadingCanShow = false
        }
    }

    fun showEmpty(data: ContextData) {
        loadingAndRetryLayout.showEmpty(data)
        isLoadingCanShow = true
    }

    val isStatusContent: Boolean
        get() = loadingAndRetryLayout.isStatusContent
    val isStatusLoading: Boolean
        get() = loadingAndRetryLayout.isStatusLoading
    val isStatusEmpty: Boolean
        get() = loadingAndRetryLayout.isStatusEmpty
    val isStatusRetry: Boolean
        get() = loadingAndRetryLayout.isStatusRetry

}