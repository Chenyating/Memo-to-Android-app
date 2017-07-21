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

public class editnote_zhuomian extends Activity {
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
		String newtime = intent.getStringExtra("time");

		Note newnote = new Note();
		newnote.setTime(newtime);

		INoteBiz notebiz1 = new NoteBizImpl();
		List<Note> lstNote = notebiz1.find2(editnote_zhuomian.this, newnote);

		title = lstNote.get(0).getTitle();
		content = lstNote.get(0).getContent();
		time = lstNote.get(0).getTime();
		id = lstNote.get(0).getIid();

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
		menuInflater.inflate(R.menu.menu_editnote_acbar_zhuo, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}