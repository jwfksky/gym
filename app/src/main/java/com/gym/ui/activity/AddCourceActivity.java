package com.gym.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.R;
import com.gym.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015-08-26.
 */
public class AddCourceActivity extends BaseActivity implements View.OnClickListener {
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
    @InjectView(R.id.gender_tv)
    TextView genderTv;
    @InjectView(R.id.addImage1)
    ImageView addImage1;
    @InjectView(R.id.addImage2)
    ImageView addImage2;
    @InjectView(R.id.addImage3)
    ImageView addImage3;
    @InjectView(R.id.addImage4)
    ImageView addImage4;
    @InjectView(R.id.height_tv)
    TextView heightTv;
    @InjectView(R.id.course_weight)
    EditText courseWeight;
    @InjectView(R.id.period_tv)
    TextView periodTv;
    @InjectView(R.id.course_height)
    EditText courseHeight;
    @InjectView(R.id.time_tv)
    TextView timeTv;
    @InjectView(R.id.course_calorie)
    EditText courseCalorie;
    @InjectView(R.id.during_tv)
    TextView duringTv;
    @InjectView(R.id.course_ibm)
    EditText courseIbm;
    private ActionBar mActionBar;
    private Dialog dialog;
    private View dialogView;
    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_add_course);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        addImage1.setOnClickListener(this);
        addImage2.setOnClickListener(this);
        addImage3.setOnClickListener(this);
        addImage4.setOnClickListener(this);
        initOperationDialog();
    }
    private void initOperationDialog() {
       /* dialogView = this.getLayoutInflater().inflate(
                R.layout.activity_edituserinfo_popuoperat, null);
        dialog = new Dialog(this, R.style.transparentFrameWindowStyle);

        takingPictures.setOnClickListener(this);

        selectPphotoAlbum.setOnClickListener(this);

        btnClose.setOnClickListener(this);

        postTitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                postTitle.setCursorVisible(true);
                postTitle.requestFocus(); // 请求获取焦点
                // postTitle.clearFocus(); //清除焦点
            }
        });*/
    }
    @Override
    public void initActionbar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.GONE);
        backTb.setVisibility(View.VISIBLE);
        rightTv.setVisibility(View.VISIBLE);
        titleTb.setText(UIUtils.getString(R.string.addCourse));
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
    public void onClick(View v) {
        if(v==addImage1||v==addImage2||v==addImage3||v==addImage4){
            showDialog();
        }
    }
    private void showDialog() {

        dialog.setContentView(dialogView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.operatPopMenu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = this.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
