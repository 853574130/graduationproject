package org.iauhsoaix.bean;

import java.util.Date;
import org.iauhsoaix.bean.BaseInfo;

import javax.persistence.Column;

public class ArticleInfo extends BaseInfo {

    private String articleTitle;

    private String articleMarkdownContent;

    private String articleHtmlContent;

    private String articleSummary;

    private String userId;

    private Integer commented;

    private String articleThumbnail;

    private Integer pageView;

    private Integer status;
    private Date updated;

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleMarkdownContent() {
        return articleMarkdownContent;
    }

    public void setArticleMarkdownContent(String articleMarkdownContent) {
        this.articleMarkdownContent = articleMarkdownContent;
    }

    public String getArticleHtmlContent() {
        return articleHtmlContent;
    }

    public void setArticleHtmlContent(String articleHtmlContent) {
        this.articleHtmlContent = articleHtmlContent;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCommented() {
        return commented;
    }

    public void setCommented(Integer commented) {
        this.commented = commented;
    }

    public String getArticleThumbnail() {
        return articleThumbnail;
    }

    public void setArticleThumbnail(String articleThumbnail) {
        this.articleThumbnail = articleThumbnail;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }
}