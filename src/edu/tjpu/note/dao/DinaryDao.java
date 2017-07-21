package edu.tjpu.note.dao;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import edu.tjpu.note.po.Dinary;


public interface DinaryDao {
	public abstract boolean insert(final Dinary dinary,
			final SQLiteDatabase sqLiteDatabase);

	public abstract boolean update(final Dinary dinary,
			final SQLiteDatabase sqLiteDatabase);

	public abstract boolean drop(final Dinary dinary,
			final SQLiteDatabase sqLiteDatabase);

	public abstract List<Dinary> select1(final Dinary dinary,final SQLiteDatabase sqLiteDatabase);

}
