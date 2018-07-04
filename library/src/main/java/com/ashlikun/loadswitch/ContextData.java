package com.ashlikun.loadswitch;

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
    private int flag;
    // 失败 时候的错误类型
    private int errCode;
    //显示的标题
    private String title;
    //显示图片的Id 大于0就会显示， 其他的显示默认
    private int resId = LoadSwitch.BASE_LOAD_SERVICE_ERROR;

    //按钮文字
    private String buttonText;
    //扩展的其他数据
    private Bundle extend;

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
    }

    public int getFlag() {
        return flag;
    }


    public int getErrCode() {
        return errCode;
    }


    public String getTitle() {
        return title;
    }


    public int getResId() {
        return resId;
    }


    public String getButtonText() {
        return buttonText;
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

    public ContextData setExtend(Bundle extend) {
        this.extend = extend;
        return this;
    }
}
