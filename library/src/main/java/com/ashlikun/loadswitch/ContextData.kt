package com.ashlikun.loadswitch

import android.content.Context
import android.os.Bundle
import androidx.annotation.DrawableRes

/**
 * @author　　: 李坤
 * 创建时间: 2022/4/30 17:03
 * 邮箱　　：496546144@qq.com
 *
 * 功能介绍：布局切换时的内容数据
 */
open class ContextData(
    //显示的标题
    var title: String = "",
    //显示图片的Id 大于0就会显示， 其他的显示默认
    @DrawableRes
    var resId: Int = LoadSwitch.BASE_LOAD_SERVICE_ERROR,
    //按钮文字
    var buttonText: String = LoadSwitch.app.getString(R.string.loadswitch_button),
    //标识
    var flag: Int = 0,
    // 失败 时候的错误类型
    var errCode: Int = 0,
    //图片宽度
    var imgWidth: Int = -1,
    //图片高度
    var imgHeight: Int = -1,
    //扩展的其他数据
    var extend: Bundle? = null
) {
    fun check() {

    }

    fun getTitleErrUI() =
        title + if (errCode != 0) "" else "\n(${LoadSwitch.app.getString(R.string.loadswitch_error_code)}:${errCode})".trimIndent()
}