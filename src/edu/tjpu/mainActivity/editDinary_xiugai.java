package edu.tjpu.mainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.finalworks.R;

import edu.tjpu.circle.CircularImage;
import edu.tjpu.note.biz.DinaryBiz;

import edu.tjpu.note.biz.impl.DinaryBizImpl;

import edu.tjpu.note.po.Dinary;

import android.app.ActionBar;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class editDinary_xiugai extends ListActivity {
	TextView tv_dinary_time;
	EditText editdinary;
	private String fmt_date;
	private ActionBar actionBar;
	private edu.tjpu.circle.CircularImage luyin;
	private edu.tjpu.circle.CircularImage tupian;
	private PopupWindow Pwindow_luyin;
	private PopupWindow Pwindow_tupian;
	private ListView list;

	private File mRecAudioFile; // 录制的音频文件
	private File mRecAudioPath; // 录制的音频文件路徑
	private MediaRecorder mMediaRecorder;// MediaRecorder对象
	private List<String> mMusicList = new ArrayList<String>();// 录音文件列表
	private String strTempFile = "recaudio_";// 零时文件的前缀

	private String time;
	private String content1;

	private ImageView imag1, imag2, imag3;// 3个放图片的容器
	private Uri uri1, uri2, uri3;// 图片路径
	private Bitmap bitmap1, bitmap2, bitmap3;// 图片
	private String im1, im2, im3;// 图片路径的字符串形式

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editdinary);

		tv_dinary_time = (TextView) findViewById(R.id.tv_dinary_time);
		editdinary = (EditText) findViewById(R.id.editdinary);

		luyin = (CircularImage) findViewById(R.id.ci_luyin);
		tupian = (CircularImage) findViewById(R.id.ci_tupian);
		// 插入图片的3个地方
		imag1 = (ImageView) findViewById(R.id.imag1);
		imag2 = (ImageView) findViewById(R.id.imag2);
		imag3 = (ImageView) findViewById(R.id.imag3);
		Intent intent = getIntent();
		time = intent.getStringExtra("time");

		luyin.setOnClickListener(new ViewOCL());
		tupian.setOnClickListener(new ViewOCL());
		initPopupWindow();
		initPopupWindow_tupian();
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.luyin);

		Bitmap bitmmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.tupiantupian);

		luyin.setImageBitmap(bitmap);
		tupian.setImageBitmap(bitmmap);
		actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false); // 标题栏的图标
		actionBar.setDisplayHomeAsUpEnabled(true); // 显示后退键

		Date date = new Date();
		fmt_date = new SimpleDateFormat("yyyy年MM月dd日").format(date);
		tv_dinary_time.setText(fmt_date);

		DinaryBiz dinarybiz = new DinaryBizImpl();
		Dinary dinary = new Dinary();
		dinary.setTime(time);
		dinary.setContent("");
		dinary.setImag1("");
		dinary.setImag2("");
		dinary.setImag3("");
		List<Dinary> lisDinary = dinarybiz
				.find1(editDinary_xiugai.this, dinary);
		String time1 = lisDinary.get(0).getTime();
		content1 = lisDinary.get(0).getContent();
		editdinary.setText(content1 + "");

		im1 = lisDinary.get(0).getImag1();
		im2 = lisDinary.get(0).getImag2();
		im3 = lisDinary.get(0).getImag3();

		ContentResolver contentResolver = editDinary_xiugai.this
				.getContentResolver();
		if (!lisDinary.get(0).getImag1().equals("")) {
			uri1 = Uri.parse(im1);
			// 插入图1
			try {
				bitmap1 = MediaStore.Images.Media.getBitmap(contentResolver,
						uri1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.imag1.setImageBitmap(bitmap1);
		}

		if (!lisDinary.get(0).getImag2().equals("")) {
			uri2 = Uri.parse(im2);
			// 插入图1
			try {
				bitmap2 = MediaStore.Images.Media.getBitmap(contentResolver,
						uri2);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.imag2.setImageBitmap(bitmap2);
		}

		if (!lisDinary.get(0).getImag3().equals("")) {
			uri3 = Uri.parse(im3);
			// 插入图1
			try {
				bitmap3 = MediaStore.Images.Media.getBitmap(contentResolver,
						uri3);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.imag3.setImageBitmap(bitmap3);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_editnote_acbar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.submit:

			DinaryBiz dinarybiz = new DinaryBizImpl();
			Dinary dinary = new Dinary();
			dinary.setTime(time);
			dinary.setContent(editdinary.getText().toString());

			dinary.setImag1(im1);
			dinary.setImag2(im2);
			dinary.setImag3(im3);
			dinarybiz.alter(getApplicationContext(), dinary);

			finish();
			break;
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class ViewOCL implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ci_luyin:
				Pwindow_luyin.setAnimationStyle(R.style.PopuwindowAnimStyle);
				Pwindow_luyin.showAtLocation(v, Gravity.BOTTOM, 0, 0);
				break;

			case R.id.ci_tupian:
				Pwindow_tupian.setAnimationStyle(R.style.PopuwindowAnimStyle);
				Pwindow_tupian.showAtLocation(v, Gravity.BOTTOM, 0, 0);
				break;
			default:
				break;
			}
		}
	}

	private void initPopupWindow() {
		Pwindow_luyin = new PopupWindow(this);
		Pwindow_luyin.setBackgroundDrawable(new BitmapDrawable());
		Pwindow_luyin.setWidth(LayoutParams.WRAP_CONTENT);
		Pwindow_luyin.setHeight(LayoutParams.WRAP_CONTENT);
		Pwindow_luyin.setOutsideTouchable(false);
		Pwindow_luyin.setFocusable(true);
		View content = this.getLayoutInflater().inflate(R.layout.luyin_jiemian,
				null);
		final Button btn_luyin = (Button) content.findViewById(R.id.btn_luyin);
		final Button btn_luyinjieshu = (Button) content
				.findViewById(R.id.btn_luyinjieshu);
		btn_luyin.setEnabled(true);
		btn_luyinjieshu.setEnabled(false);
		if (Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			mRecAudioPath = Environment.getExternalStorageDirectory();// 得到SD卡得路径
			musicList();// 更新所有录音文件到List中
		} else {
			Toast.makeText(editDinary_xiugai.this, "没有SD卡", Toast.LENGTH_LONG)
					.show();
		}
		btn_luyin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					/* ①Initial：实例化MediaRecorder对象 */
					mMediaRecorder = new MediaRecorder();
					/* ②setAudioSource/setVedioSource */
					mMediaRecorder
							.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
					/*
					 * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default
					 * THREE_GPP(3gp格式
					 * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
					 */
					mMediaRecorder
							.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					/* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default */
					mMediaRecorder
							.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					/* ②设置输出文件的路径 */
					try {
						mRecAudioFile = File.createTempFile(strTempFile,
								".amr", mRecAudioPath);

					} catch (Exception e) {
						e.printStackTrace();
					}
					mMediaRecorder.setOutputFile(mRecAudioFile
							.getAbsolutePath());
					/* ③准备 */
					mMediaRecorder.prepare();
					/* ④开始 */
					mMediaRecorder.start();
					/* 按钮状态 */
					btn_luyin.setEnabled(false);
					btn_luyinjieshu.setEnabled(true);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btn_luyinjieshu.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mRecAudioFile != null) {
					/* ⑤停止录音 */
					mMediaRecorder.stop();
					/* 将录音文件添加到List中 */
					mMusicList.add(mRecAudioFile.getName());
					ArrayAdapter<String> musicList = new ArrayAdapter<String>(
							editDinary_xiugai.this,
							android.R.layout.simple_list_item_1, mMusicList);
					setListAdapter(musicList);
					/* ⑥释放MediaRecorder */
					mMediaRecorder.release();
					mMediaRecorder = null;
					/* 按钮状态 */
					btn_luyin.setEnabled(true);
					btn_luyinjieshu.setEnabled(false);
				}
			}
		});
		Pwindow_luyin.setContentView(content);
	}

	public void musicList() {
		// 取得指定位置的文件设置显示到播放列表
		File home = mRecAudioPath;
		if (home.listFiles(new MusicFilter()).length > 0) {
			for (File file : home.listFiles(new MusicFilter())) {
				mMusicList.add(file.getName());
			}
			ArrayAdapter<String> musicList = new ArrayAdapter<String>(
					editDinary_xiugai.this,
					android.R.layout.simple_list_item_1, mMusicList);
			setListAdapter(musicList);
		}
	}

	/* 播放录音文件 */
	private void playMusic(File file) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		/* 设置文件类型 */
		intent.setDataAndType(Uri.fromFile(file), "audio");
		startActivity(intent);
	}

	@Override
	/* 当我们点击列表时，播放被点击的音乐 */
	protected void onListItemClick(ListView l, View v, int position, long id) {
		/* 得到被点击的文件 */
		File playfile = new File(mRecAudioPath.getAbsolutePath()
				+ File.separator + mMusicList.get(position));
		/* 播放 */
		playMusic(playfile);
	}

	private void initPopupWindow_tupian() {
		Pwindow_tupian = new PopupWindow(this);
		Pwindow_tupian.setBackgroundDrawable(new BitmapDrawable());
		Pwindow_tupian.setWidth(LayoutParams.WRAP_CONTENT);
		Pwindow_tupian.setHeight(LayoutParams.WRAP_CONTENT);
		Pwindow_tupian.setOutsideTouchable(false);
		Pwindow_tupian.setFocusable(true);
		View content = this.getLayoutInflater().inflate(
				R.layout.tupian_jiemian, null);
		Pwindow_tupian.setContentView(content);

		final Button bt_addphoto = (Button) content.findViewById(R.id.button1);
		bt_addphoto.setEnabled(true);
		bt_addphoto.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// "插入图片",

				if (imag1.getDrawable() == null) {

					selectimgge1();

				} else if (imag2.getDrawable() == null) {

					selectimgge2();

				} else if (imag3.getDrawable() == null) {

					selectimgge3();

				} else {
					Toast.makeText(getApplicationContext(), "最多插入三张图片",
							Toast.LENGTH_LONG).show();

				}

			}
		});

	}

	private void selectimgge1() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*"); // 图片类型
		startActivityForResult(intent, 1);
	}

	private void selectimgge2() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*"); // 图片类型
		startActivityForResult(intent, 2);
	}

	private void selectimgge3() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*"); // 图片类型
		startActivityForResult(intent, 3);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != editDinary.RESULT_OK) { // 判断是否接收到正常数据
			return;
		}
		Bitmap bitmap = null; // 解析图片
		ContentResolver contentResolver = editDinary_xiugai.this
				.getContentResolver(); // 对象时当前应用程序，当前指令征用
		if (requestCode == 1) {
			uri1 = data.getData(); // 获取uri
			im1 = uri1.toString();

			try {
				bitmap = MediaStore.Images.Media.getBitmap(contentResolver,
						uri1); // 自动添加try catch
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.imag1.setImageBitmap(bitmap);

		}
		if (requestCode == 2) {
			uri2 = data.getData(); // 获取uri
			im2 = uri2.toString();

			try {
				bitmap = MediaStore.Images.Media.getBitmap(contentResolver,
						uri2); // 自动添加try catch
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.imag2.setImageBitmap(bitmap);
		}
		if (requestCode == 3) {
			uri3 = data.getData(); // 获取uri
			im3 = uri3.toString();
			try {
				bitmap = MediaStore.Images.Media.getBitmap(contentResolver,
						uri3); // 自动添加try catch
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.imag3.setImageBitmap(bitmap);
		}

	}
}

/*
 * class MusicFilter implements FilenameFilter { public boolean accept(File dir,
 * String name) { return (name.endsWith(".amr")); } }
 */
