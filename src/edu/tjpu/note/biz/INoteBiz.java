package edu.tjpu.note.biz;

import java.util.List;

import edu.tjpu.note.po.Note;

import android.content.Context;

public interface INoteBiz {

	public abstract boolean add(final Context mContext, final Note note);

	public abstract boolean alter(final Context mContext, final Note note);

	public abstract boolean alterzhiding(final Context mContext, final Note note);

	public abstract boolean alteriid(final Context mContext);

	public abstract boolean remove(final Context mContext, final Note note);

	public abstract List<Note> find1(final Context mContext);
	public abstract List<Note> find2(final Context mContext,final Note note);
}
