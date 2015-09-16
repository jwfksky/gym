package com.gym.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.CourseCommitBean;
import com.gym.bean.FitBean;
import com.gym.bean.JobInfoBean;
import com.gym.http.protocol.AddPublishLessonProtocol;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.CourseCommitProtocol;
import com.gym.http.protocol.FitDetailProtocol;
import com.gym.ui.widget.ListViewForScrollView;
import com.gym.ui.widget.LoadRefreshLayout;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/9/6 0006.
 */
public class FitDetailActivity extends BaseActivity {

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
    ListViewForScrollView accessLv;
    @InjectView(R.id.price_rate)
    TextView priceRate;
    @InjectView(R.id.price_orig)
    TextView priceOrig;
    @InjectView(R.id.buy)
    Button buy;
    @InjectView(R.id.sv)
    ScrollView sv;

    @InjectView(R.id.right_rb)
    RadioButton rightRb;
    /*@InjectView(R.id.swipe)
    LoadRefreshLayout swipe;*/
    private ActionBar mActionBar;
    private FitBean bean;
    private boolean loading = false;
    private int totalPage = 1;
    private ArrayList<CourseCommitBean> currentList;
    private ArrayList<CourseCommitBean> list;
    private Integer pageNow = 1;
    private View footerView;
    private AssessAdapter adapter;
    private JobInfoBean currentBean;

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
        new JobInfoTask().execute(bean.getJobID() + "");
        sv.smoothScrollTo(0, 0);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FitDetailActivity.this, ConfirmOrderActivity.class);
                intent.putExtra("bean", currentBean);
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
        if(!TextUtils.isEmpty(bean.getFF_Name()))
        titleTb.setText(bean.getFF_Name());
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        rightRb.setVisibility(View.VISIBLE);
        rightRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rightRb.isChecked()) {
                    new CollectTask().execute(UIUtils.getString(R.string.AddCollect_URL));
                } else {
                    rightRb.setChecked(true);
                }
            }
        });
    }

    class JobInfoTask extends AsyncTask<String, String, JobInfoBean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(FitDetailActivity.this);
        }

        @Override
        protected JobInfoBean doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("Id", strings[0]);
            FitDetailProtocol protocol = new FitDetailProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.GetJobInfoById_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(JobInfoBean jobInfo) {
            super.onPostExecute(jobInfo);
            ProgressUtil.stopProgressBar();
            new CourseCommitTask().execute();
            if (jobInfo != null) {
                currentBean = jobInfo;
                lessonTime.setText(jobInfo.getCreateTime() + "");
                fitHouseName.setText(jobInfo.getFF_Name());
                fitHouseAddr.setText(jobInfo.getFF_Address());
                phone.setText(jobInfo.getLinkPhone());
                lessonIntro.setText(jobInfo.getJobContent());
                priceRate.setText("￥ " + jobInfo.getTreatment());
                priceOrig.setText("");


                titleTb.setText(currentBean.getFF_Name());
            }
        }
    }


    class CourseCommitTask extends AsyncTask<String, String, HashMap<String, Object>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(FitDetailActivity.this);
        }

        @Override
        protected HashMap<String, Object> doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("CourseID", bean.getJobID() + "");
            hashMap.put("pageIndex", String.valueOf(pageNow));
            hashMap.put("pageSize", "10");
            CourseCommitProtocol protocol = new CourseCommitProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.GetCourseComment_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(HashMap<String, Object> commitBeans) {
            super.onPostExecute(commitBeans);
            ProgressUtil.stopProgressBar();
            if (commitBeans != null) {
                currentList = (ArrayList<CourseCommitBean>) commitBeans.get("list");
                totalPage = Integer.parseInt((String) commitBeans.get("totalPage"));
            }
            list = (ArrayList<CourseCommitBean>) operateExtraData(currentList, list, pageNow);

           /* if (swipe != null && adapter != null) {
                swipe.setRefreshing(false);
                swipe.setLoading(false);
                adapter.notifyDataSetChanged();
            }*/

            //添加展示列表的头，尾view
         /*   footerView = UIUtils.getFooterView();
            accessLv.addFooterView(footerView);*/

            adapter = new AssessAdapter();
            accessLv.setAdapter(adapter);
            UIUtils.setListViewHeightBasedOnChildren(accessLv);

           /* //不显示加载更多
            if (pageNow >= totalPage) {
                footerView.setVisibility(View.GONE);
                swipe.setLoading(false);
            }
            //控制 加载更多和刷新
            operateSwipe();*/
        }
    }

    /**
     * 刷新和加载更多
     */
   /* private void operateSwipe() {

        swipe.setOnLoadListener(new LoadRefreshLayout.OnLoadListener() {

            @Override
            public void load() {

                if (pageNow < totalPage) {
                    pageNow++;
                    new CourseCommitTask().execute();
                    footerView.setVisibility(View.VISIBLE);
                } else {
                    footerView.setVisibility(View.GONE);
                    //  lvUncheck.removeFooterView(footerView);
                    swipe.setLoading(false);
                }
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!swipe.isLoading()) {
                    pageNow = 1;
                    new CourseCommitTask().execute();

                } else {
                    swipe.setRefreshing(false);
                }
            }
        });
    }*/
    protected List operateExtraData(List beans, List list, Integer pageNow) {
        if ("1".equals(String.valueOf(pageNow))) {
            if (list != null) {
                list.clear();
                list.addAll(beans);
            } else {
                if (beans != null && beans.size() == 0) {
                    return new ArrayList();
                }
                list = beans;
            }

        } else {
            if (beans != null)
                list.addAll(beans);
        }
        return list;
    }

    class AssessAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(FitDetailActivity.this).inflate(R.layout.item_access, null);
                // view=UIUtils.inflate(R.layout.item_access);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder = (ViewHolder) view.getTag();
            CourseCommitBean bean = list.get(i);
            holder.content.setText(bean.getReplyContent());
            holder.name.setText(bean.getCreatedByName());
            String time = bean.getCreatedAt().getYear() + "-" + bean.getCreatedAt().getMonth() + "-" + bean.getCreatedAt().getDay();
            holder.time.setText(time);

            TextView tv = new TextView(FitDetailActivity.this);
            tv.setText("aa");
            return view;
        }


    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_access.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.content)
        TextView content;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
    class CollectTask extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading=true;
            ProgressUtil.startProgressBar(FitDetailActivity.this);
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("userID", Constants.user.getUsr_UserID()+"");
            hashMap.put("CollectID", currentBean.getId()+"");
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
                UIUtils.showToastSafe(FitDetailActivity.this,UIUtils.getString(R.string.network_error));
            }else{
                UIUtils.showToastSafe(FitDetailActivity.this,s);
            }
        }
    }
}
