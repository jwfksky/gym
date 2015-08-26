package com.gym.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.CourseBean;
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

    @Override
    protected LoadingPage.LoadResult load() {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("userID", Constants.user.getUsr_UserID()+"");
        CourseProtocol protocol=new CourseProtocol(hashMap);
        ArrayList<CourseBean> list=protocol.load(UIUtils.getString(R.string.ShowAllExerciseRecord_URL), BaseProtocol.POST);
        return LoadingPage.LoadResult.SUCCEED;

    }

    @Override
    protected View createSuccessView() {
        View rootView=UIUtils.inflate(R.layout.fragment_common_list);
        ButterKnife.inject(this, rootView);
        operateData();
        return rootView;
    }

    private void operateData() {

       /* MyAdapter adapter=new MyAdatper();
        commonLv.setAdapter();*/
    }
}
