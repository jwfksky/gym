package com.gym.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.CourseBean;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.CourseProtocol;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015-08-26.
 */
public class CourseFragment extends BaseFragment {
    @InjectView(R.id.common_lv)
    ListView commonLv;
    private ArrayList<CourseBean> courseBeans;
    private MyAdatper adapter;

    @Override
    protected LoadingPage.LoadResult load() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userID", Constants.user.getUsr_UserID() + "");
        CourseProtocol protocol = new CourseProtocol(hashMap);
        courseBeans = protocol.load(UIUtils.getString(R.string.ShowAllExerciseRecord_URL), BaseProtocol.POST);
        return LoadingPage.LoadResult.SUCCEED;

    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null){
            refreshOrLoad();
            adapter.notifyDataSetChanged();
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

        adapter = new MyAdatper(courseBeans);
        commonLv.setAdapter(adapter);
    }

    class MyAdatper extends BaseAdapter {
        ArrayList<CourseBean> list;

        public MyAdatper(ArrayList<CourseBean> list) {
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
                view = UIUtils.inflate(R.layout.item_course);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) view.getTag();
            CourseBean bean = list.get(i);
            viewHolder.ibm.setText(bean.getIBM() + "");
            viewHolder.myCalorie.setText(bean.getCalorie() + "");

            viewHolder.mySportsTime.setText(bean.getP_e_last() + "");
            viewHolder.weight.setText(bean.getWeight() + "");
            String time=bean.getCreate_time().getMonth()+" 月 "+bean.getCreate_time().getDay()+" 日 ";
            viewHolder.time.setText(time);
            if (!TextUtils.isEmpty(bean.getPhotoPath1()))
                ImageLoader.load(viewHolder.courseImg, bean.getPhotoPath1());
            return view;
        }



    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_course.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.miline)
        ImageView miline;
        @InjectView(R.id.course_img)
        ImageView courseImg;
        @InjectView(R.id.weight)
        TextView weight;
        @InjectView(R.id.mySportsTime)
        TextView mySportsTime;
        @InjectView(R.id.myCalorie)
        TextView myCalorie;
        @InjectView(R.id.ibm)
        TextView ibm;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
