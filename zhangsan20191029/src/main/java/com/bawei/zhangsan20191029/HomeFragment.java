package com.bawei.zhangsan20191029;
/*
 *@auther:谷建龙
 *@Date: 2019/10/29
 *@Time:14:49
 *@Description:
 * */


import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bawei.zhangsan20191029.Base.BaseFragment;
import com.bawei.zhangsan20191029.Base.UserBean;
import com.bawei.zhangsan20191029.Sql.MyDao;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.stx.xhb.androidx.XBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private XBanner xBanner;
    private PullToRefreshListView pullToRefreshListView;
    private int page = 1;
    private boolean isLodeMore;
    List<UserBean.ListdataBean> list=new ArrayList<>();
    private MyDao myDao;

    @Override
    protected void initView(View inflate) {
        xBanner = inflate.findViewById(R.id.xb);
        pullToRefreshListView = inflate.findViewById(R.id.pull);
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserBean.ListdataBean listdataBean = list.get(position);
                String url = listdataBean.getUrl();
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("key",url);
                startActivity(intent);
            }
        });

        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                isLodeMore = false;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                isLodeMore = true;
                getData();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initData() {
        myDao = new MyDao(getActivity());
        if (NetStart.getInstance().hasNet(getActivity())) {
            getData();
        }else {
            List<UserBean.ListdataBean> listdataBeans = myDao.queryList();
            pullToRefreshListView.setAdapter(new MyAdapter(listdataBeans));
        }

    }

    private void getData() {
        String url = "";
        if (page == 1) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer.json";
        } else if (page == 2) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer2.json";
        } else if (page == 3) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer3.json";
        }
        NetUtil.getInstance().doGet(url, new NetUtil.MyCallBack() {
            @Override
            public void onDoGetSuccess(String string) {
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(string, UserBean.class);
                final List<UserBean.BannerdataBean> bannerdata = userBean.getBannerdata();
                if (bannerdata != null) {
                    xBanner.setBannerData(bannerdata);
                    xBanner.loadImage(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, final View view, int position) {
                            UserBean.BannerdataBean bannerdataBean = bannerdata.get(position);
                            String imageUrl = bannerdataBean.getImageUrl();
                            NetUtil.getInstance().doGetPhoto(imageUrl, new NetUtil.MyCallBack() {
                                @Override
                                public void onDoGetSuccess(String string) {

                                }

                                @Override
                                public void onDoGetPhotoSuccess(Bitmap bitmap) {
                                    ((ImageView) view).setImageBitmap(bitmap);
                                }
                            });
                        }
                    });
                }
                List<UserBean.ListdataBean> listdata = userBean.getListdata();
                myDao.insertList(listdata);
                if (isLodeMore){
                    list.addAll(listdata);
                    pullToRefreshListView.onRefreshComplete();
                }else {
                    list.clear();
                    list.addAll(listdata);
                    pullToRefreshListView.onRefreshComplete();
                }
                pullToRefreshListView.setAdapter(new MyAdapter(list));
            }

            @Override
            public void onDoGetPhotoSuccess(Bitmap bitmap) {

            }
        });
    }
}
