package org.iauhsoaix.test;

import org.iauhsoaix.bean.ArticleInfo;

public interface ArticleInfoMapper {
    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);
}