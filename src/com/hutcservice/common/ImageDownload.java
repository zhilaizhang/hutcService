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

public class ImageDownload extends AsyncTask<String,Integer,Boolean>{

	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		String SOAP_ACTION = "http://tempuri.org/imageTostring";
        String METHOD_NAME = "imageTostring";
        String NAMESPACE = "http://tempuri.org/";
        String URL = "http://koyisa.com/HutcService.asmx";
        
     try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);      
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