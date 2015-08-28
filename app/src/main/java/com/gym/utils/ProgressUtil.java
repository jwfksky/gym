/**
 * ProgressUtil.java [V 1.0.0]
 * classes : com.harbour.AirServicePlatform.utils.ProgressUtil
 * zhangyx Create at 2014-6-26.下午4:10:00
 */
package com.gym.utils;

import android.app.Activity;
import android.app.Dialog;

/**
 * 加载进度条 com.harbour.AirServicePlatform.utils.ProgressUtil
 * 
 * @author zhangyx <br/>
 *         create at 2014-6-26 下午4:10:00
 * 
 */
public class ProgressUtil {

	private static Dialog mDialog;

	public static void startProgressBar(Activity activity) {
		
		mDialog = DialogUtil.buildDialogRecover(activity);
		mDialog.show();

	}

	public static void stopProgressBar() {
		if (mDialog != null) {
			mDialog.cancel();
			mDialog.dismiss();
			mDialog = null;
		}
	}

}
