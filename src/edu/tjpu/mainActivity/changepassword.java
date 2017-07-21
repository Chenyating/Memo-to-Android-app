package edu.tjpu.mainActivity;

import java.util.List;

import com.example.finalworks.R;

import edu.tjpu.note.biz.IMessageBiz;
import edu.tjpu.note.biz.impl.MessageBizImpl;
import edu.tjpu.note.po.Message;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changepassword extends Fragment {

	private Button btn_changetologin;
	private EditText et_oldpassword, et_newpassword_one, et_newpassword_two;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub	
		return inflater.inflate(R.layout.changepassword, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		btn_changetologin = (Button) getActivity().findViewById(R.id.btn_changetologin);
		et_oldpassword = (EditText) getActivity().findViewById(R.id.et_oldpassword);
		et_newpassword_one = (EditText) getActivity().findViewById(R.id.et_newpassword_one);
		et_newpassword_two = (EditText) getActivity().findViewById(R.id.et_newpassword_two);
		btn_changetologin.setOnClickListener(new ViewOCL());
	}

	private class ViewOCL implements View.OnClickListener {
		IMessageBiz messageBiz = new MessageBizImpl();

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_changetologin:
				// 判断数据库的密码是否相等
				List<Message> lstMessage = messageBiz.find(getActivity());
				Message user = lstMessage.get(0);
				String str1 = user.getPassword();
				String strold = et_oldpassword.getText().toString(); // 老密码
				String strnew_one = et_newpassword_one.getText().toString();
				String strnew_two = et_newpassword_two.getText().toString();
				if (!str1.equals(strold)) {
//					Toast.makeText(getApplicationContext(), "旧密码输入错误",
//							Toast.LENGTH_SHORT).show();
				}
				if (!strnew_one.equals(strnew_two)) {
//					Toast.makeText(getApplicationContext(), "两次密码不一致，请确认",
//							Toast.LENGTH_SHORT).show();
				}
				if (strnew_one.length() < 6) {
//					Toast.makeText(getApplicationContext(), "新密码长度必须大于等于6位",
//							Toast.LENGTH_SHORT).show();
				}
				if (strnew_one.equals(strnew_two) && strnew_one.length() > 5
						&& str1.equals(strold)) { 
					//修改密码
					Message message = new Message();
					message.setPassword(strnew_one);
					messageBiz.alter(getActivity(), message);
//					Toast.makeText(getApplicationContext(), "恭喜你,修改成功",
//							Toast.LENGTH_SHORT).show();
					Intent in = new Intent(getActivity(), main_menu.class);
					startActivity(in);

				}

				break;

			default:
				break;
			}
		}

	}

}
