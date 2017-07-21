package edu.tjpu.note.dao;

import java.util.List;

import edu.tjpu.note.po.Message;

import android.database.sqlite.SQLiteDatabase;

public interface IMessageDao {

	public abstract boolean insert(final Message message,
			final SQLiteDatabase sqLiteDatabase);

	public abstract boolean update(final Message message,
			final SQLiteDatabase sqLiteDatabase);
	public abstract boolean updatephoto(final Message message,
			final SQLiteDatabase sqLiteDatabase);

	public abstract List<Message> select(final SQLiteDatabase sqLiteDatabase);
}
