package com.example.hutcservice;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hutcservice.database.QiuGouStuff;

//�����������������е�����Ϣ
public class QiuGouShowAllAdapter extends BaseAdapter {

	/* �������� */
	private LayoutInflater mInflater;
	private List<QiuGouStuff> QiuGouStuffs;
	String type;
	/* MyAdapter�Ľ����ӣ������������� */
	public QiuGouShowAllAdapter(Context context,List<QiuGouStuff> proList) {
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
			convertView = mInflater.inflate(R.layout.qiugou_show_row, null);
		}
		QiuGouStuff product= QiuGouStuffs.get(position);
		
		if((product.getclassId()).equals("1")){
			type="���г�";
		}
		if((product.getclassId()).equals("2")){
			type="��ƿ��";
		}
		if((product.getclassId()).equals("3")){
			type="��������";
		}
		if((product.getclassId()).equals("4")){
			type="������Ʒ";
		}
		if((product.getclassId()).equals("5")){
			type="������Ʒ";
		}
		
		
		((TextView) convertView.findViewById(R.id.qiugou_type)).setText(type);
		((TextView) convertView.findViewById(R.id.qiugou_title)).setText(product.getpTitle());
		((TextView) convertView.findViewById(R.id.qiugou_time)).setText(product.getpTime());
		
	
		return convertView;
	}
}
