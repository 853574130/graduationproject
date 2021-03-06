//package org.iauhsoaix.service;
//
//import org.iauhsoaix.dal.mapper.UserMapper_olde;
//import org.iauhsoaix.oldbean.Role;
//import org.iauhsoaix.oldbean.User;
//import org.iauhsoaix.dal.mapper.RolesMapper;
//import org.iauhsoaix.utils.Util;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.DigestUtils;
//
//import java.util.List;
//
///**
// * Edited by iauhsoaix
// */
////@Service
////@Transactional
//public class UserService_old implements UserDetailsService {
//    @Autowired
//    UserMapper_olde userMapper;
//    @Autowired
//    RolesMapper rolesMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userMapper.loadUserByUsername(s);
//        if (user == null) {
//            //避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
//            return new User();
//        }
//        //查询用户的角色信息，并返回存入user中
//        List<Role> roles = rolesMapper.getRolesByUid(user.getId());
//        //权限不写在user表中，想法非常好
//        user.setRoles(roles);
//        return user;
//    }
//
//    /**
//     * @param user
//     * @return 0表示成功
//     * 1表示用户名重复
//     * 2表示失败
//     */
//    public int reg(User user) {
//        User loadUserByUsername = userMapper.loadUserByUsername(user.getUsername());
//        if (loadUserByUsername != null) {
//            return 1;
//        }
//        //插入用户,插入之前先对密码进行加密
//        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
//        user.setEnabled(true);//用户可用
//
//        long result = userMapper.reg(user);
//        //返回用户id
//        //配置用户的角色，默认都是普通用户（2），管理员（1）
//        String[] roles = new String[]{"2"};
//
//        System.out.println("我来过这里吗");
//        int i = rolesMapper.addRoles(roles, user.getId());
//        System.out.println(i);
//        boolean b = i == roles.length && result == 1;
//        if (b) {
//            return 0;
//        } else {
//            return 2;
//        }
//    }
//
//    public int updateUserEmail(String email) {
//        return userMapper.updateUserEmail(email, Util.getCurrentUser().getId());
//    }
//
//    public List<User> getUserByNickname(String nickname) {
//        List<User> list = userMapper.getUserByNickname(nickname);
//        return list;
//    }
//
//    public List<Role> getAllRole() {
//        return userMapper.getAllRole();
//    }
//
//    public int updateUserEnabled(Boolean enabled, Long uid) {
//        return userMapper.updateUserEnabled(enabled, uid);
//    }
//
//    public int deleteUserById(Long uid) {
//        return userMapper.deleteUserById(uid);
//    }
//
//    public int updateUserRoles(Long[] rids, Long id) {
//        int i = userMapper.deleteUserRolesByUid(id);
//        return userMapper.setUserRoles(rids, id);
//    }
//
//    public User getUserById(Long id) {
//        return userMapper.getUserById(id);
//    }
//}
