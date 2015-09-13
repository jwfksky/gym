package com.gym.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.CourseCommitBean;
import com.gym.bean.FitBean;
import com.gym.bean.JobInfoBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.ChangeStarProtocol;
import com.gym.http.protocol.CourseCommitProtocol;
import com.gym.http.protocol.FitDetailProtocol;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/9/6 0006.
 */
public class FitDetailActivity extends BaseActivity {

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
    @InjectView(R.id.lesson_time)
    TextView lessonTime;
    @InjectView(R.id.fitHouse_name_iv)
    ImageView fitHouseNameIv;
    @InjectView(R.id.fitHouse_name)
    TextView fitHouseName;
    @InjectView(R.id.fitHouse_addr_iv)
    ImageView fitHouseAddrIv;
    @InjectView(R.id.fitHouse_addr)
    TextView fitHouseAddr;
    @InjectView(R.id.phone_iv)
    ImageView phoneIv;
    @InjectView(R.id.phone)
    TextView phone;
    @InjectView(R.id.lesson_intro)
    TextView lessonIntro;
    @InjectView(R.id.assess)
    TextView assess;
    @InjectView(R.id.access_lv)
    ListView accessLv;
    @InjectView(R.id.price_rate)
    TextView priceRate;
    @InjectView(R.id.price_orig)
    TextView priceOrig;
    @InjectView(R.id.buy)
    Button buy;
    private ActionBar mActionBar;
    private FitBean bean;
    private boolean loading = false;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_lesson_detail);
        ButterKnife.inject(this);

    }

    @Override
    public void initData() {
        super.initData();
        bean = getIntent().getParcelableExtra("bean");

        new JobInfoTask().execute(bean.getId()+"");
        lessonTime.setText(bean.getJobBeginTime().substring(0, 5) + "-" + bean.getJobEndTime().substring(0, 5));
        fitHouseName.setText(bean.getFF_Name());
        fitHouseAddr.setText(bean.getFF_Address());
        phone.setText(bean.getFF_Phone());
        lessonIntro.setText(bean.getJobContent());
        priceRate.setText("￥ "+bean.getTreatment());
        priceOrig.setText("");

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FitDetailActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("bean",bean);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initActionbar() {
        super.initActionbar();
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.GONE);
        backTb.setVisibility(View.VISIBLE);
        titleTb.setText(bean.getFF_Name());
    }

    class JobInfoTask extends AsyncTask<String,String,JobInfoBean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(FitDetailActivity.this);
        }

        @Override
        protected JobInfoBean doInBackground(String... strings) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("Id",strings[0]);
            FitDetailProtocol protocol=new FitDetailProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.GetJobInfoById_URL),BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(JobInfoBean jobInfo) {
            super.onPostExecute(jobInfo);
            ProgressUtil.stopProgressBar();
            new CourseCommitTask().execute();
            if(jobInfo!=null){

            }
        }
    }


    class CourseCommitTask extends AsyncTask<String,String,ArrayList<CourseCommitBean>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(FitDetailActivity.this);
        }

        @Override
        protected ArrayList<CourseCommitBean> doInBackground(String... strings) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("CourseID",bean.getId()+"");
            hashMap.put("pageIndex","1");
            hashMap.put("pageSize","10");
            CourseCommitProtocol protocol=new CourseCommitProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.GetCourseComment_URL),BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(ArrayList<CourseCommitBean> commitBeans) {
            super.onPostExecute(commitBeans);
            ProgressUtil.stopProgressBar();
            if(commitBeans!=null){

            }
        }
    }
}
