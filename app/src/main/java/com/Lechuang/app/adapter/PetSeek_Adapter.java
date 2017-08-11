package com.Lechuang.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.Bean.PetSeek;
import com.Lechuang.app.R;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class PetSeek_Adapter extends BaseAdapter{
    Context context;
    List<PetSeek.DataBean> pet_data;
    public PetSeek_Adapter(Context context, List<PetSeek.DataBean> pet_data) {
        this.context=context;
        this.pet_data=pet_data;
    }

    @Override
    public int getCount() {
        return pet_data == null ? 0 : pet_data.size();
    }

    @Override
    public Object getItem(int position) {
        return pet_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.my_closest_item, null);
            holder = new ViewHolder();
            holder.image_myclosest = (ImageView) convertView.findViewById(R.id.image_myclosest);
            holder.text_pet_name = (TextView) convertView.findViewById(R.id.text_pet_name);
            holder.text_pet_var = (TextView) convertView.findViewById(R.id.text_pet_var);
            holder.text_closest_juli = (TextView) convertView.findViewById(R.id.text_closest_juli);
            convertView.setTag(holder);
        } else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.text_pet_name.setText(pet_data.get(position).getPet_name());//附近宠物姓名
        holder.text_closest_juli.setText(pet_data.get(position).getDistance());//附近宠物距离
        holder.text_pet_var.setText(pet_data.get(position).getPet_type());//附近宠物品种
        //Glide.with(context).load(data.get(i).getPet_img()).into(holder.image_myclosest);
        return convertView;

    }
    class ViewHolder {
        ImageView image_myclosest;
        TextView text_pet_name;
        TextView text_pet_var;
        TextView text_closest_juli;
    }
}
