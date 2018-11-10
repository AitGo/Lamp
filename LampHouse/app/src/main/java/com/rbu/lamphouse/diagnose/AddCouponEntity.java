package com.rbu.lamphouse.diagnose;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/26 16:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddCouponEntity {
    private String flag;
    private String money;
    private String timeInterval;

    public AddCouponEntity(String flag,String money,String timeInterval) {
        this.flag = flag;
        this.money = money;
        this.timeInterval = timeInterval;
    }

    public AddCouponEntity() {

    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }
}
