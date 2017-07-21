package edu.tjpu.mainActivity;

import java.util.List;
import java.util.zip.Inflater;

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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class searchpassword extends Fragment {
	private Button btn_search_submit; // 提交按钮
	private EditText et_search_repsw1;// 密码
	private EditText et_search_repsw2;// 重复密码
	private EditText et_search_answer;// 问题答案
	private String info;
	private Spinner spin;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.searchpassword, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		btn_search_submit = (Button) getActivity().findViewById(R.id.btn_search_submit);
		et_search_answer = (EditText) getActivity().findViewById(R.id.et_serach_answer);
		et_search_repsw1 = (EditText) getActivity().findViewById(R.id.et_search_repsw1);
		et_search_repsw2 = (EditText) getActivity().findViewById(R.id.et_search_repsw2);
		info = "我的姓名是？";
		spin = (Spinner) getActivity().findViewById(R.id.sp_search_problem);
		spin.setOnItemSelectedListener(new ProvOnItemSelectedListener());
		btn_search_submit.setOnClickListener(new ViewOCL());
	}

	private class ViewOCL implements View.OnClickListener {
		IMessageBiz messageBiz = new MessageBizImpl();

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_search_submit:
				//  从数据库中读取问题和答案，判断条件，修改其中的密码,并存放到application中
				List<Message> lstMessage = messageBiz.find(getActivity());
				Message user = lstMessage.get(0);
				String str1 = user.getProblem();
				String str2 = user.getAnswer();
				String password1 = et_search_repsw1.getText().toString();
				String password2 = et_search_repsw2.getText().toString();
				String answer = et_search_answer.getText().toString();

				if (!str1.equals(info)) {
					Toast.makeText(getActivity(),
							"您选择的问题与您注册时设定的问题不一致", Toast.LENGTH_SHORT).show();
				}
				if (!str2.equals(answer)) {
					Toast.makeText(getActivity(), "您填写的答案不正确",
							Toast.LENGTH_SHORT).show();

				}
				if (password1.length() < 6) {
					Toast.makeText(getActivity(), "密码必须大于六位",
							Toast.LENGTH_SHORT).show();
				}
				if (!password1.equals(password2)) {
					Toast.makeText(getActivity(), "两次输入的密码不一致",
							Toast.LENGTH_SHORT).show();
				}
				if (str1.equals(info) && str2.equals(answer)
						&& password1.length() >= 6
						&& password1.equals(password2)) {
					Message message = new Message();
					message.setPassword(password1);
					messageBiz.alter(getActivity(), message);
					Toast.makeText(getActivity(), "恭喜您，操作成功",
							Toast.LENGTH_SHORT).show();

					Intent in = new Intent();
					in.setClass(getActivity(), main_menu.class);
					startActivity(in);
				}
				break;

			default:
				break;
			}
		}
	}

	private class ProvOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> adapter, View view,
				int position, long id) {
			info = adapter.getItemAtPosition(position).toString();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}
}
