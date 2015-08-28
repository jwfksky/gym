package com.gym.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;

public class MyDialog extends Dialog{
//	private DialogDismissCallBack dialogDismissCallBack;
	public MyDialog(Context context, int theme) {
		super(context, theme);
//		this.dialogDismissCallBack = dialogDismissCallBack;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			dismiss();
//			dialogDismissCallBack.cancleTask();
			}
		return true;
	}

}
