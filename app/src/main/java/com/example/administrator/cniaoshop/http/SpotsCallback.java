package com.example.administrator.cniaoshop.http;


import android.app.AlertDialog;
import android.content.Context;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：created by weidiezeng on 2019/3/29 18:09
 * 邮箱：1067875902@qq.com
 * 描述：
 */
public abstract class SpotsCallback<T> extends BaseCallback<T> {

    AlertDialog dialog;
    public SpotsCallback(Context context){

        dialog= new SpotsDialog.Builder().setContext(context).build();
    }
    public void showDialog(){
        dialog.show();
    }
    public void dismissDialog(){
        if(dialog!=null){
            dialog.dismiss();
        }
    }
    public void setMessage(String message){
        dialog.setMessage(message);
    }

    @Override
    public void onRequestBefore(Request request) {

        showDialog();
    }

    @Override
    public void onFailure(Call call, IOException e) {

        dismissDialog();
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }
}
