package org.iauhsoaix.controller.admin;

import org.apache.commons.io.IOUtils;
import org.iauhsoaix.bean.ArticleInfo;
import org.iauhsoaix.bean.UserInfo;
import org.iauhsoaix.controller.BaseController;
import org.iauhsoaix.dal.entity.ArticleEntity;
import org.iauhsoaix.exceptions.CommonBusinessException;
import org.iauhsoaix.filter.LoginFilter;
import org.iauhsoaix.oldbean.Article;
import org.iauhsoaix.oldbean.RespBean;
import org.iauhsoaix.service.ArticleService;
import org.iauhsoaix.service.BaseService;
import org.iauhsoaix.service.UserService;
import org.iauhsoaix.utils.LogCapability;
import org.iauhsoaix.utils.Pager;
import org.iauhsoaix.utils.Result;
import org.iauhsoaix.utils.ResultUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Edited by iauhsoaix on 2017/12/20.
 */
@RestController
public class LoginController implements LogCapability {

    private Logger logger = getLogger();
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public Result<UserInfo> login(HttpServletRequest request, @RequestParam("account") String account,
                                  @RequestParam("password") String password) {
        Result<UserInfo> result;
        try {
            UserInfo userInfo = userService.getLoginUser(account, password);
            request.getSession().setAttribute(LoginFilter.USER_SESSION, userInfo);
            result = ResultUtils.success(userInfo);
        } catch (CommonBusinessException e) {
            result = ResultUtils.failure(e);
        }
        logger.info("用户[{}]尝试登陆系统, 结果:{}", account, result.getMessage());
        return result;
    }

}
