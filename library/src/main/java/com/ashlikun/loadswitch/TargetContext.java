package com.ashlikun.loadswitch;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/1/23　10:10
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */

public class TargetContext {
    private Context context;
    private ViewGroup parentView;
    private View oldContent;
    private int childIndex;

    public TargetContext(Context context, ViewGroup parentView, View oldContent, int childIndex) {
        this.context = context;
        this.parentView = parentView;
        this.oldContent = oldContent;
        this.childIndex = childIndex;
    }

    public Context getContext() {
        return context;
    }

    View getOldContent() {
        return oldContent;
    }

    int getChildIndex() {
        return childIndex;
    }

    ViewGroup getParentView() {
        return parentView;
    }
}
