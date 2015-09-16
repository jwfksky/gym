package com.gym.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.ui.fragment.BaseFragment;
import com.gym.ui.fragment.BuyLessonViewPagerFragment;
import com.gym.ui.fragment.FragmentFactory;
import com.gym.ui.fragment.OrderManagerViewPagerFragment;
import com.gym.ui.widget.PagerTab;
import com.gym.utils.UIUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 本项目中通用的activity
 * Created by Administrator on 2015-08-26.
 */
public class CommonViewPagerActivity extends BaseActivity {

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
    @InjectView(R.id.tabs)
    PagerTab tabs;
    @InjectView(R.id.pager)
    ViewPager pager;
    private ActionBar mActionBar;
    private ArrayList<String> toolbarItem;
    private MainPagerAdapter mAdapter;
    private BaseFragment fragment;
    private String currentFragment;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_common_viewpager);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        toolbarItem = getIntent().getStringArrayListExtra(Constants.TOOLBAR_ITEM);
        if (toolbarItem!=null&&toolbarItem.size() >= 2) {
            currentFragment = toolbarItem.get(1);
        }
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(mAdapter);
        tabs.setViewPager(pager);
        tabs.setOnPageChangeListener(new MyOnPageChangeListener());

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
                        Intent intent = new Intent(CommonViewPagerActivity.this, AddCourceActivity.class);
                        startActivity(intent);
                    }
                });

            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        rightTv.setVisibility(View.GONE);
    }

    /**
     * ViewPager的适配器
     */
    public class MainPagerAdapter extends FragmentPagerAdapter {
        private String[] mTabTitle;

        public MainPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            mTabTitle = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitle[position];
        }

        @Override
        public int getCount() {
            return mTabTitle.length;
        }

        @Override
        public Fragment getItem(int position) {
            fragment = FragmentFactory.createFragment(Integer.parseInt(currentFragment));
            if(currentFragment.equals(FragmentFactory.CENTER_BUYLESSON+"")){
                ((BuyLessonViewPagerFragment)fragment).setPayState(position);
            }else if(currentFragment.equals(FragmentFactory.CENTER_ORDERMANAGER+"")){
                ((OrderManagerViewPagerFragment)fragment).setPayState(position);
            }

            return fragment;
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
            //ViewPager滑动状态改变的回调
        }

        @Override
        public void onPageScrolled(int index, float offset, int offsetPx) {
            //ViewPager滑动时的回调
        }

        @Override
        public void onPageSelected(int index) {
            // ViewPager页面被选中的回调
            fragment = FragmentFactory.createFragment(Integer.parseInt(currentFragment));
            if(currentFragment.equals(FragmentFactory.CENTER_BUYLESSON+"")){
                ((BuyLessonViewPagerFragment)fragment).setPayState(index);
            }else if(currentFragment.equals(FragmentFactory.CENTER_ORDERMANAGER+"")){
                ((OrderManagerViewPagerFragment)fragment).setPayState(index);
            }
            // 当页面被选中 再显示要加载的页面....防止ViewPager提前加载(ViewPager一般加载三个，自己，左一个，右一个)
            fragment.show();// 调用show方法加载pager里面的数据
        }
    }
}