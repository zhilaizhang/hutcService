package com.hutcservice.common;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.util.Log;

public class FaBuErShouQiuGouToWeb extends AsyncTask<String,Integer,Boolean>{

	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		String SOAP_ACTION = Config.namespace+"faBuQiuGouXinXi";
        String METHOD_NAME = "faBuQiuGouXinXi";
        String NAMESPACE = Config.namespace;
        String URL = Config.url;
        
     try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("uid", params[0]);  //用户id
            Request.addProperty("classid", params[1]);  //分类id
            Request.addProperty("title", params[2]);  //标题
            Request.addProperty("detail", params[3]);  //详细信息
            Request.addProperty("linkman", params[4]);  //联系人
            Request.addProperty("linkphone", params[5]);  //联系电话
            Request.addProperty("linkqq", params[6]);  //联系QQ
            
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);
            HttpTransportSE transport= new HttpTransportSE(URL);
            transport.call(SOAP_ACTION, soapEnvelope);
            SoapObject result = ((SoapObject)soapEnvelope.bodyIn);
            SoapPrimitive resultString = (SoapPrimitive)soapEnvelope.getResponse();
           if(resultString.toString().equals("true")){
        	   return true;
           }else{
        	   return false;
           }
            } catch (IOException e) {
			
				e.printStackTrace();
				return false;
			} 
     catch (XmlPullParserException e) {
				
				 Log.e("1234", "Error: " + e.getMessage());
				//e.printStackTrace();
				return false;
				
				
			}	
           
        } 
	
}
