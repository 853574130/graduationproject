package org.iauhsoaix.dal.mapper;

import org.apache.ibatis.annotations.*;
import org.iauhsoaix.bean.UserInfo;
import org.iauhsoaix.dal.entity.UserEntity;
import org.iauhsoaix.utils.BasicMapper;

import java.util.List;

public interface UserMapper_old extends BasicMapper<UserEntity> {
	@Select("select * from user")
	@Results(id = "userMap", value = { @Result(column = "company_id", property = "companyId"),
			@Result(column = "user_name", property = "userName"),
			@Result(column = "user_account", property = "userAccount"),
			@Result(column = "employe_id", property = "employeId"),
			@Result(column = "parent_id", property = "parentId") })
	List<UserEntity> tt();

	@Select("select * from user where user_account=#{account}")
	@ResultMap("userMap")
	UserEntity selectByAccount(String account);

	@Update("update user set password = #{password} where id = #{id}")
	void changePassword(@Param("id") Integer id, @Param("password") String password);

	@Select("SELECT id FROM user WHERE employee_number = #{employeeNumber} AND company_id = #{companyId}")
	UserInfo searchByEmployeNumber(@Param("employeeNumber") Integer employeeNumber, @Param("companyId") Integer companyId);
}
