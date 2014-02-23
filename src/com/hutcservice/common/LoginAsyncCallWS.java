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

//这个类用来登录
//用来异步调用SERVICE获取服务器端的数据，因为在主线程中不能访问网络，会出现无名的错误。

public class LoginAsyncCallWS extends AsyncTask<String,Integer,Boolean> {
	
	
	public Boolean doInBackground(String...string) {
		// TODO Auto-generated method stub
		String SOAP_ACTION =Config.namespace+"loginJudge"; 
        String METHOD_NAME = "loginJudge ";
        String NAMESPACE = Config.namespace;
        String URL = Config.url;
        
     try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("name", string[0]);  //参数1   姓名
            Request.addProperty("pass", string[1]);   //参数2  密码 
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);
            HttpTransportSE transport= new HttpTransportSE(URL);
			transport.call(SOAP_ACTION, soapEnvelope);
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
       

	protected void onPostExecute(Boolean x) {
        
        this.cancel(false);
    }


	
	

}
