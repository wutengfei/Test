package com.peale_rn_1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.peale_rn_1.model.Tb_user;

/**
 * Created by Administrator on 2016/9/2.
 */
public class UserDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    //private static Tb_user user;

    public UserDAO(Context context) {
        helper = new DBOpenHelper(context);
    }

    public Boolean insert(Tb_user user) {
        db = helper.getWritableDatabase();
        try {
            db.execSQL("insert into user (userName,passWord,realName,sex,school,grade,classNum,birthYear,isFirstLogin) values (?,?,?,?,?,?,?,?,?)",
                    new Object[]{user.getUserName(), user.getPassWord(), user.getRealName(), user.getSex(), user.getSchool(), user.getGrade(),
                            user.getClassNum(), user.getBirthYear(),1
                                 });
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String findUserByName(String username){
        db  = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select passWord from user where userName = ?",
                new String[]{username});
        if(cursor.moveToNext()){
            return (cursor.getString(cursor.getColumnIndex("passWord")));
        }
        return null;
    }
}
