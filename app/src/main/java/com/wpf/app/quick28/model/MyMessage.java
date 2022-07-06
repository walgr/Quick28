package com.wpf.app.quick28.model;

import com.wpf.app.quick.base.widgets.recyclerview.HolderBindingClass;
import com.wpf.app.quick.base.widgets.recyclerview.QuickViewDataBinding;
import com.wpf.app.quick28.adapterholder.MyMessageHolder;
import com.wpf.app.quick28.databinding.HolderMessageMyBinding;

import java.io.Serializable;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
@HolderBindingClass(holderClass = MyMessageHolder.class)
public class MyMessage extends QuickViewDataBinding<HolderMessageMyBinding> implements Serializable {
    String userName = "";
    String msg = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MyMessage() {
    }

    public MyMessage(String userName) {
        this.userName = userName;
    }

    public MyMessage(String userName, String msg) {
        this.userName = userName;
        this.msg = msg;
    }
}
