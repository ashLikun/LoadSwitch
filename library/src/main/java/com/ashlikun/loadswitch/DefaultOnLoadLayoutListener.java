package com.ashlikun.loadswitch;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 作者　　: 李坤
 * 创建时间: 17:08 Administrator
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：简单的实现不同布局的切换
 */


public class DefaultOnLoadLayoutListener implements OnLoadLayoutListener {
    protected Context context;
    protected OnLoadSwitchClick clickListion;

    public DefaultOnLoadLayoutListener(Context context, OnLoadSwitchClick clickListion) {
        this.context = context;
        this.clickListion = clickListion;
    }

    @Override
    public void setRetryEvent(View retryView, final ContextData data) {
        TextView title = (TextView) retryView.findViewById(R.id.loadSwitchTitle);
        if (title != null) {
            title.setVisibility(View.VISIBLE);
            if (data == null || TextUtils.isEmpty(data.title)) {
                title.setVisibility(View.GONE);
            } else if (data != null && !TextUtils.isEmpty(data.title)) {
                title.setVisibility(View.VISIBLE);
                title.setText(data.title + (data.errCode != 0 ? "" : ("\n(错误码:" + data.errCode + ")")));
            }
        }
        ImageView img = (ImageView) retryView.findViewById(R.id.loadSwitchImage);
        if (img != null && data != null) {
            if (data.imgHeight > 0 && data.imgWidth > 0) {
                img.getLayoutParams().width = data.imgWidth;
                img.getLayoutParams().height = data.imgHeight;
            }
            if (data.resId <= 0) {
                img.setImageResource(R.drawable.material_service_error);
            } else if (data.resId > 0) {
                img.setImageResource(data.resId);
            }
        }
        MyOnClickListener onClickListener = new MyOnClickListener(data, 2);
        TextView butt = (TextView) retryView.findViewById(R.id.loadSwitchReSet);
        if (butt != null) {
            if (data != null) {
                if (data.buttonShow) {
                    butt.setVisibility(View.VISIBLE);
                } else {
                    butt.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(data.buttonText)) {
                    butt.setText(data.buttonText);
                }
            }
            butt.setOnClickListener(onClickListener);
        }
        retryView.setOnClickListener(onClickListener);
    }

    @Override
    public void setLoadingEvent(View loadingView, ContextData data) {
        TextView title = (TextView) loadingView.findViewById(R.id.loadSwitchLoadTitle);
        if (title != null) {
            if (data == null || TextUtils.isEmpty(data.title)) {
                title.setVisibility(View.GONE);
            } else if (data != null && !TextUtils.isEmpty(data.title)) {
                title.setVisibility(View.VISIBLE);
                title.setText(data.title);
            }
        }
    }

    @Override
    public void setEmptyEvent(View emptyView, final ContextData data) {
        TextView title = (TextView) emptyView.findViewById(R.id.loadSwitchTitle);
        if (title != null) {
            if (data == null || TextUtils.isEmpty(data.title)) {
                title.setVisibility(View.GONE);
            } else if (data != null && !TextUtils.isEmpty(data.title)) {
                title.setVisibility(View.VISIBLE);
                title.setText(data.title);
            }
        }
        ImageView img = (ImageView) emptyView.findViewById(R.id.loadSwitchImage);
        if (img != null && data != null) {
            if (data.imgHeight > 0 && data.imgWidth > 0) {
                img.getLayoutParams().width = data.imgWidth;
                img.getLayoutParams().height = data.imgHeight;
            }
            if (data.resId <= 0) {
                img.setImageResource(R.drawable.material_service_error);
            } else if (data.resId > 0) {
                img.setImageResource(data.resId);
            }
        }
        TextView butt = (TextView) emptyView.findViewById(R.id.loadSwitchReSet);
        MyOnClickListener onClickListener = new MyOnClickListener(data, 1);
        if (butt != null) {
            if (data != null) {
                if (data.buttonShow) {
                    butt.setVisibility(View.VISIBLE);
                } else {
                    butt.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(data.buttonText)) {
                    butt.setText(data.buttonText);
                }
            }
            butt.setOnClickListener(onClickListener);
        }
        emptyView.setOnClickListener(onClickListener);
    }

    public class MyOnClickListener implements View.OnClickListener {
        final ContextData data;
        final int flag;

        /**
         * @param data
         * @param flag 1:代表空的时候点击事件，2：加载失败，从新加载
         */
        public MyOnClickListener(ContextData data, int flag) {
            this.data = data;
            this.flag = flag;
        }

        @Override
        public void onClick(View v) {
            if (clickListion != null) {
                if (flag == 1) {
                    clickListion.onEmptyClick(data);
                } else {
                    clickListion.onRetryClick(data);
                }
            }
        }
    }

    public Context getContext() {
        return context;
    }

    public OnLoadSwitchClick getClickListion() {
        return clickListion;
    }
}
