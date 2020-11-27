package com.ashlikun.loadswitch;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;


/**
 * 作者　　: 李坤
 * 创建时间: 17:06 Administrator
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */
public class LoadSwitchLayou extends FrameLayout {
    private OnLoadLayoutListener listener;
    private LoadSwitch.Builder builder;
    //在content后面加的View位置
    private static final int CALLBACK_CUSTOM_INDEX = 1;
    public static final int NO_LAYOUT_ID = 0;
    private int currentStatus = -999;
    private TargetContext targetContext;
    //是否是约束布局
    private boolean isConstraintLayout = false;

    public LoadSwitchLayou(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public LoadSwitchLayou(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadSwitchLayou(Context context) {
        this(context, null);
    }

    public void init() {
        ViewGroup.LayoutParams contentParams = targetContext.getOldContent().getLayoutParams();
        //如果是协调布局
        if (targetContext.getParentView() instanceof ConstraintLayout) {
            isConstraintLayout = true;
            //复制Content的LayoutParams
            setLayoutParams(new ConstraintLayout.LayoutParams(contentParams));
            targetContext.getParentView().addView(this, targetContext.getChildIndex() + 1, contentParams);
        } else {
            isConstraintLayout = false;
            targetContext.getParentView().removeView(targetContext.getOldContent());
            //其他布局，就把OldContent加入到新的LoadSwitchLayou里面
            FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            targetContext.getOldContent().setLayoutParams(lp2);
            addOtherView(targetContext.getOldContent());
            targetContext.getParentView().addView(this, targetContext.getChildIndex(), contentParams);
        }
    }

    public void setTargetContext(TargetContext targetContext) {
        this.targetContext = targetContext;
    }

    public void setListener(OnLoadLayoutListener listener) {
        this.listener = listener;
    }

    public void setBuilder(LoadSwitch.Builder builder) {
        this.builder = builder;
    }

    private boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public void showLoading(final ContextData data) {
        if (isMainThread()) {
            showView(1, data);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showView(1, data);
                }
            });
        }
    }

    public void showRetry(final ContextData data) {
        if (isMainThread()) {
            showView(2, data);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showView(2, data);
                }
            });
        }

    }

    public void showContent() {
        if (isMainThread()) {
            showView(0, null);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showView(0, null);
                }
            });
        }
    }

    public void showEmpty(final ContextData data) {
        if (isMainThread()) {
            showView(3, data);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showView(3, data);
                }
            });
        }
    }

    private void showView(int status, ContextData data) {
        if (currentStatus == status) {
            return;
        }
        currentStatus = status;
        if (data != null) {
            data.check(getContext());
        }
        //清空后加的View
        if (isConstraintLayout) {
            removeAllViews();
        } else {
            while (getChildCount() > CALLBACK_CUSTOM_INDEX) {
                removeViewAt(CALLBACK_CUSTOM_INDEX);
            }
        }
        //是否显示content
        if (getContentView() != null) {
            getContentView().setVisibility(status == 0 ? VISIBLE : GONE);
        }
        //是否显示后加的布局
        if (isConstraintLayout) {
            setVisibility(status == 0 ? GONE : VISIBLE);
        }
        if (status == 0) {
            getContentView().invalidate();
        } else if (status == 1) {
            if (listener != null) {
                listener.setLoadingEvent(addLoadingLayout(), data);
            } else {
                addLoadingLayout();
            }
        } else if (status == 2) {
            if (listener != null) {
                listener.setRetryEvent(addRetryLayout(), data);
            } else {
                addRetryLayout();
            }
        } else if (status == 3) {
            if (listener != null) {
                listener.setEmptyEvent(addEmptyLayout(), data);
            } else {
                addEmptyLayout();
            }
        }
    }

    public View addOtherView(int layoutId) {
        return addOtherView(LayoutInflater.from(getContext()).inflate(layoutId, this, false));
    }

    public View addOtherView(View view) {
        removeView(view);
        addView(view);
        return view;
    }

    public View getContentView() {
        return targetContext.getOldContent();
    }


    //添加空布局
    private View addEmptyLayout() {
        if (builder.isSetEmptyLayout()) {
            int layoutId = builder.generateEmptyLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                return addOtherView(layoutId);
            } else {
                return addOtherView(builder.generateEmptyLayout());
            }
        } else {
            if (LoadSwitch.BASE_EMPTY_LAYOUT_ID != NO_LAYOUT_ID) {
                return addOtherView(LoadSwitch.BASE_EMPTY_LAYOUT_ID);
            }
        }
        return null;
    }

    //添加加载中布局
    private View addLoadingLayout() {
        if (builder.isSetLoadingLayout()) {
            int layoutId = builder.generateLoadingLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                return addOtherView(layoutId);
            } else {
                return addOtherView(builder.generateLoadingLayout());
            }
        } else {
            if (LoadSwitch.BASE_LOADING_LAYOUT_ID != NO_LAYOUT_ID) {
                return addOtherView(LoadSwitch.BASE_LOADING_LAYOUT_ID);
            }
        }
        return null;
    }

    //添加从新加载
    private View addRetryLayout() {
        if (builder.isSetRetryLayout()) {
            int layoutId = builder.generateRetryLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                return addOtherView(layoutId);
            } else {
                return addOtherView(builder.generateRetryLayout());
            }
        } else {
            if (LoadSwitch.BASE_RETRY_LAYOUT_ID != NO_LAYOUT_ID) {
                return addOtherView(LoadSwitch.BASE_RETRY_LAYOUT_ID);
            }
        }
        return null;
    }

}
