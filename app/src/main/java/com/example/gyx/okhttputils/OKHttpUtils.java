package com.example.gyx.okhttputils;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;


/**
 * Created by GYX on 2016/10/10.
 */

public class OKHttpUtils {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * 第一步自定义方法：获取Request请求对象
     *
     * @param urlString
     * @return
     */
    private static Request buildRequestFromUrl(String urlString) {
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        return request;
    }

    /**
     * 第二步自定义方法：获取Response返回对象
     *
     * @param urlString
     * @return
     * @throws IOException
     */
    private static Response buildResponseFromUrl(String urlString) throws IOException {
        Request request = buildRequestFromUrl(urlString);
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }


    /**
     * 第三步自定义方法：获取ResponseBody对象
     *
     * @param urlString
     * @return
     * @throws IOException
     */
    private static ResponseBody buildResponseBodyFromUrl(String urlString) throws IOException {
        Response response = buildResponseFromUrl(urlString);
        //判断相应是否成功
        if (response.isSuccessful()) {
            return response.body();
        }
        return null;
    }


    /**
     * GET方式网络请求，获取字符串
     *
     * @param urlString
     * @return
     * @throws IOException
     */
    public static String getStringFromUrl(String urlString) throws IOException {
        ResponseBody responseBody = buildResponseBodyFromUrl(urlString);
        if (responseBody != null) {
            return responseBody.string();
        }
        return null;
    }

    /**
     * GET方式网路请求，返回字节数组
     *
     * @param urlString
     * @return
     * @throws IOException
     */
    public static byte[] getByteFromUrl(String urlString) throws IOException {
        ResponseBody responseBody = buildResponseBodyFromUrl(urlString);
        if (responseBody != null) {
            return responseBody.bytes();
        }
        return null;
    }

    /**
     * GET方式网络访问，返回输入流
     *
     * @param urlString
     * @return
     * @throws IOException
     */
    public static InputStream getStreamFromUrl(String urlString) throws IOException {
        ResponseBody responseBody = buildResponseBodyFromUrl(urlString);
        if (responseBody != null) {
            return responseBody.byteStream();
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////
    //get异步访问网络
    ///////////////////////////////////////////////////////////////////

    /**
     * 开启异步线程，通过实现回调方法，实现数据的加载
     *
     * @param urlString
     * @param callback
     */
    public static void getDataByNewThread(String urlString, Callback callback) {
        Log.e("okhttpUtils", "--------->>getDataByNewThread" + Thread.currentThread().getId());//主线程

        //1,获取Request对象
        Request request = buildRequestFromUrl(urlString);
        //2,调用okhttpClient方法的newcall方法的enqueue方法，
        Call call = okHttpClient.newCall(request);
        //开启子线程
        call.enqueue(callback);

    }


    //*****************************************************************************************
    //*****************************************************************************************
    //快捷键sbc
    ///////////////////////////////////////////////////////////////////////////
    // post同步网络请求
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 第一步：构建post的Request请求对象
     *
     * @param urlString
     * @param requestBody
     * @return
     */
    private static Request buildPostRequest(String urlString, RequestBody requestBody) {
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .url(urlString)
                .post(requestBody)
                .build();
        return request;
    }

    /**
     * 第二步：通过request请求得到返回的字符串
     *
     * @param urlString
     * @param requestBody
     * @return
     * @throws IOException
     */
    private static String postRequestBody(String urlString, RequestBody requestBody) throws IOException {
        Request request = buildPostRequest(urlString, requestBody);
        //通过request请求拿到response对象
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            //返回字符串
            return response.body().toString();
        }

        return null;
    }


    /**
     * 第三步：构建RequestBody对象
     * @param map
     * @return
     */
    private static RequestBody buildRequestBody(Map<String, String> map) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if (map != null && !map.isEmpty()) {
            //遍历，添加到FormEncodingBuilder中
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = builder.build();
        return requestBody;

    }

    /**
     * 最后对外提供方法：post同步访问网络，提交键值对
     * @param urlString
     * @param map
     * @return
     * @throws IOException
     */
    public static String postKeyValuePair(String urlString, Map<String, String> map) throws IOException {
        RequestBody requestBody = buildRequestBody(map);
        String s = postRequestBody(urlString, requestBody);
        return s;
    }


    //*******************post异步网络请求**************
    /**
     * 作用：post异步网络请求，提交RequestBody对象
     * @param urlString
     * @param requestBody
     * @param callback
     */
    private static void postRequestBodyAsync(String urlString, RequestBody requestBody, Callback callback) {
        Request request = buildPostRequest(urlString, requestBody);
        okHttpClient.newCall(request).enqueue(callback);

    }

    /**
     * 对外提供方法，post异步请求，提交键值对
     * @param urlString
     * @param map
     * @param callback
     */
    public static void postKeyValuePairAsync(String urlString, Map<String, String> map, Callback callback) {
        RequestBody requestBody = buildRequestBody(map);
        postRequestBodyAsync(urlString,requestBody,callback);
    }


    /**
     * 对外，作用：post同步上传文件以及其他表单控件（也就是提交分块请求）
     * @param urlString 网络地址
     * @param map 提交服务器的表单信息键值对
     * @param files 提交的文件
     * @param formFiledName 每个需要提交的文件的对应input的name值。(表单控件的名称)
     * @return
     * @throws IOException
     */
    public static String postUPloadFiles(String urlString, Map<String, String> map, File[] files, String[] formFiledName) throws IOException {
        RequestBody requestBody = buildRequestBody(map,files,formFiledName); //???
        return postRequestBody(urlString, requestBody);

    }

    /**
     * 作用：生成提交分块请求时 的RequestBody对象
     * @param map
     * @param files
     * @param formFiledName
     * @return
     */
    private static RequestBody buildRequestBody(Map<String, String> map, File[] files, String[] formFiledName) {
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        //第一部分提交：文件控件以外的其他input的数据
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                //addPart有两个参数
                builder.addPart(
                        Headers.of("Content-Disposition", "form-data:name\"" + entry.getKey() + "\"")
                        , RequestBody.create(null, entry.getValue()));
            }
        }
        //第二部分：上传文件控件的数据
        if (files != null) {
            for (int i =0; i<files.length;i++) {
                File file = files[i];
                String fileName = file.getName();//文件名称
                RequestBody requestBody = RequestBody.create(MediaType.parse(getMimeType(fileName)), file);
                //添加file input块的数据
                builder.addPart(Headers.of("Content-Disposition", "form-data:name\"" + formFiledName[i] + "\";fileName\"" + fileName + "\""), requestBody);
            }
        }
        RequestBody requestBody = builder.build();
        return requestBody;
    }

    /**
     * 获取文件的MimeType
     * @param fileName
     * @return
     */
    private static String getMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(fileName);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}


































