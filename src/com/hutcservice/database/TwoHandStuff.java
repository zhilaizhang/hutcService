package com.hutcservice.database;


import android.graphics.Bitmap;

public class TwoHandStuff {
		private String id;            //����id
		private String Userid;        //�û�id
		private String classid;       //����id
		private String title;      //����
		private String content;    //����
		private Bitmap image;     //ͼƬ
		private int price;      //�۸�
		private String linkman;   //��ϵ��
		private String linkphone;  //��ϵ�˵绰
		private String flag;     //ɾ�����
		private String linkqq;        //��ϵ��qq
		private String imagepath;  //ͼƬ·��
		private String time;    //����ʱ��
		
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
