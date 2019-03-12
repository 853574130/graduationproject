package org.iauhsoaix.dal.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "article")
public class ArticleEntity extends AbstractEntity {

    /**
     *
     */
    @Column(name = "article_title")
    private String articleTitle;

    /**
     *
     */
    @Column(name = "article_markdown_content")
    private String articleMarkdownContent;


    /**
     *
     */
    @Column(name = "article_html_content")
    private String articleHtmlContent;

    /**
     *这个先注掉  待实现   数据库中还没有这个字段
     */
//    @Column(name = "article_summary")
//    private String articleSummary;
    /**
     *
     */
    @Column(name = "user_id")
    private String userId;

    /**
     *
     */
    @Column(name = "commented")
    private Integer commented;

    /**
     *
     */
    @Column(name = "article_thumbnail")
    private String articleThumbnail;

    /**
     *
     */
    @Column(name = "pageView")
    private Integer pageView;

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "type")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
