package edu.tjpu.note.biz;

import java.util.List;

import android.content.Context;
import edu.tjpu.note.po.Dinary;

public interface DinaryBiz {
	public abstract boolean add(final Context mContext, final Dinary dinary);

	public abstract boolean alter(final Context mContext, final Dinary dinary);

	public abstract boolean remove(final Context mContext, final Dinary dinary);

	public abstract List<Dinary> find1(final Context mContext,
			final Dinary dinary);
}
