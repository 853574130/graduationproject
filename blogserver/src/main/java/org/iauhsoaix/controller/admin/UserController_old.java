//package org.iauhsoaix.controller.admin;
//
//import org.iauhsoaix.oldbean.RespBean;
//import org.iauhsoaix.service.UserService_old;
//import org.iauhsoaix.utils.Util;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
///**
// * Edited by iauhsoaix on 2017/12/24.
// */
////@RestController
//public class UserController_old {
//
//    @Autowired
//    UserService_old userServiceOld;
//
//    @RequestMapping("/currentUserName")
//    public String currentUserName() {
//        return Util.getCurrentUser().getNickname();
//    }
//
//    @RequestMapping("/currentUserId")
//    public Long currentUserId() {
//        return Util.getCurrentUser().getId();
//    }
//
//    @RequestMapping("/currentUserEmail")
//    public String currentUserEmail() {
//        return Util.getCurrentUser().getEmail();
//    }
//
//    @RequestMapping("/isAdmin")
//    public Boolean isAdmin() {
//        List<GrantedAuthority> authorities = Util.getCurrentUser().getAuthorities();
//        for (GrantedAuthority authority : authorities) {
//            if (authority.getAuthority().contains("超级管理员")) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @RequestMapping(value = "/updateUserEmail",method = RequestMethod.PUT)
//    public RespBean updateUserEmail(String email) {
//        if (userServiceOld.updateUserEmail(email) == 1) {
//            return new RespBean("success", "开启成功!");
//        }
//        return new RespBean("error", "开启失败!");
//    }
//}
