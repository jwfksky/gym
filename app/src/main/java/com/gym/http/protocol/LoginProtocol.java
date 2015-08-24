package com.gym.http.protocol;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.gym.bean.UserBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015-08-24.
 */
public class LoginProtocol extends BaseProtocol<List<UserBean>> {
    HashMap<String,String> map;
    public LoginProtocol(HashMap<String,String> hashMap){
        this.map=hashMap;
    }
    @Override
    protected String getParames() {
        return wrapParames(POST,map);
    }

    @Override
    protected List<UserBean> parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject object=new JSONObject(json);
                String result=object.optString("result");
                if("1".equals(result)){
                    String data=object.optString("data");
                    return getGson().fromJson(data,new TypeToken<List<UserBean>>(){}.getType());
                }
            } catch (Exception e) {
                LogUtils.e(e);
                return null;
            }
        }
        return null;
    }
}
