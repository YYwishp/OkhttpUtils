package com.example.navigationtest;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView idTvContent;
	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);




		idTvContent = (TextView) findViewById(R.id.id_tv_content);


		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
		mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
		mNavigationView.setItemIconTintList(null);

		setupDrawerContent(mNavigationView);
		idTvContent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		});
	}



	private void setupDrawerContent(NavigationView navigationView)
	{
		navigationView.setNavigationItemSelectedListener(

				new NavigationView.OnNavigationItemSelectedListener()
				{
					private MenuItem mPreMenuItem;
					@Override
					public boolean onNavigationItemSelected(MenuItem menuItem)
					{
						/*if (mPreMenuItem != null) {
							mPreMenuItem.setChecked(false);
						} else {
							menuItem.setChecked(true);
							mDrawerLayout.closeDrawers();
							mPreMenuItem = menuItem;
						}*/




						menuItem.setChecked(true);
						mDrawerLayout.closeDrawers();
						return true;
					}
				});
	}



}
