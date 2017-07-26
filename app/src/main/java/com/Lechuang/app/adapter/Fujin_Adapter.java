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
 * 创建时间：2017/7/19 15:32
 * 功能介绍：
 */
public class Fujin_Adapter extends BaseAdapter {
    Context context;
    public Fujin_Adapter(Context context) {
       this.context=context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.my_closest_item, null);
            holder = new ViewHolder();
            holder.image_myclosest= (ImageView) view.findViewById(R.id.image_myclosest);
            holder.text_pet_name= (TextView) view.findViewById(R.id.text_pet_name);
            holder.text_pet_var= (TextView) view.findViewById(R.id.text_pet_var);
            holder.text_closest_juli= (TextView) view.findViewById(R.id.text_closest_juli);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }
    class ViewHolder {
        ImageView image_myclosest;
        TextView text_pet_name;
        TextView text_pet_var;
        TextView text_closest_juli;
    }
}
