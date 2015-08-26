package com.gym.http.protocol;

import android.text.TextUtils;

import com.gym.bean.CourseBean;
import com.gym.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015-08-26.
 */
public class CourseProtocol extends BaseProtocol<ArrayList<CourseBean>> {
    HashMap<String,String> hashMap;
    public CourseProtocol(HashMap<String,String> hashMap){
        this.hashMap=hashMap;
    }
    @Override
    protected String getParames() {
        return wrapParames(POST,hashMap);
    }

    @Override
    protected ArrayList<CourseBean> parseFromJson(String json, String url) {
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject object=new JSONObject(json);
                String result=object.optString("result");
                if("1".equals(result)){

                }else if("0".equals(result)){
                    return new ArrayList<CourseBean>();
                }
            } catch (JSONException e) {
                LogUtils.e(e);
                return null;
            }
        }
        return null;
    }
}
