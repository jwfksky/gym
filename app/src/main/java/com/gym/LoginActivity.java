package com.gym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        operateView();
    }

    private void operateView() {
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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
