package edu.tjpu.note.datasource;

import android.database.sqlite.SQLiteDatabase;

public class TransactionManager {

	/**
	 * 方法1：开启一个事务
	 * */
	public void beginTransaction(final SQLiteDatabase sqLiteDatabase) {
		if (sqLiteDatabase != null) {
			sqLiteDatabase.beginTransaction();

		}
	}

	/**
	 * 方法2：提交一个事务
	 * */
	public void commitTransaction(final SQLiteDatabase sqLiteDatabase) {
		if (sqLiteDatabase != null) {
			sqLiteDatabase.setTransactionSuccessful();

		}
	}

	/**
	 * 方法3：关闭一个事务
	 * */
	public void closeTransaction(final SQLiteDatabase sqLiteDatabase) {
		if (sqLiteDatabase != null) {
			sqLiteDatabase.endTransaction();

		}
	}
}
