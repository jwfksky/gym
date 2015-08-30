package com.gym.http.protocol;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.gym.bean.PointsBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/8/30 0030.
 */
public class PersonPointsProtocol extends BaseProtocol<ArrayList<PointsBean>>  {
    private HashMap<String, String> hashMap;
    public PersonPointsProtocol(HashMap<String, String> hashMap) {
        this.hashMap=hashMap;
    }

    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected ArrayList<PointsBean> parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject obj=new JSONObject(json);
                String result=obj.optString("result");
                if("1".equals(result)){
                    String data=obj.optString("data");
                    return getGson().fromJson(data, new TypeToken<ArrayList<PointsBean>>(){}.getType());
                }else if("1".equals(result)){
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
