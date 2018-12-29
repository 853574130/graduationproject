package org.iauhsoaix.bean;

import java.util.Date;
import org.iauhsoaix.bean.BaseInfo;

public class UserInfo extends BaseInfo {
    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Boolean enabled;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String userface;

    /**
     * 
     */
    private Date regtime;

    /**
     * 
     * @return username 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 
     * @return nickname 
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     * @param nickname 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 
     * @return password 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 
     * @return enabled 
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 
     * @param enabled 
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 
     * @return email 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 
     * @return userface 
     */
    public String getUserface() {
        return userface;
    }

    /**
     * 
     * @param userface 
     */
    public void setUserface(String userface) {
        this.userface = userface == null ? null : userface.trim();
    }

    /**
     * 
     * @return regTime 
     */
    public Date getRegtime() {
        return regtime;
    }

    /**
     * 
     * @param regtime 
     */
    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
}