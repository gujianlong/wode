package com.bawei.zhangsan20191029;
/*
 *@auther:谷建龙
 *@Date: 2019/10/29
 *@Time:14:34
 *@Description:
 * */


import android.net.VpnService;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bawei.zhangsan20191029.Base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivityFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager pager;
    private List<Fragment> list = new ArrayList<>();
    private List<String> add = new ArrayList<>();

    @Override
    protected void initView(View inflate) {
        tabLayout = inflate.findViewById(R.id.tl);
        pager = inflate.findViewById(R.id.vp);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main_fragment;
    }

    @Override
    protected void initData() {
        list.clear();
        add.add("最新");
        add.add("婆媳");
        add.add("房产");
        add.add("仲裁");
        add.add("北京");
        HomeFragment homeFragment = new HomeFragment();
        list.add(homeFragment);

        OtherFragment otherFragment = new OtherFragment();
        Bundle bunble = new Bundle();
        bunble.putString("key", "婆媳页面");
        otherFragment.setArguments(bunble);
        list.add(otherFragment);

        OtherFragment otherFragment1 = new OtherFragment();
        Bundle bunble1 = new Bundle();
        bunble1.putString("key", "房产页面");
        otherFragment1.setArguments(bunble1);
        list.add(otherFragment1);

        OtherFragment otherFragment2 = new OtherFragment();
        Bundle bunble2 = new Bundle();
        bunble2.putString("key", "仲裁页面");
        otherFragment2.setArguments(bunble2);
        list.add(otherFragment2);

        OtherFragment otherFragment3 = new OtherFragment();
        Bundle bunble3 = new Bundle();
        bunble3.putString("key", "北京页面");
        otherFragment3.setArguments(bunble3);
        list.add(otherFragment3);

        pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return add.get(position);
            }
        });
        tabLayout.setupWithViewPager(pager);
    }
}
