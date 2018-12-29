package org.iauhsoaix.test;

import org.iauhsoaix.test.CommentsInfo;

public interface CommentsInfoMapper {
    int insert(CommentsInfo record);

    int insertSelective(CommentsInfo record);
}