package com.ashlikun.loadswitch

import android.R
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

/**
 * 作者　　: 李坤
 * 创建时间: 2018/1/23　10:09
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：
 */
object LoadSwitchUtils {
    @JvmStatic
    fun getTargetContext(activityOrFragmentOrView: Any?): TargetContext {
        var contentParent: ViewGroup
        val context: Context?
        when (activityOrFragmentOrView) {
            is Activity -> {
                context = activityOrFragmentOrView
                contentParent = activityOrFragmentOrView.findViewById(R.id.content)
            }
            is Fragment -> {
                val fragment = activityOrFragmentOrView
                context = fragment.activity
                contentParent = fragment.view!!.parent as ViewGroup
            }
            is View -> {
                val view = activityOrFragmentOrView
                contentParent = view.parent as ViewGroup
                context = view.context
            }
            else -> {
                throw IllegalArgumentException("the argument's type must be Fragment or Activity: init(context)")
            }
        }
        val childCount = contentParent.childCount
        //获取原来布局的坐标
        var index = 0
        val oldContent: View
        if (activityOrFragmentOrView is View) {
            oldContent = activityOrFragmentOrView
            for (i in 0 until childCount) {
                if (contentParent.getChildAt(i) === oldContent) {
                    index = i
                    break
                }
            }
        } else {
            oldContent = contentParent.getChildAt(0)
        }
        return TargetContext(context!!, contentParent, oldContent, index)
    }

    fun replaceId(params: ConstraintLayout.LayoutParams, fromId: Int, toId: Int) {
        if (params.circleConstraint == fromId) {
            params.circleConstraint = toId
        }
        if (params.leftToLeft == fromId) {
            params.leftToLeft = toId
        }
        if (params.leftToRight == fromId) {
            params.leftToRight = toId
        }
        if (params.rightToLeft == fromId) {
            params.rightToLeft = toId
        }
        if (params.rightToRight == fromId) {
            params.rightToRight = toId
        }
        if (params.topToTop == fromId) {
            params.topToTop = toId
        }
        if (params.topToBottom == fromId) {
            params.topToBottom = toId
        }
        if (params.bottomToTop == fromId) {
            params.bottomToTop = toId
        }
        if (params.bottomToBottom == fromId) {
            params.bottomToBottom = toId
        }
        if (params.baselineToBaseline == fromId) {
            params.baselineToBaseline = toId
        }
        if (params.startToEnd == fromId) {
            params.startToEnd = toId
        }
        if (params.startToStart == fromId) {
            params.startToStart = toId
        }
        if (params.endToStart == fromId) {
            params.endToStart = toId
        }
        if (params.endToEnd == fromId) {
            params.endToEnd = toId
        }
    }
}