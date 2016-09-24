package com.peale_rn_1.model;

/**
 * Created by Administrator on 2016/9/6.
 */
public class Tb_word_add {
    private String name;//单词
    private String grade;//内容所在的年级
    private String proText;//课文原句
    private String pathText;//课文原句发音路径
    private String proScene;//情景段落
    private String pathScene;//情景段落发音路径

    public Tb_word_add() {
    }

    public Tb_word_add(String name, String grade, String proText, String pathText, String proScene, String pathScene) {
        this.name = name;
        this.grade = grade;
        this.proText = proText;
        this.pathText = pathText;
        this.proScene = proScene;
        this.pathScene = pathScene;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProText() {
        return proText;
    }

    public void setProText(String proText) {
        this.proText = proText;
    }

    public String getPathText() {
        return pathText;
    }

    public void setPathText(String pathText) {
        this.pathText = pathText;
    }

    public String getProScene() {
        return proScene;
    }

    public void setProScene(String proScene) {
        this.proScene = proScene;
    }

    public String getPathScene() {
        return pathScene;
    }

    public void setPathScene(String pathScene) {
        this.pathScene = pathScene;
    }
}
