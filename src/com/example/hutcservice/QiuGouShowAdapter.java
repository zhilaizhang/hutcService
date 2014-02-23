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


import com.hutcservice.database.QiuGouStuff;

public class QiuGouShowAdapter  extends BaseAdapter {

	/* �������� */
	private LayoutInflater mInflater;
	private List<QiuGouStuff> QiuGouStuffs;

	/* MyAdapter�Ľ����ӣ������������� */
	public QiuGouShowAdapter(Context context,List<QiuGouStuff> proList) {
		/* ������ʼ�� */
		mInflater = LayoutInflater.from(context);
		QiuGouStuffs=proList;
	}

	/* ��̳�BaseAdapter������д����method */
	@Override
	public int getCount() {
		return QiuGouStuffs.size();
	}

	@Override
	public Object getItem(int position) {
		return QiuGouStuffs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			/* ʹ���Զ����file_row��ΪLayout */
			convertView = mInflater.inflate(R.layout.selfimformation_show_row, null);
		}
		QiuGouStuff product= QiuGouStuffs.get(position);
		
		((TextView) convertView.findViewById(R.id.product_title)).setText(product.getpTitle());
		((TextView) convertView.findViewById(R.id.product_time)).setText(product.getpTime());
		
	
		return convertView;
	}
}