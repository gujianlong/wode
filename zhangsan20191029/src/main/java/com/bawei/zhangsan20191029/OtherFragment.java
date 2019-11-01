package com.bawei.zhangsan20191029;
/*
 *@auther:谷建龙
 *@Date: 2019/10/29
 *@Time:14:30
 *@Description:
 * */


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bawei.zhangsan20191029.Base.BaseFragment;

public class OtherFragment extends BaseFragment {

    private TextView textView;

    @Override
    protected void initView(View inflate) {
        textView = inflate.findViewById(R.id.tv);
    }

    @Override
    protected int layoutId() {
        return R.layout.other_fragment;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String key = bundle.getString("key");
        textView.setText(key);
    }
}
