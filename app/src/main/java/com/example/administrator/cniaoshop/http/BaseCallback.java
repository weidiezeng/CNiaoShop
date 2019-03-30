package com.example.administrator.cniaoshop.http;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：created by weidiezeng on 2019/3/29 16:24
 * 邮箱：1067875902@qq.com
 * 描述：
 */
public abstract class BaseCallback <T>{
    Type type;
    static Type getSuperclassTypeParameter(Class<?>subclass){
        Type superclass=subclass.getGenericSuperclass();
        if(superclass instanceof Class){
            throw new RuntimeException("Missing type parameter");
        }
        ParameterizedType parameterizedType= (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }
    public BaseCallback(){
        type=getSuperclassTypeParameter(getClass());
    }
    public abstract void onRequestBefore(Request request);

    public abstract void onFailure(Call call, IOException e) ;

    public abstract void onResponse(Response response);

    public abstract void onSuccess(Response response,T t);
    public abstract void onError(Response response,int code,Exception e);
}
