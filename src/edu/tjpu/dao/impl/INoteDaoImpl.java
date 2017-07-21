package edu.tjpu.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import edu.tjpu.note.dao.INoteDao;
import edu.tjpu.note.datasource.SQLManager;

import edu.tjpu.note.po.Note;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

public class INoteDaoImpl implements INoteDao {

	private SQLManager sqlManager = null;

	public INoteDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.sqlManager = new SQLManager();
	}

	public boolean insert(Note note, SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		// 拆分属性

		String title = note.getTitle();
		String content = note.getContent();
		String time = note.getTime();

		// 组织SQL语句
		String strSQL = "insert into notes(title,content,time) values(?,?,?)";
		Object[] bindArgs = new Object[] { title, content, time };

		// 调用sqlManager对象中的方法完成数据库操作
		return this.sqlManager.execWritableBySQL(sqLiteDatabase, strSQL,
				bindArgs);
	}

	public boolean update(Note note, SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		int iid = note.getIid();
		String title = note.getTitle();
		String content = note.getContent();
		String time = note.getTime();
		// 组织SQL语句
		String strSQL = "update notes set title=?,content=?,time=? where iid=? ";
		Object[] bindArgs = new Object[] { title, content, time, iid };

		// 调用sqlManager对象中的方法完成数据库操作
		return this.sqlManager.execWritableBySQL(sqLiteDatabase, strSQL,
				bindArgs);

	}

	@Override
	public List<Note> select1(SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		// 组织SQL语句
		String strSQL = "select * from notes order by iid";
		String[] selectionArgs = new String[] {};

		// 调用sqlManager对象中的方法完成数据库操作
		Cursor cursor = this.sqlManager.execReadableBySQL(sqLiteDatabase,
				strSQL, selectionArgs);

		// ------ 数据库结构的转换------
		// 创建一个空集合
		List<Note> lstNote = new ArrayList<Note>();
		// 使用循环遍历游标数据并封装成Message对象装载到List集合中
		while (cursor.moveToNext()) {
			// 创建一个空的Note对象
			Note note = new Note();
			// 为note对象的属性赋值
			note.setIid(cursor.getInt(0));
			note.setTitle(cursor.getString(1));
			note.setContent(cursor.getString(2));
			note.setTime(cursor.getString(3));

			// 将封装好的message对象添加到集合中
			lstNote.add(note);
		}

		// 返回集合
		return lstNote;
	}

	@Override
	public boolean drop(Note note, SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		String time = note.getTime();
		// 组织SQL语句
		String strSQL = "delete from notes where time=? ";
		Object[] bindArgs = new Object[] { time };

		// 调用sqlManager对象中的方法完成数据库操作
		return this.sqlManager.execWritableBySQL(sqLiteDatabase, strSQL,
				bindArgs);

	}

	@Override
	public boolean updatezhidingiid(Note note, SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		int iid = note.getIid();
		String time = note.getTime();
		// 组织SQL语句
		String strSQL = "update notes set iid=? where time=? ";
		Object[] bindArgs = new Object[] { iid, time };

		// 调用sqlManager对象中的方法完成数据库操作
		return this.sqlManager.execWritableBySQL(sqLiteDatabase, strSQL,
				bindArgs);
	}

	@Override
	public boolean updateiid(SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub

		// 组织SQL语句
		int temp = 20;
		String strSQL = "update notes set iid=iid+? ";
		Object[] bindArgs = new Object[] { temp };

		// 调用sqlManager对象中的方法完成数据库操作
		return this.sqlManager.execWritableBySQL(sqLiteDatabase, strSQL,
				bindArgs);
	}

	@Override
	public List<Note> select2(SQLiteDatabase sqLiteDatabase, Note note) {
		// TODO Auto-generated method stub
		// 组织SQL语句
		String time=note.getTime();
		String strSQL = "select * from notes where time=?";
		String[] selectionArgs = new String[] {time};

		// 调用sqlManager对象中的方法完成数据库操作
		Cursor cursor = this.sqlManager.execReadableBySQL(sqLiteDatabase,
				strSQL, selectionArgs);

		// ------ 数据库结构的转换------
		// 创建一个空集合
		List<Note> lstNote = new ArrayList<Note>();
		// 使用循环遍历游标数据并封装成Message对象装载到List集合中
		while (cursor.moveToNext()) {
			// 创建一个空的Note对象
			Note note2 = new Note();
			// 为note对象的属性赋值
			note2.setIid(cursor.getInt(0));
			note2.setTitle(cursor.getString(1));
			note2.setContent(cursor.getString(2));
			note2.setTime(cursor.getString(3));

			// 将封装好的message对象添加到集合中
			lstNote.add(note2);
		}

		// 返回集合
		return lstNote;
	}

}
