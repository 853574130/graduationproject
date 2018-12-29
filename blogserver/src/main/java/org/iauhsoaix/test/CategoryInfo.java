package org.iauhsoaix.test;

import java.util.Date;
import org.iauhsoaix.bean.BaseInfo;

public class CategoryInfo extends BaseInfo {
    /**
     * 
     */
    private String catename;

    /**
     * 
     */
    private Date date;

    /**
     * 
     * @return cateName 
     */
    public String getCatename() {
        return catename;
    }

    /**
     * 
     * @param catename 
     */
    public void setCatename(String catename) {
        this.catename = catename == null ? null : catename.trim();
    }

    /**
     * 
     * @return date 
     */
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date 
     */
    public void setDate(Date date) {
        this.date = date;
    }
}