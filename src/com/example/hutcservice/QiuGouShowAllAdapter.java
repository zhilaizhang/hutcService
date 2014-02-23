package com.example.hutcservice;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hutcservice.database.QiuGouStuff;

//这个适配器来填充所有的求购信息
public class QiuGouShowAllAdapter extends BaseAdapter {

	/* 变量声明 */
	private LayoutInflater mInflater;
	private List<QiuGouStuff> QiuGouStuffs;
	String type;
	/* MyAdapter的建构子，传入三个参数 */
	public QiuGouShowAllAdapter(Context context,List<QiuGouStuff> proList) {
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
			convertView = mInflater.inflate(R.layout.qiugou_show_row, null);
		}
		QiuGouStuff product= QiuGouStuffs.get(position);
		
		if((product.getclassId()).equals("1")){
			type="自行车";
		}
		if((product.getclassId()).equals("2")){
			type="电瓶车";
		}
		if((product.getclassId()).equals("3")){
			type="数码世界";
		}
		if((product.getclassId()).equals("4")){
			type="文体用品";
		}
		if((product.getclassId()).equals("5")){
			type="生活用品";
		}
		
		
		((TextView) convertView.findViewById(R.id.qiugou_type)).setText(type);
		((TextView) convertView.findViewById(R.id.qiugou_title)).setText(product.getpTitle());
		((TextView) convertView.findViewById(R.id.qiugou_time)).setText(product.getpTime());
		
	
		return convertView;
	}
}
