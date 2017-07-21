package edu.tjpu.note.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ConnectionManager {

	/**
	 * 方法1：开启数据库的连接(读/写)
	 * */
	public SQLiteDatabase openConnection(final Context mContext,
			final String model) {
		// 实例化SQLiteDataBaseHelper对象
		SQLiteDatabaseHelper databaseHelper = new SQLiteDatabaseHelper(mContext);

		// 根据调用的参数model确定读取连接还是写入链接
		SQLiteDatabase sqLiteDatabase = null;
		if (model.equals("read")) {
			sqLiteDatabase = databaseHelper.getReadableDatabase();

		} else {
			sqLiteDatabase = databaseHelper.getWritableDatabase();

		}
		// 返回有效的数据库连接
		return sqLiteDatabase;
	}

	/**
	 * 方法2：关闭数据库的连接
	 * */
	public void closeConnection(final SQLiteDatabase sqLiteDatabase) {
		// 判断连接是否有效
		if (sqLiteDatabase != null) {
			sqLiteDatabase.close(); // 关闭连接

		}
	}

}
