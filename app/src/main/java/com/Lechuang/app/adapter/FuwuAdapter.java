package com.Lechuang.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;

import java.util.List;

/**
 * 项目名称：com.Lechuang.app.adapter
 * 创建人：Houzengyu
 * 创建时间：2017/7/18 15:27
 * 功能介绍：
 */
public class FuwuAdapter extends BaseAdapter {
    Context context;
    List<String> list_date;

    public FuwuAdapter(Context context, List<String> list_date) {
        this.context = context;
        this.list_date = list_date;
    }

    @Override
    public int getCount() {
        return list_date == null ? 0 : list_date.size();
    }

    @Override
    public Object getItem(int i) {
        return list_date.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.listitem_fuwu, null);
            holder = new ViewHolder();
            holder.image_fuwu= (ImageView) view.findViewById(R.id.image_fuwu);
            holder.text_item_name= (TextView) view.findViewById(R.id.text_item_name);
            holder.text_item_time= (TextView) view.findViewById(R.id.text_item_time);
            holder.text_item_juli= (TextView) view.findViewById(R.id.text_item_juli);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text_item_name.setText(list_date.get(i));
        return view;
    }
    class ViewHolder {
        ImageView image_fuwu;
        TextView text_item_name;
        TextView text_item_time;
        TextView text_item_juli;
    }
}
