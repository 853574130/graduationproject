package org.iauhsoaix.dal.mapper;

import org.apache.ibatis.annotations.*;
import org.iauhsoaix.bean.UserInfo;
import org.iauhsoaix.dal.entity.UserEntity;
import org.iauhsoaix.utils.BasicMapper;

import java.util.List;

public interface UserMapper extends BasicMapper<UserEntity> {
	@Select("select * from user")
	@Results(id = "userMap", value = {
			@Result(column = "user_name", property = "userName")})
	List<UserEntity> tt();

	@Select("select * from user where user_name=#{account}")
	@ResultMap("userMap")
	UserEntity selectByAccount(String account);

	@Update("update user set password = #{password} where id = #{id}")
	void changePassword(@Param("id") Integer id, @Param("password") String password);
}
