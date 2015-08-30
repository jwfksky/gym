package com.gym.http.protocol;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.gym.bean.BuyLessonBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/8/29 0029.
 */
public class BuyLessonProtocol extends BaseProtocol<ArrayList<BuyLessonBean>> {
    HashMap<String, String> hashMap;
    public BuyLessonProtocol(HashMap<String, String> hashMap) {
        this.hashMap=hashMap;
    }

    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected ArrayList<BuyLessonBean> parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject object=new JSONObject(json);
                String result=object.optString("result");
                if("1".equals(result)){
                    String data=object.optString("data");
                    return getGson().fromJson(data,new TypeToken<ArrayList<BuyLessonBean>>(){}.getType());
                }else if("0".equals(result)){
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
