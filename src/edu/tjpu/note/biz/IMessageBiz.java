package edu.tjpu.note.biz;

import java.util.List;

import edu.tjpu.note.po.Message;

import android.content.Context;

public interface IMessageBiz {

	public abstract boolean add(final Context mContext, final Message message);

	public abstract boolean alter(final Context mContext, final Message message);
	public abstract boolean alterphoto(final Context mContext, final Message message);

	public abstract List<Message> find(final Context mContext);
}
