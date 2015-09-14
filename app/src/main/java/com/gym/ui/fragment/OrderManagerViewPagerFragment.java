package com.gym.ui.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.FitBean;
import com.gym.bean.OrderManagerBean;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.BaseProtocol;

import com.gym.http.protocol.BuyLessonCommonProtocol;
import com.gym.http.protocol.OrderManagerProtocol;
import com.gym.http.protocol.PayLessonProtocol;
import com.gym.ui.activity.CommentActivity;
import com.gym.ui.activity.ConfirmOrderActivity;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/8/29 0029.
 */
public class OrderManagerViewPagerFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.common_lv)
    ListView commonLv;
    private int payState;
    private ArrayList<OrderManagerBean> orderBeans;
    private ArrayList<OrderManagerBean> stateBeans;
    private ViewHolder holder;
    private OrderManagerBean bean;//当前选择的bean
    private boolean loading = false;
    private OrderManagerAdapter adapter;

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    @Override
    protected LoadingPage.LoadResult load() {
        clearList();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userID", Constants.user.getUsr_UserID() + "");
        OrderManagerProtocol protocol = new OrderManagerProtocol(hashMap);
        orderBeans = protocol.load(UIUtils.getString(R.string.GetOrderByUserID_URL), BaseProtocol.POST);
        seperateState();
        // if(adapter!=null)adapter.notifyDataSetInvalidated();
        return checkResult(stateBeans);
    }

    private void clearList() {
        if (stateBeans != null)
            stateBeans.clear();
        if (orderBeans != null)
            orderBeans.clear();
    }

    /**
     * 区分返回数据的状态
     */
    private void seperateState() {
        stateBeans = new ArrayList<>();
        String state = "-1";
        if (getPayState() == 0) {
            stateBeans = orderBeans;
            return;
        } else if (getPayState() == 1) {
            state = Constants.UNPAY;
        } else if (getPayState() == 2) {
            state = Constants.REFUND;
        } else if (getPayState() == 3) {
            state = Constants.UNASSESS;
        }
        for (OrderManagerBean bean : orderBeans) {
            if (state.equals(bean.getRemark())) {
                stateBeans.add(bean);
            }
        }
    }

    @Override
    protected View createSuccessView() {
        View rootView = UIUtils.inflate(R.layout.fragment_common_list);
        ButterKnife.inject(this, rootView);
        operateData();
        return rootView;
    }

    private void operateData() {
        adapter = new OrderManagerAdapter(stateBeans);
        commonLv.setAdapter(adapter);
        commonLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                holder = (ViewHolder) view.getTag();
                bean = stateBeans.get(i);
                operateClick(holder, bean);//处理按钮点击
            }
        });
    }

    private void operateClick(ViewHolder holder, OrderManagerBean bean) {
        holder.course.setOnClickListener(this);
        holder.refund.setOnClickListener(this);
        holder.agreeRefund.setOnClickListener(this);
        holder.pay.setOnClickListener(this);
        holder.cancel.setOnClickListener(this);
        holder.delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == holder.agreeRefund) {

        } else if (view == holder.refund) {

        } else if (view == holder.course) {
            Intent intent=new Intent(getActivity(), CommentActivity.class);
            intent.putExtra("courseID",bean.getId());
            startActivity(intent);
        } else if (view == holder.pay) {
            Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
            FitBean bean = new FitBean();
            bean.setJobTitle(bean.getJobTitle());
            bean.setTreatment(bean.getTreatment());
            intent.putExtra("bean", bean);
            startActivity(intent);
        } else if (view == holder.cancel) {
            if (!loading) new BuyLessonDeleteTask().execute();
        } else if (view == holder.delete) {
            if (!loading) new BuyLessonDeleteTask().execute();
        }
    }

    class OrderManagerAdapter extends BaseAdapter {
        ArrayList<OrderManagerBean> list;

        public OrderManagerAdapter(ArrayList<OrderManagerBean> list) {
            this.list = list;
        }

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
            ViewHolder viewHolder;
            if (view == null) {
                view = UIUtils.inflate(R.layout.item_buy_lesson);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) view.getTag();
            OrderManagerBean bean = list.get(i);
            viewHolder.lessonName.setText(bean.getJobTitle());
            ImageLoader.load(viewHolder.itemImage, bean.getJobRequirements());
            viewHolder.lessonAddr.setText(bean.getJobAddress());
            if (TextUtils.isEmpty(bean.getJobAddress())) {
                viewHolder.lessonAddr.setVisibility(View.GONE);
            } else {
                viewHolder.lessonAddr.setText(bean.getJobAddress());
            }
            if (TextUtils.isEmpty(bean.getJobBeginTime())) {
                viewHolder.lessonStart.setVisibility(View.GONE);
            } else {
                viewHolder.lessonStart.setText(bean.getJobBeginTime());
            }

            viewHolder.lessonEnd.setText(bean.getJobEndTime());
            showState(viewHolder, bean);//控制显示按钮及文字
            return view;
        }


        private void showState(ViewHolder viewHolder, OrderManagerBean bean) {
            String remark = bean.getRemark();
            if (remark.equals(Constants.UNPAY)) {
                viewHolder.payState.setVisibility(View.GONE);
                viewHolder.cancel.setVisibility(View.GONE);
                viewHolder.pay.setVisibility(View.GONE);
                viewHolder.course.setVisibility(View.GONE);
                viewHolder.agreeRefund.setVisibility(View.VISIBLE);
                viewHolder.refund.setVisibility(View.GONE);
                viewHolder.agreeRefund.setVisibility(View.VISIBLE);
                viewHolder.delete.setVisibility(View.GONE);
            } else if (remark.equals(Constants.UNASSESS)) {
                viewHolder.payState.setVisibility(View.GONE);
                viewHolder.cancel.setVisibility(View.GONE);
                viewHolder.pay.setVisibility(View.GONE);
                viewHolder.course.setVisibility(View.VISIBLE);
                viewHolder.agreeRefund.setVisibility(View.GONE);
                viewHolder.refund.setVisibility(View.VISIBLE);
                viewHolder.agreeRefund.setVisibility(View.GONE);
                viewHolder.delete.setVisibility(View.VISIBLE);
            } else if (remark.equals(Constants.REFUND)) {
                viewHolder.payState.setVisibility(View.GONE);
                viewHolder.cancel.setVisibility(View.GONE);
                viewHolder.pay.setVisibility(View.GONE);
                viewHolder.course.setVisibility(View.GONE);
                viewHolder.agreeRefund.setVisibility(View.GONE);
                viewHolder.refund.setVisibility(View.GONE);
                viewHolder.agreeRefund.setVisibility(View.GONE);
                viewHolder.delete.setVisibility(View.VISIBLE);
            }
        }


    }
    @Override
    public void onResume() {
        super.onResume();
        refreshOrLoad();
    }
    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_buy_lesson.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.item_image)
        ImageView itemImage;
        @InjectView(R.id.lesson_name)
        TextView lessonName;
        @InjectView(R.id.lesson_start)
        TextView lessonStart;
        @InjectView(R.id.lesson_end)
        TextView lessonEnd;
        @InjectView(R.id.lesson_addr)
        TextView lessonAddr;
        @InjectView(R.id.delete)
        ImageView delete;
        @InjectView(R.id.payState)
        TextView payState;
        @InjectView(R.id.line)
        View line;
        @InjectView(R.id.agreeRefund)
        TextView agreeRefund;
        @InjectView(R.id.course)
        TextView course;
        @InjectView(R.id.pay)
        TextView pay;
        @InjectView(R.id.refund)
        TextView refund;
        @InjectView(R.id.cancel)
        TextView cancel;
        @InjectView(R.id.applyRefund)
        TextView applyRefund;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class BuyLessonDeleteTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(getActivity());
            loading = true;
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", bean.getId() + "");
            BuyLessonCommonProtocol protocol = new BuyLessonCommonProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.DeleteUserByCourse_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ProgressUtil.stopProgressBar();
            loading = false;
            if (TextUtils.isEmpty(s)) {
                UIUtils.showToastSafe(R.string.network_error);
            } else {
                UIUtils.showToastSafe(s);
                refreshOrLoad();
                adapter.notifyDataSetChanged();
            }
        }
    }

    class BuyLessonTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(getActivity());
            loading = true;
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("courseID", bean.getId() + "");
            hashMap.put("userID", Constants.user.getUsr_UserID() + "");
            hashMap.put("userName", Constants.user.getUsr_UserName());
            PayLessonProtocol protocol = new PayLessonProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.BuyCourse_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ProgressUtil.stopProgressBar();
            loading = false;
            if (TextUtils.isEmpty(s)) {
                UIUtils.showToastSafe(R.string.network_error);
            } else {
                UIUtils.showToastSafe(s);
                show();
            }
        }
    }
}
