package com.bawei.zhangsan20191029;
/*
 *@auther:谷建龙
 *@Date: 2019/10/29
 *@Time:13:59
 *@Description:
 * */


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {
//②　使用单例模式封装网络工具类。
    static NetUtil netUtil = new NetUtil();

    public static NetUtil getInstance() {
        return netUtil;
    }

    private NetUtil() {
    }
    //io转toStrong
    public String io2String(InputStream inputStream) {
        byte[] bytes = new byte[1024];
        int len = -1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String json = "";
        try {
            while (((len = inputStream.read(bytes)) != -1)) {
                byteArrayOutputStream.write(bytes, 0, len);
            }
            byte[] bytes1 = byteArrayOutputStream.toByteArray();
            json = new String(bytes1);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关流
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }
    //io转Mitmap
    public Bitmap io2Bitmap(InputStream inputStream){
        return BitmapFactory.decodeStream(inputStream);
    }

    //toget方法
    @SuppressLint("StaticFieldLeak")
    public void doGet(final String httpUrl, final MyCallBack myCallBack){
        //设置连接超时为5秒钟，读取超时为5秒钟。正确处理异步网络请求（可以使用AsyncTask或线程+Handler）
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String string) {
                myCallBack.onDoGetSuccess(string);
            }

            @Override
            protected String doInBackground(String... strings) {
                String json="";
                HttpURLConnection httpURLConnection=null;
                InputStream inputStream=null;
                try {
                    URL url = new URL(httpUrl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode()==200){
                         inputStream= httpURLConnection.getInputStream();
                         json= io2String(inputStream);
                    }else {
                        Log.i("a", "连接失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    //关闭连接
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    //关闭流
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return json;
            }
        }.execute();
    }
    public void doGetPhoto(final String httpUrl, final MyCallBack myCallBack){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                myCallBack.onDoGetPhotoSuccess(bitmap);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                Bitmap bitmap=null;
                HttpURLConnection httpURLConnection=null;
                InputStream inputStream=null;
                try {
                    URL url = new URL(httpUrl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode()==200){
                        inputStream= httpURLConnection.getInputStream();
                        bitmap= io2Bitmap(inputStream);
                    }else {
                        Log.i("a", "连接失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    //关闭连接
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    //关闭流
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return bitmap;
            }
        }.execute();
    }

    public interface MyCallBack {
        void onDoGetSuccess(String string);

        void onDoGetPhotoSuccess(Bitmap bitmap);
    }
}
