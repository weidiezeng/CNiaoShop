package com.example.administrator.cniaoshop.bean;

/**
 * 作者：created by weidiezeng on 2019/3/29 08:54
 * 邮箱：1067875902@qq.com
 */
public class Category extends BaseBean {
    public Category() {
    }

    public Category(String name) {

        this.name = name;
    }

    public Category(long id ,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
