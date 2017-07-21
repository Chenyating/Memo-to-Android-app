package edu.tjpu.mainActivity;

import com.example.finalworks.R;

import edu.tjpu.ResideMenu.ResideMenu;
import edu.tjpu.ResideMenu.ResideMenuItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class main_menu extends FragmentActivity implements View.OnClickListener {

	private edu.tjpu.ResideMenu.ResideMenu resideMenu;
	private edu.tjpu.ResideMenu.ResideMenuItem item_change;
	private edu.tjpu.ResideMenu.ResideMenuItem item_search;
	private edu.tjpu.ResideMenu.ResideMenuItem item_lianxi;
	private main_menu mContext;
	private Button btn_menu_left;
	private Button btn_menu_right;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_menu);
		mContext = this;
		setUpMenu();
	if (savedInstanceState == null) {
		changeFragment(new login());
	}
		
		btn_menu_left = (Button) findViewById(R.id.title_bar_left_menu);
		btn_menu_right = (Button) findViewById(R.id.title_bar_right_menu);
		
		btn_menu_left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		
		btn_menu_right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				changeFragment(new login());
			}
		});
		
	}

	private void setUpMenu() {
		resideMenu = new ResideMenu(this);
		resideMenu.setUse3D(true);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setScaleValue(0.6f);
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		item_change = new ResideMenuItem(this, R.drawable.icon_home, "修改密码");
		item_search = new ResideMenuItem(this, R.drawable.icon_profile,
				"找回密码");
		item_lianxi = new ResideMenuItem(this,R.drawable.icon_settings,"联系我们");

		item_change.setOnClickListener(this);
		item_search.setOnClickListener(this);
		item_lianxi.setOnClickListener(this);

		resideMenu.addMenuItem(item_change, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(item_search, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(item_lianxi, ResideMenu.DIRECTION_LEFT);
	}

	@Override
	public void onClick(View view) {
		if (view == item_change) {
			changeFragment(new changepassword());
		} else if (view == item_search) {
			changeFragment(new searchpassword());
		}
		else if(view == item_lianxi){
			changeFragment(new  Connect_Us());
		}
		resideMenu.closeMenu();
	}

	private void changeFragment(Fragment targetFragment) {
		resideMenu.clearIgnoredViewList();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_fragment, targetFragment, "fragment")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			System.exit(0);
		}
		return false;
	}

}
