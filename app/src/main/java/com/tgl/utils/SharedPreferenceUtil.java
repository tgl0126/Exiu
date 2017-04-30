package com.tgl.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Create by 山花烂漫
 * on 2017/4/5
 **/

public class SharedPreferenceUtil {
    private static String USER_INFO = "userInfo";

    // 存放字符串型的值
    public static void setUserInfo(Context context,String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(USER_INFO,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.putString(key, value);
        editor.commit();
    }

    // 存放整型的值
    public static void setUserInfo(Context context,String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(USER_INFO,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.putInt(key, value);
        editor.commit();
    }

    // 存放长整型值
    public static void setUserInfo(Context context,String key, Long value) {
        SharedPreferences sp = context.getSharedPreferences(USER_INFO,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.putLong(key, value);
        editor.commit();
    }

    // 存放布尔型值
    public static void setUserInfo(Context context,String key, Boolean value) {
        SharedPreferences sp = context.getSharedPreferences(USER_INFO,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.putBoolean(key, value);
        editor.commit();
    }

    // 清空记录
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_INFO,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    // 获得用户息中某项字符串参数的值
    public String getStringInfo(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sp.getString(key, "null");
    }

    // 获得用户息中某项整型参数的值
    public static int getIntInfo(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    // 获得用户信息中某项长整型参数的值
    public static Long getLongInfo(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(USER_INFO,
                Context.MODE_PRIVATE);
        return sp.getLong(key, 0);
    }

    // 获得用户信息中某项布尔型参数的值
    public static boolean getBooleanInfo(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(USER_INFO,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }
}
