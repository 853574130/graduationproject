package org.iauhsoaix.test;

import org.iauhsoaix.bean.UserInfo;

public interface userMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}