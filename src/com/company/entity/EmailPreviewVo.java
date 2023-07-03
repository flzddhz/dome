package com.company.entity;

import java.util.List;

public class EmailPreviewVo {

    private Long id;

    private String from;

    private String cc;

    private String to;

    private String subject;

    private String sentDate;

    private String content;

    private String FileName;

    private List<FileVo> attachments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
            this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
            this.from = from;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
            this.cc = cc;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
            this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
            this.subject = subject;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
            this.sentDate = sentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
            this.content = content;
    }

    public List<FileVo> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<FileVo> attachments) {
            this.attachments = attachments;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
            FileName = fileName;
    }
}
