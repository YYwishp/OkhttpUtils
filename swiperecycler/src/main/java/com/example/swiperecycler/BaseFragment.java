package com.example.swiperecycler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GYX on 2016/10/26.
 */
public abstract class BaseFragment extends Fragment {
    public View view;
    public Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initview();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 加载布局
     */
    public abstract View initview();

    /**
     * 填充数据
     */
    public abstract void initData();

    /**
     * 隐藏显示fragment
     * menuVisible : 系统事先维护好的一个值,true:显示  false:隐藏,系统根据fragment显示隐藏情况将值修改传递过来
     */
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        //隐藏显示fragment操作
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
        super.setMenuVisibility(menuVisible);
    }
}
