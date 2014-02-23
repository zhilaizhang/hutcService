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

	/* �������� */
	private LayoutInflater mInflater;
	private List<TwoHandStuff> TwoHandStuffs;

	/* MyAdapter�Ľ����ӣ������������� */
	public MyStuffShowAdapter(Context context,List<TwoHandStuff> proList) {
		/* ������ʼ�� */
		mInflater = LayoutInflater.from(context);
		TwoHandStuffs=proList;
	}

	/* ��̳�BaseAdapter������д����method */
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
			/* ʹ���Զ����file_row��ΪLayout */
			convertView = mInflater.inflate(R.layout.bike_show_row, null);
		}
		
		TwoHandStuff product= TwoHandStuffs.get(position);
		/* �趨Ҫ��ʾ����Ϣ */
		if(product.getImagePath()!=null){
			Bitmap imageBitmap = BitmapFactory.decodeFile(product.getImagePath());
			((ImageView) convertView.findViewById(R.id.product_image)).setImageBitmap(imageBitmap);
			
		}else{
			((ImageView) convertView.findViewById(R.id.product_image)).setImageBitmap(product.getImage());
		}
		((TextView) convertView.findViewById(R.id.product_title)).setText(product.getTitle());
		((TextView) convertView.findViewById(R.id.product_time)).setText(product.gettime());
		( (TextView) convertView.findViewById(R.id.product_price)).setText("��" + product.getPrice());
	
		return convertView;
	}
}
