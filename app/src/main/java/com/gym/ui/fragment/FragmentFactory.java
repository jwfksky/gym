package com.gym.ui.fragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2015-08-20.
 */
public class FragmentFactory {

    public static final int MENU_SPACE=0;
    public static final int MENU_COACH=1;
    public static final int MENU_FIT=2;
    public static final int MENU_BOX=3;
    public static final int MENU_FIGHT=4;
    public static final int CENTER_COURSE=5;
    public static final int CENTER_BUYLESSON=6;
    public static final int CENTER_ORDERMANAGER=7;
    public static final int CENTER_POINTS = 8;
    private static HashMap<Integer,BaseFragment> map=new HashMap<>();


    public static BaseFragment createFragment(int position){
        BaseFragment fragment=map.get(position);
        if(fragment==null){
            switch (position){
                case MENU_SPACE:
                    fragment=new SpaceFragment();
                    break;
                case MENU_COACH:
                    fragment=new CoachFragment();
                    break;
                case MENU_FIT:
                    fragment=new FitFragment();
                    break;
                case MENU_BOX:
                    fragment=new BoxFragment();
                    break;
                case MENU_FIGHT:
                    fragment=new FightFragment();
                    break;
                case CENTER_COURSE:
                    fragment=new CourseFragment();
                    break;
                case CENTER_BUYLESSON:
                    fragment=new BuyLessonViewPagerFragment();
                    break;
                case CENTER_ORDERMANAGER:
                    fragment=new OrderManagerViewPagerFragment();
                    break;
                case CENTER_POINTS:
                    fragment=new PersonPointsFragment();
                    break;
            }
        }
        return fragment;
    }

}
