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

	/* 变量声明 */
	private LayoutInflater mInflater;
	private List<QiuGouStuff> QiuGouStuffs;

	/* MyAdapter的建构子，传入三个参数 */
	public QiuGouShowAdapter(Context context,List<QiuGouStuff> proList) {
		/* 参数初始化 */
		mInflater = LayoutInflater.from(context);
		QiuGouStuffs=proList;
	}

	/* 因继承BaseAdapter，需重写以下method */
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
			/* 使用自定义的file_row作为Layout */
			convertView = mInflater.inflate(R.layout.selfimformation_show_row, null);
		}
		QiuGouStuff product= QiuGouStuffs.get(position);
		
		((TextView) convertView.findViewById(R.id.product_title)).setText(product.getpTitle());
		((TextView) convertView.findViewById(R.id.product_time)).setText(product.getpTime());
		
	
		return convertView;
	}
}