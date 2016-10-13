package com.peale_rn_1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.widget.Toast;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.peale_rn_1.MainApplication;
import com.peale_rn_1.model.CandidateTestWords;
import com.peale_rn_1.model.Tb_user;
import com.peale_rn_1.model.Tb_word;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/9/7.
 */
public class WordDAO {

    private DBOpenHelper helper;
    private SQLiteDatabase db;
    //private static Tb_user user;

    public WordDAO(Context context) {
        helper = new DBOpenHelper(context);
    }

    //插入
    public Boolean insert(List<Tb_word> words) {
        db = helper.getWritableDatabase();
        //49个属性
        try {
            for (Tb_word word : words) {
                db.execSQL("insert into word (" +
                                "name,proID,proThemeNumber,grade,proTopic,proClass,proPartsOfSpeech,proWordProperty," +
                                "proChinese,proVersion,proBook,proDifficulty,proAssociate,proAntonym,proSynonyms,proExtend," +
                                "proNcyclopedia,proUse,proExpand,proCommonUse,proScene1,proScene2,proScene3,proScene4,proScene5," +
                                "proScene6,pathScene1,pathScene2,pathScene3,pathScene4,pathScene5,pathScene6,proText1,proText2," +
                                "proText3,proText4,proText5,proText6,pathText1,pathText2,pathText3,pathText4,pathText5,pathText6," +
                                "pronunctionPath,picturePath,vedioPath1,vedioPath2,vedioPath3) values (?,?,?,?,?,?,?,?," +
                                "?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?)",
                        new Object[]{
                                word.getName(), word.getProID(), word.getProThemeNumber(), word.getGrade(), word.getProTopic(), word.getProClass(),
                                word.getProPartsOfSpeech(), word.getProWordProperty(), word.getProChinese(), word.getProVersion(), word.getProBook(),
                                word.getProDifficulty(), word.getProAssociate(), word.getProAntonym(), word.getProSynonyms(), word.getProExtend(),
                                word.getProNcyclopedia(), word.getProUse(), word.getProExpand(), word.getProCommonUse(), word.getProScene1(),
                                word.getProScene2(), word.getProScene3(), word.getProScene4(), word.getProScene5(), word.getProScene6(),
                                word.getPathScene1(), word.getPathScene2(), word.getPathScene3(), word.getPathScene4(), word.getPathScene5(),
                                word.getPathScene6(), word.getProText1(), word.getProText2(), word.getProText3(), word.getProText4(), word.getProText5(),
                                word.getProText6(), word.getPathText1(), word.getPathText2(), word.getPathText3(), word.getPathText4(), word.getPathText5(),
                                word.getPathText6(), word.getPronunctionPath(), word.getPicturePath(), word.getVedioPath1(), word.getVedioPath2(), word.getVedioPath3(),
                        });

            }
            db.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    //查找  根据主题和年级查找单词
    public Tb_word[] find(String proTopic, String grade) {
        db = helper.getWritableDatabase();
        Cursor cursor;
        if (proTopic == null) {
            cursor = db.rawQuery("SELECT name FROM word WHERE  grade like ?", new String[]{grade});//根据年级查找单词
        } else {
            cursor = db.rawQuery("SELECT name FROM word WHERE proTopic like ? and grade like ?", new String[]{proTopic, grade});
        }

        int resultCounts = cursor.getCount();
        if (resultCounts < 4) {
            cursor = db.rawQuery("SELECT name FROM word WHERE  grade like ?", new String[]{grade});
            resultCounts = cursor.getCount();
        }
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        Tb_word[] tb_word = new Tb_word[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            tb_word[i] = new Tb_word();
            tb_word[i].setName(cursor.getString(cursor.getColumnIndex("name")));
            cursor.moveToNext();
        }
        db.close();
        return tb_word;
    }

    //根据传过来的单词查询出年级和主题
    public String[] find(String firstWord) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT proTopic, grade FROM word WHERE name like ?", new String[]{firstWord});
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        Tb_word[] tb_word = new Tb_word[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            tb_word[i] = new Tb_word();
            tb_word[i].setProTopic(cursor.getString(cursor.getColumnIndex("proTopic")));
            tb_word[i].setGrade(cursor.getString(cursor.getColumnIndex("grade")));
            cursor.moveToNext();
        }
        db.close();
        String[] topicAndGrade = new String[]{tb_word[0].getProTopic(), tb_word[0].getGrade()};
        return topicAndGrade;
    }

    //根据单词查询其图片，音频，课文原句
    public String[] search(String word) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT picturePath, pronunctionPath, proText4 FROM word WHERE name like ?", new String[]{word});
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        Tb_word tb_word = new Tb_word();

        tb_word.setPicturePath(cursor.getString(cursor.getColumnIndex("picturePath")));
        tb_word.setPronunctionPath(cursor.getString(cursor.getColumnIndex("pronunctionPath")));
        tb_word.setProText4(cursor.getString(cursor.getColumnIndex("proText4")));
        cursor.moveToNext();

        db.close();
        String[] resource = new String[]{tb_word.getPicturePath(), tb_word.getPronunctionPath(), tb_word.getProText4()};
        return resource;
    }

