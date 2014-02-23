package com.hutcservice.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserCommand {
	  
	private DbOpenHelper dbOpenHelper;
	
	public UserCommand(Context context){
		this.dbOpenHelper= new DbOpenHelper(context);
	}
	
	public boolean login(String name,String pass){
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		String condition="(name='"+name+"'and pass='"+pass+"')";
		//Cursor cursor=db.rawQuery("select * from User where name=?", new String[]{name.toString()});
		Cursor cursor=db.query("User", null, condition, null, null, null, null);
		boolean isC=(cursor.getCount()==1);
		cursor.close();
		db.close();
		
			return isC;
		
		
	}
	public void add(String name,String pass){
		
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("INSERT INTO User(name,pass)values('"+name+"','"+pass+"')");
		db.close();
	}
   public void delete(User uid){
		
	}
   public void update(User user){
		
	}
   public User find(User uid){
	   
		return null;
	}
   public long getCount(){
	   return 0;
   }
}
