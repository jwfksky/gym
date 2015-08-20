package com.gym.ui.fragment;

import android.view.View;

import com.gym.R;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.UIUtils;

/**
 * Created by Administrator on 2015-08-20.
 */
public class SpaceFragment extends BaseFragment {
    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.SUCCEED;
    }

    @Override
    protected View createSuccessView() {
        View rootView= UIUtils.inflate(R.layout.fragment_space);
        return rootView;
    }
}
