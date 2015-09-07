package com.gym.http.protocol;

import android.text.TextUtils;

import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
public class UpdateCoachProtocol extends BaseProtocol<String> {
    HashMap<String, String> hashMap;

    public UpdateCoachProtocol(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    protected String getParames() {
        return wrapParames(POST, hashMap);
    }

    @Override
    protected String parseFromJson(String json, String url) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONObject object = new JSONObject(json);

                return object.optString("msg");

            } catch (JSONException e) {
                LogUtils.e(e);
                return null;
            }
        }
        return null;
    }
}
