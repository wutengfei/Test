package com.peale_rn_1.dao.usertest;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.peale_rn_1.dao.DBOpenHelper;
import com.peale_rn_1.model.UserTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserTestDaoImpl implements UserTestDao {
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    public UserTestDaoImpl(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    //查找通过userId，flag
    @Override
    public List<UserTest> find(String userId, int flag)  {
        db = dbOpenHelper.getWritableDatabase();
        if (flag == 0) {//未测试过
            Cursor cursor = db.rawQuery("SELECT * FROM user_test where userId like ? and totalTime = 0", new String[]{userId});
            return ConvertToUserTest(cursor);
        } else {//已测试过
            Cursor cursor = db.rawQuery("SELECT * FROM user_test where userId like ? and totalTime > 0", new String[]{userId});
            return ConvertToUserTest(cursor);
        }
    }

    //查找,通过userId,word
    public List<UserTest> find(String userId, String word) {
        db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user_test where userId like ? and word like ?", new String[]{userId, word});
        return ConvertToUserTest(cursor);
    }

    private List<UserTest> ConvertToUserTest(Cursor cursor)  {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst())
            return null;
        UserTest[] userTest = new UserTest[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            userTest[i] = new UserTest();
            userTest[i].setId(cursor.getInt(cursor.getColumnIndex("id")));
            userTest[i].setUserId(cursor.getString(cursor.getColumnIndex("userId")));
            userTest[i].setWord(cursor.getString(cursor.getColumnIndex("word")));
            userTest[i].setTestType(cursor.getInt(cursor.getColumnIndex("testType")));
            userTest[i].setTestAspect(cursor.getInt(cursor.getColumnIndex("testAspect")));
            userTest[i].setTestDifficulty(cursor.getInt(cursor.getColumnIndex("testDifficulty")));
            userTest[i].setRightTimes(cursor.getInt(cursor.getColumnIndex("rightTimes")));
            userTest[i].setWrongTimes(cursor.getInt(cursor.getColumnIndex("wrongTimes")));
            userTest[i].setTotalTimes(cursor.getInt(cursor.getColumnIndex("totalTime")));
            userTest[i].setStartTime(cursor.getString(cursor.getColumnIndex("startTime")));
            userTest[i].setEndTime(cursor.getString(cursor.getColumnIndex("endTime")));

        /*    String startTime = cursor.getString(cursor.getColumnIndex("startTime"));
            // System.out.println("startTime---------" + startTime);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = null;
            if (startTime == null) {
                date = new Date();
            } else {
                try {
                    date = format.parse(startTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            userTest[i].setStartTime(date);

            String endTime = cursor.getString(cursor.getColumnIndex("endTime"));
            //  System.out.println("endTime---------" + endTime);
            Date date2 = null;
            if (endTime == null) {
                date2 = new Date();
            } else {
                try {
                    date2 = format.parse(endTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            userTest[i].setEndTime(date2);*/

            cursor.moveToNext();
        }
        db.close();
        return Arrays.asList(userTest);
    }

    //保存
    public void save(UserTest userTest) {
        db = dbOpenHelper.getWritableDatabase();

        String sql = "insert into user_test (userId,word,testType,testAspect,testDifficulty,rightTimes,wrongTimes," +
                "totalTime,startTime,endTime) values (?,?,?,?,?,?,?,?,?,?)";
        db.execSQL(sql, new Object[]{userTest.getUserId(), userTest.getWord(), userTest.getTestType(), userTest.getTestAspect(),
                userTest.getTestDifficulty(), userTest.getRightTimes(), userTest.getWrongTimes(), userTest.getTotalTimes(),
                userTest.getStartTime(), userTest.getEndTime()});
        db.close();
    }

    //更新
    public void update(UserTest userTest) {
        db = dbOpenHelper.getWritableDatabase();

        String sql = "UPDATE user_test set testType = ?,testAspect=?,testDifficulty=?,rightTimes=?,wrongTimes=?," +
                "totalTime=?,startTime=?,endTime=? WHERE userId like ? and word like ?";
        db.execSQL(sql, new Object[]{userTest.getTestType(), userTest.getTestAspect(),
                userTest.getTestDifficulty(), userTest.getRightTimes(), userTest.getWrongTimes(), userTest.getTotalTimes(),
                userTest.getStartTime(), userTest.getEndTime(), userTest.getUserId(), userTest.getWord()});
        db.close();
    }

    //删除
    public void delete(int id) {
        db = dbOpenHelper.getWritableDatabase();
        //db.execSQL("DELETE FROM user_test WHERE id like " + id);
        int a = db.delete("user_test", "id like ?", new String[]{id + ""});
        System.out.println("删除成功了吗" + a + "id=" + id);
        db.close();
    }
}
