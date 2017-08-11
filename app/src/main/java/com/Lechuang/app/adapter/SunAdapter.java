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
 * Created by Administrator on 2017/8/7.
 */

public class SunAdapter extends BaseAdapter {
    Context context;
    List<PetClassification.DataBean.PetoneBean.PettwoBean> pettwo;

    public SunAdapter(Context context, List<PetClassification.DataBean.PetoneBean.PettwoBean> pettwo) {
        this.context = context;
        this.pettwo = pettwo;
    }

    @Override
    public int getCount() {
        return pettwo.size();
    }

    @Override
    public Object getItem(int position) {
        return pettwo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChildAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ChildAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.child_item_layout, null);
            holder.childText = (TextView) convertView
                    .findViewById(R.id.child_textView);
            convertView.setTag(holder);
        } else {
            holder = (ChildAdapter.ViewHolder) convertView.getTag();
        }
        holder.childText.setText(pettwo.get(position).getType_name());
        return convertView;
    }
}
