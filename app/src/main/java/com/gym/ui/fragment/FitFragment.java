package com.gym.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gym.R;
import com.gym.bean.FitBean;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.FitProtocol;
import com.gym.ui.activity.MainActivity;
import com.gym.ui.widget.LoadRefreshLayout;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015-08-20.
 */
public class FitFragment extends BaseFragment {
    @InjectView(R.id.common_lv)
    ListView commonLv;
    @InjectView(R.id.swipe)
    LoadRefreshLayout swipe;
    private ArrayList<FitBean.DataEntity> list;
    private ArrayList<FitBean.DataEntity> currentList;
    private int totalPage;
    private Integer pageNow = 1;
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
    private FitAdapter adapter;
    private View footerView;

    @Override
    protected LoadingPage.LoadResult load() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("jobDistrictCode", "");
        hashMap.put("jobType", "");
        hashMap.put("pageIndex", String.valueOf(pageNow));
        hashMap.put("pageSize", "10");
        FitProtocol fitProtocol = new FitProtocol(hashMap);
        HashMap<String,Object> bean = fitProtocol.load(UIUtils.getString(R.string.GetJobInfoList_URL), BaseProtocol.POST);
        currentList = (ArrayList<FitBean.DataEntity>) bean.get("list");
        totalPage=Integer.parseInt((String) bean.get("totalPage"));
        list = (ArrayList<FitBean.DataEntity>) operateExtraData(currentList, list, pageNow);
        handler.sendEmptyMessage(0);
        return checkResult(list);
    }

    @Override
    protected View createSuccessView() {
        View rootView = UIUtils.inflate(R.layout.fragment_common_list);
        ButterKnife.inject(this, rootView);
        operateData();
        return rootView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.areaTb.setVisibility(View.GONE);
        mainActivity.backTb.setVisibility(View.VISIBLE);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void operateData() {
        footerView = UIUtils.getFooterView();
        commonLv.addFooterView(footerView);
        adapter = new FitAdapter();
        commonLv.setAdapter(adapter);

        operateSwipe();
    }

    /**
     * 刷新和加载更多
     */
    private void operateSwipe() {
        if (pageNow >= totalPage) {
            footerView.setVisibility(View.GONE);
            swipe.setLoading(false);
        }
        swipe.setOnLoadListener(new LoadRefreshLayout.OnLoadListener() {
            @Override
            public void load() {
                if (pageNow < totalPage) {
                    pageNow++;
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
                    pageNow = 1;
                    refreshOrLoad();
                    footerView.setVisibility(View.VISIBLE);
                } else {
                    swipe.setRefreshing(false);
                }
            }
        });
    }

    class FitAdapter extends BaseAdapter {

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
            if(view==null){
                view=UIUtils.inflate(R.layout.item_fit);
                viewHolder=new ViewHolder(view);
                view.setTag(viewHolder);
            }
            viewHolder= (ViewHolder) view.getTag();
            FitBean.DataEntity bean=list.get(i);
            viewHolder.itemContent.setText("");
            viewHolder.itemDistance.setText("");
            ImageLoader.load(viewHolder.itemImage, bean.getJobRequirements());
            viewHolder.itemPrice.setText("");
            viewHolder.itemRating.setRating(bean.getSort());
            if(bean.getCreateTime()!=null){
            String time=bean.getCreateTime().getHour()+"-"+bean.getCreateTime().getMinute();
            viewHolder.itemTime.setText(time);}
            viewHolder.itemTitle.setText(bean.getJobTitle());
            return view;
        }


    }
    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_fit.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.item_image)
        ImageView itemImage;
        @InjectView(R.id.item_title)
        TextView itemTitle;
        @InjectView(R.id.item_rating)
        RatingBar itemRating;
        @InjectView(R.id.item_price)
        TextView itemPrice;
        @InjectView(R.id.item_content)
        TextView itemContent;
        @InjectView(R.id.item_time)
        TextView itemTime;
        @InjectView(R.id.item_distance)
        TextView itemDistance;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
