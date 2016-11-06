package com.example.gyx.okhttputils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;



public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadNetworkData();
        test();
    }

    private void test() {
        String json = "{\"data\":null,\"info\":\"success\",\"status\":10000}";
        Gson gson = new Gson();
        Bean bean = gson.fromJson(json, Bean.class);
        String s = bean.toString();
        LogUtil.e("对象本体",""+s);
        LogUtil.e("解析成功", "" + bean.getData() + "--" + bean.getInfo() + "--" + bean.getStatus());
        Logger.e("nihao");
    }

    private void loadNetworkData() {
        //做法1：get网络请求的普通做法
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result_String = OKHttpUtils.loadStringFromUrl("http://m2.qiushibaike.com/article/list/latest?page=1");
                    if (result_String == null) {
                        Toast.makeText(mContext, "网络加载失败", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.e("主函数", ""+result_String);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, "操作UI", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();*/

        //做法2：get网络请求的高级方法 (okhttp3版本)
        /*OKHttpUtils.getDataByNewThread("http://m2.qiushibaike.com/article/list/latest?page=1", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(mContext, "网络加载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.e("onResponse", "--------->>onResponse" + Thread.currentThread().getId());//子线程
                    //得到数据
                    response.body().toString();


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("handler.post", "--------->>handler.post" + Thread.currentThread().getId());//主线程
                            Toast.makeText(mContext, "操作UI", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });*/
        //做法2：get网络请求的高级方法 (okhttp2.7.5版本)
        /*OKHttpUtils.getDataByNewThread("http://m2.qiushibaike.com/article/list/latest?page=1", new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                 Toast.makeText(mContext, "网络加载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.e("onResponse", "--------->>onResponse" + Thread.currentThread().getId());//子线程
                    //得到数据
                    response.body().toString();


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("handler.post", "--------->>handler.post" + Thread.currentThread().getId());//主线程
                            Toast.makeText(mContext, "操作UI", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });*/

    }
}
































