package com.peale_rn_1.model;

/**
 * @author 刘玉婷
 * @version 创建时间：2016年6月16日 下午 15:22:05
 *          用户的测试偏好数据
 */

public class TestPreference {
    private int id;
    private String userId;
    private String feature;
    private int featureValue;
    private double pFeedback1;
    private double pFeedback2;
    private double pFeedback3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(int featureValue) {
        this.featureValue = featureValue;
    }

    public double getpFeedback1() {
        return pFeedback1;
    }

    public void setpFeedback1(double pFeedback1) {
        this.pFeedback1 = pFeedback1;
    }

    public double getpFeedback2() {
        return pFeedback2;
    }

    public void setpFeedback2(double pFeedback2) {
        this.pFeedback2 = pFeedback2;
    }

    public double getpFeedback3() {
        return pFeedback3;
    }

    public void setpFeedback3(double pFeedback3) {
        this.pFeedback3 = pFeedback3;
    }

    public TestPreference(String userId, String feature, int featureValue, double pFeedback1, double pFeedback2,
                          double pFeedback3) {
        super();
        this.userId = userId;
        this.feature = feature;
        this.featureValue = featureValue;
        this.pFeedback1 = pFeedback1;
        this.pFeedback2 = pFeedback2;
        this.pFeedback3 = pFeedback3;
    }

    public TestPreference() {
        super();
        // TODO Auto-generated constructor stub
    }

}
