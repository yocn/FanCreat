package com.fantest.wanghui.fan.utils.http;

import java.util.Map;

/**
 * Created by wanghui on 2016/3/29.
 */
public abstract class BaseRequest {
    public abstract String getUrl();

    public abstract Map<String,String> params();

    public abstract void onFailure();

    public abstract void onSuccess();
}
