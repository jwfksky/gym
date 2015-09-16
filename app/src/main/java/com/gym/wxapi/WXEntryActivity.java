package com.gym.wxapi;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.utils.UIUtils;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{
	private IWXAPI api;
	private BaseResp resp = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
		api.handleIntent(getIntent(), this);
	}
	
	

	// ΢�ŷ������󵽵�����Ӧ��ʱ����ص����÷���
	@Override
	public void onReq(BaseReq req) {
		finish();
	}

	// ������Ӧ�÷��͵�΢�ŵ�����������Ӧ�������ص����÷���
	@Override
	public void onResp(BaseResp resp) {
		String result = "";
		if (resp != null) {
			Constants.resp=resp;
		}

		switch(resp.errCode) {

			case BaseResp.ErrCode.ERR_OK:

				finish();
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				result = UIUtils.getString(R.string.cancel_weixin);
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
				finish();
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				result = UIUtils.getString(R.string.refuse_weixin);
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
				finish();
				break;
			default:
				result = UIUtils.getString(R.string.return_weixin);
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
				finish();
				break;
		}

	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
		finish();
	}
}