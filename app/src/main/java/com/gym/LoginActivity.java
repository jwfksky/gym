package com.gym;

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

import com.gym.app.Constants;
import com.gym.bean.UserBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.LoginProtocol;
import com.gym.utils.UIUtils;

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

    private boolean loading = false;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        operateView();
    }

    private void operateView() {
        sp=getSharedPreferences("config",MODE_PRIVATE);
        if(sp!=null){
            userEt.setText(sp.getString("user",""));
            pwdEt.setText(sp.getString("pwd",""));
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
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
                login("1");//1为教练用户
            }
        });
    }

    /**
     * 登陆
     * @param flag
     */
    private void login(String flag) {
        if(checkData()&&!loading){
            String user=userEt.getText().toString();
            String pwd=pwdEt.getText().toString();
            new LoginTask().execute(user,pwd,flag);
        }
    }

    /**
     * 验证输入数据的正确性
     * @return
     */
    private boolean checkData() {
        if(TextUtils.isEmpty(userEt.getText()))return false;
        if(TextUtils.isEmpty(pwdEt.getText()))return false;
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
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("emailAddress",params[0]);
            hashMap.put("password",params[1]);
            hashMap.put("userType",params[2]);
            LoginProtocol loginProtocol=new LoginProtocol(hashMap);
            List userBean=loginProtocol.load(UIUtils.getString(R.string.Login_URL), BaseProtocol.POST);
            return userBean;
        }

        @Override
        protected void onPostExecute(List<UserBean> userBeans) {
            super.onPostExecute(userBeans);
            loading = false;
            login_loading.setVisibility(View.GONE);
            if(userBeans==null){
                UIUtils.showToastSafe(LoginActivity.this,UIUtils.getString(R.string.login_error));
            }else{
                Constants.user=userBeans.get(0);
                //设置记住账号，密码
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("user",userEt.getText().toString());
                editor.putString("pwd",pwdEt.getText().toString());
                editor.commit();
                //跳转首页
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }
}
