package com.example.swiperecycler;

import android.content.Context;
import android.view.View;

/**
 * Created by GYX on 2016/11/2.
 */
//父类不知道子类显示什么界面,填充什么数据,所以,创建抽象类,让子类根据自己的特性去实现
public abstract class BasePager {
    public Context mContext;
    public View mView;

    public BasePager(Context context) {
        this.mContext = context;
        //表示在创建界面的时候加载相应界面的布局
        mView = initView();
    }

    /**
     * 加载布局
     */
    public abstract View initView();

    /**
     * 填充数据
     */
    public abstract void initData();

    /**
     * 获取pager页面对象的方法
     */
    public abstract View getRootView();


}
