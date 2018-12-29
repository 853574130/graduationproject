package org.iauhsoaix.manager;

//import com.github.pagehelper.page.PageMethod;
import org.iauhsoaix.bean.UserInfo;
import org.iauhsoaix.dal.entity.UserEntity;
import org.iauhsoaix.dal.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserManager {

	@Resource
	private UserMapper userMapper;

	public UserEntity getUserByAccount(String account) {
		return userMapper.selectByAccount(account);
	}

	public List<UserEntity> getUserList(int pageNum, int pageSize, UserEntity userEntity) {
//		PageMethod.startPage(pageNum, pageSize);
		return userMapper.select(userEntity);
	}

	public long getTotal(UserEntity userEntity) {
		return userMapper.selectCount(userEntity);
	}

	public void insertUser(UserEntity userEntity) {
		userMapper.insertSelective(userEntity);
	}

	public void updateByPrimaryKeySelective(UserEntity userEntity) {
		userMapper.updateByPrimaryKeySelective(userEntity);
	}

	public void changePassword(Integer id,String password){
		userMapper.changePassword(id,password);
	}

	public UserInfo searchByEmployeNumber(Integer employeeNumber, Integer companyId){
		return userMapper.searchByEmployeNumber(employeeNumber,companyId);
	}
}
