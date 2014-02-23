package com.hutcservice.database;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.hutcservice.common.*;

    public class TwoHandCommand {
	
	private DbOpenHelper dbOpenHelper;
	ImageManage im=new ImageManage();
	
	//服务器端的联系 开始
	 //添加数据
	public boolean insert(TwoHandStuff twohandstuff){
		String picture;
		String uid=twohandstuff.getUid();
		String classid=twohandstuff.getclassid();
		String title=twohandstuff.getTitle();
		String detail=twohandstuff.getContent();
		String price=String.valueOf(twohandstuff.getPrice());
		if(twohandstuff.getImage()==null){
			 picture="";
		}else{
			 picture=im.bitmapToString(twohandstuff.getImage());
		}		
		String linkman=twohandstuff.getLinkman();
		String linkphone=twohandstuff.getlinkphone();
		String linkQQ=twohandstuff.getlinkqq();
		TwoHandToService thts=new TwoHandToService();
		try {
			if(thts.execute(uid,classid,title,detail,price,picture,linkman,linkphone,linkQQ).get()){
				return true;
			}else{
				return false;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//服务器端的联系 结束
	public TwoHandCommand(Context context){
		this.dbOpenHelper= new DbOpenHelper(context);
	}
	
   public void add(TwoHandStuff twohandstuff){
		
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
         
		//db.execSQL("INSERT INTO TwoHand(uerid,classid)values('"+twohandstuff.getUid()+"','"+twohandstuff.getclassid()+"')");
		
		
		db.execSQL("INSERT INTO TwoHand(uerid,classid,title,content,price,picture,linkman,linkPhone,linkqq,deleteFlag)values('"+twohandstuff.getUid()+"','"+twohandstuff.getclassid()+"','"+twohandstuff.getTitle()+"','"+twohandstuff.getContent()+"','"+twohandstuff.getPrice()+"','"+twohandstuff.getImagePath()+"','"+twohandstuff.getLinkman()+"','"+twohandstuff.getlinkphone()+"','"+twohandstuff.getlinkqq()+"','"+twohandstuff.getflag()+"')");
		db.close();
	}
   public void delete(User uid){
		
	}
   public void update(User user){
		
	}
   public Cursor findAll(){
	   SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
	   String uid="1";
	   Cursor cursor=db.rawQuery("SELECT * FROM TwoHand", null);
	  // cursor.moveToFirst();
	  // cursor.getString(1);
	   return cursor;
	}
   public Cursor findone(int id){
	   SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
	  // String uid="1"; 
	   Cursor cursor=db.rawQuery("SELECT * FROM TwoHand where pid=?",
				new String[] {String.valueOf(id) });
	  // cursor.moveToFirst();
	  // cursor.getString(1);
	   return cursor;
	}
}
