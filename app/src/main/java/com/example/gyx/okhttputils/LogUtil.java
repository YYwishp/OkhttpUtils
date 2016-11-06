package com.example.gyx.okhttputils;

import android.util.Log;

/**
 * Created by GYX on 2016/10/29.
 */

public class LogUtil {
    public static boolean isLog = true;

    public static void e(String tag, String message) {
        if (isLog) {
            Log.e("-------"+tag, message + getFunctionName()+System.getProperty("line.separator") + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    }
    public static void e(String message) {
        if (isLog) {
            Log.e("---日志---", message + getFunctionName()+System.getProperty("line.separator") + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    }

    public static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();

        /*if (sts == null) {
            return null;
        }

        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(LogUtil.class.getName())) {
                continue;
            }



            return "[ 类名---->" + st.getClassName() + "方法名---->" + st.getMethodName() + "行号-----> " + st.getLineNumber() + "当前线程---->" + Thread.currentThread().getName() + " ]";
        }*/
        StringBuffer stringBuffer = new StringBuffer();

        for (StackTraceElement st : sts) {
            String className = st.getClassName();
            String methodName = st.getMethodName();
            int lineNumber = st.getLineNumber();
            stringBuffer.append("类名--》"+className + "《     方法名---》" + methodName + "《    行号---》" + lineNumber+"《");
        }

        return stringBuffer.toString();
    }

}
