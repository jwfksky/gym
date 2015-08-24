package com.gym;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gym.ui.fragment.BaseFragment;
import com.gym.ui.fragment.IFragment;
import com.gym.ui.fragment.MainFragment;
import com.gym.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity implements IFragment,View.OnClickListener {

    @InjectView(R.id.back_tb)
    TextView backTb;
    @InjectView(R.id.title_tb)
    TextView titleTb;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.main_fl)
    FrameLayout mainFl;
    @InjectView(R.id.menu_home)
    RadioButton menuHome;
    @InjectView(R.id.menu_forum)
    RadioButton menuForum;
    @InjectView(R.id.menu_center)
    RadioButton menuCenter;
    @InjectView(R.id.radioGroup_menu)
    RadioGroup radioGroupMenu;
    @InjectView(R.id.area_tb)
    TextView areaTb;
    private ActionBar mActionBar;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        fm=getSupportFragmentManager();
    }

    @Override
    public void initData() {
        menuCenter.setOnClickListener(this);
        BaseFragment bf= (BaseFragment) fm.findFragmentById(R.id.main_fl);
        if(bf==null){
            fm.beginTransaction().replace(R.id.main_fl,new MainFragment()).commit();
        }
    }

    @Override
    public void initActionbar() {
        setSupportActionBar(toolbar);
        mActionBar=getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.VISIBLE);
        backTb.setVisibility(View.GONE);
        titleTb.setText(UIUtils.getString(R.string.app_name));
    }
    @Override
    public void onChangeFragment(BaseFragment bf, Parcelable params, boolean replace) {
        if (!bf.isVisible()) {
            if (params != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", params);
                bf.setArguments(bundle);
            }
            //or not add to backStack
            if (replace) {
                fm.beginTransaction().replace(R.id.main_fl, bf).addToBackStack(null).commit();
            } else {
                fm.beginTransaction().add(R.id.main_fl, bf).addToBackStack(null).commit();
            }

        }
    }

    @Override
    public void onClick(View v) {
        if(v==menuCenter){
            usePopup(menuCenter);
        }
    }
    private void usePopup(final View anchor){
        //参考： http://www.cnblogs.com/sw926/p/3230659.html
        LayoutInflater mInflater = LayoutInflater.from(this);
        ViewGroup rootView = (ViewGroup)mInflater.inflate(R.layout.menu_center, null);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        final PopupWindow popup = new PopupWindow(this);
        //setContentView之前一定要设置宽高，否则不显示
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //去掉默认的背景
        popup.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.cellbg2));
        popup.setContentView(rootView);
        //点击空白处的时候PopupWindow会消失
        popup.setTouchable(true);
        popup.setOutsideTouchable(true);
        //如果focusable为false，在一个Activity弹出一个PopupWindow，按返回键，由于PopupWindow没有焦点，会直接退出Activity。如果focusable为true，PopupWindow弹出后，所有的触屏和物理按键都有PopupWindows处理。
        popup.setFocusable(true);
        //计算弹框位置
        int[] xy = calcPopupXY(rootView,anchor);
        //不用任何gravity，使用绝对的(x,y)坐标
        popup.showAtLocation((View)anchor.getParent(), Gravity.NO_GRAVITY, xy[0], xy[1]);
    }
    private int[] calcPopupXY(View rootView, View anchor){
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        rootView.measure(w, h);
        int popupWidth = anchor.getWidth();
        int popupHeight = rootView.getMeasuredHeight();
        Rect anchorRect = getViewAbsoluteLocation(anchor);
        int x = anchorRect.left + (anchorRect.right - anchorRect.left)/2 - popupWidth / 2;
        int y = anchorRect.top - popupHeight;
        return new int[]{x,y};
    }
    public static Rect getViewAbsoluteLocation(View view){
        if(view == null){
            return new Rect();
        }
        // 获取View相对于屏幕的坐标
        int[] location = new int[2] ;
        view.getLocationOnScreen(location);//这是获取相对于屏幕的绝对坐标，而view.getLocationInWindow(location); 是获取window上的相对坐标，本例中只有一个window，二者等价
        // 获取View的宽高
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        // 获取View的Rect
        Rect rect = new Rect();
        rect.left = location[0];
        rect.top = location[1];
        rect.right = rect.left + width;
        rect.bottom = rect.top + height;
        return rect;
    }
}
