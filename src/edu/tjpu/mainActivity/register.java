package edu.tjpu.mainActivity;

import java.util.List;

import com.example.finalworks.R;

import edu.tjpu.note.biz.IMessageBiz;
import edu.tjpu.note.biz.impl.MessageBizImpl;
import edu.tjpu.note.po.Message;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class register extends Activity {

	private Button btn_register;

	private EditText et_password_one, et_password_two;
	private EditText et_register_answer;
	private String info;
	private Spinner spin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		et_register_answer = (EditText) findViewById(R.id.et_register_answer);
		et_password_one = (EditText) findViewById(R.id.et_setpassword_one);
		et_password_two = (EditText) findViewById(R.id.et_setpassword_two);
		btn_register = (Button) findViewById(R.id.btn_register);
		info = "我的姓名是？";
		this.spin = (Spinner) findViewById(R.id.sp_register_problem);
		this.spin.setOnItemSelectedListener(new ProvOnItemSelectedListener());

		btn_register.setOnClickListener(new ViewOCL());

	}

	private class ViewOCL implements View.OnClickListener {

		IMessageBiz messageBiz = new MessageBizImpl();
		

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_register:
				String psw1 = et_password_one.getText().toString();
				String psw2 = et_password_two.getText().toString();
				String as = et_register_answer.getText().toString().trim();

				if (psw1.equals(psw2) && as != "" && psw1.length() > 5) {
					Message message = new Message();
					message.setUsername("ACCOUNT");
					message.setPassword(psw1);
					message.setProblem(info);
					message.setAnswer(as);
					messageBiz.add(register.this, message);
					Toast.makeText(getApplicationContext(), "恭喜您，注册成功",
								Toast.LENGTH_SHORT).show();
					Intent i = new Intent();
					i.setClass(register.this, main_menu.class);
					startActivity(i);
				 
				}
				if (!psw1.equals(psw2)) {
					Toast.makeText(getApplicationContext(), "两次输入的密码不一致",
							Toast.LENGTH_LONG).show();
				}
				if (as == "") {
					Toast.makeText(getApplicationContext(), "验证问题不能为空",
							Toast.LENGTH_LONG).show();
				}
				if (psw1.length() <= 5) {
					Toast.makeText(getApplicationContext(), "密码必须为6为以上",
							Toast.LENGTH_LONG).show();
				}
				break;

			default:
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unused")
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