    //选出两个错误选项的单词和图片
    public String[] search() {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, picturePath FROM word", null);
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        Tb_word[] tb_word = new Tb_word[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            tb_word[i] = new Tb_word();
            tb_word[i].setName(cursor.getString(cursor.getColumnIndex("name")));
            tb_word[i].setPicturePath(cursor.getString(cursor.getColumnIndex("picturePath")));
            cursor.moveToNext();
        }
        db.close();
        //随机选取两个单词作为干扰项
        int a = new Random().nextInt(tb_word.length);
        Tb_word s = tb_word[a];
        int b = new Random().nextInt(tb_word.length);
        Tb_word t = tb_word[b];

        String[] resource = new String[]{s.getName(), s.getPicturePath(), t.getName(), t.getPicturePath()};
        return resource;
    }

    public String searchPath() {
        db = helper.getWritableDatabase();
        String fileName = "2-17-4-4";
        String pathPrefix = "";
        Cursor cursor = db.rawQuery("SELECT filePath FROM file WHERE fileName like ?", new String[]{fileName});
        if (cursor.moveToNext()) {
            pathPrefix = cursor.getString(cursor.getColumnIndex("filePath"));
        }
        db.close();
        return pathPrefix;
    }

    public WritableMap findWordInfoByWordName(String newWord) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from word where name = ?",
                new String[]{newWord});
        if (cursor.moveToNext()) {
            WritableMap map = Arguments.createMap();
          /*  map.putString("name", cursor.getString(cursor.getColumnIndex("name")));*/
            map.putString("proID", cursor.getString(cursor.getColumnIndex("proID")));
            map.putString("proThemeNumber", cursor.getString(cursor.getColumnIndex("proThemeNumber")));
            map.putString("grade", cursor.getString(cursor.getColumnIndex("grade")));
            map.putString("proTopic", cursor.getString(cursor.getColumnIndex("proTopic")));
            map.putString("proClass", cursor.getString(cursor.getColumnIndex("proClass")));
            map.putString("proPartsOfSpeech", cursor.getString(cursor.getColumnIndex("proPartsOfSpeech")));
            map.putString("proWordProperty", cursor.getString(cursor.getColumnIndex("proWordProperty")));
            map.putString("proChinese", cursor.getString(cursor.getColumnIndex("proChinese")));
            map.putString("proVersion", cursor.getString(cursor.getColumnIndex("proVersion")));
            map.putString("proBook", cursor.getString(cursor.getColumnIndex("proBook")));
            map.putString("proDifficulty", cursor.getString(cursor.getColumnIndex("proDifficulty")));
            map.putString("proAssociate", cursor.getString(cursor.getColumnIndex("proAssociate")));
            map.putString("proAntonym", cursor.getString(cursor.getColumnIndex("proAntonym")));
            map.putString("proSynonyms", cursor.getString(cursor.getColumnIndex("proSynonyms")));
            map.putString("proExtend", cursor.getString(cursor.getColumnIndex("proExtend")));
            map.putString("proNcyclopedia", cursor.getString(cursor.getColumnIndex("proNcyclopedia")));
            map.putString("proUse", cursor.getString(cursor.getColumnIndex("proUse")));
            map.putString("proExpand", cursor.getString(cursor.getColumnIndex("proExpand")));
            map.putString("proCommonUse", cursor.getString(cursor.getColumnIndex("proCommonUse")));
            map.putString("proScene1", cursor.getString(cursor.getColumnIndex("proScene1")));
            map.putString("proScene2", cursor.getString(cursor.getColumnIndex("proScene2")));
            map.putString("proScene3", cursor.getString(cursor.getColumnIndex("proScene3")));
            map.putString("proScene4", cursor.getString(cursor.getColumnIndex("proScene4")));
            map.putString("proScene5", cursor.getString(cursor.getColumnIndex("proScene5")));
            map.putString("proScene6", cursor.getString(cursor.getColumnIndex("proScene6")));
            map.putString("pathScene1", cursor.getString(cursor.getColumnIndex("pathScene1")));
            map.putString("pathScene2", cursor.getString(cursor.getColumnIndex("pathScene2")));
            map.putString("pathScene3", cursor.getString(cursor.getColumnIndex("pathScene3")));
            map.putString("pathScene4", cursor.getString(cursor.getColumnIndex("pathScene4")));
            map.putString("pathScene5", cursor.getString(cursor.getColumnIndex("pathScene5")));
            map.putString("pathScene6", cursor.getString(cursor.getColumnIndex("pathScene6")));
            map.putString("proText1", cursor.getString(cursor.getColumnIndex("proText1")));
            map.putString("proText2", cursor.getString(cursor.getColumnIndex("proText2")));
            map.putString("proText3", cursor.getString(cursor.getColumnIndex("proText3")));
            map.putString("proText4", cursor.getString(cursor.getColumnIndex("proText4")));
            map.putString("proText5", cursor.getString(cursor.getColumnIndex("proText5")));
            map.putString("proText6", cursor.getString(cursor.getColumnIndex("proText6")));
            map.putString("pathText1", cursor.getString(cursor.getColumnIndex("pathText1")));
            map.putString("pathText2", cursor.getString(cursor.getColumnIndex("pathText2")));
            map.putString("pathText3", cursor.getString(cursor.getColumnIndex("pathText3")));
            map.putString("pathText4", cursor.getString(cursor.getColumnIndex("pathText4")));
            map.putString("pathText5", cursor.getString(cursor.getColumnIndex("pathText5")));
            map.putString("pathText6", cursor.getString(cursor.getColumnIndex("pathText6")));
            map.putString("pronunctionPath", cursor.getString(cursor.getColumnIndex("pronunctionPath")));
            map.putString("picturePath", cursor.getString(cursor.getColumnIndex("picturePath")));
            map.putString("vedioPath1", cursor.getString(cursor.getColumnIndex("vedioPath1")));
            map.putString("vedioPath2", cursor.getString(cursor.getColumnIndex("vedioPath2")));
            map.putString("vedioPath3", cursor.getString(cursor.getColumnIndex("vedioPath3")));
            return map;
        }
        db.close();
        return null;
    }


    /**
     * 根据单词找到其图片路径以及发音路径
     *
     * @param word
     * @return
     */
    public WritableMap findWordInfo(String word) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select pronunctionPath,picturePath from word where name = ?",
                new String[]{word});
        if (cursor.moveToNext()) {
            WritableMap map = Arguments.createMap();
            map.putString("pronunctionPath", cursor.getString(cursor.getColumnIndex("pronunctionPath")));
            map.putString("picturePath", cursor.getString(cursor.getColumnIndex("picturePath")));
            return map;
        }
        db.close();
        return null;
    }

}
