package com.gym.http.protocol;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.gym.bean.CollectBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
public class CollectProtocol extends BaseProtocol<CollectBean> {
    private HashMap<String, String> hashMap;
    public CollectProtocol(HashMap<String, String> hashMap) {
        this.hashMap=hashMap;
    }

    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected CollectBean parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject obj=new JSONObject(json);
                String result=obj.optString("result");
                if("1".equals(result)){
                    String data=obj.optString("data");
                    return getGson().fromJson(data, new TypeToken<CollectBean>(){}.getType());
                }else if("0".equals(result)){
                    CollectBean bean=new CollectBean();
                    bean.setData(new ArrayList<CollectBean.DataEntity>());
                    return bean;
                }
            } catch (JSONException e) {
                LogUtils.e(e);
                return null;
            }
        }
        return null;
    }
}
