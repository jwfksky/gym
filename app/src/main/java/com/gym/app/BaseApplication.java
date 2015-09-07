package com.gym.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gym.utils.LogUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;


/**
 * Created by mwqi on 2014/7/11.
 */
public class BaseApplication extends Application {
    /**
     * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
     */
    private static BaseApplication mInstance;
    /**
     * 主线程ID
     */
    private static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;
    /**
     * AcceptToken
     */
    private static String AcceptToken;
    public static LocationClient mLocationClient;
    private MyLocationListener mMyLocationListener;

    @Override
    public void onCreate() {
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;
        initSpeechExecute();
        initBDLocation();
        super.onCreate();
    }

    //初始化百度
    private void initBDLocation() {
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
    }
    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            String addr=bdLocation.getLocationDescribe();
            Constants.addrNow=addr;
        }
    }

    /**
     * 显示请求字符串
     * @param str
     */
    public String logMsg(String str) {
        return str;
    }
    /**
     * 初始化 语音功能
     */
    private void initSpeechExecute() {
        // TODO Auto-generated method stub
        if (Constants.speechUtil == null) {


            SpeechUtility.createUtility(getApplicationContext(),
                    SpeechConstant.APPID + "=5485587a");

        }
    }

    public static BaseApplication getApplication() {
        return mInstance;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static String getAcceptToken() {
        return AcceptToken;
    }

    public static void setAcceptToken(String acceptToken) {
        AcceptToken = acceptToken;
    }
}
