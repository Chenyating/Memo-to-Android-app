package edu.tjpu.mainActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.finalworks.R;

import edu.tjpu.DefineAdapter.NoteAdapter;
import edu.tjpu.circle.CircularImage;
import edu.tjpu.note.biz.IMessageBiz;
import edu.tjpu.note.biz.INoteBiz;
import edu.tjpu.note.biz.impl.MessageBizImpl;
import edu.tjpu.note.biz.impl.NoteBizImpl;
import edu.tjpu.note.po.Message;
import edu.tjpu.note.po.Note;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

public class Note_Page extends Fragment {

	private ListView listView;
	private List<Map<String, ?>> items;
	private edu.tjpu.circle.CircularImage ci_editnote;
	private int notesum;
	private INoteBiz notebiz2;
	private List<Note> lstNote;
	private Bitmap bitmap1;
	private PopupWindow pw;
	private View pwView;
	private int position2;
	private List<Note> lstNote4;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View noteView = inflater.inflate(R.layout.main_switch_note, container,
				false);
		return noteView;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		listView = (ListView) getActivity().findViewById(R.id.out);
		ci_editnote = (CircularImage) getActivity().findViewById(
				R.id.ci_editnote);
		ci_editnote.setOnClickListener(new ViewOCL());

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.editnote);
		ci_editnote.setImageBitmap(bitmap);

		// 短点击
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// arg2是选上的position, view是item

				Intent i = new Intent();
				i.setClass(getActivity(), editnote_xiugai.class);
				i.putExtra("position", arg2);
				startActivity(i);

			}

		});
		// 长点击
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				// 实现popupwindow
				position2 = arg2;
				if (pw == null) {
					pwView = View.inflate(getActivity(), R.layout.pw_layout,
							null);
					pwView.findViewById(R.id.ll_pw_uninstall)
							.setOnClickListener(new ViewOCL());

					pw = new PopupWindow(pwView, 230, arg1.getHeight());
				}
				// 如果正在显示，则移除
				if (pw.isShowing()) {
					pw.dismiss();// 只是不显示，内存中还是有的
				}

				// 然后再显示菜单
				pw.showAsDropDown(arg1, arg1.getWidth()-100, -arg1.getHeight());
				return true;
			}
		});
		// 滚动
		listView.setOnScrollListener(new OnScrollListener() {

			// 当滚动状态发生改变时，调用
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				// 如果跟着手指移动
				if (arg1 == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
					// 移除正在显示的pw
					if (pw != null && pw.isShowing()) {
						pw.dismiss();
					}

				}
			}

			// 当listview正在滚动时调用
			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub

			}
		});

	}

	private List<Map<String, ?>> init() {
		List<Map<String, ?>> items = new ArrayList<Map<String, ?>>();
		// 头像图片

		IMessageBiz messageBiz = new MessageBizImpl();
		List<Message> lstMessage = messageBiz.find(getActivity());
		String strphoto = lstMessage.get(0).getPhoto();
		if (strphoto.equals("")) {
			bitmap1 = BitmapFactory.decodeResource(getResources(),
					R.drawable.default_picture);
		} else {
			Uri myuri = Uri.parse(strphoto);
			ContentResolver contentResolver = getActivity()
					.getContentResolver();
			try {
				bitmap1 = MediaStore.Images.Media.getBitmap(contentResolver,
						myuri);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < notesum; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			String time = lstNote.get(i).getTime();
			String title = lstNote.get(i).getTitle();
			item.put("time", time);
			item.put("title", title);
			item.put("image", bitmap1);
			items.add(item);
		}
		return items;
	}

	private class ViewOCL implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ci_editnote:
				Intent i = new Intent();
				i.setClass(getActivity(), editnote.class);
				startActivity(i);
				break;
			case R.id.ll_pw_uninstall:

				// 如果正在显示，则移除
				if (pw.isShowing()) {
					pw.dismiss();// 只是不显示，内存中还是有的
				}

				INoteBiz notebiz3 = new NoteBizImpl();

				List<Note> lstNote3 = notebiz3.find1(getActivity());
				int id = lstNote3.get(position2).getIid();
				String time = lstNote3.get(position2).getTime();

				Note note3 = new Note();
				note3.setTime(time);
				notebiz3.remove(getActivity(), note3);

				INoteBiz notebiz4 = new NoteBizImpl();
				lstNote4 = notebiz4.find1(getActivity());
				notesum = lstNote4.size();
				List<Map<String, ?>> items3 = init2();

				NoteAdapter adapter4 = new NoteAdapter(getActivity(), items3);
				ListView listView4 = (ListView) getActivity().findViewById(
						R.id.out);
				listView4.setAdapter(adapter4);

			default:
				break;
			}
		}

	}

	private List<Map<String, ?>> init2() {
		List<Map<String, ?>> items = new ArrayList<Map<String, ?>>();
		// 头像图片

		IMessageBiz messageBiz = new MessageBizImpl();
		List<Message> lstMessage = messageBiz.find(getActivity());
		String strphoto = lstMessage.get(0).getPhoto();
		if (strphoto.equals("")) {
			bitmap1 = BitmapFactory.decodeResource(getResources(),
					R.drawable.default_picture);
		} else {
			Uri myuri = Uri.parse(strphoto);
			ContentResolver contentResolver = getActivity()
					.getContentResolver();
			try {
				bitmap1 = MediaStore.Images.Media.getBitmap(contentResolver,
						myuri);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < notesum; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			String time = lstNote4.get(i).getTime();
			String title = lstNote4.get(i).getTitle();
			item.put("time", time);
			item.put("title", title);
			item.put("image", bitmap1);
			items.add(item);
		}
		return items;
	}

	@Override
	public void onResume() {
		super.onResume();
		this.notebiz2 = new NoteBizImpl();
		this.lstNote = notebiz2.find1(getActivity());
		this.notesum = lstNote.size();
		items = init();

   /*    SimpleAdapter adapter = new SimpleAdapter(getActivity(), items,
				R.layout.listview_item, new String[] { "time", "title" },
				new int[] { R.id.item_date, R.id.item_title });
		this.listView.setAdapter(adapter);*/

		// 自定义适配器
		NoteAdapter adapter = new NoteAdapter(getActivity(), items);
		this.listView.setAdapter(adapter);
	}

}
