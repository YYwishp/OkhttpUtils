package com.example.gyx.okhttputils;

import android.util.Log;

/**
 * log管理工具
 * Created by GYX-S on 2016/6/3.
 */
public class LogUtil {
    //public static boolean isLog = BuildConfig.LOG_DEBUG;
    public static boolean isLog = true;
    private static final int MIN_STACK_OFFSET = 3;

    public static void e(String tag, String message) {
        if (isLog) {

            Log.e("===>" + tag + "<===", getFunctionName() + "\n║日志内容--->" + message + "\n╚═════════════════════════════════════╝");
        }

    }

    public static void e(String message) {
        if (isLog) {

            Log.e("===日志===", getFunctionName() + "\n║日志内容--->" +message + "\n╚═════════════════════════════════════╝");


        }

    }

    /**
     * 提供方法的名称，和行号，同时提供链接，鼠标点击就好
     *
     * @param methodCount 显示的层级
     * @return
     */
    private static String getLineConnect(int methodCount) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
       /* if (settings.isShowThreadInfo()) {
            logChunk(logType, tag, HORIZONTAL_DOUBLE_LINE + " Thread: " + Thread.currentThread().getName());
            logDivider(logType, tag);
        }*/
        String level = "";
        //方法调用的层级
        int stackOffset = getStackOffset(trace);

        //corresponding method count with the current stack may exceeds the stack trace. Trims the count
        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex >= trace.length) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            builder.append("")
                    .append(level)
                    //.append(getSimpleClassName(trace[stackIndex].getClassName()))
                    .append(".")
                    .append(trace[stackIndex].getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(trace[stackIndex].getFileName())
                    .append(":")
                    .append(trace[stackIndex].getLineNumber())
                    .append(")");
            level += "   ";
            //logChunk(logType, tag, builder.toString());
            //Log.e("111111111",builder.toString());
            return builder.toString();
        }
        return null;
    }
    private static int getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(LogUtil.class.getName()) && !name.equals(LogUtil.class.getName())) {
                return --i;
            }
        }
        return -1;
    }

    public static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();

        if (sts == null) {
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
/*
            if (st.getClassName().equals(OkHttpRequest.Builder.class.getName())) {
                continue;
            }*/


            return " " + "\n╔═════════════════════════════════════╗" + "\n║类名------->" + st.getClassName() + "\n║方法名----->" + st.getMethodName() + "\n║行号-------> " +  getLineConnect(1) + "\n║当前线程--->" + Thread.currentThread().getName();
        }

        return null;
    }

}
