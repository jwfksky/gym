package com.gym.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.SpaceBean;
import com.gym.bean.SpaceDetailBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.ChangeStarProtocol;
import com.gym.http.protocol.SpaceDetailProtocol;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/9/6 0006.
 */
public class SpaceDetailActivity extends BaseActivity {
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
    @InjectView(R.id.item_rating)
    RatingBar itemRating;
    @InjectView(R.id.ren)
    TextView ren;
    @InjectView(R.id.persons_tv)
    TextView personsTv;
    @InjectView(R.id.squres)
    TextView squres;
    @InjectView(R.id.squres_tv)
    TextView squresTv;
    @InjectView(R.id.appliance)
    TextView appliance;
    @InjectView(R.id.appliance_tv)
    TextView applianceTv;
    @InjectView(R.id.applianceType)
    TextView applianceType;
    @InjectView(R.id.applianceType_tv)
    TextView applianceTypeTv;
    @InjectView(R.id.applianceNum)
    TextView applianceNum;
    @InjectView(R.id.applianceNum_tv)
    TextView applianceNumTv;
    @InjectView(R.id.coachNum)
    TextView coachNum;
    @InjectView(R.id.coachNum_tv)
    TextView coachNumTv;
    @InjectView(R.id.rebackNum)
    TextView rebackNum;
    @InjectView(R.id.rebackNum_tv)
    TextView rebackNumTv;
    @InjectView(R.id.closet)
    TextView closet;
    @InjectView(R.id.closet_tv)
    TextView closetTv;
    @InjectView(R.id.bath)
    TextView bath;
    @InjectView(R.id.bath_tv)
    TextView bathTv;
    @InjectView(R.id.shower)
    TextView shower;
    @InjectView(R.id.shower_tv)
    TextView showerTv;
    @InjectView(R.id.sauna)
    TextView sauna;
    @InjectView(R.id.sauna_tv)
    TextView saunaTv;
    @InjectView(R.id.bathDep)
    TextView bathDep;
    @InjectView(R.id.bathDep_tv)
    TextView bathDepTv;
    @InjectView(R.id.aloneRoom)
    TextView aloneRoom;
    @InjectView(R.id.aloneRoom_tv)
    TextView aloneRoomTv;
    @InjectView(R.id.others)
    TextView others;
    @InjectView(R.id.others_tv)
    TextView othersTv;
    private ActionBar mActionBar;
    private SpaceBean bean;
    private boolean loading = false;
    private HashMap<String, String> hashMap;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_space_detail);
        ButterKnife.inject(this);
        new SpaceDetailTask().execute();
    }

    @Override
    public void initData() {
        super.initData();
        bean = getIntent().getParcelableExtra("bean");

        itemRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                hashMap = new HashMap<>();
                hashMap.put("userID", String.valueOf(Constants.user.getUsr_UserID()));
                hashMap.put("FFID", String.valueOf(bean.getFFID()));

                hashMap.put("Score", String.valueOf(v));

            }
        });
        itemRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChangeStarTask().execute(hashMap);
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
        titleTb.setText(bean.getGymName());
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    class SpaceDetailTask extends AsyncTask<String, String, SpaceDetailBean> {
        @Override
        protected SpaceDetailBean doInBackground(String... hashMaps) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("ID", bean.getFFID() + "");
            SpaceDetailProtocol changeStarProtocol = new SpaceDetailProtocol(hashMap);
            return changeStarProtocol.load(UIUtils.getString(R.string.GetFieldDetail_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            ProgressUtil.startProgressBar(SpaceDetailActivity.this);
        }

        @Override
        protected void onPostExecute(SpaceDetailBean bean) {
            loading = false;
            ProgressUtil.stopProgressBar();
            if (bean == null) {
                UIUtils.showToastSafe(UIUtils.getString(R.string.network_error));
            } else {
                personsTv.setText(bean.getPeopleNumber() + "");
                squresTv.setText(bean.getSeparateRoom() + "");
                applianceTv.setText(bean.getEquipmentNewAndOld() + "");
                applianceTypeTv.setText(bean.getInstrumentType() + "");
                applianceNumTv.setText(bean.getInstrumentNumber() + "");
                coachNumTv.setText(bean.getCoachNumber() + "");
                rebackNumTv.setText(bean.getWardrobeNumber() + "");
                closetTv.setText(bean.getNozzleNumber() + "");
                bathTv.setText(bean.getSeparateRoom() + "");
                showerTv.setText(bean.getShowerRoom() + "");
                saunaTv.setText(bean.getSaunaRoom() + "");
                bathDepTv.setText(bean.getEquipmentNewAndOld() + "");
                aloneRoomTv.setText(bean.getSeparateRoom() + "");
                othersTv.setText(bean.getOtherSettings() + "");
                itemRating.setRating((float) bean.getScore());
            }
        }
    }

    class ChangeStarTask extends AsyncTask<HashMap<String, String>, String, String> {
        @Override
        protected String doInBackground(HashMap<String, String>... hashMaps) {

            ChangeStarProtocol changeStarProtocol = new ChangeStarProtocol(hashMaps[0]);
            return changeStarProtocol.load(UIUtils.getString(R.string.GradeForfitnessfield_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            ProgressUtil.startProgressBar(SpaceDetailActivity.this);
        }

        @Override
        protected void onPostExecute(String s) {
            loading = false;
            ProgressUtil.stopProgressBar();
            if (TextUtils.isEmpty(s)) {
                UIUtils.showToastSafe(UIUtils.getString(R.string.network_error));
            } else {
                UIUtils.showToastSafe(s);
            }
        }
    }
}
