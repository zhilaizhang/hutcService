package com.hutcservice.common;

import android.content.SharedPreferences;
import android.os.Environment;

import com.example.hutcservice.BaseActivity;
import com.hutcservice.database.*;

public class Config {
	public static BaseActivity currentActivity;
	public static SharedPreferences sharedPreferences;
	public final static String ROOTPATH = Environment
			.getExternalStorageDirectory().getPath() + "/hutcService";
	public final static String namespace = "http://kuoyisa.cn/webservices/";
	public final static String url = "http://kuoyisa.cn/HutcService.asmx";
	public static User User = new User();

	public static boolean IsInfomationFull() {
		return User.getName().length() > 0;
	}
}
