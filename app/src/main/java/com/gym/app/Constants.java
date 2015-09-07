package com.gym.app;


import com.gym.bean.UserBean;
import com.gym.utils.SpeechSynthesisUtil;

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
    public static String city="上海市";
    public static String addrNow;
}
