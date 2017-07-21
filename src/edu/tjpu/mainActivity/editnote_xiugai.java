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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class editnote_xiugai extends Activity {
	TextView tv_time;
	EditText et_editnote, edittitle;
	private ActionBar actionBar;

	private Cursor cursor;
	private String fmt_date;
	private int position;
	private int id;
	private String title, content, time;

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

		Intent intent = getIntent();
		int position = intent.getIntExtra("position", 0);

		INoteBiz notebiz1 = new NoteBizImpl();
		List<Note> lstNote = notebiz1.find1(editnote_xiugai.this);
		title = lstNote.get(position).getTitle();
		content = lstNote.get(position).getContent();
		time = lstNote.get(position).getTime();
		id = lstNote.get(position).getIid();

		et_editnote.setText(content);

		edittitle.setText(title);

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
		Intent in = new Intent(editnote_xiugai.this, editnote_zhuomian.class);// 需要跳转的页面
		in.putExtra("title", edittitle.getText().toString());
		in.putExtra("content", et_editnote.getText().toString());
		in.putExtra("time", time);
		i.putExtra(Intent.EXTRA_SHORTCUT_INTENT, in);// 开始启动

		sendBroadcast(i);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.submit:
			// 完成

			if (!edittitle.getText().toString().equals(title)
					|| !et_editnote.getText().toString().equals(content)) {
				// time = fmt_date;

				INoteBiz notebiz2 = new NoteBizImpl();
				Note note1 = new Note();
				note1.setIid(id);
				note1.setContent(et_editnote.getText().toString());
				note1.setTime(time);
				note1.setTitle(edittitle.getText().toString());
				notebiz2.remove(getApplicationContext(), note1);
				// notebiz2.alter(editnote_xiugai.this, note);
				Note note2 = new Note();
				note2.setIid(id);
				note2.setContent(et_editnote.getText().toString());
				note2.setTime(fmt_date);
				note2.setTitle(edittitle.getText().toString());
				notebiz2.add(getApplicationContext(), note2);
			}
			finish();
			break;
		case R.id.fasong:

			if (!edittitle.getText().toString().equals(title)
					|| !et_editnote.getText().toString().equals(content)) {
				// time = fmt_date;

				INoteBiz notebiz2 = new NoteBizImpl();
				Note note1 = new Note();
				note1.setIid(id);
				note1.setContent(et_editnote.getText().toString());
				note1.setTime(time);
				note1.setTitle(edittitle.getText().toString());
				notebiz2.remove(getApplicationContext(), note1);
				// notebiz2.alter(editnote_xiugai.this, note);
				Note note2 = new Note();
				note2.setIid(id);
				note2.setContent(et_editnote.getText().toString());
				note2.setTime(fmt_date);
				note2.setTitle(edittitle.getText().toString());
				notebiz2.add(getApplicationContext(), note2);
				time=fmt_date;
			}
			addShortCut();
			finish();
			break;

		case R.id.zhiding:

			INoteBiz notebiz3 = new NoteBizImpl();
			List<Note> lstNote = notebiz3.find1(getApplicationContext());
			int newid = lstNote.get(0).getIid() - 1;
			Note note3 = new Note();

			note3.setIid(newid);
			note3.setTime(time);
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