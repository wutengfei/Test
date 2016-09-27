package com.peale_rn_1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.peale_rn_1.model.Tb_file;
import com.peale_rn_1.model.Tb_user;

/**
 * Created by Administrator on 2016/9/17.
 */
public class ResourceDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    //private static Tb_user user;

    public ResourceDAO(Context context) {
        helper = new DBOpenHelper(context);
    }

    public Tb_file findFile(String titleID, String dayId, String recommendDayId) {
        db  = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select fileName,filePath from file where titleID = ? and dayId = ? and recommendDayId = ?",
                new String[]{titleID,dayId,recommendDayId});
        if(cursor.moveToNext()){
            Tb_file file = new Tb_file();
            file.setFileName(cursor.getString(cursor.getColumnIndex("fileName")));
            file.setFilePath(cursor.getString(cursor.getColumnIndex("filePath")));
            return file;  //存在返回文件对象的file名字和路径
        }
        return null;
    }

    public Boolean insert(Tb_file file) {
        db = helper.getWritableDatabase();
        try {
            db.execSQL("insert into file (titleID,dayId,recommendDayId,fileName,filePath) values (?,?,?,?,?)",
                    new Object[]{ file.getTitleID(),file.getDayId(),file.getRecommendDayId(),file.getFileName(),file.getFilePath() });
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
