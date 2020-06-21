package com.easymall.utils;

/**
 * @author 冷夜雨花未眠
 * @create 2020/3/9 0009-12:39
 *
 * 当前工程的工具类
 */
public class WebUtils {
    private WebUtils(){

    }
    //非空判断
    public  static  boolean isNull(String name){
        return name==null||"".equals(name);
    }
}
