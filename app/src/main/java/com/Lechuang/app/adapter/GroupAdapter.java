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
 * 父ListView适配器
 * 
 * @author zihao
 * 
 */
public class GroupAdapter extends BaseAdapter {

	Context mContext;// 上下文对象
	List<PetClassification.DataBean> data;// 父item标题数组
	int mPosition = 0;// 选中的位置

	/**
	 * 构造方法
	 * @param context
	 *            // 上下文对象
	 * @param data
	 */
	public GroupAdapter(Context context, List<PetClassification.DataBean> data) {
		this.mContext = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		// 初始化布局控件
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.group_item_layout, null);
			holder.groupName = (TextView) convertView
					.findViewById(R.id.group_textView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 设置控件内容
		holder.groupName.setText(data.get(position).getType_name());
		if (mPosition == position) {
			holder.groupName.setTextColor(mContext.getResources().getColor(
					R.color.list_item_text_pressed_bg));
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.group_item_pressed_bg));
		} else {
			holder.groupName.setTextColor(mContext.getResources().getColor(
					android.R.color.black));
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.group_item_bg));
		}

		return convertView;
	}

	/**
	 * 获取item总数
	 */
	@Override
	public int getCount() {
		return data.size();
	}

	/**
	 * 获取某一个Item的内容
	 */
	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	/**
	 * 获取当前item的ID
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		/** 父Item名称 **/
		TextView groupName;
	}

	/**
	 * 设置选择的位置
	 * 
	 * @param position
	 */
	public void setSelectedPosition(int position) {
		this.mPosition = position;
	}

}
