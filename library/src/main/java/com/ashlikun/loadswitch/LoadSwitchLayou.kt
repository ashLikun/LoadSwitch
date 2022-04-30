package com.ashlikun.loadswitch

import android.content.Context
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.core.widgets.ConstraintWidget
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * 作者　　: 李坤
 * 创建时间: 17:06 Administrator
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：
 */
class LoadSwitchLayou @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = -1) :
    FrameLayout(context, attrs, defStyleAttr) {
    lateinit var listener: OnLoadLayoutListener
    lateinit var builder: LoadSwitch
    var currentStatus = -999
    lateinit var targetContext: TargetContext

    //是否是约束布局
    private var isConstraintLayout = false

    val contentView
        get() = targetContext.oldContent

    fun init() {
        val contentParams: ViewGroup.LayoutParams = targetContext.oldContent.layoutParams
        //如果是协调布局
        if (targetContext.parentView is ConstraintLayout) {
            isConstraintLayout = true

            //复制Content的LayoutParams
            val contentParamsConstraint = contentParams as ConstraintLayout.LayoutParams
            val params = ConstraintLayout.LayoutParams(contentParamsConstraint)
            if (params.constraintWidget === contentParamsConstraint.constraintWidget) {
                try {
                    //ConstraintLayout.LayoutParams 的 widget对象不能复制，否则会公用同一个
                    val widget = params.javaClass.getDeclaredField("widget")
                    widget.isAccessible = true
                    //设置一个默认的
                    widget[params] = ConstraintWidget()
                } catch (e: NoSuchFieldException) {
                    e.printStackTrace()
                    throw RuntimeException(e)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                    throw RuntimeException(e)
                }
            }
            setLayoutParams(params)
            targetContext.parentView.addView(this, targetContext.childIndex + 1, params)
        } else {
            isConstraintLayout = false
            targetContext.parentView.removeView(targetContext!!.oldContent)
            //其他布局，就把OldContent加入到新的LoadSwitchLayou里面
            targetContext.oldContent.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            addOtherView(targetContext.oldContent)
            targetContext.parentView.addView(this, targetContext.childIndex, contentParams)
        }
    }

    private val isMainThread: Boolean
        private get() = Looper.myLooper() == Looper.getMainLooper()

    fun showLoading(data: ContextData) {
        if (isMainThread) {
            showView(1, data)
        } else {
            post { showView(1, data) }
        }
    }

    fun showRetry(data: ContextData) {
        if (isMainThread) {
            showView(2, data)
        } else {
            post { showView(2, data) }
        }
    }

    fun showContent() {
        if (isMainThread) {
            showView(0, ContextData())
        } else {
            post { showView(0, ContextData()) }
        }
    }

    fun showEmpty(data: ContextData) {
        if (isMainThread) {
            showView(3, data)
        } else {
            post { showView(3, data) }
        }
    }

    private fun showView(status: Int, data: ContextData) {
        if (currentStatus == status) {
            return
        }
        currentStatus = status
        data.check()
        //清空后加的View
        if (isConstraintLayout) {
            removeAllViews()
        } else {
            while (childCount > CALLBACK_CUSTOM_INDEX) {
                removeViewAt(CALLBACK_CUSTOM_INDEX)
            }
        }
        //是否显示content
        contentView.visibility = if (status == 0) View.VISIBLE else View.GONE
        //是否显示后加的布局
        if (isConstraintLayout) {
            visibility = if (status == 0) View.GONE else View.VISIBLE
        }
        when (status) {
            0 -> contentView.requestLayout()
            1 -> addLoadingLayout()?.apply {
                listener.setLoadingEvent(this, data)
            }
            2 -> addRetryLayout()?.apply {
                listener.setRetryEvent(this, data)
            }
            3 -> addEmptyLayout()?.apply {
                listener.setEmptyEvent(this, data)
            }
        }
    }

    fun addOtherView(layoutId: Int): View {
        return addOtherView(LayoutInflater.from(context).inflate(layoutId, this, false))
    }

    fun addOtherView(view: View): View {
        removeView(view)
        addView(view)
        return view
    }


    //添加空布局
    private fun addEmptyLayout(): View? {
        return if (builder.enptyId != NO_LAYOUT_ID) {
            addOtherView(builder.enptyId)
        } else if (builder.enptyView != null) {
            addOtherView(builder.enptyView!!)
        } else {
            if (LoadSwitch.BASE_EMPTY_LAYOUT_ID != NO_LAYOUT_ID) addOtherView(LoadSwitch.BASE_EMPTY_LAYOUT_ID) else null
        }
    }

    //添加加载中布局
    private fun addLoadingLayout(): View? {
        return if (builder.loaddingId != NO_LAYOUT_ID) {
            addOtherView(builder.loaddingId)
        } else if (builder.loaddingView != null) {
            addOtherView(builder.loaddingView!!)
        } else {
            if (LoadSwitch.BASE_LOADING_LAYOUT_ID != NO_LAYOUT_ID) addOtherView(LoadSwitch.BASE_LOADING_LAYOUT_ID) else null
        }
    }

    //添加从新加载
    private fun addRetryLayout(): View? {
        return if (builder.retryId != NO_LAYOUT_ID) {
            addOtherView(builder.retryId)
        } else if (builder.retryView != null) {
            addOtherView(builder.retryView!!)
        } else {
            if (LoadSwitch.BASE_RETRY_LAYOUT_ID != NO_LAYOUT_ID) addOtherView(LoadSwitch.BASE_RETRY_LAYOUT_ID) else null
        }
    }

    val isStatusContent: Boolean
        get() = currentStatus == 0
    val isStatusLoading: Boolean
        get() = currentStatus == 1
    val isStatusEmpty: Boolean
        get() = currentStatus == 3
    val isStatusRetry: Boolean
        get() = currentStatus == 2

    companion object {
        //在content后面加的View位置
        private const val CALLBACK_CUSTOM_INDEX = 1
        const val NO_LAYOUT_ID = 0
    }
}