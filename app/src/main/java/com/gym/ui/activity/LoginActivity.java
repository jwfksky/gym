package com.gym.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.UserBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.LoginProtocol;
import com.gym.utils.LogUtils;
import com.gym.utils.UIUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/8/19 0019.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.icon)
    ImageView icon;
    @InjectView(R.id.userName)
    LinearLayout userName;
    @InjectView(R.id.pwd)
    LinearLayout pwd;
    @InjectView(R.id.buttons)
    LinearLayout buttons;
    @InjectView(R.id.text)
    LinearLayout text;
    @InjectView(R.id.text2)
    TextView text2;
    @InjectView(R.id.register)
    TextView register;
    @InjectView(R.id.forgetPwd)
    TextView forgetPwd;
    @InjectView(R.id.guide)
    TextView guide;
    @InjectView(R.id.login_person)
    Button loginPerson;
    @InjectView(R.id.login_coach)
    Button loginCoach;
    @InjectView(R.id.login_loading)
    ProgressBar login_loading;
    @InjectView(R.id.user_et)
    EditText userEt;
    @InjectView(R.id.pwd_et)
    EditText pwdEt;
    @InjectView(R.id.qqLogin)
    ImageView qqLogin;
    @InjectView(R.id.weiboLogin)
    ImageView weiboLogin;
    @InjectView(R.id.winxinLogin)
    ImageView winxinLogin;

    private boolean loading = false;
    private SharedPreferences sp;
    private Tencent mTencent;
    private LoginListener loginListener;

    private SsoHandler mSsoHandler;
    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;

    public static IWXAPI WXapi;
    private String weixinCode;
    private final static int LOGIN_WHAT_INIT = 1;
    private static String get_access_token = "";
    // 获取第一步的code后，请求以下链接获取access_token
    public static String GetCodeRequest = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    // 获取用户个人信息
    public static String GetUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        operateView();
    }

    private void operateView() {
        sp = getSharedPreferences("config", MODE_PRIVATE);
        loginListener = new LoginListener();
        if (sp != null) {
            userEt.setText(sp.getString("user", ""));
            pwdEt.setText(sp.getString("pwd", ""));
        }
        register.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
        loginPerson.setOnClickListener(this);
        loginCoach.setOnClickListener(this);
        //qq登陆
        qqLogin.setOnClickListener(this);
        //微博登陆
        weiboLogin.setOnClickListener(this);
        winxinLogin.setOnClickListener(this);
        guide.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == register) {
            showWebView(UIUtils.getString(R.string.Register_URL));
        } else if (view == forgetPwd) {
            showWebView(UIUtils.getString(R.string.GetPassword_URL));
        }else if (view == loginPerson) {
            login("0");//0为一般用户
        }else if (view == loginCoach) {
            login("2");//0为一般用户
        }else if (view == qqLogin) {
            mTencent = Tencent.createInstance(Constants.QQLogin, LoginActivity.this);
            mTencent.login(LoginActivity.this, "all", loginListener);
        }else if (view == weiboLogin) {
            //初始化新浪微博
            mAuthInfo = new AuthInfo(LoginActivity.this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
            mSsoHandler = new SsoHandler(LoginActivity.this, mAuthInfo);
            mSsoHandler.authorizeClientSso(new AuthListener());
        }else if (view == winxinLogin) {
            WXLogin();
        }else if (view == guide) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private void showWebView(String url) {
        Intent intent = new Intent(LoginActivity.this, WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }


    /**
     * 登陆
     *
     * @param flag
     */
    private void login(String flag) {
        if (checkData() && !loading) {
            String user = userEt.getText().toString();
            String pwd = pwdEt.getText().toString();
            new LoginTask().execute(user, pwd, flag);
        }
    }

    /**
     * 验证输入数据的正确性
     *
     * @return
     */
    private boolean checkData() {
        if (TextUtils.isEmpty(userEt.getText())) return false;
        if (TextUtils.isEmpty(pwdEt.getText())) return false;
        return true;
    }


    /**
     * 登陆的异步数据请求
     */
    class LoginTask extends AsyncTask<String, String, List<UserBean>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            login_loading.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<UserBean> doInBackground(String... params) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("emailAddress", params[0]);
            hashMap.put("password", params[1]);
            hashMap.put("userType", params[2]);
            LoginProtocol loginProtocol = new LoginProtocol(hashMap);
            List userBean = loginProtocol.load(UIUtils.getString(R.string.Login_URL), BaseProtocol.POST);
            return userBean;
        }

        @Override
        protected void onPostExecute(List<UserBean> userBeans) {
            super.onPostExecute(userBeans);
            loading = false;
            login_loading.setVisibility(View.GONE);
            if (userBeans == null) {
                UIUtils.showToastSafe(LoginActivity.this, UIUtils.getString(R.string.login_error));
            } else {
                Constants.user = userBeans.get(0);
                //设置记住账号，密码
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user", userEt.getText().toString());
                editor.putString("pwd", pwdEt.getText().toString());
                editor.commit();
                //跳转首页
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
            return;
        }
        mTencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        if (requestCode == com.tencent.connect.common.Constants.REQUEST_API) {

            if (resultCode == com.tencent.connect.common.Constants.RESULT_LOGIN) {
                Tencent.handleResultData(data, loginListener);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class LoginListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            try {
                JSONObject obj = new JSONObject(o.toString());
                String openid = obj.optString("openid");
                String openkey = obj.optString("pfkey");
                String qq = "1";

                new ThreeLoginTask().execute(openid, openkey, qq);
            } catch (JSONException e) {
                LogUtils.e(e);
                UIUtils.showToastSafe(LoginActivity.this, UIUtils.getString(R.string.login_error));
            }

        }

        @Override
        public void onError(UiError uiError) {
            UIUtils.showToastSafe(LoginActivity.this, UIUtils.getString(R.string.login_error));
        }

        @Override
        public void onCancel() {

        }
    }

    class ThreeLoginTask extends AsyncTask<String, String, List<UserBean>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            login_loading.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<UserBean> doInBackground(String... params) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("openId", params[0]);
            hashMap.put("openKey", params[1]);
            hashMap.put("other1", params[2]);
            hashMap.put("other2", "");
            LoginProtocol loginProtocol = new LoginProtocol(hashMap);
            List userBean = loginProtocol.load(UIUtils.getString(R.string.QQLogin_URL), BaseProtocol.POST);
            return userBean;
        }

        @Override
        protected void onPostExecute(List<UserBean> userBeans) {
            super.onPostExecute(userBeans);
            loading = false;
            login_loading.setVisibility(View.GONE);
            if (userBeans == null) {
                UIUtils.showToastSafe(LoginActivity.this, UIUtils.getString(R.string.login_error));
            } else {
                Constants.user = userBeans.get(0);
                //设置记住账号，密码
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user", userEt.getText().toString());
                editor.putString("pwd", pwdEt.getText().toString());
                editor.commit();
                //跳转首页
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }

    //微博回调信息
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 浠� Bundle 涓В鏋� Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //浠庤繖閲岃幏鍙栫敤鎴疯緭鍏ョ殑 鐢佃瘽鍙风爜淇℃伅
            String phoneNum = mAccessToken.getPhoneNum();

            String openid = mAccessToken.getUid();
            String openkey = mAccessToken.getToken();
            String weibo = "2";//qq 1 weibo 2 weixin 3

            new ThreeLoginTask().execute(openid, openkey, weibo);
            if (mAccessToken.isSessionValid()) {

            } else {
                // 浠ヤ笅鍑犵鎯呭喌锛屾偍浼氭敹鍒� Code锛�
                // 1. 褰撴偍鏈湪骞冲彴涓婃敞鍐岀殑搴旂敤绋嬪簭鐨勫寘鍚嶄笌绛惧悕鏃讹紱
                // 2. 褰撴偍娉ㄥ唽鐨勫簲鐢ㄧ▼搴忓寘鍚嶄笌绛惧悕涓嶆纭椂锛�
                // 3. 褰撴偍鍦ㄥ钩鍙颁笂娉ㄥ唽鐨勫寘鍚嶅拰绛惧悕涓庢偍褰撳墠娴嬭瘯鐨勫簲鐢ㄧ殑鍖呭悕鍜岀鍚嶄笉鍖归厤鏃躲��
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this,
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(LoginActivity.this,
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    //微信登陆

    private void WXLogin() {
        WXapi = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        WXapi.registerApp(Constants.APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo";
        WXapi.sendReq(req);
    }

    public static String getCodeRequest(String code) {
        String result = null;
        GetCodeRequest = GetCodeRequest.replace("APPID",
                urlEnodeUTF8(Constants.APP_ID));
        GetCodeRequest = GetCodeRequest.replace("SECRET",
                urlEnodeUTF8(Constants.WX_APP_SECRET));
        GetCodeRequest = GetCodeRequest.replace("CODE", urlEnodeUTF8(code));
        result = GetCodeRequest;
        return result;
    }

    public static String getUserInfo(String access_token, String openid) {
        String result = null;
        GetUserInfo = GetUserInfo.replace("ACCESS_TOKEN",
                urlEnodeUTF8(access_token));
        GetUserInfo = GetUserInfo.replace("OPENID",
                urlEnodeUTF8(openid));
        result = GetUserInfo;
        return result;
    }

    public static String urlEnodeUTF8(String str) {
        String result = str;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Runnable downloadRun = new Runnable() {

        @Override
        public void run() {
            WXGetAccessToken();

        }
    };

    private void WXGetAccessToken() {
        HttpClient get_access_token_httpClient = new DefaultHttpClient();
        HttpClient get_user_info_httpClient = new DefaultHttpClient();
        String access_token = "";
        String openid = "";
        try {
            HttpPost postMethod = new HttpPost(get_access_token);
            HttpResponse response = get_access_token_httpClient.execute(postMethod); // 执行POST方法
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                InputStream is = response.getEntity().getContent();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));
                String str = "";
                StringBuffer sb = new StringBuffer();
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                is.close();
                String josn = sb.toString();
                JSONObject json1 = new JSONObject(josn);
                access_token = (String) json1.get("access_token");
                openid = (String) json1.get("openid");


            } else {
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String get_user_info_url = getUserInfo(access_token, openid);
        WXGetUserInfo(get_user_info_url);
    }

    /**
     * 获取微信用户个人信息
     *
     * @param get_user_info_url 调用URL
     */
    private void WXGetUserInfo(String get_user_info_url) {
        HttpClient get_access_token_httpClient = new DefaultHttpClient();
        try {
            HttpGet getMethod = new HttpGet(get_user_info_url);
            HttpResponse response = get_access_token_httpClient.execute(getMethod); // 执行GET方法
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                InputStream is = response.getEntity().getContent();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));
                String str = "";
                StringBuffer sb = new StringBuffer();
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                is.close();
                String josn = sb.toString();
                Message msg = Message.obtain();
                msg.obj = josn;
                handler.sendMessage(msg);

            } else {
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String josn = (String) msg.obj;
            JSONObject json1 = null;
            try {
                json1 = new JSONObject(josn);
                String openid = (String) json1.get("openid");
                String openkey = (String) json1.get("unionid");
                String weixin = "3";//qq 1 weibo 2 weixin 3

                new ThreeLoginTask().execute(openid, openkey, weixin);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        /*
         * resp是你保存在全局变量中的
		 */
        if (Constants.resp != null) {
            BaseResp resp = Constants.resp;
            if (Constants.resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
                // code返回
                weixinCode = ((SendAuth.Resp) resp).code;

				/*
                 * 将你前面得到的AppID、AppSecret、code，拼接成URL
				 */
                get_access_token = getCodeRequest(weixinCode + "");
                Thread thread = new Thread(downloadRun);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
