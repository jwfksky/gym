package com.gym.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.CollectBean;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.CollectProtocol;
import com.gym.http.protocol.DeleteCollectProtocol;
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
public class CollectFragment extends BaseFragment {

    @InjectView(R.id.nearby)
    TextView nearby;
    @InjectView(R.id.category)
    TextView category;
    @InjectView(R.id.order)
    TextView order;
    @InjectView(R.id.inculdeMenu)
    LinearLayout inculdeMenu;
    @InjectView(R.id.common_lv)
    ListView commonLv;
    @InjectView(R.id.swipe)
    LoadRefreshLayout swipe;
    @InjectView(R.id.chargeMsg)
    EditText chargeMsg;
    @InjectView(R.id.chargeSubmit)
    Button chargeSubmit;
    private int state;
    private int pageIndex = 1;
    private CollectAdapter adapter = null;
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
    private ArrayList<CollectBean> list;
    private ArrayList<CollectBean> collectBeans;
    private View footerView;
    private boolean loading = false;

    @Override
    protected LoadingPage.LoadResult load() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userID", String.valueOf(Constants.user.getUsr_UserID()));
        hashMap.put("CollectType", getState()+"");
        hashMap.put("pageIndex", String.valueOf(pageIndex));
        hashMap.put("pageSize", String.valueOf("10"));
        CollectProtocol collectProtocol = new CollectProtocol(hashMap);
        HashMap<String, Object> bean = collectProtocol.load(UIUtils.getString(R.string.CollectInfoList_URL), BaseProtocol.POST);
        if (bean != null) {
            collectBeans = (ArrayList<CollectBean>) bean.get("list");
            totalPage = Integer.parseInt((String) bean.get("totalPage"));
        }

        list = (ArrayList<CollectBean>) operateExtraData(collectBeans, list, pageIndex);
        handler.sendEmptyMessage(0);
        return checkResult(list);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
        footerView = UIUtils.getFooterView();
        commonLv.addFooterView(footerView);
        adapter = new CollectAdapter();
        commonLv.setAdapter(adapter);
        operateSwipe();
    }

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



    class CollectAdapter extends BaseAdapter {


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
                view = UIUtils.inflate(R.layout.item_collect);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) view.getTag();
            final CollectBean bean = list.get(i);
            viewHolder.name.setText(bean.getUsr_UserName());
            viewHolder.content.setText("");
            if(!TextUtils.isEmpty(bean.getUsr_Photo())){
                ImageLoader.load(viewHolder.collectImg,bean.getUsr_Photo());
            }
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!loading)
                        new DeleteCollectTask().execute(bean.getID() + "");
                }
            });
            return view;
        }


    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_collect.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.collect_img)
        ImageView collectImg;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.content)
        TextView content;
        @InjectView(R.id.delete)
        ImageView delete;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class DeleteCollectTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            ProgressUtil.startProgressBar(getActivity());
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("ID", strings[0]);
            DeleteCollectProtocol protocol = new DeleteCollectProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.DeleteForCollect_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading = false;
            ProgressUtil.stopProgressBar();
            if (TextUtils.isEmpty(s)) {
                UIUtils.showToastSafe(getActivity(), UIUtils.getString(R.string.network_error));
            } else {
                UIUtils.showToastSafe(getActivity(), s);
                if(adapter!=null){
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
