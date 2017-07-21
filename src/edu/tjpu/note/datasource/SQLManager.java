package edu.tjpu.note.datasource;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLManager {

	/**
	 * 方法1：专门处理写入的操作的方法（insert/delete/update）
	 * */
	public boolean execWritableBySQL(final SQLiteDatabase sqLiteDatabase,
			final String strSQL, final Object... bindArgs) {

		if (sqLiteDatabase != null) {
			try {

				sqLiteDatabase.execSQL(strSQL, bindArgs);
				return true;
			} catch (SQLException ex) {

				return false;
			}
		} else {

			return false;
		}
	}

	/**
	 * 方法2：专门处理读取的操作的方法（select）
	 * */
	public Cursor execReadableBySQL(final SQLiteDatabase sqLiteDatabase,
			final String strSQL, final String... selectionArgs) {

		if (sqLiteDatabase != null) {
			try {

				Cursor cursor = sqLiteDatabase.rawQuery(strSQL, selectionArgs);
				return cursor;
			} catch (SQLException ex) {

				return null;
			}
		} else {

			return null;
		}
	}
}
