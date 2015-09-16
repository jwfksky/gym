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
import com.gym.bean.FitBean;
import com.gym.bean.JobInfoBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.PayLessonProtocol;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/9/11 0011.
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.title_tb)
    TextView titleTb;
    @InjectView(R.id.back_tb)
    ImageView backTb;
    @InjectView(R.id.area_tb)
    TextView areaTb;
    @InjectView(R.id.right_tv)
    TextView rightTv;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.price)
    TextView price;
    @InjectView(R.id.reduce)
    TextView reduce;
    @InjectView(R.id.number)
    TextView number;
    @InjectView(R.id.add)
    TextView add;
    @InjectView(R.id.count_money)
    TextView countMoney;
    @InjectView(R.id.phone)
    TextView phone;
    @InjectView(R.id.login_coach)
    Button loginCoach;
    private ActionBar mActionBar;
    private JobInfoBean bean;
private int num=1;
    private boolean loading=false;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_submitorder);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        bean = getIntent().getParcelableExtra("bean");
        name.setText(bean.getJobTitle());
        price.setText("￥"+bean.getTreatment());
        countMoney.setText(num*bean.getTreatment()+"");

        reduce.setOnClickListener(this);
        add.setOnClickListener(this);
        loginCoach.setOnClickListener(this);
    }

    @Override
    public void initActionbar() {
        super.initActionbar();
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.GONE);
        backTb.setVisibility(View.VISIBLE);
        titleTb.setText(UIUtils.getString(R.string.submit_order));
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==reduce){
            if(num>0)num--;
            number.setText(num+"");
            countMoney.setText(num*bean.getTreatment()+"");
        }else if(view==add){
            num++;
            number.setText(num+"");
            countMoney.setText(num*bean.getTreatment()+"");
        }else if(view==loginCoach){
            if(!loading) new BuyLessonTask().execute();
        }
    }

    class BuyLessonTask extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(ConfirmOrderActivity.this);
            loading=true;
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("courseID",bean.getId()+"");
            hashMap.put("Quantity",num+"");
            hashMap.put("userID", Constants.user.getUsr_UserID()+"");
            hashMap.put("userName",Constants.user.getUsr_UserName());
            PayLessonProtocol protocol=new PayLessonProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.BuyCourse_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ProgressUtil.stopProgressBar();
            loading=false;
            if(TextUtils.isEmpty(s)){
                UIUtils.showToastSafe(R.string.network_error);
            }else{
                UIUtils.showToastSafe(s);
                finish();
            }
        }
    }
}
