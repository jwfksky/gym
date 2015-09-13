package com.gym.http.protocol;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.gym.bean.CityBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class CityProtocol extends BaseProtocol<ArrayList<CityBean>> {
    @Override
    protected String getParames() {
        return wrapParames(GET,null);
    }

    @Override
    protected ArrayList<CityBean> parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject obj=new JSONObject(json);
                String result=obj.optString("result");
                if("1".equals(result)){
                    String data=obj.optString("data");
                    return getGson().fromJson(data,new  TypeToken<ArrayList<CityBean>>(){}.getType());
                }else{
                    return new ArrayList<>();
                }
            } catch (JSONException e) {
                LogUtils.e(e);
                return null;
            }

        }
        return null;
    }
}
