package com.gym.http.protocol;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.gym.bean.JobInfoBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/9/10 0010.
 */
public class FitDetailProtocol extends BaseProtocol<JobInfoBean> {

    private HashMap<String, String> hashMap;
    public FitDetailProtocol(HashMap<String, String> hashMap) {
        this.hashMap=hashMap;
    }

    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected JobInfoBean parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject obj=new JSONObject(json);
                String result=obj.optString("result");
                if("1".equals(result)){
                    String data=obj.optString("data");
                    List<JobInfoBean> beans=getGson().fromJson(data, new TypeToken<List<JobInfoBean>>() {
                    }.getType());
                    if(beans!=null&&beans.size()>0)return beans.get(0);
                }
            } catch (JSONException e) {
                LogUtils.e(e);
                return null;
            }
        }
        return null;
    }
}
