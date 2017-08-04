package com.Lechuang.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.Bean.MapHospital;
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
    List<MapHospital.DataBean> data;
    public FuwuAdapter(Context context, List<MapHospital.DataBean> data) {
        this.context = context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
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
        holder.text_item_name.setText(data.get(i).getHosname());//医院名字
        holder.text_item_juli.setText(data.get(i).getMeter());//医院距离
        holder.text_item_time.setText("营业时间："+data.get(i).getBustime()+"米以内");//营业时间
       // Glide.with(context).load(data.get(i).getHospicture()).into(holder.image_fuwu);//医院图片
        return view;
    }
    class ViewHolder {
        ImageView image_fuwu;
        TextView text_item_name;
        TextView text_item_time;
        TextView text_item_juli;
    }
}
