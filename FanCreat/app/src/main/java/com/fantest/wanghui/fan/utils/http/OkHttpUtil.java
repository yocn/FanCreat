package com.fantest.wanghui.fan.utils.http;

import android.os.Bundle;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

import okio.BufferedSink;

/**
 * Created by wanghui on 2016/3/28.
 * 封装OkHttp的工具类，包括的功能：
 */
public class OkHttpUtil {
    private static String BASE_URL = "http://api.fanfou.com/";
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private static OkHttpUtil mInstance;
    private Request.Builder builder = new Request.Builder();

    private OkHttpUtil() {
    }

    public static OkHttpUtil newInstance() {
        if (mInstance == null){
            synchronized (newInstance()){
                if (mInstance == null){
                    mInstance = new OkHttpUtil();
                }
            }
        }
        return mInstance;
    }

    public void putGet(final BaseRequest baseRequest){
        Map<String, String> params = baseRequest.params();
        if (params != null && params.size() > 0){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("?");
            for (String key : params.keySet()){
                stringBuffer.append(key);
                stringBuffer.append("=");
                stringBuffer.append(params.get(key)).append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            builder.url(BASE_URL + baseRequest.getUrl() + stringBuffer);
        }else {
            builder.url(BASE_URL + baseRequest.getUrl());
        }

        Request request = builder.get().build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                baseRequest.onFailure();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                baseRequest.onSuccess();
            }
        });
    }

    public void putPost(final BaseRequest baseRequest){
        builder.url(BASE_URL + baseRequest.getUrl()).get();

        Map<String, String> params = baseRequest.params();
        if (params != null && params.size() > 0){
            FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
            for (String key : params.keySet()){
                formEncodingBuilder.add(key, params.get(key));
            }
            builder.post(formEncodingBuilder.build());
        }

        Request request = builder.build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                baseRequest.onFailure();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                baseRequest.onSuccess();
            }
        });
    }

}
