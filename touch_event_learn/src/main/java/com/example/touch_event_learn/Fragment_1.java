package com.example.touch_event_learn;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GYX on 2017/6/7.
 */
public class Fragment_1 extends Fragment {
	private SuperSwipeRefreshLayout refresh;
	private SwipeMenuRecyclerView recyclerView;

	private ArrayList<String> list;
	private MyAdapter myAdapter;
	private MenuAdapter mMenuAdapter;
	
	int n;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_1,container,false);


//		refresh = (SuperSwipeRefreshLayout) inflate.findViewById(R.id.refresh);

		recyclerView = (SwipeMenuRecyclerView) inflate.findViewById(R.id.recycler_view);
		
		
		list = new ArrayList<>();
		for (int i = 0; i <40; i++) {

			list.add("1111111");
		}
		myAdapter = new MyAdapter(getContext(),list);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));// 布局管理器。
		recyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
		recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
		
		recyclerView.setSwipeMenuCreator(swipeMenuCreator);
		mMenuAdapter = new MenuAdapter(list);
		recyclerView.setAdapter(mMenuAdapter);
		
		//刷新头
		/*SinaRefreshView headerView = new SinaRefreshView(getContext());
		refresh.setHeaderView(headerView);
		//加载更多
		LoadingView loadingView = new LoadingView(getContext());
		refresh.setBottomView(loadingView);
		//自动加载更多
		refresh.setAutoLoadMore(true);
		refresh.startRefresh();
		refresh.setOnRefreshListener(new RefreshListenerAdapter() {
			@Override
			public void onRefresh(TwinklingRefreshLayout refreshLayout) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {

						list.clear();
						for (int i = 0; i < 20; i++) {
							list.add(i+"*******");
						}
						myAdapter.notifyDataSetChanged();
						refresh.finishRefreshing();
					}
				}, 2000);




			}

			@Override
			public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
				super.onLoadMore(refreshLayout);

				n++;
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);

				myAdapter.notifyDataSetChanged();
				refresh.finishLoadmore();

			}
		});*/


		return inflate;

	}
	
	/**
	 * 菜单点击监听。
	 */
	private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
		/**
		 * Item的菜单被点击的时候调用。
		 * @param closeable       closeable. 用来关闭菜单。
		 * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
		 * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
		 * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
		 */
		@Override
		public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
			closeable.smoothCloseMenu();// 关闭被点击的菜单。

			/*if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
				Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
			} else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
				Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
			}*/
			Toast.makeText(getContext(), "被点击了", Toast.LENGTH_SHORT).show();
		}
	};

	/**
	 * 菜单创建器。在Item要创建菜单的时候调用。
	 */
	private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
		@Override
		public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
			int width = getResources().getDimensionPixelSize(R.dimen.item_width);
			int size = getResources().getDimensionPixelSize(R.dimen.item_menu_text_size);
			// MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
			int height = ViewGroup.LayoutParams.MATCH_PARENT;
			// 添加右侧的，如果不添加，则右侧不会出现菜单。
			{
				SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
						.setBackgroundDrawable(R.drawable.selector_red)
						//.setImage(R.mipmap.ic_action_delete)
						.setText("隐藏") // 文字，还可以设置文字颜色，大小等。。
						.setTextColor(Color.WHITE)
						.setTextSize(size)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
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
