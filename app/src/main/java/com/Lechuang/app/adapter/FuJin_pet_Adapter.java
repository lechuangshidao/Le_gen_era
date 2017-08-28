package com.Lechuang.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.Activity.Pet_details_Activity;
import com.Lechuang.app.Bean.PetInformation;
import com.Lechuang.app.R;
import com.Lechuang.app.entity.GlobalParam;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 项目名称：com.Lechuang.app.adapter
 * 创建人：Houzengyu
 * 创建时间：2017/7/24 10:33
 * 功能介绍：
 */
public class FuJin_pet_Adapter extends BaseAdapter{
    Context context;
    List<PetInformation.DataBean> data;
    public FuJin_pet_Adapter(Context context, List<PetInformation.DataBean> data) {
        this.context=context;
        this.data=data;
        Log.i("data", "FuJin_pet_Adapter:"+data.toString());
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.fujin_pet_item, null);
            holder = new ViewHolder();
            holder.image_fujin_pet= (ImageView) view.findViewById(R.id.image_fujin_pet);
            holder.text_fujin_pet_name= (TextView) view.findViewById(R.id.text_fujin_pet_name);
            holder.text_fujin_pet_dingwei= (TextView) view.findViewById(R.id.text_fujin_pet_dingwei);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text_fujin_pet_name.setText(data.get(i).getPet_name());//宠物信息名字
        holder.text_fujin_pet_dingwei.setText(data.get(i).getKm());//宠物距离
        Glide.with(context).load(GlobalParam.IP+data.get(i).getPet_img()).into(holder.image_fujin_pet);//宠物图片
        //点击跳转到详情
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String petid = data.get(i).getPetid();
                Intent intent=new Intent(context, Pet_details_Activity.class);
                intent.putExtra("petid",petid);
                context.startActivity(intent);
            }
        });
        return view;
    }class ViewHolder {
        ImageView image_fujin_pet;
        TextView text_fujin_pet_name;
        TextView text_fujin_pet_dingwei;
    }
}
