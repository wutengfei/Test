
package com.peale_rn_1.dao.userword;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.peale_rn_1.dao.DBOpenHelper;
import com.peale_rn_1.model.UserWord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserWordDaoImpl implements UserWordDao {
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    public UserWordDaoImpl(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    @Override
    public List<UserWord> find(String userId, String word) throws ParseException {
        db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user_test WHERE userId like ? and word like ?", new String[]{userId, word});
        return ConvertToUserWord(cursor);
    }

    private List<UserWord> ConvertToUserWord(Cursor cursor) throws ParseException {


        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        UserWord[] userWord = new UserWord[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            userWord[i] = new UserWord();
            userWord[i].setUserId(cursor.getString(cursor.getColumnIndex("userId")));
            userWord[i].setWord(cursor.getString(cursor.getColumnIndex("word")));
            userWord[i].setTopicLevel(cursor.getInt(cursor.getColumnIndex("topicLevel")));
            userWord[i].setWordLearn(cursor.getInt(cursor.getColumnIndex("wordLearn")));
            userWord[i].setTest(cursor.getInt(cursor.getColumnIndex("test")));

            String time2 = cursor.getString(cursor.getColumnIndex("time"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//===========================保存的时间格式是什么
            Date date = format.parse(time2);
            userWord[i].setTime(date);
            cursor.moveToNext();
        }
        db.close();
        return Arrays.asList(userWord);
    }

    @Override
    public List<UserWord> findAll(String userId, String word, int topiclevel) throws ParseException {
        db = dbOpenHelper.getWritableDatabase();
        String topic = topiclevel + "";
        Cursor cursor = db.rawQuery("SELECT * FROM user_test WHERE userId like ? and word like ? and topicLevel like ?",
                new String[]{userId, word, topic});
        return ConvertToUserWord(cursor);

    }

    @Override
    public List<UserWord> findlearncount(String word, int topiclevel) throws ParseException {
        db = dbOpenHelper.getWritableDatabase();
        String topic = topiclevel + "";
        Cursor cursor = db.rawQuery("SELECT * FROM user_test WHERE word like ? and topicLevel like ?", new String[]{word, topic});
        return ConvertToUserWord(cursor);
    }


}
