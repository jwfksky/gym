package com.gym.http.protocol;

import android.text.TextUtils;

import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/9/1 0001.
 */
public class AddPublishLessonProtocol extends BaseProtocol<String> {
    private HashMap<String, String> hashMap;
    public AddPublishLessonProtocol(HashMap<String, String> hashMap) {
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
                JSONObject obj=new JSONObject(json);
                return obj.optString("msg");
            } catch (JSONException e) {
                LogUtils.e(e);
                return null;
            }
        }
        return null;
    }
}
