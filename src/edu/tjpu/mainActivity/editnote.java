package edu.tjpu.mainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.finalworks.R;

import edu.tjpu.note.biz.INoteBiz;
import edu.tjpu.note.biz.impl.NoteBizImpl;

import edu.tjpu.note.datasource.SQLiteDatabaseHelper;
import edu.tjpu.note.po.Note;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class editnote extends Activity {
	TextView tv_time;
	EditText et_editnote, edittitle;
	private ActionBar actionBar;

	private Cursor cursor;
	private String fmt_date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editnote);

		tv_time = (TextView) findViewById(R.id.tv_time);
		et_editnote = (EditText) findViewById(R.id.et_editnote);
		edittitle = (EditText) findViewById(R.id.edittitle);
		actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false); // 标题栏的图标
		actionBar.setDisplayHomeAsUpEnabled(true); // 显示后退键

		Date date = new Date();
		fmt_date = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(date);
		tv_time.setText(fmt_date + "");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_editnote_acbar_xin, menu);
		return super.onCreateOptionsMenu(menu);
	}

	private void addShortCut() {
		Intent i = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		i.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				Intent.ShortcutIconResource.fromContext(this, R.drawable.icon));
		i.putExtra(Intent.EXTRA_SHORTCUT_NAME, edittitle.getText().toString());// String类型，123123这里放事务的标题
		Intent in = new Intent(editnote.this, editnote_zhuomian.class);// 需要跳转的页面
		in.putExtra("title", edittitle.getText().toString());
		in.putExtra("content", et_editnote.getText().toString());
		in.putExtra("time", fmt_date);
		i.putExtra(Intent.EXTRA_SHORTCUT_INTENT, in);// 开始启动

		sendBroadcast(i);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.submit:
			// 完成

			INoteBiz notebiz1 = new NoteBizImpl();
			Note note = new Note();
			note.setTitle(edittitle.getText().toString());
			note.setContent(et_editnote.getText().toString());
			note.setTime(fmt_date + "");
			notebiz1.add(editnote.this, note);
			finish();
			break;

		case R.id.fasong:

			INoteBiz notebiz4 = new NoteBizImpl();
			Note note4 = new Note();
			note4.setTitle(edittitle.getText().toString());
			note4.setContent(et_editnote.getText().toString());
			note4.setTime(fmt_date + "");
			notebiz4.add(editnote.this, note4);
            
			addShortCut();
			finish();
			break;

		case R.id.zhiding:
			INoteBiz notebiz3 = new NoteBizImpl();
			Note note2 = new Note();
			note2.setTitle(edittitle.getText().toString());
			note2.setContent(et_editnote.getText().toString());
			note2.setTime(fmt_date + "");
			notebiz3.add(editnote.this, note2);

			List<Note> lstNote = notebiz3.find1(getApplicationContext());
			int newid = lstNote.get(0).getIid() - 1;
			Note note3 = new Note();
			note3.setIid(newid);
			note3.setTime(fmt_date + "");
			notebiz3.alterzhiding(getApplicationContext(), note3);

			finish();
			break;

		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}