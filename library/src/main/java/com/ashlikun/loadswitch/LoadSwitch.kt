package com.ashlikun.loadswitch

import android.app.Application
import android.content.Context
import android.view.View
import com.ashlikun.loadswitch.LoadSwitchUtils.getTargetContext

/**
 * 作者　　: 李坤
 * 创建时间: 2018/1/23　14:33
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：
 */
class LoadSwitch(
    var loaddingId: Int = 0,
    var retryId: Int = 0,
    var enptyId: Int = 0,
    var loaddingView: View? = null,
    var retryView: View? = null,
    var enptyView: View? = null,
) {
    /**
     * 带协调滚动
     */
    fun setNested(): LoadSwitch {
        enptyId = R.layout.base_load_empty_nested_scroll
        loaddingId = R.layout.base_load_loading_nested_scroll
        retryId = R.layout.base_load_retry_nested_scroll
        return this
    }

    /**
     * 带协调滚动
     */
    fun setNestedNoLoadding(): LoadSwitch {
        enptyId = R.layout.base_load_empty_nested_scroll
        retryId = R.layout.base_load_retry_nested_scroll
        return this
    }

    /**
     * 创建
     */
    fun create(activityOrFragmentOrView: Any, listener: OnLoadLayoutListener): LoadSwitchService {
        val targetContext = getTargetContext(activityOrFragmentOrView)
        val switchService = LoadSwitchService(targetContext, this, listener)
        return switchService
    }

    companion object {
        var BASE_LOADING_LAYOUT_ID = R.layout.base_load_loading
        var BASE_RETRY_LAYOUT_ID = R.layout.base_load_retry
        var BASE_EMPTY_LAYOUT_ID = R.layout.base_load_empty
        var BASE_LOAD_SERVICE_ERROR = R.drawable.material_service_error
        lateinit var app: Context
        fun init(application: Application) {
            app = application
        }
    }
}