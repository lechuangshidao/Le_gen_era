package com.Lechuang.app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.Bean.CreditsExchange;
import com.Lechuang.app.Bean.ShoppingMall;
import com.Lechuang.app.R;
import com.Lechuang.app.entity.GlobalParam;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.xcd.com.mylibrary.help.OkHttpHelper;

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
    List<ShoppingMall.DataBean> data;
    String token;
    public Gift_Adapter(Context context, List<ShoppingMall.DataBean> data, String token) {
        this.context=context;
        this.data=data;
        this.token=token;
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
            view = View.inflate(context, R.layout.griditem_gift, null);
            holder = new ViewHolder();
            holder.image_jifen_gift= (ImageView) view.findViewById(R.id.image_jifen_gift);
            holder.text_gift_name= (TextView) view.findViewById(R.id.text_gift_name);
            holder.gift_num= (TextView) view.findViewById(R.id.gift_num);
            holder.text_duihuan_item= (TextView) view.findViewById(R.id.text_duihuan_item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text_gift_name.setText(data.get(i).getShopname());
        holder.gift_num.setText(data.get(i).getIntegral());
        holder.image_jifen_gift.setImageResource(R.mipmap.image_jifen_shop);
        //Glide.with(context).load(data.get(i).getShopimg()).into(holder.image_jifen_gift);
        holder.text_duihuan_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        final AlertDialog alertDialog;alertDialog = customizeDialog.create();
        alertDialog.show();
        image_dialog_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        button_dialog_wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCreditsExchange();//积分兑换
                alertDialog.dismiss();
            }
        });
        image_dialog_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
    //积分兑换
    private void getCreditsExchange() {
        Map<String,Object> params=new HashMap<>();
        params.put("token",token);
        for (int i = 0; i <data.size(); i++) {
            params.put("shopid",data.get(i).getId());
            params.put("shopintegral",data.get(i).getIntegral());
        }
        OkHttpHelper.getInstance().postAsyncHttp(100, GlobalParam.CREDITSEXCHANGE,params,new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        String creditsexchangecre= (String) msg.obj;
                        CreditsExchange creditsExchange = JSON.parseObject(creditsexchangecre, CreditsExchange.class);
                        break;
                }
            }
        });

    }
}
