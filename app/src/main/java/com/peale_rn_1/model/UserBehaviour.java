package com.peale_rn_1.model;

import java.util.Date;


/**
 * @author 刘玉婷
 * @version 创建时间：2016年8月21日 20:30
 *          类说明
 */
public class UserBehaviour {
    private int id;
    private String userId;
    private String doWhere;
    private String doWhat;
    private Date doWhen;

    public UserBehaviour() {
    }

    public UserBehaviour( String userId, String doWhere, String doWhat, Date doWhen) {
        this.userId = userId;
        this.doWhere = doWhere;
        this.doWhat = doWhat;
        this.doWhen = doWhen;
    }

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

    public String getDoWhere() {
        return doWhere;
    }

    public void setDoWhere(String doWhere) {
        this.doWhere = doWhere;
    }

    public String getDoWhat() {
        return doWhat;
    }

    public void setDoWhat(String doWhat) {
        this.doWhat = doWhat;
    }

    public Date getDoWhen() {
        return doWhen;
    }

    public void setDoWhen(Date doWhen) {
        this.doWhen = doWhen;
    }
}
