package com.lxp.vo;

public class Article {
    private String topicId;
    private String resourceId;
    private String topicName;
    private String title;
    private String author;
    private String zan;
    private String content;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "topicId='" + topicId + '\'' +
                ", topicName='" + topicName + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", zan='" + zan + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
