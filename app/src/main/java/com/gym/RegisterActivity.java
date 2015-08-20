package com.gym;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.title_tb)
    TextView title_tb;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.user_image)
    ImageView userImage;
    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.phone)
    EditText phone;
    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.back_tb)
    TextView backTb;
    private ActionBar mActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void init() {
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionbar() {
        setSupportActionBar(toolbar);
        mActionbar = getSupportActionBar();
        mActionbar.setDefaultDisplayHomeAsUpEnabled(true);
        mActionbar.setHomeButtonEnabled(true);
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
