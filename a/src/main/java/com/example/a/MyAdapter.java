package com.example.a;
/*
 *@auther:谷建龙
 *@Date: 2019/11/1
 *@Time:19:10
 *@Description:
 * */


import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.a.Base.UserBean;

import java.util.List;

class MyAdapter extends BaseAdapter {
    private List<UserBean.ListdataBean> listdata;

    public MyAdapter(List<UserBean.ListdataBean> listdata) {
        this.listdata = listdata;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.a, null);
            holder = new ViewHolder();
            holder.image = convertView.findViewById(R.id.image);
            holder.name = convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        UserBean.ListdataBean listdataBean = listdata.get(position);
        holder.name.setText(listdataBean.getName());
        final ViewHolder finalHolder = holder;
        NetUtil.getInstance().doGetPhoto(listdataBean.getAvatar(), new NetUtil.MyCallBack() {
            @Override
            public void onDoGetSuccess(String string) {

            }

            @Override
            public void onDoGetPhotoSuccess(Bitmap bitmap) {
                finalHolder.image.setImageBitmap(bitmap);
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView name;
        ImageView image;
    }
}
