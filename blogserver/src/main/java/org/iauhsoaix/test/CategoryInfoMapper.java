package org.iauhsoaix.test;

import org.iauhsoaix.test.CategoryInfo;

public interface CategoryInfoMapper {
    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);
}