package edu.tjpu.note.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

	// 构造方法用于自动构建数据库，通过调用时传入数据库名称参数自动完成
	public SQLiteDatabaseHelper(Context context, CursorFactory factory) {
		super(context, "WingsNote", factory, 1);
		// TODO Auto-generated constructor stub
	}

	// 构造方法重载：当需要实现数据库更新时，使用该重载方法连接数据库
	public SQLiteDatabaseHelper(Context context) {
		super(context, "WingsNote", null, 1);
		// TODO Auto-generated constructor stub
	}

	// 该方法用于创建数据库对象（表、约束、索引……）
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		// 编写创建数据表的sql语句
		String sql1 = "create table user(mid integer primary key autoincrement,"
				+ " username varchar(20) , "
				+ "password varchar(20) , "
				+ "problem varchar(100),"
				+ "answer varchar(100),"
				+ "photo text)";

		// 执行sql语句
		sqLiteDatabase.execSQL(sql1);
		
		String sql2 = "create table notes(iid integer primary key autoincrement,"
				+ "title text," + "content text,time text)";
		sqLiteDatabase.execSQL(sql2);
		
		String sql3="create table dinary(time text primary key,"
				+ "content text," + "imag1 text,imag2 text,imag3 text)";
		sqLiteDatabase.execSQL(sql3);

	}

	// 该方法主要用与数据库更新使用
	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int arg1, int arg2) {

	}

}
