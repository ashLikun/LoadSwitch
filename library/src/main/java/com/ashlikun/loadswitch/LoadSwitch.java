package com.ashlikun.loadswitch;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/1/23　14:33
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */

public class LoadSwitch {
    public static int BASE_LOADING_LAYOUT_ID = R.layout.base_load_loading;
    public static int BASE_RETRY_LAYOUT_ID = R.layout.base_load_retry;
    public static int BASE_EMPTY_LAYOUT_ID = R.layout.base_load_empty;
    private static volatile LoadSwitch loadSir;
    private Builder builder;

    public static LoadSwitch getDefault() {
        if (loadSir == null) {
            synchronized (LoadSwitch.class) {
                if (loadSir == null) {
                    loadSir = new LoadSwitch();
                }
            }
        }
        return loadSir;
    }

    private LoadSwitch() {
        this.builder = new Builder();
    }

    public LoadSwitchService register(@NonNull Object target) {
        return register(target, null);
    }

    public LoadSwitchService register(Object activityOrFragmentOrView, OnLoadLayoutListener listener) {
        TargetContext targetContext = LoadSwitchUtils.getTargetContext(activityOrFragmentOrView);
        return new LoadSwitchService(targetContext, builder, listener);
    }

    public LoadSwitch newBuilder() {
        this.builder = new Builder();
        return this;
    }

    public static class Builder {
        protected int loaddingId;
        protected int retryId;
        protected int enptyId;
        protected View loaddingView;
        protected View retryView;
        protected View enptyView;

        public Builder setLoaddingId(int loaddingId) {
            this.loaddingId = loaddingId;
            return this;
        }

        public Builder setRetryId(int retryId) {
            this.retryId = retryId;
            return this;
        }

        public Builder setEnptyId(int enptyId) {
            this.enptyId = enptyId;
            return this;
        }

        public Builder setLoaddingView(View loaddingView) {
            this.loaddingView = loaddingView;
            return this;
        }

        public Builder setRetryView(View retryView) {
            this.retryView = retryView;
            return this;
        }

        public Builder setEnptyView(View enptyView) {
            this.enptyView = enptyView;
            return this;
        }

        public int generateLoadingLayoutId() {
            return loaddingId;
        }

        public int generateRetryLayoutId() {
            return retryId;
        }

        public int generateEmptyLayoutId() {
            return enptyId;
        }

        public View generateLoadingLayout() {
            return loaddingView;
        }

        public View generateRetryLayout() {
            return retryView;
        }

        public View generateEmptyLayout() {
            return enptyView;
        }

        public boolean isSetLoadingLayout() {
            return generateLoadingLayoutId() != LoadSwitchLayou.NO_LAYOUT_ID || generateLoadingLayout() != null;
        }

        public boolean isSetRetryLayout() {
            return generateRetryLayoutId() != LoadSwitchLayou.NO_LAYOUT_ID || generateRetryLayout() != null;
        }

        public boolean isSetEmptyLayout() {
            return generateEmptyLayoutId() != LoadSwitchLayou.NO_LAYOUT_ID || generateEmptyLayout() != null;
        }
    }
}