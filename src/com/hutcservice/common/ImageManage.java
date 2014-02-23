package com.hutcservice.common;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.util.Base64;


public class ImageManage {
              //传递过来图片的路径准换成Bitmap64格式的字符串
            public String rootToString(String root){
            	  ////FileInputStream fis = new FileInputStream(Config.ROOTPATH+"/"+"myImage"+"/"+"111.jpg");  
            	FileInputStream fis;
				try {
					fis = new FileInputStream(root);
                 BitmapFactory.Options options = new BitmapFactory.Options();  
                 options.inJustDecodeBounds = false;  
                 options.inSampleSize = 5;  
                 //使用位图工厂类将包含图片信息的输入流转换成位图  
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
            
            //将位图转换成字符串
            public String bitmapToString(Bitmap bitmap){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();  
				   bitmap.compress(CompressFormat.PNG, 100, baos);  
				   byte[] bytes = baos.toByteArray();    
				  String string = Base64.encodeToString(bytes,  Base64.DEFAULT); 
				  return string;  
          }
            // 将字符串转换成Bitmap类型   
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
