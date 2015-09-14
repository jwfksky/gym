package com.gym.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.CommentBean;
import com.gym.bean.FitBean;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.BuyLessonCommonProtocol;
import com.gym.http.protocol.CommentProtocol;
import com.gym.ui.activity.CommentActivity;
import com.gym.ui.activity.FitDetailActivity;
import com.gym.ui.widget.LoadRefreshLayout;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015-08-26.
 */
public class CommentFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.common_lv)
    ListView commonLv;
    @InjectView(R.id.swipe)
    LoadRefreshLayout swipe;
    private int pageIndex = 1;
    private CommentAdapter adapter = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (swipe != null && adapter != null) {
                swipe.setRefreshing(false);
                swipe.setLoading(false);
                adapter.notifyDataSetChanged();
            }
        }
    };
    private Integer totalPage;
    private ArrayList<CommentBean> list;
    private ArrayList<CommentBean> collectBeans;
    private View footerView;
    private boolean loading = false;
    private View dialogView;
    private Dialog dialog;
    private Button btnClose;
    private Button showLesson;
    private Button edit;
    private Button delete;
    private CommentBean bean;

    @Override
    protected LoadingPage.LoadResult load() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", String.valueOf(Constants.user.getUsr_UserID()));
        CommentProtocol commentProtocol = new CommentProtocol(hashMap);
        HashMap<String, Object> bean = commentProtocol.load(UIUtils.getString(R.string.GetAllOfMyTopic_URL), BaseProtocol.POST);
        if (bean != null) {
            collectBeans = (ArrayList<CommentBean>) bean.get("list");
            totalPage = Integer.parseInt((String) bean.get("totalPage"));
        }
        list = (ArrayList<CommentBean>) operateExtraData(collectBeans, list, pageIndex);
        handler.sendEmptyMessage(0);
        return checkResult(list);
    }

    @Override
    protected View createSuccessView() {
        View rootView = UIUtils.inflate(R.layout.fragment_common_list);
        ButterKnife.inject(this, rootView);
        swipe.setVisibility(View.VISIBLE);
        operateData();
        return rootView;
    }

    private void operateData() {
        initOperationDialog();
        footerView = UIUtils.getFooterView();
        commonLv.addFooterView(footerView);
        adapter = new CommentAdapter();
        commonLv.setAdapter(adapter);
        commonLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog();
                bean = list.get(i);
            }
        });
        operateSwipe();
    }

    private void initOperationDialog() {
        dialogView = getActivity().getLayoutInflater().inflate(
                R.layout.activity_edit_comment, null);
        dialog = new Dialog(getActivity(), R.style.transparentFrameWindowStyle);
        btnClose = (Button) dialogView.findViewById(R.id.btnClose);
        showLesson = (Button) dialogView.findViewById(R.id.showLesson);
        edit = (Button) dialogView.findViewById(R.id.edit);
        delete = (Button) dialogView.findViewById(R.id.delete);

        btnClose.setOnClickListener(this);
        showLesson.setOnClickListener(this);
        edit.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    private void showDialog() {

        dialog.setContentView(dialogView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.operatPopMenu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    } // 打开本地相册

    /**
     * 刷新和加载更多
     */
    private void operateSwipe() {
        if (pageIndex >= totalPage) {
            footerView.setVisibility(View.GONE);
            swipe.setLoading(false);
        }
        swipe.setOnLoadListener(new LoadRefreshLayout.OnLoadListener() {
            @Override
            public void load() {
                if (pageIndex < totalPage) {
                    pageIndex++;
                    refreshOrLoad();
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
                    pageIndex = 1;
                    refreshOrLoad();
                    footerView.setVisibility(View.VISIBLE);
                } else {
                    swipe.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == showLesson) {
            FitBean fitBean = new FitBean();
            fitBean.setJobID(bean.getJobId());
            Intent intent = new Intent(getActivity(), FitDetailActivity.class);
            intent.putExtra("bean", fitBean);
            startActivity(intent);
        } else if (view == edit) {
            Intent intent=new Intent(getActivity(), CommentActivity.class);
            intent.putExtra("courseID",bean.getTopic_id()+"");
            intent.putExtra("from", Constants.COMMENTFRAGMENT);
            startActivity(intent);
        } else if (view == delete) {
            new CommonTask().execute(UIUtils.getString(R.string.DeleteCourseTopic_URL));
        } else if (view == btnClose) {
            dialog.dismiss();
        }

    }

    class CommentAdapter extends BaseAdapter {


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
                view = UIUtils.inflate(R.layout.item_commit);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) view.getTag();
            final CommentBean bean = list.get(i);
            viewHolder.commitContent.setText(bean.getTopic_content());
            ImageLoader.load(viewHolder.commitHeadIv, bean.getUsr_Photo());
            ImageLoader.load(viewHolder.commitLessonImage, bean.getCourse_Photo());
            viewHolder.commitLessonAddr.setText(bean.getJobAddress());
            viewHolder.commitLessonName.setText(bean.getJobTitle());
            viewHolder.commitLessonTime.setText(bean.getJobBeginTime().substring(0, 5) + "-" + bean.getJobEndTime().substring(0, 5));
            viewHolder.commitTitle.setText(Constants.user.getUsr_UserName());
            viewHolder.commitTime.setText(bean.getCreate_time().getHour() + ":" + bean.getCreate_time().getMinute());

            return view;
        }


    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_commit.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.commit_headIv)
        ImageView commitHeadIv;
        @InjectView(R.id.commit_title)
        TextView commitTitle;
        @InjectView(R.id.commit_time)
        TextView commitTime;
        @InjectView(R.id.commit_content)
        TextView commitContent;
        @InjectView(R.id.commit_lesson_image)
        ImageView commitLessonImage;
        @InjectView(R.id.commit_lesson_name)
        TextView commitLessonName;
        @InjectView(R.id.commit_lesson_time)
        TextView commitLessonTime;
        @InjectView(R.id.commit_lesson_addr)
        TextView commitLessonAddr;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class CommonTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtil.startProgressBar(getActivity());
            loading = true;
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("topicID", bean.getTopic_id() + "");
            BuyLessonCommonProtocol protocol = new BuyLessonCommonProtocol(hashMap);
            return protocol.load(strings[0], BaseProtocol.POST);
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
}
