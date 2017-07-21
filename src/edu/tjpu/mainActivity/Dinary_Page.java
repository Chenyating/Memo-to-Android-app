package edu.tjpu.mainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.finalworks.R;

import edu.tjpu.DefineAdapter.CalendarAdapter;
import edu.tjpu.note.biz.DinaryBiz;
import edu.tjpu.note.biz.impl.DinaryBizImpl;
import edu.tjpu.note.po.Dinary;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;

public class Dinary_Page extends Fragment implements View.OnClickListener {

	private GestureDetector gestureDetector = null;
	private edu.tjpu.DefineAdapter.CalendarAdapter calV = null;
	private ViewFlipper flipper = null;
	private GridView gridView = null;
	private static int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
	private static int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;
	private String currentDate = "";
	private int gvFlag = 0;
	private TextView currentMonth;// 当前年月，在日历的顶端
	private ImageView prevMonth;// 当前月份
	private ImageView nextMonth;// 下个月

	public Dinary_Page() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		currentDate = sdf.format(date); // 当期日期
		year_c = Integer.parseInt(currentDate.split("-")[0]); // 将获取年月日以'－'为分界线，分开
		month_c = Integer.parseInt(currentDate.split("-")[1]);
		day_c = Integer.parseInt(currentDate.split("-")[2]);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View dinaryView = inflater.inflate(R.layout.main_switch_dinary,
				container, false);
		return dinaryView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		currentMonth = (TextView) getActivity().findViewById(R.id.currentMonth);
		prevMonth = (ImageView) getActivity().findViewById(R.id.prevMonth);
		nextMonth = (ImageView) getActivity().findViewById(R.id.nextMonth);
		setListener();
		gestureDetector = new GestureDetector(getActivity(),
				new MyGestureListener());
		flipper = (ViewFlipper) getActivity().findViewById(R.id.flipper);
		flipper.removeAllViews();
		calV = new CalendarAdapter(getActivity(), getResources(), jumpMonth,
				jumpYear, year_c, month_c, day_c);
		addGridView();
		gridView.setAdapter(calV);
		flipper.addView(gridView, 0);
		addTextToTopTextView(currentMonth);
	}

	private void addTextToTopTextView(TextView view) {
		StringBuffer textDate = new StringBuffer();
		textDate.append(calV.getShowYear()).append("年")
				.append(calV.getShowMonth()).append("月").append("\t");
		view.setText(textDate);

	}

	private void addGridView() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// 取得屏幕的宽度和高度
		View view = getView();
		int Width = view.getWidth();
		int Height = view.getHeight();

		gridView = new GridView(getActivity());
		gridView.setNumColumns(7);
		gridView.setColumnWidth(40);
		// gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		if (Width == 720 && Height == 1280) {
			gridView.setColumnWidth(40);
		}
		gridView.setGravity(Gravity.CENTER_VERTICAL);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		// 去除gridView边框
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
		gridView.setOnTouchListener(new OnTouchListener() {
			// 将gridview中的触摸事件回传给gestureDetector

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return Dinary_Page.this.gestureDetector.onTouchEvent(event);
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				// 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
				int startPosition = calV.getStartPositon();
				int endPosition = calV.getEndPosition();
				if (startPosition <= position + 7
						&& position <= endPosition - 7) {
					String scheduleDay = calV.getDateByClickItem(position)
							.split("\\.")[0]; // 这一天的阳历
					// String scheduleLunarDay =
					// calV.getDateByClickItem(position).split("\\.")[1];
					// //这一天的阴历
					String scheduleYear = calV.getShowYear();
					String scheduleMonth = calV.getShowMonth();
					// Toast.makeText(getActivity(),scheduleYear + "-" +
					// scheduleMonth + "-" + scheduleDay, 2000).show();
					// 此出加入点击时间啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
					/*
					 * Toast.makeText( getActivity(), scheduleYear + "," +
					 * scheduleMonth+ "," + position ,
					 * Toast.LENGTH_SHORT).show();
					 */
					// time=nian,yue,position;
					String time = scheduleYear + "," + scheduleMonth + ","
							+ position;
					// 查询数据库
					DinaryBiz dinarybiz = new DinaryBizImpl();
					Dinary dinary = new Dinary();
					dinary.setTime(time);
					dinary.setContent("");
					dinary.setImag1("");
					dinary.setImag2("");
					dinary.setImag3("");
					List<Dinary> lisDinary = dinarybiz.find1(getActivity(),
							dinary);
					int listdinarysize = lisDinary.size();

					if (listdinarysize == 0) {
						Intent i = new Intent();
						i.setClass(getActivity(), editDinary.class);
						i.putExtra("time", time);
						startActivity(i);
					}
					if (listdinarysize == 1) {
						Intent i = new Intent();
						i.setClass(getActivity(), editDinary_xiugai.class);
						i.putExtra("time", time);
						startActivity(i);
					}

				}
			}
		});
		gridView.setLayoutParams(params);
	}

	private void setListener() {
		prevMonth.setOnClickListener(this);
		nextMonth.setOnClickListener(this); // 这里可能出错了，this改为getactivity()
	}

	private class MyGestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			int gvFlag = 0; // 每次添加gridview到viewflipper中时给的标记
			// if (e1.getX() - e2.getX() > 120) {
			// // 像左滑动
			// enterNextMonth(gvFlag);
			// return true;
			// } else if (e1.getX() - e2.getX() < -120) {
			// // 向右滑动
			// enterPrevMonth(gvFlag);
			// return true;
			// }
			return false;
		}
	}

	private void enterNextMonth(int gvFlag) {
		addGridView(); // 添加一个gridView
		jumpMonth++; // 下一个月

		calV = new CalendarAdapter(getActivity(), this.getResources(),
				jumpMonth, jumpYear, year_c, month_c, day_c);
		gridView.setAdapter(calV);
		addTextToTopTextView(currentMonth); // 移动到下一月后，将当月显示在头标题中
		gvFlag++;
		flipper.addView(gridView, gvFlag);
		flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
				R.anim.push_left_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
				R.anim.push_left_out));
		flipper.showNext();
		flipper.removeViewAt(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.nextMonth: // 下一个月
			enterNextMonth(gvFlag);
			break;
		case R.id.prevMonth: // 上一个月
			enterPrevMonth(gvFlag);
			break;
		default:
			break;
		}

	}

	private void enterPrevMonth(int gvFlag) {
		addGridView(); // 添加一个gridView
		jumpMonth--; // 上一个月

		calV = new CalendarAdapter(getActivity(), this.getResources(),
				jumpMonth, jumpYear, year_c, month_c, day_c);
		gridView.setAdapter(calV);
		gvFlag++;
		addTextToTopTextView(currentMonth); // 移动到上一月后，将当月显示在头标题中
		flipper.addView(gridView, gvFlag);

		flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
				R.anim.push_right_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
				R.anim.push_right_out));
		flipper.showPrevious();
		flipper.removeViewAt(0);

	}

}
