package org.iauhsoaix.controller.admin;


//import com.ecms.web.filter.LoginFilter;
import org.iauhsoaix.bean.UserInfo;
import org.iauhsoaix.service.UserService;
import org.iauhsoaix.utils.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

	private static final int DEFAULT_PAGE_SIZE = 20;

	@Resource
	private UserService userService;

	@RequestMapping("list")
	public Result<Pager<UserInfo>> login(int pageNum, UserInfo userInfo) {
		ExchangeUtils.convertBlank2Null(userInfo);
//		Pager<UserInfo> pager = userService.getUserList(pageNum, DEFAULT_PAGE_SIZE, userInfo);
//		return ResultUtils.success(pager);
		return ResultUtils.success();
	}

	@RequestMapping("insert")
	public Result<Void> insertUser(UserInfo userInfo) {
//		userService.insertUser(userInfo);
		return ResultUtils.success();
	}

	@RequestMapping("status/update")
	public Result<Void> insertUser(int id, int status) {
//		userService.updateStatus(id, status);
		return ResultUtils.success();
	}

	@RequestMapping("searchByEmployeNumber")
	public Result<UserInfo> searchByEmployeNumber(Integer employeeNumber){
//		Integer companyId = LoginFilter.getCurrentUser().getCompanyId();
//        UserInfo ui = userService.searchByEmployeNumber(employeeNumber,companyId);
//		return ResultUtils.success(ui);
		return ResultUtils.success();
	}

	@RequestMapping("getUserInfoList")
	public Result<Pager<UserInfo>> getUserInfoList() {
		UserInfo userInfo = new UserInfo();
//		userInfo.setEmployeId(LoginFilter.getCurrentUser().getEmployeId());
		Pager<UserInfo> pager = userService.getUserList(1, DEFAULT_PAGE_SIZE, userInfo);
//		return ResultUtils.success(pager);
		return ResultUtils.success();
	}

	@RequestMapping("changePassword")
	public Result<Void> changePassword(UserInfo userInfo){
//		userService.changePassword(userInfo.getId(),userInfo.getPassword());
		return ResultUtils.success();
	}
}
