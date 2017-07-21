package edu.tjpu.DefineAdapter;

import java.util.List;
import java.util.Map;

import com.example.finalworks.R;

import edu.tjpu.MyViewHolder.myViewHolder;
import edu.tjpu.circle.CircularImage;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NoteAdapter extends BaseAdapter {
	
	private Context mycontet; // 上下文环境
	private List<Map<String, ?>> items; // 数据资源集合
	private LayoutInflater inflater; // 动态绑定接口
	
	public NoteAdapter(Context mycontext, List<Map<String, ?>> items) {
		super();
		this.mycontet = mycontext;
		this.items = items;
		this.inflater = LayoutInflater.from(this.mycontet);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.items.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		edu.tjpu.MyViewHolder.myViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new myViewHolder();// 实例化holder组件
			
			//convertView = this.inflater.inflate(R.layout.pagenote_items, null); //绑定布局资源文件
			convertView = this.inflater.inflate(R.layout.listview_item, null); // 绑定布局资源文件

			viewHolder.item_title = (TextView) convertView
					.findViewById(R.id.item_title);
			viewHolder.item_date = (TextView) convertView
					.findViewById(R.id.item_date);
			viewHolder.imgRegisterPhoto2 = (CircularImage) convertView
					.findViewById(R.id.imgRegisterPhoto2);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (myViewHolder) convertView.getTag();
		}
		
		viewHolder.item_title.setText(items.get(position).get("title")
				.toString());
		viewHolder.item_date
				.setText(items.get(position).get("time").toString());
		viewHolder.imgRegisterPhoto2.setImageBitmap((Bitmap) items
				.get(position).get("image"));
		return convertView;
	}
}

