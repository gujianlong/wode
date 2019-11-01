package com.bawei.zhangsan20191029.Sql;
/*
 *@auther:谷建龙
 *@Date: 2019/10/29
 *@Time:15:43
 *@Description:
 * */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.zhangsan20191029.Base.UserBean;

import java.util.ArrayList;
import java.util.List;

public class MyDao {

    private final MySql mySql;

    public MyDao(Context context) {
        mySql = new MySql(context, "yue.db", null, 1);
    }

    public void insertList(List<UserBean.ListdataBean> listdata) {
        SQLiteDatabase writableDatabase = mySql.getWritableDatabase();
        for (UserBean.ListdataBean listdatum : listdata) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", listdatum.getName());
            contentValues.put("info", listdatum.getInfo());
            writableDatabase.insert("list", null, contentValues);
        }
        writableDatabase.close();
    }

    public List<UserBean.ListdataBean> queryList() {
        SQLiteDatabase writableDatabase = mySql.getWritableDatabase();
        Cursor cursor = writableDatabase.query("list", null, null, null, null, null, null);
        List<UserBean.ListdataBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String info = cursor.getString(cursor.getColumnIndex("info"));
            UserBean.ListdataBean listdataBean = new UserBean.ListdataBean();
            listdataBean.setName(name);
            listdataBean.setInfo(info);
            list.add(listdataBean);
        }
        writableDatabase.close();
        return list;

    }
}
