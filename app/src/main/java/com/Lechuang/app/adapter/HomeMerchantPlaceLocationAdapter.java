package com.Lechuang.app.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Lechuang.app.R;

import java.util.List;
import java.util.Map;


/**
 * Created by Android on 2017/5/10.
 */

public class HomeMerchantPlaceLocationAdapter extends BaseAdapter {

    private List<Map<String,String>> list;
    private Context context;
    private Handler handler;
    public HomeMerchantPlaceLocationAdapter(Context context,Handler handler) {
        this.context = context;
        this.handler = handler;
    }
    public void setData(List<Map<String,String>> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        ViewHodler hodler = null;
        if (convertView == null) {
            hodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.mypetaddlocation_listitem, null);
            hodler.name = (TextView) convertView.findViewById(R.id.name);
            hodler.phone = (TextView) convertView.findViewById(R.id.phone);
            hodler.address = (TextView) convertView.findViewById(R.id.address);
            hodler.compile = (LinearLayout) convertView.findViewById(R.id.compile);
            hodler.delete = (LinearLayout) convertView.findViewById(R.id.delete);
            hodler.opt_text = (TextView) convertView.findViewById(R.id.opt_text);
            hodler.opt_image = (ImageView) convertView.findViewById(R.id.opt_image);
            hodler.type = (LinearLayout) convertView.findViewById(R.id.type);
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        Map map = list.get(position);
        String recive_name = (String) map.get("name");
        hodler.name.setText(recive_name);

        String phone = (String) map.get("phone");
        hodler.phone.setText(phone);

        String address = (String) map.get("address");
        hodler.address.setText(address);

        String status = (String) map.get("status");
        if ("1".equals(status)){
            hodler.opt_text.setTextColor(context.getResources().getColor(R.color.red));
            hodler.opt_image.setBackgroundResource(R.mipmap.red_radio);
        }else {
            hodler.opt_text.setTextColor(context.getResources().getColor(R.color.black_99));
            hodler.opt_image.setBackgroundResource(R.mipmap.white_radio);
        }
        final String id = (String) map.get("id");
        hodler.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = handler.obtainMessage();
                message.obj = id;
                message.arg1 = position;
                message.what = 1;
                handler.sendMessage(message);
            }
        });
        hodler.compile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = handler.obtainMessage();
                message.obj = id;
                message.arg1 = position;
                message.what = 3;
                handler.sendMessage(message);
            }
        });
        hodler.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = handler.obtainMessage();
                message.obj = id;
                message.arg1 = position;
                message.what = 4;
                handler.sendMessage(message);
            }
        });
        return convertView;
    }

    class ViewHodler {
        private  TextView name,phone,address,opt_text;
        private ImageView opt_image;
        private LinearLayout type,compile,delete;
    }
}
