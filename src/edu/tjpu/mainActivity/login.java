package edu.tjpu.mainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.example.finalworks.R;

import edu.tjpu.circle.CircularImage;
import edu.tjpu.note.biz.IMessageBiz;
import edu.tjpu.note.biz.impl.MessageBizImpl;
import edu.tjpu.note.po.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends Fragment {

	private CircularImage login_headpicture; // 登录界面的头像
	private Button btn_login;
	private EditText et_password;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.login, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		btn_login = (Button) getActivity().findViewById(R.id.btn_login);
		et_password = (EditText) getActivity().findViewById(R.id.et_password);
		login_headpicture = (CircularImage) getActivity().findViewById(
				R.id.login_headpicture);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.default_picture);
		login_headpicture.setImageBitmap(bitmap);

		IMessageBiz messageBiz = new MessageBizImpl();
		List<Message> lstMessage = messageBiz.find(getActivity());
		String strphoto = lstMessage.get(0).getPhoto();
		if (strphoto.equals("")) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.default_picture);
			login_headpicture.setImageBitmap(bitmap);
		} else {
			Uri myuri = Uri.parse(strphoto);
			ContentResolver contentResolver = getActivity()
					.getContentResolver();
			try {
				bitmap = MediaStore.Images.Media.getBitmap(contentResolver,
						myuri);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.login_headpicture.setImageBitmap(bitmap);
		}

		login_headpicture.setOnClickListener(new ViewOCL());
		btn_login.setOnClickListener(new ViewOCL());

	}

	private class ViewOCL implements View.OnClickListener {

		Intent i = new Intent();
		IMessageBiz messageBiz = new MessageBizImpl();

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_headpicture: // 更改头像

				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				final String[] types = { "拍照", "从相册中选择" };
				builder.setItems(types, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int position) {
						switch (position) {
						case 0:
							Intent intent = null;
							intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
									.fromFile(new File(Environment
											.getExternalStorageDirectory(),
											"temp.jpg")));
							startActivityForResult(intent, 1);// 1是拍照

							break;
						case 1:
							/*
							 * intent = new Intent(Intent.ACTION_PICK, null);
							 * intent.setDataAndType(
							 * MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
							 * IMAGE_UNSPECIFIED);
							 * startActivityForResult(intent, PHOTO_ZOOM);
							 */
							selectimgge();
							break;
						default:
							break;
						}
					}
				});
				builder.show();

				break;

			case R.id.btn_login:
				List<Message> lstMessage = messageBiz.find(getActivity());
				String str1 = lstMessage.get(0).getPassword();

				if (et_password.getText().toString().equals(str1)) {
					i.setClass(getActivity(), Main_Activity.class);
					startActivity(i);
				} else {
					Toast.makeText(getActivity(), "输入密码有误", Toast.LENGTH_LONG)
							.show();
				}
				break;

			default:
				break;
			}
		}
	}

	// 关于点击切换图像
	private void selectimgge() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*"); // 图片类型
		startActivityForResult(intent, 1411);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != getActivity().RESULT_OK) { // 判断是否接收到正常数据
			return;
		}
		Bitmap bitmap = null; // 解析图片
		ContentResolver contentResolver = getActivity().getContentResolver(); // 对象时当前应用程序，当前指令征用
		if (requestCode == 1411) {
			Uri imguri = data.getData(); // 获取uri
			IMessageBiz messageBiz = new MessageBizImpl();
			Message messagephoto = new Message();
			messagephoto.setPhoto(imguri.toString());
			messageBiz.alterphoto(getActivity(), messagephoto);

			try {
				bitmap = MediaStore.Images.Media.getBitmap(contentResolver,
						imguri); // 自动添加try catch
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.login_headpicture.setImageBitmap(bitmap);
		}
		if (requestCode == 1) {
			// 设置文件保存路径
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			Uri imguri = Uri.fromFile(picture);
			// startPhotoZoom(Uri.fromFile(picture));
			IMessageBiz messageBiz = new MessageBizImpl();
			Message messagephoto = new Message();
			messagephoto.setPhoto(imguri.toString());
			messageBiz.alterphoto(getActivity(), messagephoto);

			try {
				bitmap = MediaStore.Images.Media.getBitmap(contentResolver,
						imguri); // 自动添加try catch
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.login_headpicture.setImageBitmap(bitmap);
		}
	}
	// 点击切换头像结束

}
