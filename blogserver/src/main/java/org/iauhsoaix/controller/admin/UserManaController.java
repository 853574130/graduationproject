//package org.iauhsoaix.controller.admin;
//
//import org.iauhsoaix.oldbean.RespBean;
//import org.iauhsoaix.oldbean.Role;
//import org.iauhsoaix.oldbean.User;
//import org.iauhsoaix.service.UserService_old;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
///**
// * Edited by iauhsoaix on 2017/12/24.
// */
////@RestController
////@RequestMapping("/admin")
//public class UserManaController {
//    @Autowired
////    UserService_old userServiceOld;
//
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public List<User> getUserByNickname(String nickname) {
//        return userServiceOld.getUserByNickname(nickname);
//    }
//
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
//    public User getUserById(@PathVariable Long id) {
//        return userServiceOld.getUserById(id);
//    }
//
//    @RequestMapping(value = "/roles", method = RequestMethod.GET)
//    public List<Role> getAllRole() {
//        return userServiceOld.getAllRole();
//    }
//
//    @RequestMapping(value = "/user/enabled", method = RequestMethod.PUT)
//    public RespBean updateUserEnabled(Boolean enabled, Long uid) {
//        if (userServiceOld.updateUserEnabled(enabled, uid) == 1) {
//            return new RespBean("success", "更新成功!");
//        } else {
//            return new RespBean("error", "更新失败!");
//        }
//    }
//
//    @RequestMapping(value = "/user/{uid}", method = RequestMethod.DELETE)
//    public RespBean deleteUserById(@PathVariable Long uid) {
//        if (userServiceOld.deleteUserById(uid) == 1) {
//            return new RespBean("success", "删除成功!");
//        } else {
//            return new RespBean("error", "删除失败!");
//        }
//    }
//
//    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
//    public RespBean updateUserRoles(Long[] rids, Long id) {
//        if (userServiceOld.updateUserRoles(rids, id) == rids.length) {
//            return new RespBean("success", "更新成功!");
//        } else {
//            return new RespBean("error", "更新失败!");
//        }
//    }
//}
