package com.bawei.zhangsan20191029;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bawei.zhangsan20191029.Base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void initData() {
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        list.add(mainActivityFragment);

        OtherFragment otherFragment = new OtherFragment();
        Bundle bunble = new Bundle();
        bunble.putString("key", "消息页面");
        otherFragment.setArguments(bunble);
        list.add(otherFragment);

        OtherFragment otherFragment1 = new OtherFragment();
        Bundle bunble1 = new Bundle();
        bunble1.putString("key", "发现页面");
        otherFragment1.setArguments(bunble1);
        list.add(otherFragment1);

        OtherFragment otherFragment2 = new OtherFragment();
        Bundle bunble2 = new Bundle();
        bunble2.putString("key", "我的页面");
        otherFragment2.setArguments(bunble2);
        list.add(otherFragment2);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio01:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.radio02:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.radio03:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.radio04:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });


    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.vp);
        radioGroup = findViewById(R.id.rg);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
}
