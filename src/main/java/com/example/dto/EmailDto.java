package com.example.dto;

public class EmailDto {
    private String templateId;
    private String emailId;
    private String toSend;
    private String placeholderData;

    public EmailDto() {
    }

    public EmailDto(String templateId, String emailId, String toSend, String placeholderData) {
        this.templateId = templateId;
        this.emailId = emailId;
        this.toSend = toSend;
        this.placeholderData = placeholderData;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getToSend() {
        return toSend;
    }

    public void setToSend(String toSend) {
        this.toSend = toSend;
    }

    public String getPlaceholderData() {
        return placeholderData;
    }

    public void setPlaceholderData(String placeholderData) {
        this.placeholderData = placeholderData;
    }

    @Override
    public String toString() {
        return "{" +
                "templateId='" + templateId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", toSend='" + toSend + '\'' +
                ", placeholderData='" + placeholderData + '\'' +
                '}';
    }
}
