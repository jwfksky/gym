package com.gym.ui.activity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.CoachBean;
import com.gym.bean.CoachDetailBean;
import com.gym.bean.CoachPhotoBean;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.AddPublishLessonProtocol;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.CoachDetailProtocol;
import com.gym.http.protocol.CoachPhotoProtocol;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class CoachDetailActivity extends BaseActivity {
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
    @InjectView(R.id.ren)
    TextView ren;
    @InjectView(R.id.gender_tv)
    TextView genderTv;
    @InjectView(R.id.height)
    TextView height;
    @InjectView(R.id.height_tv)
    TextView heightTv;
    @InjectView(R.id.weight)
    TextView weight;
    @InjectView(R.id.weight_tv)
    TextView weightTv;
    @InjectView(R.id.expertise)
    TextView expertise;
    @InjectView(R.id.expertise_tv)
    TextView expertiseTv;
    @InjectView(R.id.body)
    TextView body;
    @InjectView(R.id.applianceType_tv)
    TextView applianceTypeTv;
    @InjectView(R.id.coachNum)
    TextView coachNum;
    @InjectView(R.id.coachNum_tv)
    TextView coachNumTv;
    @InjectView(R.id.cert_image1)
    ImageView certImage1;
    @InjectView(R.id.cert_image2)
    ImageView certImage2;
    @InjectView(R.id.cert_image3)
    ImageView certImage3;
    @InjectView(R.id.cert_image4)
    ImageView certImage4;
    @InjectView(R.id.ward_image1)
    ImageView wardImage1;
    @InjectView(R.id.ward_image2)
    ImageView wardImage2;
    @InjectView(R.id.ward_image3)
    ImageView wardImage3;
    @InjectView(R.id.ward_image4)
    ImageView wardImage4;
    @InjectView(R.id.right_rb)
    RadioButton rightRb;
    private ActionBar mActionBar;
    private CoachBean bean;
    private boolean loading = false;
    private CoachDetailBean mCoachDetailBean1;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_coachinfo);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        super.initData();
        bean = getIntent().getParcelableExtra("bean");
        new CoachDetailTask().execute(bean.getUsr_UserID() + "");
        new GetCoachCertTask().execute(bean.getUsr_UserID() + "",UIUtils.getString(R.string.GetCoachDetail_Photh_Z_URL));
        new GetCoachCertTask().execute(bean.getUsr_UserID() + "",UIUtils.getString(R.string.GetCoachDetail_Photh_J_URL));
    }

    @Override
    public void initActionbar() {
        super.initActionbar();
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.GONE);
        backTb.setVisibility(View.VISIBLE);
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        titleTb.setText(UIUtils.getString(R.string.coachInfo));
        rightRb.setVisibility(View.VISIBLE);
        rightRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(rightRb.isChecked()){
                    new CollectTask().execute(UIUtils.getString(R.string.AddCollect_URL));
                }else{
                    rightRb.setChecked(true);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rightRb.setVisibility(View.GONE);
    }

    class CoachDetailTask extends AsyncTask<String, String, CoachDetailBean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            ProgressUtil.startProgressBar(CoachDetailActivity.this);
        }

        @Override
        protected CoachDetailBean doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userID", strings[0]);
            CoachDetailProtocol protocol = new CoachDetailProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.GetCoachDetail_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(CoachDetailBean coachDetailBean) {
            super.onPostExecute(coachDetailBean);
            loading = false;
            ProgressUtil.stopProgressBar();
            if (coachDetailBean == null) {
                UIUtils.showToastSafe(CoachDetailActivity.this, UIUtils.getString(R.string.network_error));
            } else {
                mCoachDetailBean1 = coachDetailBean;
                genderTv.setText(coachDetailBean.getUsr_Sex());
                heightTv.setText(coachDetailBean.getUsr_Height()+"");
                weightTv.setText(coachDetailBean.getUsr_Weight()+"");
                expertiseTv.setText(coachDetailBean.getUsr_Expertise()+"");
                coachNumTv.setText(coachDetailBean.getTeachingYear()+"");
                body.setText(coachDetailBean.getUsr_Shape() + "");
            }
        }
    }
    class GetCoachCertTask extends AsyncTask<String,String,ArrayList<CoachPhotoBean>>{

        @Override
        protected ArrayList<CoachPhotoBean> doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userID", strings[0]);
            CoachPhotoProtocol protocol = new CoachPhotoProtocol(hashMap);
            return (ArrayList<CoachPhotoBean>) protocol.load(strings[1],BaseProtocol.POST);

        }

        @Override
        protected void onPostExecute(ArrayList<CoachPhotoBean> coachPhotoBeans) {
            super.onPostExecute(coachPhotoBeans);
            if(coachPhotoBeans!=null){
                for(int i=0;i<coachPhotoBeans.size();i++){
                    CoachPhotoBean bean=coachPhotoBeans.get(i);
                    if(TextUtils.isEmpty(bean.getPhotopath_j())){
                        //证书
                        if(i==0){
                            ImageLoader.load(certImage1,bean.getPhotopath_z());
                        }else if(i==1){
                            ImageLoader.load(certImage2,bean.getPhotopath_z());
                        }
                        else if(i==2){
                            ImageLoader.load(certImage3,bean.getPhotopath_z());
                        }
                        else if(i==3){
                            ImageLoader.load(certImage4,bean.getPhotopath_z());
                        }
                    }else{
                        if(i==0){
                            ImageLoader.load(wardImage1,bean.getPhotopath_j());
                        }else if(i==1){
                            ImageLoader.load(wardImage2,bean.getPhotopath_j());
                        }
                        else if(i==2){
                            ImageLoader.load(wardImage3,bean.getPhotopath_j());
                        }
                        else if(i==3){
                            ImageLoader.load(wardImage4,bean.getPhotopath_j());
                        }
                    }
                }
            }
        }
    }
    class CollectTask extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading=true;
            ProgressUtil.startProgressBar(CoachDetailActivity.this);
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("userID", Constants.user.getUsr_UserID()+"");
            hashMap.put("CollectID", mCoachDetailBean1.getUsr_UserID()+"");
            hashMap.put("CollectType", "0");
            AddPublishLessonProtocol protocol=new AddPublishLessonProtocol(hashMap);
            return protocol.load(strings[0],BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading=false;
            ProgressUtil.stopProgressBar();
            if(TextUtils.isEmpty(s)){
                UIUtils.showToastSafe(CoachDetailActivity.this,UIUtils.getString(R.string.network_error));
            }else{
                UIUtils.showToastSafe(CoachDetailActivity.this,s);
            }
        }
    }
}
