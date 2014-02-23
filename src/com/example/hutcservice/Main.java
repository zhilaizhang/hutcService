package com.example.hutcservice;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Main extends Activity {
    Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Intent intent = new Intent(this, Appsevice.class);
		startService(intent);
		btn=(Button)findViewById(R.id.btntest);
		btn.setOnClickListener(new OnClickListener(){			
			public void onClick(View v) {
				Intent it= new Intent(Main.this,MyGoodThingsActivity.class);
				startActivity(it);
			}			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
