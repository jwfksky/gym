package com.gym.app;


import com.gym.bean.CityBean;
import com.gym.bean.UserBean;
import com.gym.utils.SpeechSynthesisUtil;
import com.tencent.mm.sdk.modelbase.BaseResp;

/**
 * Author: Jwf(feijia101@gmail.com) <br\>
 * Date: 2015-06-23 13:14<br\>
 * Version: 1.0<br\>
 * Desc:<br\>
 * Revise:<br\>
 */
public class Constants {
    public static final int REQUEST_CODE = 0;
    public static final int RESPONSE_CODE = 200;
    public static final String BILL_UNCHECK = "0";
    public static final String BILL_CHECKED = "1";
    public static final String TOOLBAR_ITEM = "toolbarItem";
    public static final String APP_TMEP_FILE_PATH = "/gym/temp";
    public static final String UNPAY = "0";//未付款
    public static final String UNASSESS = "1";//未评价
    public static final String REFUND= "4";//已退款
    public static final String COMMENTFRAGMENT = "CommentFragment";//区别更新评论来源
    //微博登陆
    public static final String APP_KEY = "10645264";
    public static final String REDIRECT_URL = "http://www.hao123.com";
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
    //微信登陆
    public static final String APP_ID = "wxcde39e7190d48aaf";
    public static BaseResp resp;
    public static String WX_APP_SECRET="136195c3cb5a7e3d9dcd3674b525ffeb";

    public static String VOICER = "xiaoyan";
    public static SpeechSynthesisUtil speechUtil;
    /*http response code*/
    public static final Integer HTTP_STATUSCODE_200 = 200;
    public static final Integer HTTP_STATUSCODE_400 = 400;
    public static final Integer HTTP_STATUSCODE_401 = 401;
    public static final Integer HTTP_STATUSCODE_2000 = 2000;
    public static final Integer HTTP_STATUSCODE_2001 = 2001;
    public static final Integer HTTP_STATUSCODE_2002 = 2002;
    public static final Integer HTTP_STATUSCODE_500 = 500;
    public static UserBean user;
    public static CityBean city;
    public static String addrNow;
    public static String QQLogin="1104651057";
}
