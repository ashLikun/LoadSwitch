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
    private Context context;
    private OnLoadSwitchClick clickListion;


    public DefaultOnLoadLayoutListener(Context context, OnLoadSwitchClick clickListion) {
        this.context = context;
        this.clickListion = clickListion;
    }

    @Override
    public void setRetryEvent(View retryView, final ContextData data) {
        TextView title = (TextView) retryView.findViewById(R.id.title);
        if (title != null) {
            title.setVisibility(View.VISIBLE);
            if (data == null || TextUtils.isEmpty(data.getTitle())) {
                title.setText(context.getResources().getString(R.string.http_request_failure));
            } else if (data != null && !TextUtils.isEmpty(data.getTitle())) {
                title.setText(data.getTitle() + (data.getErrCode() != 0 ? "" : ("\n(错误码:" + data.getErrCode() + ")")));
            }
        }
        ImageView img = (ImageView) retryView.findViewById(R.id.image);
        if (img != null) {
            if (data == null || data.getResId() <= 0) {
                img.setImageResource(R.drawable.material_service_error);
            } else if (data != null && data.getResId() > 0) {
                img.setImageResource(data.getResId());
            }
        }
        MyOnClickListener onClickListener = new MyOnClickListener(data, 2);
        TextView butt = (TextView) retryView.findViewById(R.id.reSet);
        if (butt != null) {
            if (!TextUtils.isEmpty(data.getButtonText())) {
                butt.setText(data.getButtonText());
            }
            butt.setOnClickListener(onClickListener);
        }
        retryView.setOnClickListener(onClickListener);
    }

    @Override
    public void setLoadingEvent(View loadingView, ContextData data) {
        TextView title = (TextView) loadingView.findViewById(R.id.content);
        if (title != null) {
            if (data == null || TextUtils.isEmpty(data.getTitle())) {
                title.setText(context.getResources().getString(R.string.loadding));
            } else if (data != null && !TextUtils.isEmpty(data.getTitle())) {
                title.setText(data.getTitle());
            }
        }
    }

    @Override
    public void setEmptyEvent(View emptyView, final ContextData data) {
        TextView title = (TextView) emptyView.findViewById(R.id.title);
        if (title != null) {
            title.setVisibility(View.VISIBLE);
            if (data == null || TextUtils.isEmpty(data.getTitle())) {
                title.setText(context.getResources().getString(R.string.no_data));
            } else if (data != null && !TextUtils.isEmpty(data.getTitle())) {
                title.setText(data.getTitle());
            }
        }
        ImageView img = (ImageView) emptyView.findViewById(R.id.image);
        if (img != null) {
            if (data == null || data.getResId() <= 0) {
                img.setImageResource(R.drawable.material_service_error);
            } else if (data != null && data.getResId() > 0) {
                img.setImageResource(data.getResId());
            }
        }
        TextView butt = (TextView) emptyView.findViewById(R.id.reSet);
        MyOnClickListener onClickListener = new MyOnClickListener(data, 1);
        if (butt != null) {
            if (!TextUtils.isEmpty(data.getButtonText())) {
                butt.setText(data.getButtonText());
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
}
