package com.tablelayout.usedemo.utils;

import android.util.Log;

/**
 * Created by My on 2016/12/1.
 */
public class LogUtils {
    private static String  TAG="yinyr";
    private static boolean DEBUG=true;
    private void LogUtils(){

    }
    public static void init(String tag,boolean debug ) {
        TAG=tag;
        DEBUG=debug;
    }
    public static void setTAG(String tag){
        TAG=tag;
    }
    public static  void  e(String msg){
        if(DEBUG){
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.e(TAG,"("+targetStackTraceElement.getFileName()+":"+targetStackTraceElement.getLineNumber()+")"+"  MSG : "+msg);
        }
    }
    private static StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(LogUtils.class.getName());
//            Log.e(TAG,stackTraceElement.getFileName()+stackTraceElement.getLineNumber()+isLogMethod);
//            12-01 03:13:29.324 27035-27063/summary.log.com.loggersummary E/yinyr: VMStack.java-2 false
//            12-01 03:13:29.324 27035-27063/summary.log.com.loggersummary E/yinyr: Thread.java580 false
//            12-01 03:13:29.324 27035-27063/summary.log.com.loggersummary E/yinyr: LogUtils.java31 true
//            12-01 03:13:29.324 27035-27063/summary.log.com.loggersummary E/yinyr: LogUtils.java23 true
//            12-01 03:13:29.324 27035-27063/summary.log.com.loggersummary E/yinyr: MainActivity.java15 false
            //先为true  再为false  作为触发条件
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }
}
