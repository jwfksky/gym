package com.gym.ui.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.ui.fragment.BaseFragment;
import com.gym.ui.fragment.FragmentFactory;
import com.gym.ui.fragment.IFragment;
import com.gym.ui.fragment.MainFragment;
import com.gym.utils.UIUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity implements IFragment, View.OnClickListener {

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
    private PopupWindow popup;
    private TextView edit;
    private TextView collect;
    private TextView course;
    private TextView buyLesson;
    private TextView orderManager;
    private TextView editInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        fm = getSupportFragmentManager();
    }

    @Override
    public void initData() {
        menuCenter.setOnClickListener(this);
        BaseFragment bf = (BaseFragment) fm.findFragmentById(R.id.main_fl);
        if (bf == null) {
            fm.beginTransaction().replace(R.id.main_fl, new MainFragment()).commit();
        }
    }

    @Override
    public void initActionbar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
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
        if (v == menuCenter) {
            usePopup(menuCenter);
        }
        if (v == edit) {
            popup.dismiss();
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        }
        if (v == collect) {
            popup.dismiss();
            Intent intent = new Intent(MainActivity.this, CommonActivity.class);
            ArrayList<String> list = new ArrayList<>();
            list.add("积分明细");
            list.add(FragmentFactory.CENTER_POINTS + "");
            intent.putStringArrayListExtra(Constants.TOOLBAR_ITEM, list);
            startActivity(intent);
        }
        if (v == course) {
            popup.dismiss();
            Intent intent = new Intent(MainActivity.this, CommonActivity.class);
            //第一个为标题，第二个为指定显示的fragment,第三个为toolbar右侧
            ArrayList<String> list = new ArrayList<>();
            list.add("个人里程");
            list.add(FragmentFactory.CENTER_COURSE + "");
            list.add(UIUtils.getString(R.string.addCourse));
            intent.putStringArrayListExtra(Constants.TOOLBAR_ITEM, list);
            startActivity(intent);
        }if (v == buyLesson) {
            popup.dismiss();
            Intent intent = new Intent(MainActivity.this, CommonViewPagerActivity.class);
            //第一个为标题，第二个为指定显示的fragment,第三个为toolbar右侧
            ArrayList<String> list = new ArrayList<>();
            list.add("购买课程");
            list.add(FragmentFactory.CENTER_BUYLESSON + "");
            intent.putStringArrayListExtra(Constants.TOOLBAR_ITEM, list);
            startActivity(intent);
        }
        if (v == orderManager) {
            popup.dismiss();
            Intent intent = new Intent(MainActivity.this, CommonViewPagerActivity.class);
            //第一个为标题，第二个为指定显示的fragment,第三个为toolbar右侧
            ArrayList<String> list = new ArrayList<>();
            list.add("订单管理");
            list.add(FragmentFactory.CENTER_ORDERMANAGER + "");
            intent.putStringArrayListExtra(Constants.TOOLBAR_ITEM, list);
            startActivity(intent);
        }
        if (v == editInfo) {
            popup.dismiss();
            Intent intent = new Intent(MainActivity.this, EditPersonActivity.class);
            //第一个为标题，第二个为指定显示的fragment,第三个为toolbar右侧
            ArrayList<String> list = new ArrayList<>();
            list.add("编辑个人资料");
            list.add(FragmentFactory.CENTER_ORDERMANAGER + "");
            intent.putStringArrayListExtra(Constants.TOOLBAR_ITEM, list);
            startActivity(intent);
        }
    }

    /**
     * 使用popupWindow
     *
     * @param anchor
     */
    private void usePopup(final View anchor) {
        LayoutInflater mInflater = LayoutInflater.from(this);
        ViewGroup rootView = (ViewGroup) mInflater.inflate(R.layout.menu_center, null);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        operateItem(rootView);
        popup = new PopupWindow(this);
        //setContentView之前一定要设置宽高，否则不显示
        // popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(anchor.getWidth());
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
        int[] xy = calcPopupXY(rootView, anchor);
        //不用任何gravity，使用绝对的(x,y)坐标
        popup.showAtLocation((View) anchor.getParent(), Gravity.NO_GRAVITY, xy[0], xy[1]);
    }

    /**
     * 操作popupwindow的项
     *
     * @param rootView
     */
    private void operateItem(ViewGroup rootView) {
        edit = (TextView) rootView.findViewById(R.id.edit_tv);
        collect = (TextView) rootView.findViewById(R.id.collect_tv);
        course = (TextView) rootView.findViewById(R.id.course_tv);
        buyLesson = (TextView) rootView.findViewById(R.id.buyLesson_tv);
        orderManager = (TextView) rootView.findViewById(R.id.orderManager_tv);
        editInfo = (TextView) rootView.findViewById(R.id.editInfo_tv);
        edit.setOnClickListener(this);
        collect.setOnClickListener(this);
        course.setOnClickListener(this);
        buyLesson.setOnClickListener(this);
        orderManager.setOnClickListener(this);
        editInfo.setOnClickListener(this);
    }

    /**
     * 测量popup要显示的位置
     *
     * @param rootView
     * @param anchor
     * @return
     */
    private int[] calcPopupXY(View rootView, View anchor) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        rootView.measure(w, h);
        int popupWidth = rootView.getMeasuredWidth();
        int popupHeight = rootView.getMeasuredHeight();
        Rect anchorRect = getViewAbsoluteLocation(anchor);
        Rect parentRect = getViewAbsoluteLocation(radioGroupMenu);
        // int x = anchorRect.left + (anchorRect.right - anchorRect.left)/2 - popupWidth / 2;
        // int y = anchorRect.top - popupHeight;
        int x = anchorRect.left;
        int y = parentRect.top - popupHeight;
        return new int[]{x, y};
    }

    /**
     * 获得view所在的位置
     *
     * @param view
     * @return
     */
    public static Rect getViewAbsoluteLocation(View view) {
        if (view == null) {
            return new Rect();
        }
        // 获取View相对于屏幕的坐标
        int[] location = new int[2];
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        popup.dismiss();
    }
}
