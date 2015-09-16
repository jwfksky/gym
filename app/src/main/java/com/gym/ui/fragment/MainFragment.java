package com.gym.ui.fragment;

import android.content.Intent;
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
import com.gym.bean.SpecailBean;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.SpecailProtocol;
import com.gym.ui.activity.FitDetailActivity;
import com.gym.ui.activity.LoginActivity;
import com.gym.ui.activity.MainActivity;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015-08-20.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.menu_space)
    TextView menuSpace;
    @InjectView(R.id.menu_coach)
    TextView menuCoach;
    @InjectView(R.id.menu_fit)
    TextView menuFit;
    @InjectView(R.id.menu_box)
    TextView menuBox;
    @InjectView(R.id.menu_fight)
    TextView menuFight;
    @InjectView(R.id.specailItems)
    ListView specailItems;
    private ArrayList<SpecailBean> list;

    @Override
    protected LoadingPage.LoadResult load() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageIndex", "1");
        hashMap.put("pageSize", "1");
        SpecailProtocol protocol = new SpecailProtocol(hashMap);
        list = protocol.load(UIUtils.getString(R.string.GetCourseInfo_URL), BaseProtocol.POST);
        return LoadingPage.LoadResult.SUCCEED;
    }

    @Override
    protected View createSuccessView() {
        View rootView = UIUtils.inflate(R.layout.fragment_main);
        ButterKnife.inject(this, rootView);
        operateData();
        return rootView;
    }

    private void operateData() {
        menuSpace.setOnClickListener(this);
        menuCoach.setOnClickListener(this);
        menuFit.setOnClickListener(this);
        menuBox.setOnClickListener(this);
        menuFight.setOnClickListener(this);
        SpecailAdapter adapter = new SpecailAdapter();
        specailItems.setAdapter(adapter);
        specailItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SpecailBean specailBean = list.get(i);
                FitBean bean = new FitBean();
                bean.setJobID(specailBean.getId());
                bean.setFF_Name(specailBean.getFF_Name());
                Intent intent = new Intent(getActivity(), FitDetailActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == menuFit) {
            if (Constants.user != null) {
                ((MainActivity) getActivity()).onChangeFragment(FragmentFactory.createFragment(FragmentFactory.MENU_FIT), null, true);
            } else {
                UIUtils.showToastSafe(getActivity(), UIUtils.getString(R.string.no_login));
            }

        } else if (view == menuSpace) {
            if (Constants.user != null) {
                ((MainActivity) getActivity()).onChangeFragment(FragmentFactory.createFragment(FragmentFactory.MENU_SPACE), null, true);
            } else {
                UIUtils.showToastSafe(getActivity(), UIUtils.getString(R.string.no_login));
            }
        } else if (view == menuCoach) {
            if (Constants.user != null) {
                ((MainActivity) getActivity()).onChangeFragment(FragmentFactory.createFragment(FragmentFactory.MENU_COACH), null, true);
            } else {
                UIUtils.showToastSafe(getActivity(), UIUtils.getString(R.string.no_login));
            }
        } else if (view == menuBox) {
            if (Constants.user != null) {
                FitFragment fit = (FitFragment) FragmentFactory.createFragment(FragmentFactory.MENU_FIT);
                fit.setJobType(UIUtils.getString(R.string.box));
                ((MainActivity) getActivity()).onChangeFragment(fit, null, true);
            } else {
                UIUtils.showToastSafe(getActivity(), UIUtils.getString(R.string.no_login));
            }
        } else if (view == menuFight) {
            if (Constants.user != null) {
                FitFragment fit = (FitFragment) FragmentFactory.createFragment(FragmentFactory.MENU_FIT);
                fit.setJobType(UIUtils.getString(R.string.fight));
                ((MainActivity) getActivity()).onChangeFragment(fit, null, true);
            } else {
                UIUtils.showToastSafe(getActivity(), UIUtils.getString(R.string.no_login));
            }
        }
    }

    class SpecailAdapter extends BaseAdapter {

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
                view = UIUtils.inflate(R.layout.item_home_list);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder = (ViewHolder) view.getTag();
            SpecailBean bean = list.get(i);
            holder.title.setText(bean.getJobTitle());
            holder.content.setText(bean.getJobContent());
            if (TextUtils.isEmpty(bean.getJobRequirements())) {
                ImageLoader.load(holder.ivHome, bean.getJobRequirements());
            }
            return view;
        }


    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_home_list.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.iv_home)
        ImageView ivHome;
        @InjectView(R.id.title)
        TextView title;
        @InjectView(R.id.content)
        TextView content;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
