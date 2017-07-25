package com.Lechuang.app.ui.pickerview.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wudl
 * @date Created at 2016/4/21.
 */
public class DataWheelAdapter<T> implements WheelAdapter {
    private List<T> list = new ArrayList<>();

    public DataWheelAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public Object getItem(int index) {
        return list.get(index);
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0;i<list.size();i++){
            if (list.get(i).equals(o)){
                return i;
            }
        }
        return 0;
    }
}
