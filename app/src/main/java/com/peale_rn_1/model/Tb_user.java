package com.peale_rn_1.model;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Tb_user {
    private int userId;
    private String system;
    private String userName;
    private String passWord;
    private String realName;
    private String sex;
    private String school;
    private String grade;
    private String classNum;
    private String birthYear;
    private String beforeLevel;
    private String EnglishScore;
    private String learningStyle1;
    private String learningStyle2;
    private String learningStyle3;
    private String learningStyle4;
    private String token;


    //构造函数。
    public Tb_user() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getBeforeLevel() {
        return beforeLevel;
    }

    public void setBeforeLevel(String beforeLevel) {
        this.beforeLevel = beforeLevel;
    }

    public String getEnglishScore() {
        return EnglishScore;
    }

    public void setEnglishScore(String englishScore) {
        EnglishScore = englishScore;
    }

    public String getLearningStyle1() {
        return learningStyle1;
    }

    public void setLearningStyle1(String learningStyle1) {
        this.learningStyle1 = learningStyle1;
    }

    public String getLearningStyle2() {
        return learningStyle2;
    }

    public void setLearningStyle2(String learningStyle2) {
        this.learningStyle2 = learningStyle2;
    }

    public String getLearningStyle3() {
        return learningStyle3;
    }

    public void setLearningStyle3(String learningStyle3) {
        this.learningStyle3 = learningStyle3;
    }

    public String getLearningStyle4() {
        return learningStyle4;
    }

    public void setLearningStyle4(String learningStyle4) {
        this.learningStyle4 = learningStyle4;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
