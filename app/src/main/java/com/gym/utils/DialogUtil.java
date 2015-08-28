package com.gym.utils;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.gym.R;
import com.gym.ui.widget.MyDialog;

/**
 * Dialog 工具类
 *com.hb56.DriverReservation.android.utils.DialogUtil
 * @author Admin-zhangyx
 *
 * create at 2015-1-12 下午2:07:30
 */
public class DialogUtil {
	private static Handler handler;

	/**
	 * 获取能够转动加载的Dialog
	 * 
	 * @param activity
	 *            当前的上下文
	 * @return Dialog
	 */
	public static Dialog buildDialogRecover(Activity activity) {
		MyDialog dialog = new MyDialog(activity, R.style.dialog_styles);
		try {
			LayoutInflater laInflater = activity.getLayoutInflater();
			View view = laInflater.inflate(R.layout.loadingimager, null);
			dialog.setContentView(view);

			ImageView loadImageView = (ImageView) view
					.findViewById(R.id.loadImager);

			loadImageView.setBackgroundResource(R.drawable.progress_big);
			final AnimationDrawable frameAnimation = (AnimationDrawable) loadImageView
					.getBackground();
			frameAnimation.setOneShot(false);
			// loadImageView.setImageDrawable(frameAnimation);

			// 一定要在Handler中延迟开始，要不不会转
			if (handler == null) {
				handler = new Handler();
			}
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					frameAnimation.start();
				}
			}, 50);

			WindowManager.LayoutParams params = dialog.getWindow()
					.getAttributes();
			params.gravity = Gravity.CENTER;
			dialog.getWindow().setAttributes(params);

			// 当Dialog取消的时候关闭动画
			dialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					Log.d("CreatLoadingDialog", "Dialog is cancle");
					frameAnimation.stop();
				}
			});
			dialog.show();
		} catch (Exception e) {
			dialog.dismiss();
		}

		return dialog;

	}

	
}
