/**
 * AnimationUtils.java [V 1..0.0]
 * classes : com.hb56.hps.android.utils.AnimationUtils
 * zhangyx Create at 2014-10-31 下午2:31:50
 */
package com.gym.utils;



import android.app.Activity;
import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

import com.gym.R;

/**
 * 自定义控件的动画效果 com.hb56.hps.android.utils.AnimationUtils
 * 
 * create at 2014-10-31 下午2:31:50
 */
public class AnimationUtil {

	/* 特效源码---------listview加载的效果 */
	public static LayoutAnimationController getListAnimTranslate() {
		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(300);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(500);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.5f);

		controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
		return controller;
		/*-----------------------------------------*/
	}

	/**
	 * 退出Activity的动画 : zoom 动画
	 * 
	 * @param context
	 */
	public static void finishActivityAnimation(Context context) {
		((Activity) context).finish();
		((Activity) context).overridePendingTransition(R.anim.zoom_enter,
				R.anim.zoom_exit);
	}
	
	/**
	 * 退出Activity的动画 : Popup 动画
	 * 
	 * @param context
	 */
	public static void finishActivityAnimationPopup(Context context) {
		((Activity) context).finish();
		((Activity) context).overridePendingTransition(R.anim.popup_right_in,
				R.anim.popup_right_out);
	}
	
	/**
	 * 退出Activity的动画 : Popup 动画
	 * 
	 * @param context
	 */
	public static void activityAnimationPopup(Context context) {
		((Activity) context).overridePendingTransition(R.anim.popup_right_in,
				R.anim.popup_right_out);
	}
	
	/**
	 * 退出Activity的动画 : PushLeft 动画
	 * 
	 * @param context
	 */
	public static void activityAnimationPushLeft(Context context) {
		((Activity) context).overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}
	
	/**
	 * 退出Activity的动画 : PushLeft 动画
	 * 
	 * @param context
	 */
	public static void finishActivityAnimationPushLeft(Context context) {
		((Activity) context).finish();
		((Activity) context).overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}
	

	/***
	 * zoom 动画s
	 * 
	 * @param context
	 */
	public static void activityZoomAnimation(Context context) {
		((Activity) context).overridePendingTransition(R.anim.zoom_enter,
				R.anim.zoom_exit);
	}

}
