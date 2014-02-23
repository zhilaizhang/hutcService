 package com.hutcservice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


//用来产生数据库
public class DbOpenHelper extends SQLiteOpenHelper {

	public DbOpenHelper(Context context) {
		super(context, "hutcservice.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
			db.execSQL("CREATE TABLE if not exists User(uid integer primary key autoincrement,name varchar(20),pass varchar(20))");
			db.execSQL("CREATE TABLE if not exists TwoHand(pid integer primary key autoincrement,uerid integer,classid integer,title TEXT,content TEXT,price integer,picture TEXT,linkman TEXT,linkPhone TEXT,linkqq TEXT,deleteFlag BOOLEAN)");
			//db.execSQL("CREATE TABLE if not exists TwoHand1(pid integer primary key autoincrement,uerid integer,classid integer)");
			

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
