package com.example.administrator.cniaoshop.bean;

import java.io.Serializable;

/**
 * 作者：created by weidiezeng on 2019/3/29 08:51
 * 邮箱：1067875902@qq.com
 * 描述: 初始数据
 */
public class BaseBean implements Serializable {
    protected   long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
