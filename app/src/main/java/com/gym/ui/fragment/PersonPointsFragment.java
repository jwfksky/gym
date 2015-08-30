package com.gym.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.PointsBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.PersonPointsProtocol;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/8/30 0030.
 */
public class PersonPointsFragment extends BaseFragment {
    @InjectView(R.id.common_lv)
    ListView commonLv;
    private ArrayList<PointsBean> pointsBeans;

    @Override
    protected LoadingPage.LoadResult load() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userID", Constants.user.getUsr_UserID() + "");
        PersonPointsProtocol protocol = new PersonPointsProtocol(hashMap);
        pointsBeans = protocol.load(UIUtils.getString(R.string.GetPersonalIntegration_URL), BaseProtocol.POST);
        return checkResult(pointsBeans);
    }

    @Override
    protected View createSuccessView() {
        View rootView = UIUtils.inflate(R.layout.fragment_common_list);
        ButterKnife.inject(this, rootView);
        operateData();
        return rootView;
    }

    private void operateData() {
        PersonPointsAdapter adapter = new PersonPointsAdapter(pointsBeans);
        View headerView = UIUtils.inflate(R.layout.item_points_header);
        commonLv.addHeaderView(headerView);
        commonLv.setAdapter(adapter);
    }

    class PersonPointsAdapter extends BaseAdapter {
        ArrayList<PointsBean> pointsBeans;

        public PersonPointsAdapter(ArrayList<PointsBean> pointsBeans) {
            this.pointsBeans = pointsBeans;
        }

        @Override
        public int getCount() {
            return pointsBeans == null ? 0 : pointsBeans.size();
        }

        @Override
        public Object getItem(int i) {
            return pointsBeans.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(view==null){
                view=UIUtils.inflate(R.layout.item_points);
                viewHolder=new ViewHolder(view);
                view.setTag(viewHolder);
            }
            viewHolder= (ViewHolder) view.getTag();
            PointsBean pointsBean=pointsBeans.get(i);
            viewHolder.content.setText(pointsBean.getCourse_Name());
            viewHolder.record.setText(pointsBean.getIntegral() + "");
            if(pointsBean.getDate()!=null){
                viewHolder.time.setText(pointsBean.getDate().getYear()+"-"+pointsBean.getDate().getMonth()+"-"+pointsBean.getDate().getDay());
            }
            return view;
        }


    }
    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_points.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.content)
        TextView content;
        @InjectView(R.id.record)
        TextView record;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
