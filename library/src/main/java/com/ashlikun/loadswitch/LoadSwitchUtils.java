package com.ashlikun.loadswitch;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/1/23　10:09
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */

public class LoadSwitchUtils {
    public final static TargetContext getTargetContext(Object activityOrFragmentOrView) {
        ViewGroup contentParent = null;
        Context context;
        if (activityOrFragmentOrView instanceof Activity) {
            Activity activity = (Activity) activityOrFragmentOrView;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (activityOrFragmentOrView instanceof Fragment) {
            Fragment fragment = (Fragment) activityOrFragmentOrView;
            context = fragment.getActivity();
            contentParent = (ViewGroup) (fragment.getView().getParent());
        } else if (activityOrFragmentOrView instanceof View) {
            View view = (View) activityOrFragmentOrView;
            contentParent = (ViewGroup) (view.getParent());
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("the argument's type must be Fragment or Activity: init(context)");
        }
        int childCount = contentParent.getChildCount();
        //获取原来布局的坐标
        int index = 0;
        View oldContent;
        if (activityOrFragmentOrView instanceof View) {
            oldContent = (View) activityOrFragmentOrView;
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldContent) {
                    index = i;
                    break;
                }
            }
        } else {
            oldContent = contentParent.getChildAt(0);
        }
        contentParent.removeView(oldContent);
        return new TargetContext(context, contentParent, oldContent, index);
    }

    public static void replaceId(ConstraintLayout.LayoutParams params, int fromId, int toId) {
        if (params.circleConstraint == fromId) {
            params.circleConstraint = toId;
        }
        if (params.leftToLeft == fromId) {
            params.leftToLeft = toId;
        }
        if (params.leftToRight == fromId) {
            params.leftToRight = toId;
        }
        if (params.rightToLeft == fromId) {
            params.rightToLeft = toId;
        }
        if (params.rightToRight == fromId) {
            params.rightToRight = toId;
        }
        if (params.topToTop == fromId) {
            params.topToTop = toId;
        }
        if (params.topToBottom == fromId) {
            params.topToBottom = toId;
        }
        if (params.bottomToTop == fromId) {
            params.bottomToTop = toId;
        }
        if (params.bottomToBottom == fromId) {
            params.bottomToBottom = toId;
        }
        if (params.baselineToBaseline == fromId) {
            params.baselineToBaseline = toId;
        }
        if (params.startToEnd == fromId) {
            params.startToEnd = toId;
        }
        if (params.startToStart == fromId) {
            params.startToStart = toId;
        }
        if (params.endToStart == fromId) {
            params.endToStart = toId;
        }
        if (params.endToEnd == fromId) {
            params.endToEnd = toId;
        }
    }


}
