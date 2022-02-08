package org.microdegree.com.app.exp.ui.signin.model;

public class SignInFields {

    private String name;
    private String mobile;
    private String email;
    private String referral;
    private String id;
    private String createdTime;
    private String updatedTime;
    private boolean errorFields;

    public boolean isErrorFields() {
        return errorFields;
    }

    public void setErrorFields(boolean errorFields) {
        this.errorFields = errorFields;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }
}
