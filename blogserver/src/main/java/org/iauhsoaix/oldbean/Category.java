package org.iauhsoaix.oldbean;

import java.sql.Timestamp;

/**
 * Edited by iauhsoaix on 2018/12/19.
 */
public class Category extends BaseInfo {
    /**
     * 分类编号
     */
    private Long id;

    private String cateName;
    /**
     * 分类描述
     */
    private String cateDesc;

    private Timestamp date;

    public Category() {
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
