package com.ashlikun.loadswitch

import android.content.Context
import android.view.ViewGroup
import android.view.View

/**
 * 作者　　: 李坤
 * 创建时间: 2018/1/23　10:10
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：
 */
data class TargetContext(val context: Context, val parentView: ViewGroup, val oldContent: View, val childIndex: Int)