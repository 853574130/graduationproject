package org.iauhsoaix.test;

public class articleWithBLOBs extends article {
    /**
     * md文件源码
     */
    private String mdcontent;

    /**
     * html源码
     */
    private String htmlcontent;

    /**
     * 
     */
    private String summary;

    /**
     * md文件源码
     * @return mdContent md文件源码
     */
    public String getMdcontent() {
        return mdcontent;
    }

    /**
     * md文件源码
     * @param mdcontent md文件源码
     */
    public void setMdcontent(String mdcontent) {
        this.mdcontent = mdcontent == null ? null : mdcontent.trim();
    }

    /**
     * html源码
     * @return htmlContent html源码
     */
    public String getHtmlcontent() {
        return htmlcontent;
    }

    /**
     * html源码
     * @param htmlcontent html源码
     */
    public void setHtmlcontent(String htmlcontent) {
        this.htmlcontent = htmlcontent == null ? null : htmlcontent.trim();
    }

    /**
     * 
     * @return summary 
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary 
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}