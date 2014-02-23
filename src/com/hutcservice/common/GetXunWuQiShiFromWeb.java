package com.hutcservice.common;

import java.io.IOException;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.util.Log;

public class GetXunWuQiShiFromWeb extends AsyncTask<String,Integer,ArrayList<String>>{
    public	ArrayList<String>  alStr=new ArrayList<String>();
 
	@Override
	protected ArrayList<String> doInBackground(String... params) {
		// TODO Auto-generated method stub
		String SOAP_ACTION = Config.namespace+"getXunWuQiShi";
        String METHOD_NAME = "getXunWuQiShi";
        String NAMESPACE = Config.namespace;
        String URL = Config.url;
        
     try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);           
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request); 
            HttpTransportSE transport= new HttpTransportSE(URL);
            transport.call(SOAP_ACTION, soapEnvelope);
            SoapObject result1=(SoapObject)soapEnvelope.getResponse();
            if(result1==null){
            	return null;
            }
            for(int i=0;i<result1.getPropertyCount();i++)
            	alStr.add(result1.getProperty(i).toString());	
        	   return alStr;
            } catch (IOException e) {
			
				e.printStackTrace();
				return null;
			} catch (XmlPullParserException e) {
				
				 Log.e("1234", "Error: " + e.getMessage());
				//e.printStackTrace();
				return null;
				
				
			}	
           
        } 
}
