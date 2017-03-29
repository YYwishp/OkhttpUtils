package com.gyx.baidumap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyx.baidumap.clusterutil.clustering.Cluster;
import com.gyx.baidumap.clusterutil.clustering.ClusterItem;
import com.gyx.baidumap.clusterutil.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 聚合坐标点
 */
public class MarkerClusterActivity extends AppCompatActivity implements BaiduMap.OnMapLoadedCallback {

	private MapView mMapView;
	BaiduMap mBaiduMap;
	private ClusterManager<MyItem> mClusterManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marker_cluster);


		mMapView = (MapView) findViewById(R.id.bmapView);
		//1,
		mBaiduMap = mMapView.getMap();
		//设置地图加载完成回调
		mBaiduMap.setOnMapLoadedCallback(this);
		//以动画方式更新地图状态
		// mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));



		// 2, 定义点聚合管理类ClusterManager
		mClusterManager = new ClusterManager<MyItem>(this, mBaiduMap);
		// 3,添加Marker点
		addMarkers();




		// 4,设置地图监听，当地图状态发生改变时，进行点聚合运算
		mBaiduMap.setOnMapStatusChangeListener(mClusterManager);

		// 设置maker点击时的响应
		/*mBaiduMap.setOnMarkerClickListener(mClusterManager);

		mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
			@Override
			public boolean onClusterClick(Cluster<MyItem> cluster) {
				Toast.makeText(getApplicationContext(),
						"有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT).show();

				return false;
			}
		});
		mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
			@Override
			public boolean onClusterItemClick(MyItem item) {
				Toast.makeText(getApplicationContext(),
						"点击单个Item", Toast.LENGTH_SHORT).show();

				return false;
			}
		});*/
	}


	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		super.onDestroy();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	/**
	 * 向地图添加Marker点
	 */
	public void addMarkers() {
		// 添加Marker点
		LatLng llA = new LatLng(39.963175, 116.400244);
		LatLng llB = new LatLng(39.942821, 117.369199);
		LatLng llC = new LatLng(39.939723, 118.425541);
		LatLng llD = new LatLng(39.906965, 119.401394);
		LatLng llE = new LatLng(39.956965, 120.331394);
		LatLng llF = new LatLng(39.886965, 115.441394);
		LatLng llG = new LatLng(39.996965, 114.411394);

		//五个以上才会出现总体数字
		List<MyItem> items = new ArrayList<MyItem>();
		items.add(new MyItem(llA,"http://static.lifemenu.net/11/53846.jpg"));
		items.add(new MyItem(llB,"http://static.lifemenu.net/11/53143.jpg"));
		items.add(new MyItem(llC,"http://static.lifemenu.net/11/53846.jpg"));
		items.add(new MyItem(llD,"http://static.lifemenu.net/11/53846.jpg"));
		items.add(new MyItem(llE,"http://static.lifemenu.net/11/53143.jpg"));
//		items.add(new MyItem(llF,"http://static.lifemenu.net/11/53846.jpg"));
//		items.add(new MyItem(llG,"http://static.lifemenu.net/11/53846.jpg"));

		mClusterManager.addItems(items);
		LatLngBounds.Builder builder = new LatLngBounds.Builder();
		for (MyItem myItem:items) {
			LatLng position = myItem.getPosition();
			//让该地理范围包含一个地理位置坐标
			builder.include(position);
		}
		LatLngBounds latLngBounds = builder.build();

//        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngBounds(latLngBounds));
		//mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(latLngBounds.getCenter(),2));
		mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLngBounds.getCenter()));
		//mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLngBounds(latLngBounds));

	}

	/**
	 * 每个Marker点，包含Marker点坐标以及图标
	 */
	public class MyItem implements ClusterItem {
		private final LatLng mPosition;
		private String mAvatar;
		private SimpleDraweeView imgMapPreviousHeader;
		public MyItem(LatLng latLng,String avatar) {
			mPosition = latLng;
			mAvatar = avatar;
		}

		@Override
		public LatLng getPosition() {
			return mPosition;
		}

		@Override
		public BitmapDescriptor getBitmapDescriptor() {
           /* return BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_gcoding);*/
			View inflate = View.inflate(getApplicationContext(), R.layout.item_map_header, null);
			imgMapPreviousHeader = (SimpleDraweeView) inflate.findViewById(R.id.img_map_previous_header);
			imgMapPreviousHeader.setImageURI(mAvatar);
			return BitmapDescriptorFactory.fromView(inflate);
		}
	}

	@Override
	public void onMapLoaded() {
		// TODO Auto-generated method stub
		//zoom，起最终决定性大小
		MapStatus ms= new MapStatus.Builder().zoom(6).build();
		//  mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
	}
}
