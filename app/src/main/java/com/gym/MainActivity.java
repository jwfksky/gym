package com.gym;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gym.ui.fragment.BaseFragment;
import com.gym.ui.fragment.IFragment;
import com.gym.ui.fragment.MainFragment;
import com.gym.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity implements IFragment,View.OnClickListener {

    @InjectView(R.id.back_tb)
    TextView backTb;
    @InjectView(R.id.title_tb)
    TextView titleTb;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.main_fl)
    FrameLayout mainFl;
    @InjectView(R.id.menu_home)
    RadioButton menuHome;
    @InjectView(R.id.menu_forum)
    RadioButton menuForum;
    @InjectView(R.id.menu_center)
    RadioButton menuCenter;
    @InjectView(R.id.radioGroup_menu)
    RadioGroup radioGroupMenu;
    @InjectView(R.id.area_tb)
    TextView areaTb;
    private ActionBar mActionBar;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        fm=getSupportFragmentManager();
    }

    @Override
    public void initData() {
        menuCenter.setOnClickListener(this);
        BaseFragment bf= (BaseFragment) fm.findFragmentById(R.id.main_fl);
        if(bf==null){
            fm.beginTransaction().replace(R.id.main_fl,new MainFragment()).commit();
        }
    }

    @Override
    public void initActionbar() {
        setSupportActionBar(toolbar);
        mActionBar=getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.VISIBLE);
        backTb.setVisibility(View.GONE);
        titleTb.setText(UIUtils.getString(R.string.app_name));
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
                fm.beginTransaction().replace(R.id.main_fl, bf).addToBackStack(null).commit();
            } else {
                fm.beginTransaction().add(R.id.main_fl, bf).addToBackStack(null).commit();
            }

        }
    }

    @Override
    public void onClick(View v) {
        if(v==menuCenter){
            PopupMenu popupMenu=new PopupMenu(this,menuCenter);
            getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return false;
                }
            });
            popupMenu.show();

        }
    }
}
