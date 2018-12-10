package org.iauhsoaix.dal.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "article")
public class ArticleEntity extends AbstractEntity {
//    private Integer id;

    /**
     *
     */
    @Column(name = "title")
    private String title;

    /**
     *
     */
    @Column(name = "cid")
    private Integer cid;

    /**
     *
     */
    @Column(name = "uid")
    private Long uid;

    /**
     *
     */
    @Column(name = "publishdate")
    private Date publishdate;
    /**
     * md文件源码
     */
    @Column(name = "mdcontent")
    private String mdcontent;

    /**
     * html源码
     */
    @Column(name = "htmlcontent")
    private String htmlcontent;

    /**
     *
     */
    @Column(name = "summary")
    private String summary;

    /**
     *
     */
    @Column(name = "edittime")
    private Date edittime;

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     */
    @Column(name = "state")
    private Integer state;

    /**
     *
     */
    @Column(name = "pageview")
    private Integer pageview;

//    /**
//     *
//     * @return id
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     *
//     * @param id
//     */
//    public void setId(Integer id) {
//        this.id = id;
//    }

    /**
     *
     * @return title
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
    public Long getUid() {
        return uid;
    }

    /**
     *
     * @param uid
     */
    public void setUid(Long uid) {
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

    /**
     *
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     *
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     *
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
