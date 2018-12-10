package org.iauhsoaix.manager;

import org.iauhsoaix.dal.entity.ArticleEntity;
import org.iauhsoaix.dal.mapper.ArticleMapper;
import org.iauhsoaix.utils.BasicMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ArticleManager extends BaseManager<ArticleEntity> {
    @Resource
    ArticleMapper articleMapper;
    @Override
    protected BasicMapper<ArticleEntity> getMapper() {
        return articleMapper;
    }
    //要获取对应的Mapper
}
