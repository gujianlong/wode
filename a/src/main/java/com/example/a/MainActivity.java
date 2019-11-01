package com.example.a;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.a.Base.BaseActivity;
import com.example.a.Base.UserBean;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends BaseActivity {

    private GridView gridView;

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        NetUtil.getInstance().doGet("http://blog.zhaoliang5156.cn/api/news/lawyer.json\n", new NetUtil.MyCallBack() {
            @Override
            public void onDoGetSuccess(String string) {
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(string, UserBean.class);
                List<UserBean.ListdataBean> listdata = userBean.getListdata();
                gridView.setAdapter(new MyAdapter(listdata));
            }

            @Override
            public void onDoGetPhotoSuccess(Bitmap bitmap) {

            }
        });
    }

    @Override
    protected void initView() {
        gridView = findViewById(R.id.gv);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
}
