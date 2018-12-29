package org.iauhsoaix.oldbean;

import org.iauhsoaix.bean.BaseInfo;

import java.sql.Timestamp;
import java.util.List;

/**
 * Edited by iauhsoaix on 2018/12/20.
 */
public class Article extends BaseInfo {
    /**
     * 文章编号
     */
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容 Markdown格式
     */
    private String mdContent;
    /**
     * 文章内容 html格式
     */
    private String htmlContent;
    /**
     * 文章摘要
     */
    private String summary;

    private Integer cid;
    /**
     * 发表用户 多对一
     */
    private Long uid;

    private Timestamp publishDate;
    /**
     * 0 已发布
     * 1 草稿
     * 2 回收站
     */
    private Integer state;
    /**
     * 文章访问量
     */
    private Integer pageView;

    /**
     * 是否允许评论
     */
    private Integer allowComment = 0;

    private Timestamp editTime;


    private String[] dynamicTags;

    private String nickname;
    /**
     * 分类名
     */
    private String cateName;

    /**
     * 文章所属标签
     */
    private List<Tags> tags;

    private String stateStr;

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String[] getDynamicTags() {
        return dynamicTags;
    }

    public void setDynamicTags(String[] dynamicTags) {
        this.dynamicTags = dynamicTags;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }
}
