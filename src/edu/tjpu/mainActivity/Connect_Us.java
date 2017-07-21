package edu.tjpu.mainActivity;

import java.util.List;

import com.example.finalworks.R;

import edu.tjpu.note.biz.IMessageBiz;
import edu.tjpu.note.biz.impl.MessageBizImpl;
import edu.tjpu.note.po.Message;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connect_Us extends Fragment {
	private EditText neirong, phonenumber;
	private Button tijiao, callus;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.connect_us, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		neirong = (EditText) getActivity().findViewById(R.id.neirong);
		phonenumber = (EditText) getActivity().findViewById(R.id.phonenumber);
		tijiao = (Button) getActivity().findViewById(R.id.tijiao);
		callus = (Button) getActivity().findViewById(R.id.callus);

		// 电话
		/*
		 * callus.setOnClickListener(new View.OnClickListener(){
		 * 
		 * public void onClick(View arg0) { String number = "15222737323";
		 * Intent intent = new
		 * Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));
		 * startActivity(intent); Connect_Us.this.startActivity(intent); }
		 * 
		 * });
		 */

		tijiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String phone_number = "15222737323";
				String sms_content = neirong.getText().toString().trim();
				SmsManager smsManager = SmsManager.getDefault();
				if (sms_content.length() > 0) {
					List<String> contents = smsManager
							.divideMessage(sms_content);
					for (String sms : contents) {
						smsManager.sendTextMessage(phone_number, null, sms,
								null, null);
					}
					Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT)
							.show();
				} else {
					if (sms_content.length() == 0) {
						Toast.makeText(getActivity(), "請輸入內容",
								Toast.LENGTH_SHORT).show();
					}
				}

			}

		});
		callus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// String phone_number = null;
				String phone_number = phonenumber.getText().toString().trim();

				IMessageBiz messageBiz = new MessageBizImpl();
				List<Message> lstMessage = messageBiz.find(getActivity());
				String str1 = lstMessage.get(0).getPassword();
                str1="感谢您使用WingsNote，您的密码是:"+str1;
				SmsManager smsManager = SmsManager.getDefault();
				if (phone_number.length() == 11) {
					List<String> contents = smsManager
							.divideMessage(str1);
					for (String sms : contents) {
						smsManager.sendTextMessage(phone_number, null, sms,
								null, null);
					}
					Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(getActivity(), "请输入正确的电话号码", Toast.LENGTH_SHORT)
							.show();
				}

			}

		});

	}
}
