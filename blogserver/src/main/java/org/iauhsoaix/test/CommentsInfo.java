package org.iauhsoaix.test;

import java.util.Date;
import org.iauhsoaix.bean.BaseInfo;

public class CommentsInfo extends BaseInfo {
    /**
     * 
     */
    private Integer aid;

    /**
     * 
     */
    private Date publishdate;

    /**
     * -1表示正常回复，其他值表示是评论的回复
     */
    private Integer parentid;

    /**
     * 
     */
    private Integer uid;

    /**
     * 
     */
    private String content;

    /**
     * 
     * @return aid 
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 
     * @param aid 
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 
     * @return publishDate 
     */
    public Date getPublishdate() {
        return publishdate;
    }

    /**
     * 
     * @param publishdate 
     */
    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    /**
     * -1表示正常回复，其他值表示是评论的回复
     * @return parentId -1表示正常回复，其他值表示是评论的回复
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * -1表示正常回复，其他值表示是评论的回复
     * @param parentid -1表示正常回复，其他值表示是评论的回复
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 
     * @return uid 
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 
     * @param uid 
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 
     * @return content 
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content 
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}