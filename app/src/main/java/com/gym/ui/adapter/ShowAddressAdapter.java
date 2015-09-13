package com.gym.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.gym.R;
import com.gym.bean.FitSpaceBean;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**   
 * @Title: ShowAddressAdapter.java 
 * @Package com.eFan.SuperGroundAttack.adapter 
 * @Description: TODO 显示地址（省份、地市、区域、学校的adapter）
 * @author Jwf feijia101_gmail_com   
 * @date 2015-4-20 上午9:13:08 
 * @version V1.0   
 */
public class ShowAddressAdapter extends BaseAdapter {
	private ArrayList<FitSpaceBean> list;

	private Context context;
	public ShowAddressAdapter(List<FitSpaceBean> list,Context context) {
		this.list = (ArrayList<FitSpaceBean>) list;
		this.context=context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list==null?0:list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
		if (convertView == null) {
			mHolder = new ViewHolder();
			convertView = View.inflate(context,
					R.layout.address_item, null);
			mHolder.tv = (TextView) convertView.findViewById(R.id.tv_addr);
			convertView.setTag(mHolder);
		}
		mHolder = (ViewHolder) convertView.getTag();
		if (!TextUtils.isEmpty(list.get(position).getGymName()))
			mHolder.tv.setText(list.get(position).getGymName());

		return convertView;
	}
	static class ViewHolder {
		TextView tv;
	}

}
