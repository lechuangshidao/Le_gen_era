package com.Lechuang.app.func;

import android.app.Activity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.func.BaseTopImageBtnFunc;


public class CommonBackTopBtnFunc_shouye extends BaseTopImageBtnFunc {

	public CommonBackTopBtnFunc_shouye(Activity activity) {
		super(activity);
	}

	@Override
	public int getFuncId() {
		return R.id.topbar_left_shouye;
	}
	@Override
	public int getFuncIcon() {
		return R.mipmap.image_news;
	}

	@Override
	public void onclick(View v) {
		Map<String, Boolean> map = new HashMap<>();
//		Log.e("TAG_", "name=" + Conversation.ConversationType.PRIVATE.getName());
		map.put(Conversation.ConversationType.PRIVATE.getName(), false); // 会话列表需要显示私聊会话, 第二个参数 true 代表私聊会话需要聚合显示
		RongIM.getInstance().startConversationList(getActivity(), map);
	}

}
