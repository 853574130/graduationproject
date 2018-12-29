package org.iauhsoaix.test;

import org.iauhsoaix.bean.BaseInfo;

public class RoleInfo extends BaseInfo {
    /**
     * 
     */
    private String name;

    /**
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}