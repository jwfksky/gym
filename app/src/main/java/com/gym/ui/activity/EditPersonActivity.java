package com.gym.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.UpdateUserProtocol;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/8/30 0030.
 */
public class EditPersonActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.title_tb)
    TextView titleTb;
    @InjectView(R.id.back_tb)
    TextView backTb;
    @InjectView(R.id.area_tb)
    TextView areaTb;
    @InjectView(R.id.right_tv)
    TextView rightTv;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.userName)
    EditText userName;
    @InjectView(R.id.neckName)
    EditText neckName;
    @InjectView(R.id.gender)
    EditText gender;
    @InjectView(R.id.phone)
    EditText phone;
    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.birth)
    EditText birth;
    @InjectView(R.id.address)
    EditText address;
    @InjectView(R.id.sign)
    EditText sign;
    @InjectView(R.id.user_picture)
    ImageView userPicture;
    @InjectView(R.id.editPerson_submit)
    Button editPersonSubmit;
    private ActionBar mActionBar;
    private ArrayList<String> toolbarItem;
    private boolean loading=false;
    private HashMap<String, String> params;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_editperson);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        toolbarItem = getIntent().getStringArrayListExtra(Constants.TOOLBAR_ITEM);
        editPersonSubmit.setOnClickListener(this);
    }

    @Override
    public void initActionbar() {
        super.initActionbar();
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.GONE);
        backTb.setVisibility(View.VISIBLE);
        if (toolbarItem != null && toolbarItem.size() > 0) {
            titleTb.setText(toolbarItem.get(0));
            if (toolbarItem.size() >= 3) {
                rightTv.setText(toolbarItem.get(2));
                rightTv.setVisibility(View.VISIBLE);
            }
        }
        operateToolbar();
    }

    private void operateToolbar() {
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initInfo();//预加载用户信息
    }

    private void initInfo() {
        userName.setText(Constants.user.getUsr_UserName());
        neckName.setText(Constants.user.getUsr_User_ActualName());
        gender.setText(Constants.user.getUsr_Sex());
        phone.setText(Constants.user.getUsr_MobilePhone());
        email.setText(Constants.user.getUsr_EmailAddress());
        birth.setText(Constants.user.getUsr_Birthday());
        address.setText(Constants.user.getUsr_address());
        if (!TextUtils.isEmpty(Constants.user.getUsr_Photo())) {
            ImageLoader.load(userPicture, Constants.user.getUsr_Photo());
        }
    }

    @Override
    public void onClick(View view) {
        if(view==editPersonSubmit){
            new UpdateUserTask().execute();
        }
    }

    public HashMap<String,String> getParams() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userID",Constants.user.getUsr_UserID()+"");
        hashMap.put("UserName",userName.getText()+"");
        hashMap.put("UserAlia",neckName.getText()+"");
        hashMap.put("Sex",gender.getText()+"");
        hashMap.put("MobilePhone",phone.getText()+"");
        hashMap.put("EmailAddress",email.getText()+"");
        hashMap.put("Birthday",birth.getText()+"");
        hashMap.put("Location",address.getText()+"");
        hashMap.put("Signature" ,sign.getText()+"");
        return hashMap;
    }

    class UpdateUserTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading=true;
            ProgressUtil.startProgressBar(EditPersonActivity.this);
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = getParams();
            UpdateUserProtocol protocol=new UpdateUserProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.UpdateUsers_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading=false;
            ProgressUtil.stopProgressBar();
            if(TextUtils.isEmpty(s)){
                UIUtils.showToastSafe(UIUtils.getString(R.string.network_error));
            }else{
                UIUtils.showToastSafe(s);
            }
        }
    }
}
