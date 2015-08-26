package com.gym.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gym.R;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015-08-26.
 */
public class CollectFragment extends BaseFragment {
    @InjectView(R.id.common_lv)
    ListView commonLv;

    @Override
    protected LoadingPage.LoadResult load() {

        return LoadingPage.LoadResult.EMPTY;
    }

    @Override
    protected View createSuccessView() {
        View rootView=UIUtils.inflate(R.layout.fragment_common_list);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

}
