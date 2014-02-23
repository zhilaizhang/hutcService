package com.example.hutcservice;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;



import com.hutcservice.common.*;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;


public class test extends Activity{
	         private String string1=null;
             public void onCreate(Bundle savedInstanceState){
            	 super.onCreate(savedInstanceState);
            	 ImageDownload idown=new ImageDownload();
            	// ImageUpload iu=new ImageUpload();
            	// string1=testUpload();
            	 idown.execute();
             }
             public String testUpload(){ 
            	 String uploadBuffer;
                 try{  
                	 
       
                     FileInputStream fis = new FileInputStream(Config.ROOTPATH+"/"+"myImage"+"/"+"111.jpg");  
                     //
                     BitmapFactory.Options options = new BitmapFactory.Options();  
                     options.inJustDecodeBounds = false;  
                     options.inSampleSize = 5;  
                     //使用位图工厂类将包含图片信息的输入流转换成位图  
                   Bitmap bm= BitmapFactory.decodeStream(fis, null, options);  
                     //
                   
                     ByteArrayOutputStream baos = new ByteArrayOutputStream();  
                     
                     bm.compress(CompressFormat.PNG, 100, baos);  
                     
                     byte[] bytes = baos.toByteArray();  
                     //bytes.length()
                    String string = Base64.encodeToString(bytes,  Base64.DEFAULT); 
                     
                     
                     
                     
                
                     return string;
                 }catch(Exception e){  
                     e.printStackTrace();  
                     return null;
                 }
             }  
}
