package com.hutcservice.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import android.os.Environment;

public class Storage {
	
	public static boolean saveFile(String fileName,byte[] data){
		try {
				File dir=new File(Config.ROOTPATH);
				if(!dir.exists())
					dir.mkdir();
				File file=new File(Config.ROOTPATH+"/"+fileName);
				if(!file.exists())
					file.createNewFile();
				FileOutputStream fos =new FileOutputStream(file);  
	            fos.write(data);  
	            fos.close();  
	            return true;
            
        } catch (Exception e) {
        }  
    	return false;
		
	}
	
	public static File getFile(String fileName,byte[]data){
		if(saveFile(fileName,data))
			return new File(Config.ROOTPATH+"/"+fileName);
		return null;
	}
	public static byte[] getFile(String fileName) {
		byte[] buffer=null;
		String path=Config.ROOTPATH+"/"+fileName;
        File file=new File(path);
        if (file.exists()) {
        	buffer=new byte[(int) file.length()];
    		try {  
                @SuppressWarnings("resource")
				FileInputStream fis =new FileInputStream(path);  
                fis.read(buffer);
            } catch (Exception e) {
            	buffer=null;
            }
		}
		return buffer;
	}
}
