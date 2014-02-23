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

public class RegisterAsyncCallWS extends AsyncTask<String, Integer, Boolean> {
	@Override
	protected Boolean doInBackground(String... params) {
		String SOAP_ACTION = Config.namespace + "register";
		String METHOD_NAME = "register";
		String NAMESPACE = Config.namespace;
		String URL = Config.url;
		try {
			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("name", params[0]); // 参数1 姓名
			Request.addProperty("pass", params[1]); // 参数2 密码
			Request.addProperty("phone", "1234567");
			Request.addProperty("email", "1234325556");
			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);
			HttpTransportSE transport = new HttpTransportSE(URL);
			transport.call(SOAP_ACTION, soapEnvelope);
			SoapPrimitive resultString = (SoapPrimitive) soapEnvelope
					.getResponse();
			if (resultString.toString().equals("true")) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (XmlPullParserException e) {
			Log.e("1234", "Error: " + e.getMessage());
			return false;

		}

	}

}
