package com.rbu.lamphouse.event;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/6 16:41
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class UIChangedEvent {

    public static final int MSG_ADD_PROFILES = 0;
    public static final int MSG_LAMP_OFF = 1;
    public static final int MSG_LAMP_ON = 2;
    public static final int MSG_LOGIN = 3;
    public static final int MSG_UNREAD_EDIT = 4;
    public static final int MSG_UNREAD_DONE = 5;
    public static final int MSG_READ_EDIT = 6;
    public static final int MSG_READ_DONE = 7;
    public static final int MSG_UNREAD_DELETE = 8;
    public static final int MSG_READ_DELETE = 9;
    public static final int MSG_UNREAD_ALL = 10;
    public static final int MSG_READ_ALL = 11;
    public static final int MSG_UNREAD_INITDATA = 12;
//    public static final int MSG_READ_NOMOREDATA = 13;
    public static final int MSG_LOGOUT = 15;

    private int mUIEventTpye;

    public UIChangedEvent(int msg) {
        mUIEventTpye = msg;
    }

    public int getMsg() {
        return mUIEventTpye;
    }
}
