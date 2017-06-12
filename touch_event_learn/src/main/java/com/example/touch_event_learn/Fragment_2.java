package com.example.touch_event_learn;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GYX on 2017/6/7.
 */
public class Fragment_2 extends Fragment {
	private SwipeMenuRecyclerView recyclerView;
	private ArrayList<String> mStrings;
	private MenuAdapter mMenuAdapter;
	private TwinklingRefreshLayout refresh;
	private MyAdapter myAdapter;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_2,container,false);
		recyclerView = (SwipeMenuRecyclerView) inflate.findViewById(R.id.recycler_view);
		
		
		refresh = (TwinklingRefreshLayout) inflate.findViewById(R.id.refresh);
		
		mStrings = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			mStrings.add("我是第" + i + "个。");
		}
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));// 布局管理器。
		recyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
		recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//		recyclerView.addItemDecoration(new ListViewDecoration());// 添加分割线。
		
		// 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
		// 设置菜单创建器。
		recyclerView.setSwipeMenuCreator(swipeMenuCreator);
		// 设置菜单Item点击监听。
//		recyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
		//想要可以侧滑，就不能进行viewpager滑动就要用MenuAdapter
		mMenuAdapter = new MenuAdapter(mStrings);
		//没有侧滑菜单，就用自己的adapter，这样就可以和viewpager一样滑动
		myAdapter = new MyAdapter(getContext(), mStrings);
//		mMenuAdapter.setOnItemClickListener(onItemClickListener);
		recyclerView.setAdapter(mMenuAdapter);
		return inflate;

	}
	
	
	
	/**
	 * 菜单创建器。在Item要创建菜单的时候调用。
	 */
	private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
		@Override
		public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
			int width = getResources().getDimensionPixelSize(R.dimen.item_height);
			
			// MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
			int height = ViewGroup.LayoutParams.MATCH_PARENT;
			
			// 添加左侧的，如果不添加，则左侧不会出现菜单。
			{
				SwipeMenuItem addItem = new SwipeMenuItem(getContext())
						.setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
						.setImage(R.mipmap.ic_action_add) // 图标。
						.setWidth(width) // 宽度。
						.setHeight(height); // 高度。
				swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
				
				SwipeMenuItem closeItem = new SwipeMenuItem(getContext())
						.setBackgroundDrawable(R.drawable.selector_red)
						.setImage(R.mipmap.ic_action_close)
						.setWidth(width)
						.setHeight(height);
				
				swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
			}
			
			// 添加右侧的，如果不添加，则右侧不会出现菜单。
			{
				SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
						.setBackgroundDrawable(R.drawable.selector_red)
						.setImage(R.mipmap.ic_action_delete)
						.setText("删除") // 文字，还可以设置文字颜色，大小等。。
						.setTextColor(Color.WHITE)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
				
				SwipeMenuItem closeItem = new SwipeMenuItem(getContext())
						.setBackgroundDrawable(R.drawable.selector_purple)
						.setImage(R.mipmap.ic_action_close)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。
				
				SwipeMenuItem addItem = new SwipeMenuItem(getContext())
						.setBackgroundDrawable(R.drawable.selector_green)
						.setText("添加")
						.setTextColor(Color.WHITE)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
			}
		}
	};
	class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
		private final LayoutInflater mInflate;
		private Context mContext;
		private List<String> mDatas;
		public MyAdapter(Context context, List<String> datas) {
			this.mDatas = datas;
			this.mContext = context;
			mInflate = LayoutInflater.from(context);
		}
		
		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = mInflate.inflate(R.layout.item, parent, false);
			
			MyViewHolder myViewHolder = new MyViewHolder(view);
			return myViewHolder;
			
			
		}
		
		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {
			holder.textview.setText(mDatas.get(position));
		}
		
		@Override
		public int getItemCount() {
			return mDatas.size();
		}
	}
	
	class MyViewHolder extends RecyclerView.ViewHolder {
		
		TextView textview;
		
		public MyViewHolder(View itemView) {
			super(itemView);
			textview = (TextView) itemView.findViewById(R.id.tv_title);
			
		}
	}
	
}
