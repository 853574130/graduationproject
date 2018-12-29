package org.iauhsoaix.dal.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "user")
public class UserEntity extends AbstractEntity {

	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_account")
	private String userAccount;
	@Column(name = "password")
	private String password;
	//类型，0普通账号，10管理员
	private Integer type;
//	@Column(name = "parent_id")
//	private Integer parentId;
	private Integer role;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}



	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}


}
