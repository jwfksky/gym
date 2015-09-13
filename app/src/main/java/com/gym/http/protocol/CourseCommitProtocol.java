package com.gym.http.protocol;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.gym.bean.CourseCommitBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/9/11 0011.
 */
public class CourseCommitProtocol extends BaseProtocol<ArrayList<CourseCommitBean>> {
    private HashMap<String, String> hashMap;
    public CourseCommitProtocol(HashMap<String, String> hashMap) {
        this.hashMap=hashMap;
    }

    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected ArrayList<CourseCommitBean> parseFromJson(String json, String url) {
        if(TextUtils.isEmpty(json)){
            try {
                JSONObject obj=new JSONObject(json);
                String result=obj.optString("result");
                if("1".equals(result)){
                    String data=obj.optString("data");
                    return getGson().fromJson(data,new TypeToken<ArrayList<CourseCommitBean>>(){}.getType());
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
