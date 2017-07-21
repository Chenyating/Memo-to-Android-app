package edu.tjpu.mainActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.finalworks.R;

import edu.tjpu.DefineAdapter.SwitchFragmentAdapter;
import edu.tjpu.note.biz.INoteBiz;
import edu.tjpu.note.biz.impl.NoteBizImpl;
import edu.tjpu.note.po.Note;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main_Activity extends FragmentActivity {

	private ViewPager myViewPager;
	private List<Fragment> myFragmentList = new ArrayList<Fragment>();
	private edu.tjpu.DefineAdapter.SwitchFragmentAdapter sfAdapter;
	private TextView tv_TabNote, tv_TabDinary;
	private ImageView iv_TabLine;
	private Note_Page fg_note;
	private Dinary_Page fg_dinary;
	private int currentIndex;
	private int screenWidth;
	private long exitTime;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainactivity);

		tv_TabDinary = (TextView) this.findViewById(R.id.id_dinary_tv);
		tv_TabNote = (TextView) this.findViewById(R.id.id_note_tv);
		iv_TabLine = (ImageView) this.findViewById(R.id.id_tab_line_iv);
		myViewPager = (ViewPager) this.findViewById(R.id.id_page_vp);

		init();
		initLineWidth();
	}

	private void init() {
		// TODO Auto-generated method stub
		fg_dinary = new Dinary_Page();
		fg_note = new Note_Page();
		myFragmentList.add(fg_dinary);
		myFragmentList.add(fg_note);
		sfAdapter = new SwitchFragmentAdapter(this.getSupportFragmentManager(),
				myFragmentList);
		myViewPager.setAdapter(sfAdapter);
		myViewPager.setCurrentItem(0);
		myViewPager.setOnPageChangeListener(new OPCL());
	}

	private class OPCL implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int position, float offset, int offsetPixels) {
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_TabLine
					.getLayoutParams();
			if (currentIndex == 0 && position == 0)// 0->1
			{
				lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
						* (screenWidth / 2));
			} else if (currentIndex == 1 && position == 0) // 1->0
			{
				lp.leftMargin = (int) (-(1 - offset) * (screenWidth * 1.0 / 2) + currentIndex
						* (screenWidth / 2));
			}
			iv_TabLine.setLayoutParams(lp);
		}

		public void onPageSelected(int position) {
			resetTextView();
			switch (position) {
			case 0:
				tv_TabDinary.setTextColor(Color.parseColor("#22ac38"));
				tv_TabDinary.setBackgroundResource(R.drawable.white_background);

				tv_TabNote.setTextColor(Color.parseColor("#FFFFFF"));
				tv_TabNote.setBackgroundResource(R.drawable.blue_background);
				break;
			case 1:
				tv_TabNote.setTextColor(Color.parseColor("#22ac38"));
				tv_TabNote.setBackgroundResource(R.drawable.white_background);

				tv_TabDinary.setTextColor(Color.parseColor("#FFFFFF"));
				tv_TabDinary.setBackgroundResource(R.drawable.blue_background);
				break;
			}
			currentIndex = position;
		}

	}

	private void resetTextView() {
		// TODO Auto-generated method stub
		tv_TabDinary.setTextColor(Color.WHITE);
		tv_TabNote.setTextColor(Color.parseColor("#12bf75"));
	}

	private void initLineWidth() {
		DisplayMetrics dpMetrics = new DisplayMetrics();
		getWindow().getWindowManager().getDefaultDisplay()
				.getMetrics(dpMetrics);
		screenWidth = dpMetrics.widthPixels;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_TabLine
				.getLayoutParams();
		lp.width = screenWidth / 2;
		iv_TabLine.setLayoutParams(lp);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				// 应用名
				String applicationName = getResources().getString(
						R.string.app_name);
				String msg = "再按一次返回键回到桌面";
				Toast.makeText(this, msg, 0).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				Intent home = new Intent(Intent.ACTION_MAIN);
				home.addCategory(Intent.CATEGORY_HOME);
				startActivity(home);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
