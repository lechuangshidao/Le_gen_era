package com.Lechuang.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.Bean.PeopleNearby;
import com.Lechuang.app.R;

import java.util.List;

/**
 * 项目名称：com.Lechuang.app.adapter
 * 创建人：Houzengyu
 * 创建时间：2017/7/19 15:32
 * 功能介绍：
 */
public class Fujin_Adapter extends BaseAdapter {
    Context context;
    List<PeopleNearby.DataBean> data;
    public Fujin_Adapter(Context context, List<PeopleNearby.DataBean> data) {
        this.context = context;
        this.data = data;
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
            view = View.inflate(context, R.layout.my_closest_item, null);
            holder = new ViewHolder();
            holder.image_myclosest = (ImageView) view.findViewById(R.id.image_myclosest);
            holder.text_pet_name = (TextView) view.findViewById(R.id.text_pet_name);
            holder.text_pet_var = (TextView) view.findViewById(R.id.text_pet_var);
            holder.text_closest_juli = (TextView) view.findViewById(R.id.text_closest_juli);
            view.setTag(holder);
        } else {
            holder= (ViewHolder) view.getTag();
        }
        holder.text_pet_name.setText(data.get(i).getNickname());//附近宠物姓名
        holder.text_closest_juli.setText(data.get(i).getKm());//附近宠物距离
        holder.text_pet_var.setText(data.get(i).getType_name());//附近宠物品种
        //Glide.with(context).load(data.get(i).getPet_img()).into(holder.image_myclosest);
        return view;
    }

    class ViewHolder {
        ImageView image_myclosest;
        TextView text_pet_name;
        TextView text_pet_var;
        TextView text_closest_juli;
    }
}
