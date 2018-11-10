package com.rbu.lamphouse.diagnose;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/25 9:50
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MessageInfoEntity {
//    comment	注释	object
    //    content	内容	string
//    create_id	创建人id	object
//    create_time	创建时间	object
//    detail	详情	string
//    icon_img	图片	string
//    id	消息信息id	object
//    is_read	标记是否已读	boolean
//    modifier_id	修改者id	object
//    tag	标签	string
//    title	标题	string
//    update_time	更新时间	object

    private String comment;
    private String content;
    private int create_id;
    private String create_time;
    private String detail;
    private String icon_img;
    private int id;
    private boolean is_read;
    private int modifier_id;
    private String tag;
    private String title;
    private String update_time;

    public MessageInfoEntity(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIcon_img() {
        return icon_img;
    }

    public void setIcon_img(String icon_img) {
        this.icon_img = icon_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    public int getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(int modifier_id) {
        this.modifier_id = modifier_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
