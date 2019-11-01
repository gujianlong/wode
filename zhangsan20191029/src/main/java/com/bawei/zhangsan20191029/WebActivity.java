package com.bawei.zhangsan20191029;
/*
 *@auther:谷建龙
 *@Date: 2019/10/29
 *@Time:15:39
 *@Description:
 * */


import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.zhangsan20191029.Base.BaseActivity;

class WebActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void initData() {
        final String key = getIntent().getStringExtra("key");
        webView.loadUrl(key);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                webView.loadUrl(key);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("e", "页面开始加载" );
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("e", "页面加载完成" );
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e("e", "页面加载的进度为"+newProgress+"%" );
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("e", "页面的标题为"+title );
            }
        });
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.we);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_web;
    }
}
