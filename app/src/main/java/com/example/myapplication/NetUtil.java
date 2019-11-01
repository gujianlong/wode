package com.example.myapplication;
/*
 *@auther:谷建龙
 *@Date: 2019/11/1
 *@Time:14:09
 *@Description:
 * */


public class NetUtil {

    private NetUtil() {
    }
    public static NetUtil getInstance(){
        return netUt.netUtil;
    }
    private static class netUt{
        private static NetUtil netUtil=new NetUtil();
    }

}
