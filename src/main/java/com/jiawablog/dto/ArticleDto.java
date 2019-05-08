package com.jiawablog.dto;

import java.util.Date;

public class ArticleDto {
    /**
     * ID
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类别：category.id
     */
    private String categoryId;

    /**
     * 概述
     */
    private String summary;

    /**
     * 文章时间
     */
    private Date at;

    /**
     * 状态：P publish；D draft
     */
    private String status;

    private ContentDto content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContentDto getContent() {
        return content;
    }

    public void setContent(ContentDto content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ArticleDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", categoryId='").append(categoryId).append('\'');
        sb.append(", summary='").append(summary).append('\'');
        sb.append(", at=").append(at);
        sb.append(", status='").append(status).append('\'');
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }
}