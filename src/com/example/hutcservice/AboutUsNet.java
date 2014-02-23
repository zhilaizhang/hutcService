package com.example.hutcservice;

import android.os.Bundle;
import android.webkit.WebView;

public class AboutUsNet extends BaseActivity{
	private WebView webview;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutnet);
		webview=(WebView)findViewById(R.id.web_view);
		webview.loadUrl("http://www.koyisa.com");
	}
}
