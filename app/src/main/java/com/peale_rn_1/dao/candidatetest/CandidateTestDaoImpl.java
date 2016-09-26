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
 //  Context context;

    public CandidateTestDaoImpl(Context context) {
      //  this.context = context;
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
        Cursor cursor = db.rawQuery("SELECT userId, testType, testAspect, testDifficulty FROM test_candidate where " +
                "userId like ? and candidate like 1", new String[]{userId});

        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        CandidateTest[] candidate=new CandidateTest[ resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            candidate[i]=new CandidateTest();
            candidate[i].setUserId(cursor.getString(cursor.getColumnIndex(userId)));
            candidate[i].setTestType(cursor.getInt(1));
            candidate[i].setTestAspect(cursor.getInt(2));
            candidate[i].setTestDifficulty(cursor.getInt(3));
            cursor.moveToNext();
        }
        db.close();
        //把CandidateTest[]数组中的东西放入 List<CandidateTest>
        List<CandidateTest> candidateList = Arrays.asList(candidate);
        return candidateList;
    }

    /*	@Override
        public List<CandidateTest> getCandidate(String userId) {
            List<CandidateTest> list = getHt().find("From CandidateTest o where o.userId=? and o.candidate=1",userId);
            if(list != null && list.size() >= 5)
                return list;
            return null;
        }
        }*/
    //查找   这里返回的只有一个CandidateTest，所以不用CandidateTest[]
    public CandidateTest find(String userId, int testType, int testAspect, int testDifficulty) {
        db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT userId, testType, testAspect, testDifficulty FROM test_candidate where userId like ?",
                new String[]{userId});
        if (cursor.moveToNext()) {
            CandidateTest candidateTest = new CandidateTest();
            candidateTest.setUserId(cursor.getString(cursor.getColumnIndex(userId)));
            candidateTest.setTestType(cursor.getInt(1));
            candidateTest.setTestAspect(cursor.getInt(2));
            candidateTest.setTestDifficulty(cursor.getInt(3));
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
        Cursor cursor = db.rawQuery("SELECT * FROM test_candidate WHERE userId like ? and testDifficulty like ? and " +
                "candidate like ?", new String[]{queryParam0, queryParam1, queryParam2});
        return ConvertToBook(cursor);
    }

    private CandidateTest[] ConvertToBook(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        CandidateTest[] CandidateTest = new CandidateTest[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            CandidateTest[i] = new CandidateTest();
            CandidateTest[i].setUserId(cursor.getString(0));
            CandidateTest[i].setTestType(cursor.getInt(1));
            CandidateTest[i].setTestAspect(cursor.getInt(2));
            CandidateTest[i].setTestDifficulty(cursor.getInt(3));
            CandidateTest[i].setPass1Count(cursor.getInt(4));
            CandidateTest[i].setPass2Count(cursor.getInt(5));
            CandidateTest[i].setPass3Count(cursor.getInt(6));
            CandidateTest[i].setCandidate(cursor.getInt(7));
            cursor.moveToNext();
        }
        return CandidateTest;
    }
}
