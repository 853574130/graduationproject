package org.iauhsoaix.bean;

import java.util.Date;
import org.iauhsoaix.bean.BaseInfo;

public class ArticleInfo extends BaseInfo {
    /**
     * 
     */
    private String title;

    /**
     * md文件源码
     */
    private String mdcontent;

    /**
     * html源码
     */
    private String htmlcontent;

    /**
     * 
     */
    private String summary;

    /**
     * 
     */
    private Integer cid;

    /**
     * 
     */
    private Integer uid;

    /**
     * 
     */
    private Date publishdate;

    /**
     * 
     */
    private Date edittime;

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     */
    private Integer state;

    /**
     * 
     */
    private Integer pageview;

    /**
     * 
     * @return title 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * md文件源码
     * @return mdContent md文件源码
     */
    public String getMdcontent() {
        return mdcontent;
    }

    /**
     * md文件源码
     * @param mdcontent md文件源码
     */
    public void setMdcontent(String mdcontent) {
        this.mdcontent = mdcontent == null ? null : mdcontent.trim();
    }

    /**
     * html源码
     * @return htmlContent html源码
     */
    public String getHtmlcontent() {
        return htmlcontent;
    }

    /**
     * html源码
     * @param htmlcontent html源码
     */
    public void setHtmlcontent(String htmlcontent) {
        this.htmlcontent = htmlcontent == null ? null : htmlcontent.trim();
    }

    /**
     * 
     * @return summary 
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary 
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * 
     * @return cid 
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 
     * @param cid 
     */
    public void setCid(Integer cid) {
        this.cid = cid;
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
     * 
     * @return editTime 
     */
    public Date getEdittime() {
        return edittime;
    }

    /**
     * 
     * @param edittime 
     */
    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     * @return state 0表示草稿箱，1表示已发表，2表示已删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     * @param state 0表示草稿箱，1表示已发表，2表示已删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 
     * @return pageView 
     */
    public Integer getPageview() {
        return pageview;
    }

    /**
     * 
     * @param pageview 
     */
    public void setPageview(Integer pageview) {
        this.pageview = pageview;
    }
}