package com.example.hutcservice;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hutcservice.database.GoodManStuff;


public class GoodManShowAdapter extends BaseAdapter {

	/* �������� */
	private LayoutInflater mInflater;
	private List<GoodManStuff> GoodManStuffs;

	/* MyAdapter�Ľ����ӣ������������� */
	public GoodManShowAdapter(Context context,List<GoodManStuff> proList) {
		/* ������ʼ�� */
		mInflater = LayoutInflater.from(context);
		GoodManStuffs=proList;
	}

	/* ��̳�BaseAdapter������д����method */
	@Override
	public int getCount() {
		return GoodManStuffs.size();
	}

	@Override
	public Object getItem(int position) {
		return GoodManStuffs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			/* ʹ���Զ����file_row��ΪLayout */
			convertView = mInflater.inflate(R.layout.goodman_show_row, null);
		}
		GoodManStuff product= GoodManStuffs.get(position);
		/* �趨Ҫ��ʾ����Ϣ */
		if(product.getImagePath()!=null){
			Bitmap imageBitmap = BitmapFactory.decodeFile(product.getImagePath());
			((ImageView) convertView.findViewById(R.id.product_image)).setImageBitmap(imageBitmap);
			
		}else
		((ImageView) convertView.findViewById(R.id.product_image)).setImageBitmap(product.getpPicture());
		((TextView) convertView.findViewById(R.id.product_title)).setText(product.getpTitle());
		((TextView) convertView.findViewById(R.id.product_time)).setText(product.getpTime());
		
	
		return convertView;
	}
}
