package com.Lechuang.app.view;


import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.Lechuang.app.Bean.PetMessageInfo;
import com.Lechuang.app.adapter.ParentCategoryAdapter;

import java.util.List;

import www.xcd.com.mylibrary.R;

/**
 * 选择PopupWindow
 * @author ansen
 * @create time 2015-10-09
 */
public class SelectPopupWindow extends PopupWindow{
	private SelectCategory selectCategory;
	
	private  List<PetMessageInfo.PetMessageData> parentStrings;
	private ListView lvParentCategory = null;
	private ListView lvChildrenCategory= null;
	private ParentCategoryAdapter parentCategoryAdapter = null;
	
	/**
	 * @param parentStrings   字类别数据
	 * @param activity
	 * @param selectCategory  回调接口注入
	 */
    public SelectPopupWindow(List<PetMessageInfo.PetMessageData> parentStrings, Activity activity, SelectCategory selectCategory) {
    		this.selectCategory=selectCategory;
    		this.parentStrings=parentStrings;
    		View contentView = LayoutInflater.from(activity).inflate(R.layout.layout_quyu_choose_view, null);
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取手机屏幕的大小
    		
    		this.setContentView(contentView);
    		this.setWidth(dm.widthPixels);
    		this.setHeight(dm.heightPixels*7/10);
    		
		/* 设置背景显示 */
		setBackgroundDrawable(activity.getDrawable(R.drawable.shape_custom_dialog_bg));
		/* 设置触摸外面时消失 */
		setOutsideTouchable(true);
		setTouchable(true);
		setFocusable(true); /*设置点击menu以外其他地方以及返回键退出 */
		
		/**
		 * 1.解决再次点击MENU键无反应问题
		 */
		contentView.setFocusableInTouchMode(true);
		
		//父类别适配器
		lvParentCategory= (ListView) contentView.findViewById(R.id.lv_parent_category);
		parentCategoryAdapter = new ParentCategoryAdapter(activity,parentStrings);
		lvParentCategory.setAdapter(parentCategoryAdapter);
		lvParentCategory.setOnItemClickListener(parentItemClickListener);
    }

    /**
     * 父类别点击事件
     */
    private OnItemClickListener parentItemClickListener=new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			if(selectCategory!=null){
				selectCategory.selectCategory(position);
			}
			Log.e("TAG_","position="+position);
			parentCategoryAdapter.setSelectedPosition(position);
			parentCategoryAdapter.notifyDataSetChanged();
			dismiss();
		}
	};
	
	/**
	 * 选择成功回调
	 * @author apple
	 *
	 */
	public interface SelectCategory{
		/**
		 * 把选中的下标通过方法回调回来
		 * @param parentSelectposition  父类别选中下标
		 */
		public void selectCategory(int parentSelectposition);
	}
    
}
