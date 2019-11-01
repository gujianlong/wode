package com.bawei.zhangsan20191029;
/*
 *@auther:谷建龙
 *@Date: 2019/10/29
 *@Time:15:12
 *@Description:
 * */


import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.zhangsan20191029.Base.UserBean;

import java.util.List;

class MyAdapter extends BaseAdapter {
    private List<UserBean.ListdataBean> list;
    private int type0 = 0;
    private int type1 = 1;

    public MyAdapter(List<UserBean.ListdataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        UserBean.ListdataBean listdataBean = list.get(position);
        int type = listdataBean.getType();
        if (type == 1) {
            return type0;
        } else if (type == 2) {
            return type1;
        }
        return type0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        ViewHolder holder = null;
        if (convertView == null) {
            if (itemViewType == type0) {
                convertView = View.inflate(parent.getContext(), R.layout.list1, null);
                holder = new ViewHolder();
                holder.name = convertView.findViewById(R.id.name);
                holder.image = convertView.findViewById(R.id.image);
                holder.info = convertView.findViewById(R.id.info);
                convertView.setTag(holder);
            } else if (itemViewType == type1) {
                convertView = View.inflate(parent.getContext(), R.layout.list2, null);
                holder = new ViewHolder();
                holder.name = convertView.findViewById(R.id.name);
                holder.image = convertView.findViewById(R.id.image);
                holder.info = convertView.findViewById(R.id.info);
                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        UserBean.ListdataBean listdataBean = list.get(position);
        holder.info.setText(listdataBean.getInfo());
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
        ImageView image;
        TextView name, info;
    }
}
