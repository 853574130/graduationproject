package org.iauhsoaix.oldbean;
 /** 
   * @Author:iauhsoaix   
   * @date 2018/12/5 
   * @Description: 
   */  
public abstract class BaseInfo {
    //主键id字段，不可修改
    protected Long id;

    protected Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 返回记录的有效性字段
     * @return Integer status
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}