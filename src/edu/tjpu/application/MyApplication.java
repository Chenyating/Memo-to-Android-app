package edu.tjpu.application;

import android.app.Application;

public class MyApplication extends Application {
	
	public String passWord = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	//每次开启程序，从数据库中读取密码，然后在这里初始化至application中
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getPassWord() {
		return passWord;
	}

}
