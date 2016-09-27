package com.peale_rn_1.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class Tb_word {
    private String name;//单词
    private String proID;//单词ID
    //private String proFunction;//单词
    private String proThemeNumber;//单词主题
    private String grade;//单词所在的年级
    private String proTopic;//单词主题
    private String proClass;//单词同类
    private String proPartsOfSpeech;//词性
    private String proWordProperty;//复数
    private String proChinese;//中文词性
    private String proVersion;//单词教材
    private String proBook;//单词册数
    private String proDifficulty;//单词难度
    private String proAssociate;//联想到的单词
    private String proAntonym;//同义词
    private String proSynonyms;//反义词
    private String proExtend;//拓展（单词）
    private String proNcyclopedia;//百科
    private String proUse;//用法
    private String proExpand;//延伸例句
    private String proCommonUse;//常用
    //private String pathText1;//课文原句发音路径
    private String proScene1;//情景段落
    private String proScene2;//情景段落
    private String proScene3;//情景段落
    private String proScene4;//情景段落
    private String proScene5;//情景段落
    private String proScene6;//情景段落
    private String pathScene1;//情景段落发音路径
    private String pathScene2;//情景段落发音路径
    private String pathScene3;//情景段落发音路径
    private String pathScene4;//情景段落发音路径
    private String pathScene5;//情景段落发音路径
    private String pathScene6;//情景段落发音路径

    private String proText1;//课文原句
    private String proText2;//课文原句
    private String proText3;//课文原句
    private String proText4;//课文原句
    private String proText5;//课文原句
    private String proText6;//课文原句
    private String pathText1;//课文原句发音路径
    private String pathText2;//课文原句发音路径
    private String pathText3;//课文原句发音路径
    private String pathText4;//课文原句发音路径
    private String pathText5;//课文原句发音路径
    private String pathText6;//课文原句发音路径
    private String pronunctionPath;//单词发音路径
    private String picturePath;//图片路径
    private String vedioPath1;//视频路径难度1
    private String vedioPath2;//视频路径难度2
    private String vedioPath3;//视频路径难度3

    public Tb_word() {};

    public Tb_word(String name, String proID, String proThemeNumber, String grade, String proTopic, String proClass, String proPartsOfSpeech, String proWordProperty, String proChinese, String proVersion, String proBook, String proDifficulty, String proAssociate, String proAntonym, String proSynonyms, String proExtend, String proNcyclopedia, String proUse, String proExpand, String proCommonUse, String proScene1, String proScene2, String proScene3, String proScene4, String proScene5, String proScene6, String pathScene1, String pathScene2, String pathScene3, String pathScene4, String pathScene5, String pathScene6, String proText1, String proText2, String proText3, String proText4, String proText5, String proText6, String pathText1, String pathText2, String pathText3, String pathText4, String pathText5, String pathText6, String pronunctionPath, String picturePath, String vedioPath1, String vedioPath2, String vedioPath3) {
        this.name = name;
        this.proID = proID;
        this.proThemeNumber = proThemeNumber;
        this.grade = grade;
        this.proTopic = proTopic;
        this.proClass = proClass;
        this.proPartsOfSpeech = proPartsOfSpeech;
        this.proWordProperty = proWordProperty;
        this.proChinese = proChinese;
        this.proVersion = proVersion;
        this.proBook = proBook;
        this.proDifficulty = proDifficulty;
        this.proAssociate = proAssociate;
        this.proAntonym = proAntonym;
        this.proSynonyms = proSynonyms;
        this.proExtend = proExtend;
        this.proNcyclopedia = proNcyclopedia;
        this.proUse = proUse;
        this.proExpand = proExpand;
        this.proCommonUse = proCommonUse;
        this.proScene1 = proScene1;
        this.proScene2 = proScene2;
        this.proScene3 = proScene3;
        this.proScene4 = proScene4;
        this.proScene5 = proScene5;
        this.proScene6 = proScene6;
        this.pathScene1 = pathScene1;
        this.pathScene2 = pathScene2;
        this.pathScene3 = pathScene3;
        this.pathScene4 = pathScene4;
        this.pathScene5 = pathScene5;
        this.pathScene6 = pathScene6;
        this.proText1 = proText1;
        this.proText2 = proText2;
        this.proText3 = proText3;
        this.proText4 = proText4;
        this.proText5 = proText5;
        this.proText6 = proText6;
        this.pathText1 = pathText1;
        this.pathText2 = pathText2;
        this.pathText3 = pathText3;
        this.pathText4 = pathText4;
        this.pathText5 = pathText5;
        this.pathText6 = pathText6;
        this.pronunctionPath = pronunctionPath;
        this.picturePath = picturePath;
        this.vedioPath1 = vedioPath1;
        this.vedioPath2 = vedioPath2;
        this.vedioPath3 = vedioPath3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getProThemeNumber() {
        return proThemeNumber;
    }

    public void setProThemeNumber(String proThemeNumber) {
        this.proThemeNumber = proThemeNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProTopic() {
        return proTopic;
    }

    public void setProTopic(String proTopic) {
        this.proTopic = proTopic;
    }

    public String getProClass() {
        return proClass;
    }

    public void setProClass(String proClass) {
        this.proClass = proClass;
    }

    public String getProPartsOfSpeech() {
        return proPartsOfSpeech;
    }

    public void setProPartsOfSpeech(String proPartsOfSpeech) {
        this.proPartsOfSpeech = proPartsOfSpeech;
    }

    public String getProChinese() {
        return proChinese;
    }

    public void setProChinese(String proChinese) {
        this.proChinese = proChinese;
    }

    public String getProWordProperty() {
        return proWordProperty;
    }

    public void setProWordProperty(String proWordProperty) {
        this.proWordProperty = proWordProperty;
    }

    public String getProVersion() {
        return proVersion;
    }

    public void setProVersion(String proVersion) {
        this.proVersion = proVersion;
    }

    public String getProBook() {
        return proBook;
    }

    public void setProBook(String proBook) {
        this.proBook = proBook;
    }

    public String getProDifficulty() {
        return proDifficulty;
    }

    public void setProDifficulty(String proDifficulty) {
        this.proDifficulty = proDifficulty;
    }

    public String getProNcyclopedia() {
        return proNcyclopedia;
    }

    public void setProNcyclopedia(String proNcyclopedia) {
        this.proNcyclopedia = proNcyclopedia;
    }

    public String getProUse() {
        return proUse;
    }

    public void setProUse(String proUse) {
        this.proUse = proUse;
    }

    public String getProAssociate() {
        return proAssociate;
    }

    public void setProAssociate(String proAssociate) {
        this.proAssociate = proAssociate;
    }

    public String getProAntonym() {
        return proAntonym;
    }

    public void setProAntonym(String proAntonym) {
        this.proAntonym = proAntonym;
    }

    public String getProSynonyms() {
        return proSynonyms;
    }

    public void setProSynonyms(String proSynonyms) {
        this.proSynonyms = proSynonyms;
    }

    public String getProExtend() {
        return proExtend;
    }

    public void setProExtend(String proExtend) {
        this.proExtend = proExtend;
    }

    public String getPronunctionPath() {
        return pronunctionPath;
    }

    public void setPronunctionPath(String pronunctionPath) {
        this.pronunctionPath = pronunctionPath;
    }

    public String getProCommonUse() {
        return proCommonUse;
    }

    public void setProCommonUse(String proCommonUse) {
        this.proCommonUse = proCommonUse;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getVedioPath1() {
        return vedioPath1;
    }

    public void setVedioPath1(String vedioPath1) {
        this.vedioPath1 = vedioPath1;
    }

    public String getVedioPath2() {
        return vedioPath2;
    }

    public void setVedioPath2(String vedioPath2) {
        this.vedioPath2 = vedioPath2;
    }

    public String getVedioPath3() {
        return vedioPath3;
    }

    public void setVedioPath3(String vedioPath3) {
        this.vedioPath3 = vedioPath3;
    }

    public String getProText1() {
        return proText1;
    }

    public void setProText1(String proText1) {
        this.proText1 = proText1;
    }

    public String getProText2() {
        return proText2;
    }

    public void setProText2(String proText2) {
        this.proText2 = proText2;
    }

    public String getProText3() {
        return proText3;
    }

    public void setProText3(String proText3) {
        this.proText3 = proText3;
    }

    public String getProText4() {
        return proText4;
    }

    public void setProText4(String proText4) {
        this.proText4 = proText4;
    }

    public String getProText5() {
        return proText5;
    }

    public void setProText5(String proText5) {
        this.proText5 = proText5;
    }

    public String getPathText1() {
        return pathText1;
    }

    public void setPathText1(String pathText1) {
        this.pathText1 = pathText1;
    }

    public String getProText6() {
        return proText6;
    }

    public void setProText6(String proText6) {
        this.proText6 = proText6;
    }

    public String getPathText2() {
        return pathText2;
    }

    public void setPathText2(String pathText2) {
        this.pathText2 = pathText2;
    }

    public String getPathText3() {
        return pathText3;
    }

    public void setPathText3(String pathText3) {
        this.pathText3 = pathText3;
    }

    public String getPathText4() {
        return pathText4;
    }

    public void setPathText4(String pathText4) {
        this.pathText4 = pathText4;
    }

    public String getPathText5() {
        return pathText5;
    }

    public void setPathText5(String pathText5) {
        this.pathText5 = pathText5;
    }

    public String getPathText6() {
        return pathText6;
    }

    public void setPathText6(String pathText6) {
        this.pathText6 = pathText6;
    }

    public String getProScene1() {
        return proScene1;
    }

    public void setProScene1(String proScene1) {
        this.proScene1 = proScene1;
    }

    public String getProScene2() {
        return proScene2;
    }

    public void setProScene2(String proScene2) {
        this.proScene2 = proScene2;
    }

    public String getProScene3() {
        return proScene3;
    }

    public void setProScene3(String proScene3) {
        this.proScene3 = proScene3;
    }

    public String getProScene4() {
        return proScene4;
    }

    public void setProScene4(String proScene4) {
        this.proScene4 = proScene4;
    }

    public String getProScene5() {
        return proScene5;
    }

    public void setProScene5(String proScene5) {
        this.proScene5 = proScene5;
    }

    public String getProScene6() {
        return proScene6;
    }

    public void setProScene6(String proScene6) {
        this.proScene6 = proScene6;
    }

    public String getPathScene1() {
        return pathScene1;
    }

    public void setPathScene1(String pathScene1) {
        this.pathScene1 = pathScene1;
    }

    public String getPathScene2() {
        return pathScene2;
    }

    public void setPathScene2(String pathScene2) {
        this.pathScene2 = pathScene2;
    }

    public String getPathScene3() {
        return pathScene3;
    }

    public void setPathScene3(String pathScene3) {
        this.pathScene3 = pathScene3;
    }

    public String getPathScene4() {
        return pathScene4;
    }

    public void setPathScene4(String pathScene4) {
        this.pathScene4 = pathScene4;
    }

    public String getPathScene5() {
        return pathScene5;
    }

    public void setPathScene5(String pathScene5) {
        this.pathScene5 = pathScene5;
    }

    public String getPathScene6() {
        return pathScene6;
    }

    public void setPathScene6(String pathScene6) {
        this.pathScene6 = pathScene6;
    }

    public String getProExpand() {
        return proExpand;
    }

    public void setProExpand(String proExpand) {
        this.proExpand = proExpand;
    }
}
