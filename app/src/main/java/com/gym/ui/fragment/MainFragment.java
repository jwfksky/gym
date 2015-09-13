package com.gym.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.gym.R;
import com.gym.ui.activity.MainActivity;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.UIUtils;

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

    @Override
    protected LoadingPage.LoadResult load() {
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
    }

    @Override
    public void onClick(View view) {
        if(view==menuFit){
            ((MainActivity)getActivity()).onChangeFragment(FragmentFactory.createFragment(FragmentFactory.MENU_FIT),null,true);
        }else if(view==menuSpace){
            ((MainActivity)getActivity()).onChangeFragment(FragmentFactory.createFragment(FragmentFactory.MENU_SPACE),null,true);
        }else if(view==menuCoach){
            ((MainActivity)getActivity()).onChangeFragment(FragmentFactory.createFragment(FragmentFactory.MENU_COACH),null,true);
        }else if(view==menuBox){
            FitFragment fit= (FitFragment) FragmentFactory.createFragment(FragmentFactory.MENU_FIT);
            fit.setJobType(UIUtils.getString(R.string.box));
            ((MainActivity)getActivity()).onChangeFragment(fit, null, true);
        }else if(view==menuFight){
            FitFragment fit= (FitFragment) FragmentFactory.createFragment(FragmentFactory.MENU_FIT);
            fit.setJobType(UIUtils.getString(R.string.fight));
            ((MainActivity)getActivity()).onChangeFragment(fit, null, true);
        }
    }
}
