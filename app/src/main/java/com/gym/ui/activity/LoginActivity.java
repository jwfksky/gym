package com.gym.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.UserBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.LoginProtocol;
import com.gym.utils.LogUtils;
import com.gym.utils.UIUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/8/19 0019.
 */
public class LoginActivity extends BaseActivity {
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

    private boolean loading = false;
    private SharedPreferences sp;
    private Tencent mTencent;
    private LoginListener loginListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        operateView();
    }

    private void operateView() {
        sp = getSharedPreferences("config", MODE_PRIVATE);
        loginListener=new LoginListener();
        if (sp != null) {
            userEt.setText(sp.getString("user", ""));
            pwdEt.setText(sp.getString("pwd", ""));
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebView(UIUtils.getString(R.string.Register_URL));
            }
        });
        forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebView(UIUtils.getString(R.string.GetPassword_URL));
            }
        });
        loginPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("0");//0为一般用户
            }
        });
        loginCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("2");//2为教练用户
            }
        });
        //qq登陆
        qqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTencent = Tencent.createInstance(Constants.QQLogin, LoginActivity.this);
                mTencent.login(LoginActivity.this, "all",loginListener );
            }
        });
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
        mTencent.onActivityResultData(requestCode, resultCode,data,loginListener);

        if(requestCode == com.tencent.connect.common.Constants.REQUEST_API) {
            if(resultCode == com.tencent.connect.common.Constants.RESULT_LOGIN) {
                Tencent.handleResultData(data, loginListener);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class LoginListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            try {
                JSONObject obj=new JSONObject(o.toString());
                String openid=obj.optString("openid");
               String openkey=obj.optString("pfkey");
                String qq="1";

                new ThreeLoginTask().execute(openid,openkey,qq);
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

    class ThreeLoginTask extends AsyncTask<String,String,List<UserBean>>{

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
}
