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
                            user.getClassNum(), user.getBirthYear(), 1
                    });
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String findUserByName(String username) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select passWord from user where userName = ?",
                new String[]{username});
        if (cursor.moveToNext()) {
            return (cursor.getString(cursor.getColumnIndex("passWord")));
        }
        return null;
    }

    public void isAnswerRight(String userId, boolean isTrue) {
        int token = 0, rightTimes = 0, wrongTimes = 0;
        db = helper.getWritableDatabase();
        //得到原来的金币数
        Cursor cursor = db.rawQuery("select token from user where userId = ?",
                new String[]{userId});
        if (cursor.moveToNext()) {
            token = cursor.getInt(cursor.getColumnIndex("token"));
        }
        //得到原来的正确和错误次数
        Cursor cursors = db.rawQuery("select rightTimes, wrongTimes from user_test where userId = ?",
                new String[]{userId});
        if (cursor.moveToNext()) {
            rightTimes = cursors.getInt(cursors.getColumnIndex("rightTimes"));
            wrongTimes = cursors.getInt(cursors.getColumnIndex("wrongTimes"));
        }
        if (isTrue) {
            db.execSQL("UPDATE user SET token = ? WHERE userId = ?", new Object[]{token + 1, userId});//金币数+1
            db.execSQL("UPDATE user_test SET rightTimes = ? WHERE userId = ?", new Object[]{rightTimes + 1, userId});//正确次数+1
        }else {
            db.execSQL("UPDATE user_test SET wrongTimes = ? WHERE userId = ?", new Object[]{wrongTimes + 1, userId});//错误次数+1
        }
    }
}
