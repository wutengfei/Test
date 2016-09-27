package com.peale_rn_1.dao.candidatetest;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.peale_rn_1.dao.DBOpenHelper;
import com.peale_rn_1.model.CandidateTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CandidateTestDaoImpl implements CandidateTestDao {

    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    public CandidateTestDaoImpl(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    //保存
    public void save(CandidateTest candidateTest) {
        db = dbOpenHelper.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("id", candidateTest.getId());
        newValues.put("userId", candidateTest.getUserId());
        newValues.put("testType", candidateTest.getTestType());
        newValues.put("testAspect", candidateTest.getTestAspect());
        newValues.put("testDifficulty", candidateTest.getTestDifficulty());
        newValues.put("pass1Count", candidateTest.getPass1Count());
        newValues.put("pass2Count", candidateTest.getPass2Count());
        newValues.put("pass3Count", candidateTest.getPass3Count());
        newValues.put("candidate", candidateTest.getCandidate());

        db.insert("test_candidate", null, newValues);
        db.close();
    }

    //更新
    public void update(CandidateTest candidateTest) {
        db = dbOpenHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("userId", candidateTest.getUserId());
        newValues.put("testType", candidateTest.getTestType());
        newValues.put("testAspect", candidateTest.getTestAspect());
        newValues.put("testDifficulty", candidateTest.getTestDifficulty());
        db.close();
        db.update("test_candidate", newValues, null, null);
    }

    @Override
    public List<CandidateTest> getCandidate(String userId) {
        db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM test_candidate where " +
                "userId like ? and candidate = 1", new String[]{userId});

        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        CandidateTest[] candidateTest = new CandidateTest[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            candidateTest[i] = new CandidateTest();
            candidateTest[i].setUserId(cursor.getString(cursor.getColumnIndex("userId")));
            candidateTest[i].setTestType(cursor.getInt(cursor.getColumnIndex("testType")));
            candidateTest[i].setTestAspect(cursor.getInt(cursor.getColumnIndex("testAspect")));
            candidateTest[i].setTestDifficulty(cursor.getInt(cursor.getColumnIndex("testDifficulty")));
            candidateTest[i].setPass1Count(cursor.getInt(cursor.getColumnIndex("pass1Count")));
            candidateTest[i].setPass2Count(cursor.getInt(cursor.getColumnIndex("pass2Count")));
            candidateTest[i].setPass3Count(cursor.getInt(cursor.getColumnIndex("pass3Count")));
            candidateTest[i].setCandidate(cursor.getInt(cursor.getColumnIndex("candidate")));
            cursor.moveToNext();
        }
        db.close();
        //把CandidateTest[]数组中的东西放入 List<CandidateTest>
        List<CandidateTest> candidateList = Arrays.asList(candidateTest);
        return candidateList;
    }

    //查找   这里返回的只有一个CandidateTest，所以不用CandidateTest[]
    public CandidateTest find(String userId, int testType, int testAspect, int testDifficulty) {
        db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM test_candidate where userId like ?",
                new String[]{userId});
        if (cursor.moveToNext()) {
            CandidateTest candidateTest = new CandidateTest();
            candidateTest.setUserId(cursor.getString(cursor.getColumnIndex("userId")));
            candidateTest.setTestType(cursor.getInt(cursor.getColumnIndex("testType")));
            candidateTest.setTestAspect(cursor.getInt(cursor.getColumnIndex("testAspect")));
            candidateTest.setTestDifficulty(cursor.getInt(cursor.getColumnIndex("testDifficulty")));
            candidateTest.setPass1Count(cursor.getInt(cursor.getColumnIndex("pass1Count")));
            candidateTest.setPass2Count(cursor.getInt(cursor.getColumnIndex("pass2Count")));
            candidateTest.setPass3Count(cursor.getInt(cursor.getColumnIndex("pass3Count")));
            candidateTest.setCandidate(cursor.getInt(cursor.getColumnIndex("candidate")));
            db.close();
            return candidateTest;
        }
        db.close();
        return null;
    }

    //得到所有数据
    public CandidateTest[] getAllData(Object[] queryParams) {
        String queryParam0 = queryParams[0] + "";
        String queryParam1 = queryParams[1] + "";
        String queryParam2 = queryParams[2] + "";
        db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM test_candidate WHERE userId like ? and testDifficulty = ? and " +
                "candidate = ?", new String[]{queryParam0, queryParam1, queryParam2});
        return ConvertToCandidateTest(cursor);
    }

    private CandidateTest[] ConvertToCandidateTest(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        CandidateTest[] candidateTest = new CandidateTest[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            candidateTest[i] = new CandidateTest();
            candidateTest[i].setUserId(cursor.getString(cursor.getColumnIndex("userId")));
            candidateTest[i].setTestType(cursor.getInt(cursor.getColumnIndex("testType")));
            candidateTest[i].setTestAspect(cursor.getInt(cursor.getColumnIndex("testAspect")));
            candidateTest[i].setTestDifficulty(cursor.getInt(cursor.getColumnIndex("testDifficulty")));
            candidateTest[i].setPass1Count(cursor.getInt(cursor.getColumnIndex("pass1Count")));
            candidateTest[i].setPass2Count(cursor.getInt(cursor.getColumnIndex("pass2Count")));
            candidateTest[i].setPass3Count(cursor.getInt(cursor.getColumnIndex("pass3Count")));
            candidateTest[i].setCandidate(cursor.getInt(cursor.getColumnIndex("candidate")));
            cursor.moveToNext();
        }
        db.close();
        return candidateTest;
    }
}
