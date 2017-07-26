package com.Lechuang.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;

/**
 * 项目名称：com.Lechuang.app.adapter
 * 创建人：Houzengyu
 * 创建时间：2017/7/24 10:33
 * 功能介绍：
 */
public class FuJin_pet_Adapter extends BaseAdapter{
    Context context;
    public FuJin_pet_Adapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.fujin_pet_item, null);
            holder.image_fujin_pet= (ImageView) view.findViewById(R.id.image_fujin_pet);
            holder.text_fujin_pet_name= (TextView) view.findViewById(R.id.text_pet_name);
            holder.text_fujin_pet_dingwei= (TextView) view.findViewById(R.id.text_fujin_pet_dingwei);
            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        return view;
    }class ViewHolder {
        ImageView image_fujin_pet;
        TextView text_fujin_pet_name;
        TextView text_fujin_pet_dingwei;
    }
}
