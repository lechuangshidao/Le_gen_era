package com.Lechuang.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.Bean.PetMessageInfo;
import com.Lechuang.app.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import static com.Lechuang.app.R.id.pet_head;

/**
 * Created by Android on 2017/5/18.
 */
public class PetMessageAdapter extends BaseAdapter {
    private Context context;
    private List<PetMessageInfo> list;

    public PetMessageAdapter(Context context, List<PetMessageInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHodler hodler = null;
        if (convertView == null) {
            hodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.petmessageadapter_item, null);
            hodler.pet_name = (TextView) convertView.findViewById(R.id.pet_name);
            hodler.pet_breed = (TextView) convertView.findViewById(R.id.pet_breed);
            hodler.pet_age = (TextView) convertView.findViewById(R.id.pet_age);
            hodler.pet_label = (TextView) convertView.findViewById(R.id.pet_label);
            hodler.pet_compile = (TextView) convertView.findViewById(R.id.pet_compile);
            hodler.pet_head = (ImageView) convertView.findViewById(pet_head);
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        PetMessageInfo result = list.get(position);
        String name = result.getName();
        hodler.pet_name.setText((name==null)?("未知"+position):name);

        String breed = result.getBreed();
        hodler.pet_breed.setText("宠物品种："+((breed==null)?("未知"+position):breed));

        String age = result.getAge();
        hodler.pet_age.setText("宠物年龄："+((age==null)?("未知"+position):age));
        String label = result.getLabel();
        hodler.pet_label.setText("宠物标签："+((label==null)?("未知"+position):label));
        String imgurl = result.getPetheadurl();
        Glide.with(context.getApplicationContext())
                .load(imgurl)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.pethead)
                .error(R.mipmap.pethead)
                .into(hodler.pet_head);

        return convertView;
    }

    class ViewHodler {
        private TextView pet_name, pet_breed, pet_age,pet_label,pet_compile;
        private ImageView pet_head;
    }
}
