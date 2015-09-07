package com.gym.ui.fragment;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.PublishLessonBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.DeletePublishLessonProtocol;
import com.gym.http.protocol.PublishLessonProtocol;
import com.gym.ui.widget.LoadRefreshLayout;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/9/1 0001.
 */
public class PublishLessonFragment extends BaseFragment {
    @InjectView(R.id.common_lv)
    ListView commonLv;
    @InjectView(R.id.chargeMsg)
    EditText chargeMsg;
    @InjectView(R.id.chargeSubmit)
    Button chargeSubmit;
    private int acceptState;
    private ArrayList<PublishLessonBean> lessonBeans;
    private ArrayList<PublishLessonBean> stateBeans;
    private boolean loading = false;
    private PublishLessonAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (adapter != null) adapter.notifyDataSetInvalidated();
        }
    };

    @Override
    protected LoadingPage.LoadResult load() {
        clearList();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("CoachID", String.valueOf(Constants.user.getUsr_UserID()));
        PublishLessonProtocol protocol = new PublishLessonProtocol(hashMap);
        lessonBeans = protocol.load(UIUtils.getString(R.string.Select_ff_jobinfoByCoachID_URL), BaseProtocol.POST);
        seperateState();
        handler.sendEmptyMessage(0);

        return checkResult(lessonBeans);
    }

    private void clearList() {
        if (stateBeans != null)
            stateBeans.clear();
        if (lessonBeans != null)
            lessonBeans.clear();
    }

    private void seperateState() {
        stateBeans = new ArrayList<>();
        for (PublishLessonBean bean : lessonBeans) {
            if (String.valueOf(getAcceptState()).equals(bean.getStatus())) {
                stateBeans.add(bean);
            }
        }
    }

    @Override
    protected View createSuccessView() {
        View rootView = UIUtils.inflate(R.layout.fragment_common_list);
        ButterKnife.inject(this, rootView);
        oeprateData();
        return rootView;
    }

    private void oeprateData() {
        adapter = new PublishLessonAdapter();
        commonLv.setAdapter(adapter);
    }

    class PublishLessonAdapter extends BaseAdapter {
      /*  public ArrayList<PublishLessonBean> beans;

        public PublishLessonAdapter(ArrayList<PublishLessonBean> beans) {
            this.beans = beans;
        }*/

        @Override
        public int getCount() {
            return stateBeans == null ? 0 : stateBeans.size();
        }

        @Override
        public Object getItem(int i) {
            return stateBeans.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.item_publish_lesson, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) view.getTag();
            final PublishLessonBean bean = stateBeans.get(i);
            viewHolder.lessonAddr.setText(bean.getJobContent());
            viewHolder.lessonName.setText(bean.getJobTitle());
            viewHolder.lessonTime.setText(bean.getCourseTime());
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!loading)
                    new DeletePublishLessonTask().execute(String.valueOf(bean.getId()));
                }
            });
            return view;
        }


    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_publish_lesson.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.item_image)
        ImageView itemImage;
        @InjectView(R.id.lesson_name)
        TextView lessonName;
        @InjectView(R.id.lesson_time)
        TextView lessonTime;
        @InjectView(R.id.lesson_addr)
        TextView lessonAddr;
        @InjectView(R.id.delete)
        ImageView delete;
        @InjectView(R.id.edit)
        ImageView edit;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public int getAcceptState() {
        return acceptState;
    }

    public void setAcceptState(int acceptState) {
        this.acceptState = acceptState;
    }

    class DeletePublishLessonTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = false;
            ProgressUtil.startProgressBar(getActivity());
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", strings[0]);
            DeletePublishLessonProtocol protocol = new DeletePublishLessonProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.DeleteCourseByID_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading = false;
            ProgressUtil.stopProgressBar();
            if (TextUtils.isEmpty(s)) {
                UIUtils.showToastSafe(UIUtils.getString(R.string.network_error));
            } else {
                UIUtils.showToastSafe(s);
            }
            refreshOrLoad();

        }
    }
}
