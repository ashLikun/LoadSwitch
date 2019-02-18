package com.ashlikun.loadswitch;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


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
    private static final String TAG = LoadSwitchLayou.class.getSimpleName();
    private int currentStatus = -999;

    public LoadSwitchLayou(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public LoadSwitchLayou(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadSwitchLayou(Context context) {
        this(context, null);
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
        if (getChildCount() > CALLBACK_CUSTOM_INDEX) {
            removeViewAt(CALLBACK_CUSTOM_INDEX);
        }
        if (getChildAt(0) != null) {
            getChildAt(0).setVisibility(status == 0 ? VISIBLE : GONE);
        }
        if (status == 1) {
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
        } else if (status == 0) {
            getChildAt(0).invalidate();
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
        return getChildAt(0);
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
