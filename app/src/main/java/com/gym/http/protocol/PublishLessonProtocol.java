package com.gym.http.protocol;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.gym.bean.PublishLessonBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/9/1 0001.
 */
public class PublishLessonProtocol extends BaseProtocol<ArrayList<PublishLessonBean>> {

    private HashMap<String, String> hashMap;
    public PublishLessonProtocol(HashMap<String, String> hashMap) {
        this.hashMap=hashMap;
    }

    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected ArrayList<PublishLessonBean> parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject obj=new JSONObject(json);
                String result=obj.optString("result");
                if("1".equals(result)){
                    String data=obj.optString("data");
                    return getGson().fromJson(data,new TypeToken<ArrayList<PublishLessonBean>>(){}.getType());
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
