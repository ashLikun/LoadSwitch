package com.ashlikun.loadswitch;

import android.content.Context;
import android.os.Bundle;

/**
 * 作者　　: 李坤
 * 创建时间: 17:07 Administrator
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：布局切换时的内容数据
 */

public class ContextData {
    //标识
    int flag;
    // 失败 时候的错误类型
    int errCode;
    //显示的标题
    String title;
    //显示图片的Id 大于0就会显示， 其他的显示默认
    int resId = LoadSwitch.BASE_LOAD_SERVICE_ERROR;
    /**
     * 图片宽度
     */
    int imgWidth = -1;
    /**
     * 图片高度
     */
    int imgHeight = -1;
    boolean imgSizeIsDp = false;

    //按钮文字,
    String buttonText;
    //按钮是否显示
    boolean buttonShow = true;
    //扩展的其他数据
    Bundle extend;

    public void check(Context context) {
        if (imgSizeIsDp) {
            imgWidth = dip2px(context, imgWidth);
            imgHeight = dip2px(context, imgHeight);
            imgSizeIsDp = false;
        }
    }

    public ContextData() {
    }

    public ContextData(String title) {
        this.title = title;
    }

    public ContextData(String title, int resId) {
        this.title = title;
        this.resId = resId;
    }

    public ContextData(int errCode, String title) {
        this.errCode = errCode;
        this.title = title;
    }

    public ContextData(String title, int resId, String buttonText) {
        this(title, resId, buttonText, -1);
    }

    public ContextData(String title, String buttonText) {
        this(title, R.drawable.material_service_error, buttonText, -1);
    }

    public ContextData(String title, int resId, String buttonText, int flag) {
        this.flag = flag;
        this.title = title;
        this.resId = resId;
        this.buttonText = buttonText;
        buttonShow = !(buttonText == null || buttonText.isEmpty());
    }

    int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public Bundle getExtend() {
        return extend;
    }

    public ContextData setFlag(int flag) {
        this.flag = flag;
        return this;
    }

    public ContextData setErrCode(int errCode) {
        this.errCode = errCode;
        return this;
    }

    public ContextData setTitle(String title) {
        this.title = title;
        return this;
    }

    public ContextData setResId(int resId) {
        this.resId = resId;
        return this;
    }

    public ContextData setButtonText(String buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    public ContextData setButtonShow(boolean buttonShow) {
        this.buttonShow = buttonShow;
        return this;
    }

    public ContextData setExtend(Bundle extend) {
        this.extend = extend;
        return this;
    }

    public ContextData setImgSize(int imgWidth, int imgHeight) {
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        return this;
    }

    public ContextData setImgSizeDp(int imgWidthDp, int imgHeightDp) {
        this.imgWidth = imgWidthDp;
        this.imgHeight = imgHeightDp;
        imgSizeIsDp = true;
        return this;
    }


}
