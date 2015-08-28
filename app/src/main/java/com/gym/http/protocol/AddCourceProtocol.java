package com.gym.http.protocol;

import android.text.TextUtils;

import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2015-08-28.
 */
public class AddCourceProtocol extends BaseProtocol<String> {
    private HashMap<String,String> hashMap;
    public AddCourceProtocol(HashMap<String,String> hashMap){
        this.hashMap=hashMap;
    }
    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected String parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject object=new JSONObject(json);
                String result=object.optString("result");
                return result;
            } catch (JSONException e) {
                LogUtils.e(e);
                return "-1";
            }
        }
        return "-1";
    }
}
