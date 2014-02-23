package com.hutcservice.database;

import com.hutcservice.common.*;


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


//��������û�����Ϣ������ʵ�ּ�ס������Զ���½
public class User {
	private String _name,_pass;
	private boolean _flag=false,_rememberpass=false,_autologin=false;
	//private Context context;
	
	//����û��Ƿ�ѡ���˼�ס����
	public boolean getRememberpass(){
		if(_rememberpass==false)
			_rememberpass=Config.sharedPreferences.getBoolean("Rememberpass", false);
		return _rememberpass;
	}
   public void setRememberpass(boolean rememberpass){
	   _rememberpass=rememberpass; 
		Editor editor = Config.sharedPreferences.edit();
		editor.putBoolean("Rememberpass", _rememberpass);
		editor.commit();
	}

 //����û��Ƿ�ѡ�����Զ���½
 	public boolean getAutologin(){
 		if(_autologin==false)
 			_autologin=Config.sharedPreferences.getBoolean("Autologin", false);
 		return _autologin;
 	}
    public void setAutologin(boolean autologin){
    	_autologin=autologin; 
 		Editor editor = Config.sharedPreferences.edit();
 		editor.putBoolean("Autologin", _autologin);
 		editor.commit();
 	}
   
   //����û��Ƿ��˳�
	public boolean getFlag(){
		if(_flag==false)
		_flag=Config.sharedPreferences.getBoolean("Flag", false);
		return _flag;
	}
   public void setFlag(boolean flag){
	    _flag=flag; 
		Editor editor = Config.sharedPreferences.edit();
		editor.putBoolean("Flag", _flag);
		editor.commit();
	}

	public String getName(){
	
		if(_name==null)
			_name=Config.sharedPreferences.getString("Name", "");
		return _name;
	}
	public String getPass(){
		
		if(_pass==null)
			_pass=Config.sharedPreferences.getString("Pass", "");
		return _pass;
	}
	
	public void setName(String name){
		_name=name; 
		Editor editor = Config.sharedPreferences.edit();
		editor.putString("Name", name);
		editor.commit();
	}
	public void setPass(String pass){
		_pass=pass;
		Editor editor = Config.sharedPreferences.edit();
		editor.putString("Pass", _pass);
		editor.commit();
	}
}
