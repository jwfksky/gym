package com.gym.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.PublishLessonBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.PublishLessonProtocol;
import com.gym.ui.fragment.BaseFragment;
import com.gym.ui.fragment.IFragment;
import com.gym.ui.fragment.PublishLessonFragment;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
public class PublishLessonActivity extends BaseActivity implements View.OnClickListener,IFragment {
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
    @InjectView(R.id.accept)
    TextView accept;
    @InjectView(R.id.unaccept)
    TextView unaccept;
    @InjectView(R.id.course_fl)
    FrameLayout courseFl;

    private ActionBar mActionBar;
    private boolean loading = false;
    private ArrayList<PublishLessonBean> lessonBeans;
    private FragmentManager fm;
    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_publish_lesson);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        fm=getSupportFragmentManager();
        accept.setOnClickListener(this);
        unaccept.setOnClickListener(this);
        showFragment(1);
    }

    @Override
    public void initActionbar() {
        super.initActionbar();
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.GONE);
        backTb.setVisibility(View.VISIBLE);
        rightTv.setVisibility(View.VISIBLE);
        rightTv.setText(UIUtils.getString(R.string.add_publishLesson));
        operateItems();
        titleTb.setText(UIUtils.getString(R.string.publishLesson));
    }

    private void operateItems() {
        rightTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == rightTv) {
            Intent intent = new Intent(PublishLessonActivity.this, AddPublishLessonActivity.class);
            startActivity(intent);
        }else if(view==accept){
            accept.setTextColor(UIUtils.getColor(R.color.black));
            unaccept.setTextColor(UIUtils.getColor(R.color.light_grey));
            showFragment(1);
        } else if(view==unaccept){
            unaccept.setTextColor(UIUtils.getColor(R.color.black));
            accept.setTextColor(UIUtils.getColor(R.color.light_grey));
            showFragment(0);
        }
    }

    private void showFragment(int state) {
        PublishLessonFragment fragment=new PublishLessonFragment();
        fragment.setAcceptState(state);
        onChangeFragment(fragment, null, true);
    }

    @Override
    public void onChangeFragment(BaseFragment bf, Parcelable params, boolean replace) {
        if (!bf.isVisible()) {
            if (params != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", params);
                bf.setArguments(bundle);
            }
            //or not add to backStack
            if (replace) {
                fm.beginTransaction().replace(R.id.course_fl, bf).commit();
            } else {
                fm.beginTransaction().add(R.id.course_fl, bf).addToBackStack(null).commit();
            }
        }
    }


}
