package com.rbu.lamphouse.diagnose;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/21 16:18
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class CouponNetEntity {
    private List<CouponEntity> data;
    private String message;
    private PageEntity page;
    private int status;

    public List<CouponEntity> getData() {
        return data;
    }

    public void setData(List<CouponEntity> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
