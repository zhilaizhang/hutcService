package com.hutcservice.database;

import android.graphics.Bitmap;

public class GoodManStuff {
	private String gId;
	private String userId;
	private String pTitle;
	private String pDetail;
	private Bitmap pPicture;
	private String linkMan;
	private String linkPhone;
	private String linkQQ;
	private String pTime;
	private String imagepath;
	
	public String getgId(){
		return gId;
	}
	public void setgId(String gid){
		this.gId=gid;
	}
	
	public String getuserId(){
		return userId;
	}
	public void setuserId(String userid){
		this.userId=userid;
	}
	
	public String getpTitle(){
		return pTitle;
	}
	public void setpTitle(String ptitle){
		this.pTitle=ptitle;
	}
	
	public String getpDetail(){
		return pDetail;
	}
	public void setpDetail(String pdetail){
		this.pDetail=pdetail;
	}
	
	public Bitmap getpPicture(){
		return pPicture;
	}
	public void setpPicture(Bitmap ppicture){
		this.pPicture=ppicture;
	}
	
	public String getlinkMan(){
		return linkMan;
	}
	public void setlinkMan(String linkman){
		this.linkMan=linkman;
	}
	
	public String getlinkPhone(){
		return linkPhone;
	}
	public void setlinkPhone(String linkphone){
		this.linkPhone=linkphone;
	}
	
	public String getlinkQQ(){
		return linkQQ;
	}
	public void setlinkQQ(String linkqq){
		this.linkQQ=linkqq;
	}
	
	public String getpTime(){
		return pTime;
	}
	public void setpTime(String ptime){
		this.pTime=ptime;
	}	
	public String getImagePath() {
		return imagepath;
	}

	public void setImagePath(String imagepath) {
		this.imagepath = imagepath;
	}
}
