package com.hutcservice.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.hutcservice.database.GoodManStuff;
import com.hutcservice.database.QiuGouStuff;
import com.hutcservice.database.TwoHandStuff;

//�������ݴ��ݹ�����id����Ϊ�м䷽�������첽������ȡ�������˵�����
public class GetStuff {
	ArrayList<String> stuff=new ArrayList<String>();
	
	
	ImageManage im=new ImageManage();
    //���ݷ���id��ö��ֻ������Ϣ
	public List<TwoHandStuff> getBike(String cid){
		GetTwoHandByCid gthb=new GetTwoHandByCid(); 
		List<TwoHandStuff> twostuffs = new ArrayList<TwoHandStuff>();
			try{				
				stuff=gthb.execute(cid).get();
				if(stuff==null){
					return null;
				}
				int x=stuff.size();
				for(int i=0;;i=i+12){//ÿ����Ʒ��11��Ԫ��
					TwoHandStuff twostuff=new TwoHandStuff();
					twostuff.setId(stuff.get(i));
					twostuff.setUid(stuff.get(i+1));
					twostuff.setclassid(stuff.get(i+2));
					twostuff.setTitle(stuff.get(i+3));
					twostuff.setContent(stuff.get(i+4));
					twostuff.setPrice(Integer.parseInt(stuff.get(i+5)));
					twostuff.setImage(im.stringtoBitmap(stuff.get(i+6)));
					twostuff.setLinkman(stuff.get(i+7));
					twostuff.setlinkphone(stuff.get(i+8));
					twostuff.setlinkqq(stuff.get(i+9));
					twostuff.setflag(stuff.get(i+10));
					twostuff.setTime(stuff.get(i+11));
					twostuffs.add(twostuff);
					 if(i+11==x-1){
						 return twostuffs;
					 }
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	
	 //�����û�id��ö��ֻ������Ϣ
		public List<TwoHandStuff> getStuff1(String uid){
			GetTwoHandByUid gthb=new GetTwoHandByUid(); 
			List<TwoHandStuff> twostuffs = new ArrayList<TwoHandStuff>();
				try{				
					stuff=gthb.execute(uid).get();
					if(stuff==null){
						return null;
					}
					int x=stuff.size();
					for(int i=0;;i=i+12){//ÿ����Ʒ��11��Ԫ��
						TwoHandStuff twostuff=new TwoHandStuff();
						twostuff.setId(stuff.get(i));
						twostuff.setUid(stuff.get(i+1));
						twostuff.setclassid(stuff.get(i+2));
						twostuff.setTitle(stuff.get(i+3));
						twostuff.setContent(stuff.get(i+4));
						twostuff.setPrice(Integer.parseInt(stuff.get(i+5)));
						twostuff.setImage(im.stringtoBitmap(stuff.get(i+6)));
						twostuff.setLinkman(stuff.get(i+7));
						twostuff.setlinkphone(stuff.get(i+8));
						twostuff.setlinkqq(stuff.get(i+9));
						twostuff.setflag(stuff.get(i+10));
						twostuff.setTime(stuff.get(i+11));
						twostuffs.add(twostuff);
						 if(i+11==x-1){
							 return twostuffs;
						 }
					}
					//return twostuffs;
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
				}
		//�����û�id��ö����󹺵���Ϣ
				public List<QiuGouStuff> getQiuGou(String uid){
					GetQiuGouByUid gqgbu=new GetQiuGouByUid(); 
					List<QiuGouStuff> twostuffs = new ArrayList<QiuGouStuff>();
						try{				
							stuff=gqgbu.execute(uid).get();
							if(stuff==null){
								return null;
							}
							int x=stuff.size();
							for(int i=0;;i=i+9){//ÿ����Ʒ��9��Ԫ��
								QiuGouStuff twostuff=new QiuGouStuff();
								twostuff.setgId(stuff.get(i));
								twostuff.setuserId(stuff.get(i+1));
								twostuff.setclassId(stuff.get(i+2));
								twostuff.setpTitle(stuff.get(i+3));
								twostuff.setpDetail(stuff.get(i+4));
								twostuff.setlinkMan(stuff.get(i+5));
								twostuff.setlinkPhone(stuff.get(i+6));
								twostuff.setlinkQQ(stuff.get(i+7));								
								twostuff.setpTime(stuff.get(i+8));
								twostuffs.add(twostuff);
								 if(i+8==x-1){
									 return twostuffs;
								 }
							}
							//return twostuffs;
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						}
				//��ö�����������Ʒ��Ϣ
				public List<QiuGouStuff> getQiuGouAll(){
					GetQiuGouAll gqgbu=new GetQiuGouAll(); 
					List<QiuGouStuff> twostuffs = new ArrayList<QiuGouStuff>();
						try{				
							stuff=gqgbu.execute().get();
							if(stuff==null){
								return null;
							}
							int x=stuff.size();
							for(int i=0;;i=i+9){//ÿ����Ʒ��9��Ԫ��
								QiuGouStuff twostuff=new QiuGouStuff();
								twostuff.setgId(stuff.get(i));
								twostuff.setuserId(stuff.get(i+1));
								twostuff.setclassId(stuff.get(i+2));
								twostuff.setpTitle(stuff.get(i+3));
								twostuff.setpDetail(stuff.get(i+4));
								twostuff.setlinkMan(stuff.get(i+5));
								twostuff.setlinkPhone(stuff.get(i+6));
								twostuff.setlinkQQ(stuff.get(i+7));								
								twostuff.setpTime(stuff.get(i+8));
								twostuffs.add(twostuff);
								 if(i+8==x-1){
									 return twostuffs;
								 }
							}
							//return twostuffs;
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						}
				
		//��ȡʰ��������Ϣ
				public List<GoodManStuff> getShiJinBuMeiStuff(){
					GetShiJinBuMei gsjbm=new GetShiJinBuMei(); 
					List<GoodManStuff> twostuffs = new ArrayList<GoodManStuff>();
						try{				
							stuff=gsjbm.execute().get();
							if(stuff==null){
								return null;
							}
							int x=stuff.size();
							for(int i=0;;i=i+9){//ÿ����Ʒ��9��Ԫ��
								GoodManStuff twostuff=new GoodManStuff();
								twostuff.setgId(stuff.get(i));
								twostuff.setuserId(stuff.get(i+1));
								twostuff.setpTitle(stuff.get(i+2));
								twostuff.setpDetail(stuff.get(i+3));
								if((stuff.get(i+4).toString()).equals("")||(stuff.get(i+4).toString()).equals(null)){
									twostuff.setpPicture(null);
								}else{
								twostuff.setpPicture(im.stringtoBitmap(stuff.get(i+4)));
								}
								twostuff.setlinkMan(stuff.get(i+5));
								twostuff.setlinkPhone(stuff.get(i+6));
								twostuff.setlinkQQ(stuff.get(i+7));
								twostuff.setpTime(stuff.get(i+8));
								twostuffs.add(twostuff);
								 if(i+8==x-1){
									 return twostuffs;
								 }
							}
							//return twostuffs;
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						}
				//�����û�ID��ȡʰ��������Ϣ
				public List<GoodManStuff> getShiJinBuMeiStuffByUid(String uid){
					GetShiJinBuMeiByUid gsjbm=new GetShiJinBuMeiByUid(); 
					List<GoodManStuff> twostuffs = new ArrayList<GoodManStuff>();
						try{				
							stuff=gsjbm.execute(uid).get();
							if(stuff==null){
								return null;
							}
							int x=stuff.size();
							for(int i=0;;i=i+9){//ÿ����Ʒ��9��Ԫ��
								GoodManStuff twostuff=new GoodManStuff();
								twostuff.setgId(stuff.get(i));
								twostuff.setuserId(stuff.get(i+1));
								twostuff.setpTitle(stuff.get(i+2));
								twostuff.setpDetail(stuff.get(i+3));
								if((stuff.get(i+4).toString()).equals("")||(stuff.get(i+4).toString()).equals(null)){
									twostuff.setpPicture(null);
								}else{
								twostuff.setpPicture(im.stringtoBitmap(stuff.get(i+4)));
								}
								twostuff.setlinkMan(stuff.get(i+5));
								twostuff.setlinkPhone(stuff.get(i+6));
								twostuff.setlinkQQ(stuff.get(i+7));
								twostuff.setpTime(stuff.get(i+8));
								twostuffs.add(twostuff);
								 if(i+8==x-1){
									 return twostuffs;
								 }
							}
							//return twostuffs;
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						}
				
				//��ȡѰ�����µ���Ϣ
				public List<GoodManStuff> getXunWuQiShiStuff(){
					GetXunWuQiShiFromWeb gxwqs=new GetXunWuQiShiFromWeb(); 
					List<GoodManStuff> twostuffs = new ArrayList<GoodManStuff>();
						try{				
							stuff=gxwqs.execute().get();
							if(stuff==null){
								return null;
							}
							int x=stuff.size();
							for(int i=0;;i=i+9){//ÿ����Ʒ��9��Ԫ��
								GoodManStuff twostuff=new GoodManStuff();
								twostuff.setgId(stuff.get(i));
								twostuff.setuserId(stuff.get(i+1));
								twostuff.setpTitle(stuff.get(i+2));
								twostuff.setpDetail(stuff.get(i+3));
								twostuff.setpPicture(im.stringtoBitmap(stuff.get(i+4)));
								twostuff.setlinkMan(stuff.get(i+5));
								twostuff.setlinkPhone(stuff.get(i+6));
								twostuff.setlinkQQ(stuff.get(i+7));
								twostuff.setpTime(stuff.get(i+8));
								twostuffs.add(twostuff);
								 if(i+8==x-1){
									 return twostuffs;
								 }
							}
							//return twostuffs;
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						}	
				//�����û�ID��ȡѰ�����µ���Ϣ
				public List<GoodManStuff> getXunWuQiShiStuffByUid(String uid){
					GetXunWuQiShiByUid gxwqs=new GetXunWuQiShiByUid(); 
					List<GoodManStuff> twostuffs = new ArrayList<GoodManStuff>();
						try{				
							stuff=gxwqs.execute(uid).get();
							if(stuff==null){
								return null;
							}
							int x=stuff.size();
							for(int i=0;;i=i+9){//ÿ����Ʒ��9��Ԫ��
								GoodManStuff twostuff=new GoodManStuff();
								twostuff.setgId(stuff.get(i));
								twostuff.setuserId(stuff.get(i+1));
								twostuff.setpTitle(stuff.get(i+2));
								twostuff.setpDetail(stuff.get(i+3));
								twostuff.setpPicture(im.stringtoBitmap(stuff.get(i+4)));
								twostuff.setlinkMan(stuff.get(i+5));
								twostuff.setlinkPhone(stuff.get(i+6));
								twostuff.setlinkQQ(stuff.get(i+7));
								twostuff.setpTime(stuff.get(i+8));
								twostuffs.add(twostuff);
								 if(i+8==x-1){
									 return twostuffs;
								 }
							}
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						}	
				
				//��ȡ����ͬѧ����Ϣ
				public List<GoodManStuff> getFuLiTongXueStuff(){
					GetFuLiTongXueFromWeb gfltx=new GetFuLiTongXueFromWeb(); 
					List<GoodManStuff> twostuffs = new ArrayList<GoodManStuff>();
						try{				
							stuff=gfltx.execute().get();
							if(stuff==null){
								return null;
							}
							int x=stuff.size();
							for(int i=0;;i=i+9){//ÿ����Ʒ��9��Ԫ��
								GoodManStuff twostuff=new GoodManStuff();
								twostuff.setgId(stuff.get(i));
								twostuff.setuserId(stuff.get(i+1));
								twostuff.setpTitle(stuff.get(i+2));
								twostuff.setpDetail(stuff.get(i+3));
								twostuff.setpPicture(im.stringtoBitmap(stuff.get(i+4)));
								twostuff.setlinkMan(stuff.get(i+5));
								twostuff.setlinkPhone(stuff.get(i+6));
								twostuff.setlinkQQ(stuff.get(i+7));
								twostuff.setpTime(stuff.get(i+8));
								twostuffs.add(twostuff);
								 if(i+8==x-1){
									 return twostuffs;
								 }
							}
							//return twostuffs;
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						}	
				
				//�����û�ID��ȡ����ͬѧ����Ϣ
				public List<GoodManStuff> getFuLiTongXueStuffByUid(String uid){
					GetFuLiTongXueByUid gfltx=new GetFuLiTongXueByUid(); 
					List<GoodManStuff> twostuffs = new ArrayList<GoodManStuff>();
						try{				
							stuff=gfltx.execute(uid).get();
							if(stuff==null){
								return null;
							}
							int x=stuff.size();
							for(int i=0;;i=i+9){//ÿ����Ʒ��9��Ԫ��
								GoodManStuff twostuff=new GoodManStuff();
								twostuff.setgId(stuff.get(i));
								twostuff.setuserId(stuff.get(i+1));
								twostuff.setpTitle(stuff.get(i+2));
								twostuff.setpDetail(stuff.get(i+3));
								twostuff.setpPicture(im.stringtoBitmap(stuff.get(i+4)));
								twostuff.setlinkMan(stuff.get(i+5));
								twostuff.setlinkPhone(stuff.get(i+6));
								twostuff.setlinkQQ(stuff.get(i+7));
								twostuff.setpTime(stuff.get(i+8));
								twostuffs.add(twostuff);
								 if(i+8==x-1){
									 return twostuffs;
								 }
							}
							//return twostuffs;
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						}	
}
