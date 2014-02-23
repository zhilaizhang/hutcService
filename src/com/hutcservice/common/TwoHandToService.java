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

public class TwoHandToService extends AsyncTask<String, Integer, Boolean> {
	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		String SOAP_ACTION = Config.namespace + "publishTwoHand";
		String METHOD_NAME = "publishTwoHand";
		String NAMESPACE = Config.namespace;
		String URL = Config.url;
		try {
			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("uid", params[0]); // �û�id
			Request.addProperty("classid", params[1]); // ����id
			Request.addProperty("title", params[2]); // ����
			Request.addProperty("detail", params[3]); // ��ϸ��Ϣ
			Request.addProperty("price", params[4]); // �۸�
			Request.addProperty("picture", params[5]); // ͼƬת�����ַ���
			Request.addProperty("linkman", params[6]); // ��ϵ��
			Request.addProperty("linkphone", params[7]); // ��ϵ�绰
			Request.addProperty("linkqq", params[8]); // ��ϵQQ
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
