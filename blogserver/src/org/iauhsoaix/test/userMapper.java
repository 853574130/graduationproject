package org.iauhsoaix.test;

public interface userMapper {
    int insert(userWithBLOBs record);

    int insertSelective(userWithBLOBs record);
}