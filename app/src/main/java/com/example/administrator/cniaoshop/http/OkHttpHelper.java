package com.example.administrator.cniaoshop.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;



import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：created by weidiezeng on 2019/3/29 11:52
 * 邮箱：1067875902@qq.com
 */
public class OkHttpHelper {
    private Gson gson;
    private static OkHttpClient okHttpClient;
    private Handler handler;
    private OkHttpHelper(){
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
        gson=new Gson();
        handler=new Handler(Looper.getMainLooper());

    }

    public static OkHttpHelper getInstance(){
        return new OkHttpHelper();
    }

    public void get(String url,BaseCallback callback){
        Request request=buildRequest(url,null,HttpMethodType.GET);
        doRequest(request,callback);

    }
    public void post(String url,Map<String,String> par,BaseCallback callback){
        Request request=buildRequest(url,par,HttpMethodType.POST);
        doRequest(request,callback);
    }

    public void doRequest(final Request request, final BaseCallback callback){

        callback.onRequestBefore(request);

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                callback.onFailure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                callback.onResponse(response);
                if(response.isSuccessful()){
                    String resultStr=response.body().string();
                    if(callback.type == String.class){
                        callback.onSuccess(response,resultStr);
                        callbackSuccess(callback,response,resultStr);
                    }else {
                       try {
                           Object object=gson.fromJson(resultStr,callback.type);
                           callback.onSuccess(response,object);
                       }catch (JsonParseException e){
                           callback.onError(response,response.code(),e);

                       }
                    }
                }else {
                    callback.onError(response,response.code(),null);
                    callbackError(callback,response,null);
                }

            }
        });
    }

    private Request buildRequest(String url,Map<String,String>par,HttpMethodType methodType){

        Request.Builder builder=new Request.Builder();
        builder.url(url);
        if(methodType==HttpMethodType.GET){
            builder.get();
        }else if(methodType==HttpMethodType.POST){
            RequestBody body=buildFormData(par);
            builder.post(body);
        }
        return builder.build();
    }
    private void callbackSuccess(final BaseCallback callback,final Response response,final Object object){

        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response,object);
            }
        });
    }
    private void callbackError(final BaseCallback callback,final Response response,final Object object){

        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response,response.code(),null);
            }
        });
    }
    private RequestBody buildFormData(Map<String,String>par){

        FormBody.Builder builder=new FormBody.Builder();
        if(par!=null){

            for(Map.Entry<String,String>entry:par.entrySet()){

                builder.add(entry.getKey(),entry.getValue());
            }
        }
        return builder.build();
    }
    enum HttpMethodType{
        GET,
        POST
    }

}
