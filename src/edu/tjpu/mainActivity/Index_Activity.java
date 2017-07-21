package edu.tjpu.mainActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.finalworks.R;

import edu.tjpu.note.biz.IMessageBiz;
import edu.tjpu.note.biz.impl.MessageBizImpl;
import edu.tjpu.note.po.Message;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class Index_Activity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//
		setContentView(R.layout.index);
		Timer timer = new Timer();

		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				IMessageBiz messageBiz = new MessageBizImpl();
				List<Message> lstMessage = messageBiz.find(Index_Activity.this);
				Intent in = new Intent(Intent.ACTION_MAIN);
				in.addCategory(Intent.CATEGORY_HOME);
				int str = lstMessage.size();
				if (str == 0) {
					in.setClass(Index_Activity.this, register.class);
					startActivity(in);
					finish();
				} else {
					in.setClass(Index_Activity.this, main_menu.class);
					startActivity(in);
					finish();
				}
			}
		};
		timer.schedule(tt, 3000);
	}
}
