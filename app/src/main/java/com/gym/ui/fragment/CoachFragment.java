package com.gym.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gym.R;
import com.gym.bean.CoachBean;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.CoachProtocol;
import com.gym.ui.activity.CoachDetailActivity;
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
public class CoachFragment extends BaseFragment {
    @InjectView(R.id.common_lv)
    ListView commonLv;
    @InjectView(R.id.swipe)
    LoadRefreshLayout swipe;
    @InjectView(R.id.chargeMsg)
    EditText chargeMsg;
    @InjectView(R.id.chargeSubmit)
    Button chargeSubmit;

    private ArrayList<CoachBean> list;
    private ArrayList<CoachBean> currentList;
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
    private View footerView;
    private View headerView;

    private FitAdapter adapter;

    private ListView dropMenu;
    private String[] nearBy;

    private String CommentNumber;
    private String Distance;
    private String Score;
    private String Price;
    private PopupWindow popupWindow;
    private MainActivity mainActivity;
private String jobType;
    @Override
    protected LoadingPage.LoadResult load() {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("pageIndex", String.valueOf(pageNow));
        hashMap.put("pageSize", "10");
        hashMap.put("strWhere", jobType==null?"":getJobType());
        hashMap.put("ScoreNumber", CommentNumber==null?"":getCommentNumber());
        hashMap.put("Score", Score==null?"":getScore());
        CoachProtocol coachProtocol = new CoachProtocol(hashMap);
        HashMap<String, Object> bean = coachProtocol.load(UIUtils.getString(R.string.GetCoachInfo_URL), BaseProtocol.POST);
        if (bean != null) {
            currentList = (ArrayList<CoachBean>) bean.get("list");
            totalPage = Integer.parseInt((String) bean.get("totalPage"));
        }
        list = (ArrayList<CoachBean>) operateExtraData(currentList, list, pageNow);
        handler.sendEmptyMessage(0);
        return LoadingPage.LoadResult.SUCCEED;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.areaTb.setVisibility(View.GONE);
        mainActivity.backTb.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        mainActivity.areaTb.setVisibility(View.VISIBLE);
        mainActivity.backTb.setVisibility(View.GONE);
    }

    @Override
    protected View createSuccessView() {
        View rootView = UIUtils.inflate(R.layout.fragment_common_list);
        ButterKnife.inject(this, rootView);
        headerView = rootView.findViewById(R.id.inculdeMenu);
        headerView.setVisibility(View.VISIBLE);
        operateData();
        return rootView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.areaTb.setVisibility(View.GONE);
        mainActivity.backTb.setVisibility(View.VISIBLE);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void operateData() {
        //添加展示列表的头，尾view
        footerView = UIUtils.getFooterView();

        commonLv.addFooterView(footerView);

        adapter = new FitAdapter();
        commonLv.setAdapter(adapter);
        commonLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = i - 1;
                if (position >= 0) {
                    CoachBean bean = list.get(position);
                    Intent intent = new Intent(getActivity(), CoachDetailActivity.class);
                    intent.putExtra("bean", bean);
                    startActivity(intent);
                }
            }
        });
        //初始化下拉菜单
        dropMenu = new ListView(getActivity());

        DropMenuAdapter dropMenuAdapter = new DropMenuAdapter();
        dropMenu.setAdapter(dropMenuAdapter);

        //显示下拉菜单
        operateHead(headerView);
        //不显示加载更多
        if (pageNow >= totalPage) {
            footerView.setVisibility(View.GONE);
            swipe.setLoading(false);
        }
        //控制 加载更多和刷新
        operateSwipe();
    }

    /**
     * 处理下拉选择的点击事件
     *
     * @param headerView
     */
    private void operateHead(final View headerView) {
        TextView nearby = (TextView) headerView.findViewById(R.id.nearby);
        TextView category = (TextView) headerView.findViewById(R.id.category);
        TextView order = (TextView) headerView.findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nearBy = UIUtils.getStringArray(R.array.items_space);
                showPopup(headerView);
                initDropItems();
                dropMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i == 1) {
                            setCommentNumber("1");
                        } else if (i == 2) {
                            setScore("1");
                        }
                        popupWindow.dismiss();
                        refreshOrLoad();

                    }
                });
            }
        });
        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nearBy = UIUtils.getStringArray(R.array.items_cat);
                showPopup(headerView);
                setJobType("");
                dropMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i > 0)
                            setJobType(nearBy[i]);
                        popupWindow.dismiss();
                        refreshOrLoad();

                    }
                });
            }
        });
    }

    private void initDropItems() {
        setPrice("");
        setScore("");
        setCommentNumber("");
        setDistance("");
    }

    //显示下拉
    private void showPopup(View headerView) {
        popupWindow = new PopupWindow(getActivity());
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.bg_tab));
        popupWindow.setContentView(dropMenu);
        //点击空白处的时候PopupWindow会消失
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        //如果focusable为false，在一个Activity弹出一个PopupWindow，按返回键，由于PopupWindow没有焦点，会直接退出Activity。如果focusable为true，PopupWindow弹出后，所有的触屏和物理按键都有PopupWindows处理。
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(headerView);
    }

    /**
     * 刷新和加载更多
     */
    private void operateSwipe() {

        swipe.setOnLoadListener(new LoadRefreshLayout.OnLoadListener() {

            @Override
            public void load() {

                if (pageNow < totalPage) {
                    pageNow++;
                    refreshOrLoad();
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
                    refreshOrLoad();

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
            if (view == null) {
                view = UIUtils.inflate(R.layout.item_coach);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);

            }
            viewHolder = (ViewHolder) view.getTag();
            CoachBean bean = list.get(i);
            viewHolder.itemTitle.setText(bean.getUsr_User_ActualName());
            viewHolder.itemSubtitle.setText(bean.getUsr_UserAlia());
            viewHolder.itemContent.setText(bean.getUsr_Shape());
            viewHolder.itemDistance.setText(bean.getDistance() + "");
            viewHolder.itemRating.setRating((float) bean.getScore());
            if (!TextUtils.isEmpty(bean.getUsr_Photo())) {
                ImageLoader.load(viewHolder.itemImage, bean.getUsr_Photo());
            }
            return view;
        }


    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_coach.xml'
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
        @InjectView(R.id.item_subtitle)
        TextView itemSubtitle;
        @InjectView(R.id.item_content)
        TextView itemContent;
        @InjectView(R.id.item_distance)
        TextView itemDistance;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    /**
     * 下拉菜单的adapter
     */
    class DropMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nearBy == null ? 0 : nearBy.length;
        }

        @Override
        public Object getItem(int i) {
            return nearBy[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            DropViewHolder dropViewHolder;
            if (view == null) {
                view = UIUtils.inflate(R.layout.popup_dropmenu);
                dropViewHolder = new DropViewHolder(view);
                view.setTag(dropViewHolder);
            }
            dropViewHolder = (DropViewHolder) view.getTag();
            String item = nearBy[i];
            if (i == 0) {
                dropViewHolder.name.setTextColor(UIUtils.getColor(R.color.light_yellow));
                dropViewHolder.line.setBackgroundColor(UIUtils.getColor(R.color.light_yellow));
            }
            dropViewHolder.name.setText(item);
            return view;
        }

    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'popup_dropmenu.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class DropViewHolder {
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.line)
        View line;

        DropViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public String getCommentNumber() {
        return CommentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        CommentNumber = commentNumber;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
