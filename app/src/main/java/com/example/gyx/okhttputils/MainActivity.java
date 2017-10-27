package com.example.gyx.okhttputils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    private Handler handler = new Handler();
    private EditText edit;
    
    private String json = "{\n" +
            "  \"AUD\": {\n" +
            "    \"15m\": 7784.63,\n" +
            "    \"last\": 7784.63,\n" +
            "    \"buy\": 7780.18,\n" +
            "    \"sell\": 7786.41,\n" +
            "    \"symbol\": \"AU$\"\n" +
            "  },\n" +
            "  \"BRL\": {\n" +
            "    \"15m\": 19557.77,\n" +
            "    \"last\": 19557.77,\n" +
            "    \"buy\": 19546.58,\n" +
            "    \"sell\": 19562.22,\n" +
            "    \"symbol\": \"R$\"\n" +
            "  },\n" +
            "  \"CAD\": {\n" +
            "    \"15m\": 7649.33,\n" +
            "    \"last\": 7649.33,\n" +
            "    \"buy\": 7644.96,\n" +
            "    \"sell\": 7651.07,\n" +
            "    \"symbol\": \"CA$\"\n" +
            "  },\n" +
            "  \"CHF\": {\n" +
            "    \"15m\": 5934.5,\n" +
            "    \"last\": 5934.5,\n" +
            "    \"buy\": 5931.11,\n" +
            "    \"sell\": 5935.85,\n" +
            "    \"symbol\": \"?\"\n" +
            "  },\n" +
            "  \"CLP\": {\n" +
            "    \"15m\": 3774278.79,\n" +
            "    \"last\": 3774278.79,\n" +
            "    \"buy\": 3772120.57,\n" +
            "    \"sell\": 3775138.26,\n" +
            "    \"symbol\": \"CL$\"\n" +
            "  },\n" +
            "  \"CNY\": {\n" +
            "    \"15m\": 39588.25,\n" +
            "    \"last\": 39588.25,\n" +
            "    \"buy\": 39565.61,\n" +
            "    \"sell\": 39597.27,\n" +
            "    \"symbol\": \"￥\"\n" +
            "  },\n" +
            "  \"CZK\": {\n" +
            "    \"15m\": 131234.42,\n" +
            "    \"last\": 131234.42,\n" +
            "    \"buy\": 131159.38,\n" +
            "    \"sell\": 131264.3,\n" +
            "    \"symbol\": \"K\"\n" +
            "  },\n" +
            "  \"DKK\": {\n" +
            "    \"15m\": 38029.49,\n" +
            "    \"last\": 38029.49,\n" +
            "    \"buy\": 38007.74,\n" +
            "    \"sell\": 38038.15,\n" +
            "    \"symbol\": \"kr\"\n" +
            "  },\n" +
            "  \"EUR\": {\n" +
            "    \"15m\": 5110.41,\n" +
            "    \"last\": 5110.41,\n" +
            "    \"buy\": 5107.49,\n" +
            "    \"sell\": 5111.57,\n" +
            "    \"symbol\": \"€\"\n" +
            "  },\n" +
            "  \"GBP\": {\n" +
            "    \"15m\": 4528.81,\n" +
            "    \"last\": 4528.81,\n" +
            "    \"buy\": 4526.22,\n" +
            "    \"sell\": 4529.84,\n" +
            "    \"symbol\": \"￡\"\n" +
            "  },\n" +
            "  \"HKD\": {\n" +
            "    \"15m\": 46391.27,\n" +
            "    \"last\": 46391.27,\n" +
            "    \"buy\": 46364.74,\n" +
            "    \"sell\": 46401.83,\n" +
            "    \"symbol\": \"HK$\"\n" +
            "  },\n" +
            "  \"HUF\": {\n" +
            "    \"15m\": 1587269.59,\n" +
            "    \"last\": 1587269.59,\n" +
            "    \"buy\": 1586361.96,\n" +
            "    \"sell\": 1587631.04,\n" +
            "    \"symbol\": \"F\"\n" +
            "  },\n" +
            "  \"IDR\": {\n" +
            "    \"15m\": 80735731.53,\n" +
            "    \"last\": 80735731.53,\n" +
            "    \"buy\": 80689565.05,\n" +
            "    \"sell\": 80754116.45,\n" +
            "    \"symbol\": \"R\"\n" +
            "  },\n" +
            "  \"ILS\": {\n" +
            "    \"15m\": 20971.05,\n" +
            "    \"last\": 20971.05,\n" +
            "    \"buy\": 20959.06,\n" +
            "    \"sell\": 20975.83,\n" +
            "    \"symbol\": \"?\"\n" +
            "  },\n" +
            "  \"INR\": {\n" +
            "    \"15m\": 386536.51,\n" +
            "    \"last\": 386536.51,\n" +
            "    \"buy\": 386315.48,\n" +
            "    \"sell\": 386624.53,\n" +
            "    \"symbol\": \"?\"\n" +
            "  },\n" +
            "  \"ISK\": {\n" +
            "    \"15m\": 633438.37,\n" +
            "    \"last\": 633438.37,\n" +
            "    \"buy\": 633076.16,\n" +
            "    \"sell\": 633582.62,\n" +
            "    \"symbol\": \"kr\"\n" +
            "  },\n" +
            "  \"JPY\": {\n" +
            "    \"15m\": 678657.32,\n" +
            "    \"last\": 678657.32,\n" +
            "    \"buy\": 678269.25,\n" +
            "    \"sell\": 678811.86,\n" +
            "    \"symbol\": \"￥\"\n" +
            "  },\n" +
            "  \"KRW\": {\n" +
            "    \"15m\": 6717105.9,\n" +
            "    \"last\": 6717105.9,\n" +
            "    \"buy\": 6713264.91,\n" +
            "    \"sell\": 6718635.5,\n" +
            "    \"symbol\": \"?\"\n" +
            "  },\n" +
            "  \"MXN\": {\n" +
            "    \"15m\": 114341.69,\n" +
            "    \"last\": 114341.69,\n" +
            "    \"buy\": 114276.31,\n" +
            "    \"sell\": 114367.73,\n" +
            "    \"symbol\": \"MX$\"\n" +
            "  },\n" +
            "  \"MYR\": {\n" +
            "    \"15m\": 25186.14,\n" +
            "    \"last\": 25186.14,\n" +
            "    \"buy\": 25171.73,\n" +
            "    \"sell\": 25191.87,\n" +
            "    \"symbol\": \"RM\"\n" +
            "  },\n" +
            "  \"NOK\": {\n" +
            "    \"15m\": 48674.83,\n" +
            "    \"last\": 48674.83,\n" +
            "    \"buy\": 48647,\n" +
            "    \"sell\": 48685.91,\n" +
            "    \"symbol\": \"kr\"\n" +
            "  },\n" +
            "  \"NZD\": {\n" +
            "    \"15m\": 8701.69,\n" +
            "    \"last\": 8701.69,\n" +
            "    \"buy\": 8696.72,\n" +
            "    \"sell\": 8703.67,\n" +
            "    \"symbol\": \"NZ$\"\n" +
            "  },\n" +
            "  \"PHP\": {\n" +
            "    \"15m\": 308159.21,\n" +
            "    \"last\": 308159.21,\n" +
            "    \"buy\": 307983,\n" +
            "    \"sell\": 308229.38,\n" +
            "    \"symbol\": \"?\"\n" +
            "  },\n" +
            "  \"PKR\": {\n" +
            "    \"15m\": 626220.28,\n" +
            "    \"last\": 626220.28,\n" +
            "    \"buy\": 625862.19,\n" +
            "    \"sell\": 626362.88,\n" +
            "    \"symbol\": \"?\"\n" +
            "  },\n" +
            "  \"PLN\": {\n" +
            "    \"15m\": 21738.75,\n" +
            "    \"last\": 21738.75,\n" +
            "    \"buy\": 21726.32,\n" +
            "    \"sell\": 21743.7,\n" +
            "    \"symbol\": \"z?\"\n" +
            "  },\n" +
            "  \"RON\": {\n" +
            "    \"15m\": 23501.34,\n" +
            "    \"last\": 23501.34,\n" +
            "    \"buy\": 23487.9,\n" +
            "    \"sell\": 23506.69,\n" +
            "    \"symbol\": \"RON\"\n" +
            "  },\n" +
            "  \"RUB\": {\n" +
            "    \"15m\": 344216.33,\n" +
            "    \"last\": 344216.33,\n" +
            "    \"buy\": 344019.5,\n" +
            "    \"sell\": 344294.72,\n" +
            "    \"symbol\": \"?\"\n" +
            "  },\n" +
            "  \"SEK\": {\n" +
            "    \"15m\": 49792.96,\n" +
            "    \"last\": 49792.96,\n" +
            "    \"buy\": 49764.49,\n" +
            "    \"sell\": 49804.3,\n" +
            "    \"symbol\": \"kr\"\n" +
            "  },\n" +
            "  \"SGD\": {\n" +
            "    \"15m\": 8130.2,\n" +
            "    \"last\": 8130.2,\n" +
            "    \"buy\": 8125.55,\n" +
            "    \"sell\": 8132.05,\n" +
            "    \"symbol\": \"SG$\"\n" +
            "  },\n" +
            "  \"THB\": {\n" +
            "    \"15m\": 197882.02,\n" +
            "    \"last\": 197882.02,\n" +
            "    \"buy\": 197768.87,\n" +
            "    \"sell\": 197927.08,\n" +
            "    \"symbol\": \"?\"\n" +
            "  },\n" +
            "  \"TRY\": {\n" +
            "    \"15m\": 22725.82,\n" +
            "    \"last\": 22725.82,\n" +
            "    \"buy\": 22712.83,\n" +
            "    \"sell\": 22731,\n" +
            "    \"symbol\": \"t\"\n" +
            "  },\n" +
            "  \"TWD\": {\n" +
            "    \"15m\": 179914.09,\n" +
            "    \"last\": 179914.09,\n" +
            "    \"buy\": 179811.21,\n" +
            "    \"sell\": 179955.06,\n" +
            "    \"symbol\": \"NT$\"\n" +
            "  },\n" +
            "  \"USD\": {\n" +
            "    \"15m\": 5944.43,\n" +
            "    \"last\": 5944.43,\n" +
            "    \"buy\": 5941.03,\n" +
            "    \"sell\": 5945.78,\n" +
            "    \"symbol\": \"US$\"\n" +
            "  },\n" +
            "  \"ZAR\": {\n" +
            "    \"15m\": 84855.23,\n" +
            "    \"last\": 84855.23,\n" +
            "    \"buy\": 84806.71,\n" +
            "    \"sell\": 84874.55,\n" +
            "    \"symbol\": \"R\"\n" +
            "  },\n" +
            "  \"refreshrate\": 1000000,\n" +
            "  \"updatedAt\": \"2017-10-27T02:55:43.279Z\"\n" +
            "}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




      //  loadNetworkData();
       // test();
        initView();
        TreeMap<String, GloableBean> treeMap = test_2();
        Set<Map.Entry<String, GloableBean>> entries = treeMap.entrySet();
        treeMap.remove("refreshrate");
        treeMap.remove("updatedAt");
        Set<Map.Entry<String, GloableBean>> entries1 = treeMap.entrySet();
        Iterator<Map.Entry<String, GloableBean>> iterator = entries1.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, GloableBean> next = iterator.next();
            String key = next.getKey();
            GloableBean value = next.getValue();
            String last = value.getLast();
            String last1= value.getSymbol();
        }
        
    }
    
    private TreeMap<String, GloableBean>  test_2() {
        TreeMap<String, GloableBean> treeMap = new TreeMap<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            Iterator iterator = jsonObject.keys();  // 应用迭代器Iterator 获取所有的key值
            while (iterator.hasNext()) { // 遍历每个key
                String key = iterator.next() + "";
                if ("refreshrate".equals(key) || "updatedAt".equals(key)) {
                    continue;
                }
                JSONObject jsb = jsonObject.getJSONObject(key);
                GloableBean ttt = new GloableBean();
               
                ttt.setBuy(jsb.getString("buy"));
                ttt.setLast(jsb.getString("last"));
                ttt.setSell(jsb.getString("sell"));
                ttt.setSymbol(jsb.getString("symbol"));
                treeMap.put(key, ttt);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return treeMap;
    }
    
    private void initView() {


        edit = (EditText) findViewById(R.id.edit);
        String trim = edit.getText().toString().trim();
        Log.e("文字----","----"+trim);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                LogUtil.e("文字--前"+s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtil.e("文字--中"+s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtil.e("文字--后"+s);
            }
        });
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
































