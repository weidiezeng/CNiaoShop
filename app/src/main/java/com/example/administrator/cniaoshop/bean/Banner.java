package com.example.administrator.cniaoshop.bean;

/**
 * 作者：created by weidiezeng on 2019/3/29 10:35
 * 邮箱：1067875902@qq.com
 */
public class Banner extends BaseBean {
    private String name;

    private String imgUrl;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
