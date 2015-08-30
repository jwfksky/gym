package com.gym.ui.fragment;

import android.view.View;

import com.gym.ui.widget.LoadingPage;

/**
 * Created by Administrator on 2015-08-20.
 */
public class CoachFragment extends BaseFragment {
    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.EMPTY;
    }

    @Override
    protected View createSuccessView() {
        return null;
    }
}
