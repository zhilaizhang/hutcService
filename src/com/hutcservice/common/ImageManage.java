package com.hutcservice.common;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.util.Base64;


public class ImageManage {
              //���ݹ���ͼƬ��·��׼����Bitmap64��ʽ���ַ���
            public String rootToString(String root){
            	  ////FileInputStream fis = new FileInputStream(Config.ROOTPATH+"/"+"myImage"+"/"+"111.jpg");  
            	FileInputStream fis;
				try {
					fis = new FileInputStream(root);
                 BitmapFactory.Options options = new BitmapFactory.Options();  
                 options.inJustDecodeBounds = false;  
                 options.inSampleSize = 5;  
                 //ʹ��λͼ�����ཫ����ͼƬ��Ϣ��������ת����λͼ  
                 Bitmap bm= BitmapFactory.decodeStream(fis, null, options);  
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
                 bm.compress(CompressFormat.PNG, 100, baos);  
                 byte[] bytes = baos.toByteArray();    
                String string = Base64.encodeToString(bytes,  Base64.DEFAULT); 
                return string;
				} catch (FileNotFoundException e) {
                     e.printStackTrace();
					return null;
				}  
            }
            
            //��λͼת�����ַ���
            public String bitmapToString(Bitmap bitmap){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();  
				   bitmap.compress(CompressFormat.PNG, 100, baos);  
				   byte[] bytes = baos.toByteArray();    
				  String string = Base64.encodeToString(bytes,  Base64.DEFAULT); 
				  return string;  
          }
            // ���ַ���ת����Bitmap����   
            public Bitmap stringtoBitmap(String string) {                   
                Bitmap bitmap = null;  
                try {  
                       byte[] bitmapArray;   
                        bitmapArray = Base64.decode(string, Base64.DEFAULT);  
                        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,    
                                        bitmapArray.length);    
                } catch (Exception e) {  
                        e.printStackTrace();    
                }  
                return bitmap;  
  
        }    
}
