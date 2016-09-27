package com.peale_rn_1.dao.testpreference;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.peale_rn_1.dao.DBOpenHelper;

import com.peale_rn_1.model.TestPreference;


public class TestPreferenceDaoImpl implements TestPreferenceDao {
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    public TestPreferenceDaoImpl(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    //查找
    @Override
    public TestPreference find(String userId, String feature, int featureValue) {
        double[] tpFeedback = new double[3];
        db = dbOpenHelper.getWritableDatabase();
        String featureValues = featureValue + "";
        Cursor cursor = db.rawQuery("SELECT * FROM test_preference where userId like ? and feature like ? and featureValue like ?",
                new String[]{userId, feature, featureValues});
        TestPreference[] tp = ConvertToTestPreference(cursor);
        if (tp != null && tp.length > 0)
            return tp[0];
        return null;

    }

    private TestPreference[] ConvertToTestPreference(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        TestPreference[] testPreference = new TestPreference[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            testPreference[i] = new TestPreference();
            testPreference[i].setUserId(cursor.getString(cursor.getColumnIndex("userId")));
            testPreference[i].setFeatureValue(cursor.getInt(cursor.getColumnIndex("featureValue")));
            testPreference[i].setpFeedback1(cursor.getInt(cursor.getColumnIndex("pFeedback1")));
            testPreference[i].setpFeedback2(cursor.getInt(cursor.getColumnIndex("pFeedback2")));
            testPreference[i].setpFeedback3(cursor.getInt(cursor.getColumnIndex("pFeedback3")));
            testPreference[i].setFeature(cursor.getString(cursor.getColumnIndex("feature")));
            cursor.moveToNext();
        }
        db.close();

        return testPreference;
    }

    //保存
    public void save(TestPreference testPreference) {

        ContentValues newValues = new ContentValues();
        newValues.put("userId", testPreference.getUserId());
        newValues.put("featureValue", testPreference.getFeatureValue());
        newValues.put("pFeedback1", testPreference.getpFeedback1());
        newValues.put("pFeedback2", testPreference.getpFeedback2());
        newValues.put("pFeedback3", testPreference.getpFeedback3());
        newValues.put("feature", testPreference.getFeature());

        db.insert("test_preference", null, newValues);
        db.close();
    }

    //更新
    public void update(TestPreference testPreference) {
        db = dbOpenHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("userId", testPreference.getUserId());
        newValues.put("featureValue", testPreference.getFeatureValue());
        newValues.put("pFeedback1", testPreference.getpFeedback1());
        newValues.put("pFeedback2", testPreference.getpFeedback2());
        newValues.put("pFeedback3", testPreference.getpFeedback3());
        newValues.put("feature", testPreference.getFeature());
        db.close();
        db.update("test_preference", newValues, null, null);
    }
}
