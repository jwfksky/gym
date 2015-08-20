package com.gym.ui.fragment;

import android.os.Parcelable;

/**
 * Created by Administrator on 2015-08-20.
 */
public interface IFragment {
    void onChangeFragment(BaseFragment bf, Parcelable params, boolean replace);
}
