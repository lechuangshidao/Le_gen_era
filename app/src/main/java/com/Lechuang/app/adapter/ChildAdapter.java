package com.Lechuang.app.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Lechuang.app.Bean.PetClassification;
import com.Lechuang.app.R;

import java.util.List;

/**
 * 子ListView适配器
 * 
 * @author zihao
 * 
 */
public class ChildAdapter extends BaseAdapter {

	Context mContext;
	List<PetClassification.DataBean.PetoneBean> petone;
	/**
	 * 构造方法
	 *
	 * @param context
	 * @param petone
	 */
	public ChildAdapter(Context context, List<PetClassification.DataBean.PetoneBean> petone) {
		mContext = context;
		this.petone=petone;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.child_item_layout, null);
			holder.childText = (TextView) convertView
					.findViewById(R.id.child_textView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.childText.setText(petone.get(position).getType_name());
		return convertView;
	}

	static class ViewHolder {
		TextView childText;
	}

	/**
	 * 获取item总数
	 */
	@Override
	public int getCount() {
		if (petone == null) {
			return 0;
		}
		return petone.size();
	}

	/**
	 * 获取某一个Item的内容
	 */
	@Override
	public Object getItem(int position) {
		return petone.size();
	}

	/**
	 * 获取当前item的ID
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

}
