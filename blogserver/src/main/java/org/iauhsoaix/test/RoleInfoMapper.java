package org.iauhsoaix.test;

import org.iauhsoaix.test.RoleInfo;

public interface RoleInfoMapper {
    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);
}