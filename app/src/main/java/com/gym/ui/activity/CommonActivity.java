package com.gym.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.ui.fragment.BaseFragment;
import com.gym.ui.fragment.FragmentFactory;
import com.gym.ui.fragment.IFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 本项目中通用的activity
 * Created by Administrator on 2015-08-26.
 */
public class CommonActivity extends BaseActivity implements IFragment {
    @InjectView(R.id.back_tb)
    ImageView backTb;
    @InjectView(R.id.area_tb)
    TextView areaTb;
    @InjectView(R.id.title_tb)
    TextView titleTb;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.common_fl)
    FrameLayout commonFl;
    @InjectView(R.id.right_tv)
    TextView rightTv;
    @InjectView(R.id.common_iv)
    ImageView commonIv;
    private ActionBar mActionBar;
    private ArrayList<String> toolbarItem;
    private FragmentManager fm;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_common);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        fm = getSupportFragmentManager();
        //
        toolbarItem = getIntent().getStringArrayListExtra(Constants.TOOLBAR_ITEM);
        //根据传递的fragment编号确定当前展示的fragment
        if (toolbarItem != null && toolbarItem.size() >= 2) {
            //需要显示顶部图片的fragment
            if (Integer.parseInt(toolbarItem.get(1)) == FragmentFactory.CENTER_COURSE) {
                commonIv.setVisibility(View.VISIBLE);
            }else{
                commonIv.setVisibility(View.GONE);
            }
            //切换fragment
            onChangeFragment(FragmentFactory.createFragment(Integer.parseInt(toolbarItem.get(1))), null, true);
        }

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
        if (toolbarItem != null && toolbarItem.size() >= 3) {
            if (toolbarItem.get(1).equals(String.valueOf(FragmentFactory.CENTER_COURSE))) {
                rightTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CommonActivity.this, AddCourceActivity.class);
                        startActivity(intent);
                    }
                });

            }
        }
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
                fm.beginTransaction().replace(R.id.common_fl, bf).commit();
            } else {
                fm.beginTransaction().add(R.id.common_fl, bf).addToBackStack(null).commit();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        rightTv.setVisibility(View.GONE);
    }

}