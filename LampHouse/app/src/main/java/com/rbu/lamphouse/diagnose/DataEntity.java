package com.rbu.lamphouse.diagnose;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 9:53
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DataEntity {
//    createTime	创建时间	string	@mock=1519971741000
//    eMail	邮箱	string	@mock=wujiandao07@163.com
//    password	密码 返回值位“”	string	@mock=E10ADC3949BA59ABBE56E057F20F883E
//    userId	用户id

    private String createTime;
    private String eMail;//login
    private String password;//loogin,forget
    private String userId;

    private String email;//regist tkjn forget
    private int isRegister;//regist tkjn

    private String graphCode;//regist forget
    private String userPwd;//regist

    private String confirmPassword; //forget

    private String token;//login

    private int overDue;//coupon
    private int unUse;//coupon
    private int used;//coupon


    private DataEntity(Builder builder) {
        this.createTime = builder.createTime;
        this.eMail = builder.eMail;
        this.password = builder.password;
        this.userId = builder.userId;

        this.email = builder.email;
        this.isRegister = builder.isRegister;

        this.graphCode = builder.graphCode;
        this.userPwd = builder.userPwd;

        this.confirmPassword = builder.confirmPassword;

        this.token = builder.token;
    }

    public static class Builder{
        private String createTime;
        private String eMail;//login
        private String password;//loogin,forget
        private String userId;

        private String email;//regist tkjn forget
        private int isRegister;//regist tkjn

        private String graphCode;//regist forget
        private String userPwd;//regist

        private String confirmPassword; //forget

        private String token;//login

        public Builder() {

        }

        public Builder createTime(String val) {
            this.createTime = val;
            return this;
        }

        public Builder eMail(String val) {
            this.eMail = val;
            return this;
        }

        public Builder password(String val) {
            this.password = val;
            return this;
        }

        public Builder userId(String val) {
            this.userId = val;
            return this;
        }

        public Builder email(String val) {
            this.email = val;
            return this;
        }

        public Builder isRegister(int val) {
            this.isRegister = val;
            return this;
        }

        public Builder graphCode(String val) {
            this.graphCode = val;
            return this;
        }

        public Builder userPwd(String val) {
            this.userPwd = val;
            return this;
        }

        public Builder confirmPassword(String val) {
            this.confirmPassword = val;
            return this;
        }

        public Builder token(String val) {
            this.token = val;
            return this;
        }
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(int isRegister) {
        this.isRegister = isRegister;
    }

    public String getGraphCode() {
        return graphCode;
    }

    public void setGraphCode(String graphCode) {
        this.graphCode = graphCode;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOverDue() {
        return overDue;
    }

    public void setOverDue(int overDue) {
        this.overDue = overDue;
    }

    public int getUnUse() {
        return unUse;
    }

    public void setUnUse(int unUse) {
        this.unUse = unUse;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }
}
