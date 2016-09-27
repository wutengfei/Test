package com.peale_rn_1.dao.candidatetestwords;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.peale_rn_1.dao.DBOpenHelper;

import com.peale_rn_1.model.CandidateTestWords;



public class CandidateTestWordsDaoImpl implements CandidateTestWordsDao {
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    public CandidateTestWordsDaoImpl(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    @Override
    public CandidateTestWords find(String userId, int difficulty) {
        String difficult = difficulty + "";
        db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM test_candidateWords where userId like ? and difficulty = ?",
                new String[]{userId, difficult});
        CandidateTestWords[] ctw = ConvertToCandidateTestWords(cursor);
        if (ctw != null && ctw.length > 0)
            return ctw[0];
        return null;
    }

    @Override
    public CandidateTestWords find(String userId, String word) {
        db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM test_candidateWords where userId like ? and word = ?",
                new String[]{userId, word});
        CandidateTestWords[] ctw = ConvertToCandidateTestWords(cursor);
        if (ctw != null && ctw.length > 0)
            return ctw[0];
        return null;
    }

    private CandidateTestWords[] ConvertToCandidateTestWords(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        CandidateTestWords[] candidateTestWords = new CandidateTestWords[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            candidateTestWords[i] = new CandidateTestWords();
            candidateTestWords[i].setUserId(cursor.getString(cursor.getColumnIndex("userId")));
            candidateTestWords[i].setWord(cursor.getString(cursor.getColumnIndex("word")));
            candidateTestWords[i].setDifficulty(cursor.getInt(cursor.getColumnIndex("difficulty")));
            cursor.moveToNext();
        }
        db.close();
        return candidateTestWords;
    }

    public void save(CandidateTestWords candidateTestWords) {
        ContentValues newValues = new ContentValues();
        newValues.put("userId", candidateTestWords.getUserId());
        newValues.put("word", candidateTestWords.getWord());
        newValues.put("difficulty", candidateTestWords.getDifficulty());
        db.insert("test_candidateWords", null, newValues);
        db.close();
    }
}
