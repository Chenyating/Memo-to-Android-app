package edu.tjpu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import edu.tjpu.note.dao.DinaryDao;
import edu.tjpu.note.datasource.SQLManager;
import edu.tjpu.note.po.Dinary;


public class DinaryDaoImpl implements DinaryDao {

	private SQLManager sqlManager = null;

	public DinaryDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.sqlManager = new SQLManager();
	}

	@Override
	public boolean insert(Dinary dinary, SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub

		String time = dinary.getTime();
		String content = dinary.getContent();
		String imag1 = dinary.getImag1();
		String imag2 = dinary.getImag2();
		String imag3 = dinary.getImag3();

		// 组织SQL语句
		String strSQL = "insert into dinary(time,content,imag1, imag2, imag3) values(?,?,?,?,?)";
		Object[] bindArgs = new Object[] { time, content, imag1, imag2, imag3 };

		// 调用sqlManager对象中的方法完成数据库操作
		return this.sqlManager.execWritableBySQL(sqLiteDatabase, strSQL,
				bindArgs);
	}

	@Override
	public boolean update(Dinary dinary, SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		String time = dinary.getTime();
		String content =dinary.getContent();
		String imag1 = dinary.getImag1();
		String imag2 = dinary.getImag2();
		String imag3 = dinary.getImag3();
		// 组织SQL语句
		String strSQL = "update dinary set content=?,imag1=?,imag2=?,imag3=? where time=? ";
		Object[] bindArgs = new Object[] { content,imag1,imag2,imag3,time};

		// 调用sqlManager对象中的方法完成数据库操作
		return this.sqlManager.execWritableBySQL(sqLiteDatabase, strSQL,
				bindArgs);
	}

	@Override
	public boolean drop(Dinary dinary, SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		String time = dinary.getTime();
		// 组织SQL语句
		String strSQL = "delete from dinary where time=? ";
		Object[] bindArgs = new Object[] {time};

		// 调用sqlManager对象中的方法完成数据库操作
		return this.sqlManager.execWritableBySQL(sqLiteDatabase, strSQL,
				bindArgs);
	}

	@Override
	public List<Dinary> select1(Dinary dinary,SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		String time=dinary.getTime();
		String strSQL = "select * from dinary where time=? ";
		String[] selectionArgs = new String[] {time};

		// 调用sqlManager对象中的方法完成数据库操作
		Cursor cursor = this.sqlManager.execReadableBySQL(sqLiteDatabase,
				strSQL, selectionArgs);

		// ------ 数据库结构的转换------
		// 创建一个空集合
		List<Dinary> lstDinary = new ArrayList<Dinary>();
		// 使用循环遍历游标数据并封装成Message对象装载到List集合中
		while (cursor.moveToNext()) {
			// 创建一个空的Note对象
			Dinary dinary1=new Dinary();
		
			// 为note对象的属性赋值
			dinary1.setTime(cursor.getString(0));
			dinary1.setContent(cursor.getString(1));
			dinary1.setImag1(cursor.getString(2));
			dinary1.setImag2(cursor.getString(3));
			dinary1.setImag3(cursor.getString(4));

			// 将封装好的message对象添加到集合中
			lstDinary.add(dinary1);
		}

		// 返回集合
		return lstDinary;
	}
}
