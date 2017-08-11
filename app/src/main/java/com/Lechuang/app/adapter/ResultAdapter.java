package com.Lechuang.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Lechuang.app.R;


/**
 * Created by Beyond on 17/10/2016.
 */

public class ResultAdapter extends BaseAdapter {
    private String[] mDataSet;
    private Context context;

    public ResultAdapter(Context context) {
        this.context = context;

    }

    public void setData(String[] mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public int getCount() {
        return mDataSet.length;
    }

    @Override
    public Object getItem(int position) {
        return mDataSet[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ResultAdapter.ViewHodler hodler = null;
        if (convertView == null) {
            hodler = new ResultAdapter.ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.result_item, null);
            hodler.id_num = (TextView) convertView.findViewById(R.id.id_num);
            convertView.setTag(hodler);
        } else {
            hodler = (ResultAdapter.ViewHodler) convertView.getTag();
        }

        hodler.id_num.setText(mDataSet[position]);
        return convertView;
    }

    class ViewHodler {
        private TextView id_num;
    }

}

