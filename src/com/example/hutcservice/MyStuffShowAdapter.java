package com.example.hutcservice;

import com.hutcservice.database.*;

import java.util.List;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyStuffShowAdapter extends BaseAdapter {

	/* 变量声明 */
	private LayoutInflater mInflater;
	private List<TwoHandStuff> TwoHandStuffs;

	/* MyAdapter的建构子，传入三个参数 */
	public MyStuffShowAdapter(Context context,List<TwoHandStuff> proList) {
		/* 参数初始化 */
		mInflater = LayoutInflater.from(context);
		TwoHandStuffs=proList;
	}

	/* 因继承BaseAdapter，需重写以下method */
	@Override
	public int getCount() {	
		if(TwoHandStuffs.equals(null)){
			return 0;
		}else{
		return TwoHandStuffs.size();}
	}

	@Override
	public Object getItem(int position) {
		return TwoHandStuffs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			/* 使用自定义的file_row作为Layout */
			convertView = mInflater.inflate(R.layout.bike_show_row, null);
		}
		
		TwoHandStuff product= TwoHandStuffs.get(position);
		/* 设定要显示的信息 */
		if(product.getImagePath()!=null){
			Bitmap imageBitmap = BitmapFactory.decodeFile(product.getImagePath());
			((ImageView) convertView.findViewById(R.id.product_image)).setImageBitmap(imageBitmap);
			
		}else{
			((ImageView) convertView.findViewById(R.id.product_image)).setImageBitmap(product.getImage());
		}
		((TextView) convertView.findViewById(R.id.product_title)).setText(product.getTitle());
		((TextView) convertView.findViewById(R.id.product_time)).setText(product.gettime());
		( (TextView) convertView.findViewById(R.id.product_price)).setText("￥" + product.getPrice());
	
		return convertView;
	}
}
