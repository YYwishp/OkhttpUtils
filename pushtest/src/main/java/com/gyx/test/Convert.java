package com.gyx.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * ====================================
 * - Gson转换工具类
 *
 *
 *
 *
 * Created by GYX on 2016/10/27.
 */

public class Convert {
    private static Gson create() {
        return Convert.GsonHolder.gson;
    }

    private static class GsonHolder {
        private static Gson gson = new Gson();
    }

    /** json串解析成对象 */
    public static <T> T fromJson(String json, Class<T> type) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(json, type);
    }
    /** json串解析成对象 */
    public static <T> T fromJson(String json, Type type) {
        return create().fromJson(json, type);
    }
    /** json串解析成对象 */
    public static <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(reader, typeOfT);
    }
    /** json串解析成对象 */
    public static <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        return create().fromJson(json, classOfT);
    }
    /** json串解析成对象 */
    public static <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(json, typeOfT);
    }

    /**
     * 转成list
     * 解决泛型问题
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        //异常处理
        try {
            if (json.equals("[]")) {
                return null;
            }
            Gson gson = new Gson();
            List<T> list = new ArrayList<T>();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for(final JsonElement elem : array){
                list.add(gson.fromJson(elem, cls));
            }
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 解析成json串
     * @param src
     * @return
     */
    public static String toJson(Object src) {
        return create().toJson(src);
    }
    /**
     * 解析成json串
     * @param src
     * @return
     */
    public static String toJson(Object src, Type typeOfSrc) {
        return create().toJson(src, typeOfSrc);
    }
}
