package org.iauhsoaix.dal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.iauhsoaix.oldbean.Article;
import org.iauhsoaix.dal.entity.ArticleEntity;
import org.iauhsoaix.utils.BasicMapper;

import java.util.List;

/**
 * Edited by iauhsoaix on 2017/12/20.
 */
@Mapper
public interface ArticleMapper extends BasicMapper<ArticleEntity> {
    int addNewArticle(Article article);

    int updateArticle(Article article);

    List<Article> getArticleByState(@Param("state") Integer state, @Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid,@Param("keywords") String keywords);

//    List<Article> getArticleByStateByAdmin(@Param("start") int start, @Param("count") Integer count, @Param("keywords") String keywords);

    int getArticleCountByState(@Param("state") Integer state, @Param("uid") Long uid, @Param("keywords") String keywords);

    int updateArticleState(@Param("aids") Long aids[], @Param("state") Integer state);

    int deleteArticleById(@Param("aids") Long[] aids);

    Article getArticleById(Long aid);

    /*
    * <select id="getArticleById" parameterType="Long" resultMap="BaseResultMap">
        SELECT a.*,t.`tagName`,t.`id` AS tid,u.`nickname`,c.`cateName` FROM article a LEFT JOIN article_tags ats ON a.`id`=ats.`aid` LEFT JOIN tags t ON ats.`tid`=t.`id` LEFT JOIN user u ON a.`uid`=u.`id` LEFT JOIN category c ON a.`cid`=c.`id` WHERE a.id=#{aid}
    </select>
    <resultMap id="BaseResultMap" type="org.iauhsoaix.oldbean.Article">
        <id column="id" property="id"/>
        <result column="title" property="title"/>
        <result column="cid" property="cid"/>
        <result column="uid" property="uid"/>
        <result column="publishDate" property="publishDate"/>
        <result column="editTime" property="editTime"/>
        <result column="state" property="state"/>
        <result column="pageView" property="pageView"/>
        <result column="mdContent" property="mdContent"/>
        <result column="htmlContent" property="htmlContent"/>
        <result column="summary" property="summary"/>
        <result column="nickname" property="nickname"/>
        <result column="cateName" property="cateName"/>
        <collection property="tags" ofType="org.iauhsoaix.oldbean.Tags" column="tagName">
            <id property="id" column="tid"/>
            <result property="tagName" column="tagName"/>
        </collection>
    </resultMap>
    */

    void pvIncrement(Long aid);

    //INSERT INTO pv(countDate,pv,uid) SELECT NOW(),SUM(pageView),uid FROM article GROUP BY uid
    void pvStatisticsPerDay();

    List<String> getCategories(Long uid);

    List<Integer> getDataStatistics(Long uid);
}
