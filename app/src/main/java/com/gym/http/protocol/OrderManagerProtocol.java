package com.gym.http.protocol;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/8/30 0030.
 */
public class OrderManagerProtocol extends BaseProtocol {
    private HashMap<String,String> hashMap;
    public OrderManagerProtocol(HashMap<String,String> hashMap){
        this.hashMap=hashMap;
    }
    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected Object parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){

        }
        return null;
    }
}
