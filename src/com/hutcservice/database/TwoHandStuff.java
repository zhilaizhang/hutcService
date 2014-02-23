package com.hutcservice.database;


import android.graphics.Bitmap;

public class TwoHandStuff {
		private String id;            //货物id
		private String Userid;        //用户id
		private String classid;       //分类id
		private String title;      //标题
		private String content;    //描述
		private Bitmap image;     //图片
		private int price;      //价格
		private String linkman;   //联系人
		private String linkphone;  //联系人电话
		private String flag;     //删除标记
		private String linkqq;        //联系人qq
		private String imagepath;  //图片路径
		private String time;    //发布时间
		
		public String gettime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}
		
		public String getflag() {
			return flag;
		}

		public void setflag(String flag) {
			this.flag = flag;
		}
		
		public String getclassid() {
			return classid;
		}

		public void setclassid(String classid) {
			this.classid = classid;
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		public String getUid() {
			return Userid;
		}

		public void setUid(String Uid) {
			this.Userid = Uid;
		}
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getlinkqq() {
			return linkqq;
		}

		public void setlinkqq(String linkqq) {
			this.linkqq = linkqq;
		}
		
		public String getlinkphone() {
			return linkphone;
		}

		public void setlinkphone(String linkphone) {
			this.linkphone = linkphone;
		}
		
		public String getLinkman() {
			return linkman;
		}

		public void setLinkman(String linkman) {
			this.linkman = linkman;
		}
		
		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
		
		public String getImagePath() {
			return imagepath;
		}

		public void setImagePath(String imagepath) {
			this.imagepath = imagepath;
		}
		
		public Bitmap getImage() {
			return image;
		}

		public void setImage(Bitmap image) {
			this.image = image;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}
}
