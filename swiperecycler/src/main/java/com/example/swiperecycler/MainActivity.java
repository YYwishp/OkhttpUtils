package com.example.swiperecycler;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private Activity mContext;

	private SwipeRefreshLayout mSwipeRefreshLayout;

	private MenuAdapter mMenuAdapter;

	private List<String> mStrings;

	private SwipeMenuRecyclerView mSwipeMenuRecyclerView;

	private int size = 50;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		mContext = this;
		//刷新
		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
		mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

		mStrings = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			mStrings.add("我是第" + i + "个。");
		}

		//RecyclerView
		mSwipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
		mSwipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
		mSwipeMenuRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
		mSwipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
		mSwipeMenuRecyclerView.addItemDecoration(new ListViewDecoration());// 添加分割线。
		// 添加滚动监听。
		mSwipeMenuRecyclerView.addOnScrollListener(mOnScrollListener);

		// 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
		// 设置菜单创建器。
		mSwipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
		// 设置菜单Item点击监听。
		mSwipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
		//传递数据
		mMenuAdapter = new MenuAdapter(mStrings);
		//RecyclerView点击世事件
		mMenuAdapter.setOnItemClickListener(onItemClickListener);
		//设置适配器
		mSwipeMenuRecyclerView.setAdapter(mMenuAdapter);
	}

	/**
	 * 刷新监听。
	 */
	private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
		@Override
		public void onRefresh() {
			mSwipeMenuRecyclerView.postDelayed(new Runnable() {
				@Override
				public void run() {
					//刷新
					mSwipeRefreshLayout.setRefreshing(false);
				}
			}, 2000);
		}
	};

	/**
	 * 加载更多
	 */
	private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
				// TODO 这里有个注意的地方，如果你刚进来时没有数据，但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。

				Toast.makeText(MainActivity.this, "滑到最底部了，去加载更多吧！", Toast.LENGTH_SHORT).show();
				size += 50;
				for (int i = size - 50; i < size; i++) {
					mStrings.add("我是第" + i + "个。");
				}
				mMenuAdapter.notifyDataSetChanged();
			}
		}
	};

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
				SwipeMenuItem addItem = new SwipeMenuItem(mContext)
						.setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
						//.setImage(R.mipmap.ic_action_add) // 图标。
						.setWidth(width) // 宽度。
						.setHeight(height); // 高度。
				swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

				SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
						.setBackgroundDrawable(R.drawable.selector_red)
						//.setImage(R.mipmap.ic_action_close)
						.setWidth(width)
						.setHeight(height);

				swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
			}

			// 添加右侧的，如果不添加，则右侧不会出现菜单。
			{
				SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
						.setBackgroundDrawable(R.drawable.selector_red)
						//.setImage(R.mipmap.ic_action_delete)
						.setText("删除") // 文字，还可以设置文字颜色，大小等。。
						.setTextColor(Color.WHITE)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

				SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
						.setBackgroundDrawable(R.drawable.selector_purple)
						//.setImage(R.mipmap.ic_action_close)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

				SwipeMenuItem addItem = new SwipeMenuItem(mContext)
						.setBackgroundDrawable(R.drawable.selector_green)
						.setText("添加")
						.setTextColor(Color.WHITE)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
			}
		}
	};
	/**
	 * RecyclerView点击事件
	 */
	private OnItemClickListener onItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(int position) {
			Toast.makeText(mContext, "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
		}
	};

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

			if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
				Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
			} else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
				Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
			}

			// TODO 推荐调用Adapter.notifyItemRemoved(position)，也可以Adapter.notifyDataSetChanged();
			if (menuPosition == 0) {// 删除按钮被点击。
				mStrings.remove(adapterPosition);
				mMenuAdapter.notifyItemRemoved(adapterPosition);
			}
		}
	};



}
