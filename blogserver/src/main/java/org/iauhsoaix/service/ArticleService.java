package org.iauhsoaix.service;

import org.iauhsoaix.bean.ArticleInfo;
import org.iauhsoaix.oldbean.Article;
import org.iauhsoaix.dal.entity.ArticleEntity;
import org.iauhsoaix.dal.mapper.ArticleMapper;
import org.iauhsoaix.dal.mapper.TagsMapper;
import org.iauhsoaix.manager.ArticleManager;
import org.iauhsoaix.manager.BaseManager;
//import org.iauhsoaix.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Edited by iauhsoaix on 2017/12/20.
 */
@Service
@Transactional
public class ArticleService extends BaseService<ArticleEntity, ArticleInfo> {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagsMapper tagsMapper;
    @Resource
    ArticleManager articleManager;

    @Override
    protected BaseManager<ArticleEntity> getManager() {
        return articleManager;
    }

//    public int addNewArticle(Article article) {
//        //处理文章摘要
//        if (article.getSummary() == null || "".equals(article.getSummary())) {
//            //直接截取
//            String stripHtml = stripHtml(article.getHtmlContent());
//            article.setSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
//        }
//        if (article.getId() == -1) {
//            //添加操作
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            if (article.getStatus() == 1) {
//                //设置发表日期
//                article.setPublishDate(timestamp);
//            }
//            article.setEditTime(timestamp);
//            //设置当前用户
//            article.setUid(Util.getCurrentUser().getId());
//            int i = articleMapper.addNewArticle(article);
//            //打标签
//            String[] dynamicTags = article.getDynamicTags();
//            if (dynamicTags != null && dynamicTags.length > 0) {
//                int tags = addTagsToArticle(dynamicTags, article.getId());
//                if (tags == -1) {
//                    return tags;
//                }
//            }
//            return i;
//        } else {
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            if (article.getStatus() == 1) {
//                //设置发表日期
//                article.setPublishDate(timestamp);
//            }
//            //更新
//            article.setEditTime(new Timestamp(System.currentTimeMillis()));
//            int i = articleMapper.updateArticle(article);
//            //修改标签
//            String[] dynamicTags = article.getDynamicTags();
//            if (dynamicTags != null && dynamicTags.length > 0) {
//                int tags = addTagsToArticle(dynamicTags, article.getId());
//                if (tags == -1) {
//                    return tags;
//                }
//            }
//            return i;
//        }
//    }

    private int addTagsToArticle(String[] dynamicTags, Long aid) {
        //1.删除该文章目前所有的标签
        tagsMapper.deleteTagsByAid(aid);
        //2.将上传上来的标签全部存入数据库
        tagsMapper.saveTags(dynamicTags);
        //3.查询这些标签的id
        List<Long> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);
        //4.重新给文章设置标签
        int i = tagsMapper.saveTags2ArticleTags(tIds, aid);
        return i == dynamicTags.length ? i : -1;
    }

    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }
 /** 这个方法不好，私密的文章也能查出来，只是前端不显示
   * @Author:iauhsoaix
   * @date 2018/12/5
   * @Description:
   */
//    public List<Article> getArticleByState(Integer state, Integer page, Integer count,String keywords) {
//        int start = (page - 1) * count;
//        Long uid = Util.getCurrentUser().getId();
//        return articleMapper.getArticleByState(state, start, count, uid,keywords);
//    }

//    public List<Article> getArticleByStateByAdmin(Integer page, Integer count,String keywords) {
//        int start = (page - 1) * count;
//        return articleMapper.getArticleByStateByAdmin(start, count,keywords);
//    }

    public int getArticleCountByState(Integer state, Long uid,String keywords) {
        return articleMapper.getArticleCountByState(state, uid,keywords);
    }

    public int updateArticleState(Long[] aids, Integer state) {
        if (state == 2) {
            return articleMapper.deleteArticleById(aids);
        } else {
            return articleMapper.updateArticleState(aids, 2);//放入到回收站中
        }
    }

    public ArticleInfo getArticleById(Long aid) {
        ArticleInfo article=this.getModel(aid);
        return article;
    }

    public void pvStatisticsPerDay() {
        articleMapper.pvStatisticsPerDay();
    }

    /**
     * 获取最近七天的日期
     * @return
     */
//    public List<String> getCategories() {
//        return articleMapper.getCategories(Util.getCurrentUser().getId());
//    }
//
//    /**
//     * 获取最近七天的数据
//     * @return
//     */
//    public List<Integer> getDataStatistics() {
//        return articleMapper.getDataStatistics(Util.getCurrentUser().getId());
//    }


}
