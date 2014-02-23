package com.example.hutcservice;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Path.FillType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<Bitmap> images;

	// 构造函数
	public ImageAdapter(Context context, List<Bitmap> image) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		/* 参数初始化 */
		images = image;
	}

	// 返回所有图片的个数
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.size();
	}

	// 返回图片在资源的位置
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 返回图片在资源的位置
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			/* 使用自定义的file_row作为Layout */
			convertView = mInflater
					.inflate(R.layout.product_show_one_row, null);
		}

		/* 设定要显示的信息 */
		((ImageView) convertView.findViewById(R.id.product_show_one_img))
				.setImageBitmap(images.get(position));
		return convertView;
	}

}
