package com.rbu.lamphouse.diagnose;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/21 14:13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MsgNetEntity {
    private List<MessageEntity> data;
    private String message;
    private PageEntity page;
    private int status;

    private int state;

    public List<MessageEntity> getData() {
        return data;
    }

    public void setData(List<MessageEntity> data) {
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
