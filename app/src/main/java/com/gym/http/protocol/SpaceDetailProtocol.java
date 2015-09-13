package com.gym.http.protocol;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.gym.bean.SpaceDetailBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/9/11 0011.
 */
public class SpaceDetailProtocol extends BaseProtocol<SpaceDetailBean> {
    private HashMap<String, String> hashMap;
    public SpaceDetailProtocol(HashMap<String, String> hashMap) {
        this.hashMap=hashMap;
    }

    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected SpaceDetailBean parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject obj=new JSONObject(json);
                String result=obj.optString("result");
                if("1".equals(result)){
                    String data=obj.optString("data");
                    ArrayList<SpaceDetailBean> beans=getGson().fromJson(data, new TypeToken<ArrayList<SpaceDetailBean>>() {
                    }.getType());
                    return beans.get(0);
                }
            } catch (JSONException e) {
                LogUtils.e(e);
                return null;
            }
        }
        return null;
    }
}
