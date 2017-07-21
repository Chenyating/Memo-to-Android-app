package edu.tjpu.note.dao;

import java.util.List;

import edu.tjpu.note.po.Note;

import android.database.sqlite.SQLiteDatabase;

public interface INoteDao {

	public abstract boolean insert(final Note note,
			final SQLiteDatabase sqLiteDatabase);

	public abstract boolean update(final Note note,
			final SQLiteDatabase sqLiteDatabase);

	public abstract boolean updatezhidingiid(final Note note,
			final SQLiteDatabase sqLiteDatabase);

	public abstract boolean updateiid(final SQLiteDatabase sqLiteDatabase);

	public abstract boolean drop(final Note note,
			final SQLiteDatabase sqLiteDatabase);

	public abstract List<Note> select1(final SQLiteDatabase sqLiteDatabase);
	
	public abstract List<Note> select2(final SQLiteDatabase sqLiteDatabase,final Note note);
}
