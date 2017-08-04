package com.Lechuang.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Lechuang.app.Bean.PetMessageInfo;

import java.util.List;

import www.xcd.com.mylibrary.R;


/**
 * 父类别 适配器
 * @author ansen
 * @create time 2015-09-25
 */
public class ParentCategoryAdapter extends BaseAdapter {
	private Context mContext;
	private List<PetMessageInfo.PetMessageData> list;
	private int pos;
	
	public ParentCategoryAdapter(Context context,List<PetMessageInfo.PetMessageData> list) {
		mContext = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list==null?0:list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_parent_category_item, null);
			holder.tvParentCategoryName = (TextView) convertView.findViewById(R.id.tv_parent_category_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvParentCategoryName.setText(list.get(position).getPet_name());
		return convertView;
	}

	public class ViewHolder {
		public TextView tvParentCategoryName;
	}
	
	public void setSelectedPosition(int pos) {
		this.pos = pos;
	}
	
	public int getPos() {
		return pos;
	}
}
