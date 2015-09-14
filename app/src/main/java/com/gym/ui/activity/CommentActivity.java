package com.gym.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.BuyLessonCommonProtocol;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class CommentActivity extends BaseActivity {


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
    @InjectView(R.id.comment_content)
    EditText commentContent;
    private ActionBar mActionBar;
    private boolean loading = false;
    private String courseID;
    private String from;


    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_comment);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        courseID = getIntent().getStringExtra("courseID");
        from = getIntent().getStringExtra("from");

    }

    @Override
    public void initActionbar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        rightTv.setVisibility(View.VISIBLE);
        titleTb.setText(UIUtils.getString(R.string.comment));
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(courseID) && TextUtils.isEmpty(commentContent.getText())) {
                    UIUtils.showToastSafe(R.string.params_error);
                } else {
                    if (!loading) {
                        if(TextUtils.isEmpty(from)){
                            new CommentTask().execute(commentContent.getText().toString());
                        }else{
                            new UpdateCommentTask().execute(commentContent.getText().toString());
                        }

                    }
                }
            }
        });
    }
    class CommentTask extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(CommentActivity.this);
            loading=true;
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("courseID",courseID);
            hashMap.put("userID", Constants.user.getUsr_UserID()+"");
            hashMap.put("topicContent",strings[0]);
            hashMap.put("createUser",Constants.user.getUsr_UserName());
            BuyLessonCommonProtocol protocol=new BuyLessonCommonProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.AddCourseTopic_URL), BaseProtocol.POST);
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


    class UpdateCommentTask extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(CommentActivity.this);
            loading=true;
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("topicID",courseID);
            hashMap.put("topic_Content",strings[0]);
            BuyLessonCommonProtocol protocol=new BuyLessonCommonProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.UpdateCourseTopic_URL), BaseProtocol.POST);
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
