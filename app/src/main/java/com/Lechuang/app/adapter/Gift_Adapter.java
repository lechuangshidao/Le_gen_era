package com.Lechuang.app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;

/**
 * 项目名称：com.Lechuang.app.adapter
 * 创建人：Houzengyu
 * 创建时间：2017/7/19 14:00
 * 功能介绍：
 */
public class Gift_Adapter extends BaseAdapter{
    Context context;
    private TextView text_dialog_name;
    private TextView text_duihuan_item;

    public Gift_Adapter(Context context) {
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
            view = View.inflate(context, R.layout.griditem_gift, null);
            holder = new ViewHolder();
            holder.image_jifen_gift= (ImageView) view.findViewById(R.id.image_jifen_gift);
            holder.text_gift_name= (TextView) view.findViewById(R.id.text_gift_name);
            holder.gift_num= (TextView) view.findViewById(R.id.gift_num);
            text_duihuan_item = holder.text_duihuan_item;
            text_duihuan_item = (TextView) view.findViewById(R.id.text_duihuan_item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        text_duihuan_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomizeDialog();
            }
        });
        return view;
    }
    class ViewHolder {
        ImageView image_jifen_gift;
        TextView text_gift_name;
        TextView gift_num;
        TextView text_duihuan_item;
    }
    private void showCustomizeDialog() {
    /* @setView 装入自定义View ==> R.layout.dialog_customize
     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
     * dialog_customize.xml可自定义更复杂的View
     */
        final AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.dialog_shop_item, null);
        customizeDialog.setView(view);
        final ImageView image_dialog_back=  (ImageView) view.findViewById(R.id.image_dialog_back);
        TextView text_dialog_name=  (TextView) view.findViewById(R.id.text_dialog_name);
        final Button button_dialog_wancheng= (Button) view.findViewById(R.id.button_dialog_wancheng);
        button_dialog_wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customizeDialog.create().dismiss();
            }
        });
        image_dialog_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customizeDialog.create().dismiss();
            }
        });
        customizeDialog.create().show();
    }
}
