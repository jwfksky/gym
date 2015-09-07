package com.gym.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gym.app.BaseApplication;
import com.gym.ui.widget.LoadingPage;
import com.gym.utils.UIUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Author: Jwf(feijia101@gmail.com) <br\>
 * Date: 2015-04-15 14:26<br\>
 * Version: 1.0<br\>
 * Desc:<br\>
 * Revise:<br\>
 */
public abstract class BaseFragment extends Fragment implements View.OnTouchListener {

    protected LoadingPage mContentView;
    protected int currentPage = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentPage = 1;
        //  if(mContentView==null){//如果为空，就新建一个
        mContentView = new LoadingPage(UIUtils.getContext()) {

            @Override
            public View createLoadedView() {
                return BaseFragment.this.createSuccessView();
            }

            @Override
            public LoadResult load() {
                return BaseFragment.this.load();
            }
        };
      /*  }else{//不为null时，需要把自身从父布局中移除，因为ViewPager会再次添加
            ViewUtils.removeSelfFromParent(mContentView);
        }*/
        mContentView.setOnTouchListener(this);
        return mContentView;
    }

    /**
     * 当显示的时候，加载该页面
     */
    public void show() {

        if (mContentView != null)
            mContentView.show();

    }

    /**
     * 刷新或加载更多 加载数据
     */
    public void refreshOrLoad() {
        if (mContentView != null)
            mContentView.executeTask();
    }

    /**
     * 验证数据返回结果，刷新页面
     */
    protected LoadingPage.LoadResult checkResult(Object obj) {

        if (obj == null) {
            return LoadingPage.LoadResult.ERROR;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() <= 0) {
                return LoadingPage.LoadResult.EMPTY;
            }
        }
        return LoadingPage.LoadResult.SUCCEED;
    }

    protected abstract LoadingPage.LoadResult load();

    protected abstract View createSuccessView();

    @Override
    public void onResume() {
        super.onResume();
        show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * operate load or refresh data
     *
     * @param beans   extra data for api
     * @param list    current display data
     * @param pageNow current page
     */
    protected List operateExtraData(List beans, List list, Integer pageNow) {
        if ("1".equals(String.valueOf(pageNow))) {
            if (list != null) {
                list.clear();
                list.addAll(beans);
            } else {
                if (beans != null && beans.size() == 0) {
                    return new ArrayList();
                }
                list = beans;
            }

        } else {
            if (beans != null)
                list.addAll(beans);
        }
        return list;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }



}
