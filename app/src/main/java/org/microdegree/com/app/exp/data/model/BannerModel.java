package org.microdegree.com.app.exp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(primaryKeys = {"bannerId"})
@IgnoreExtraProperties
public class BannerModel {
    String bannerCampaignName;
    String bannerEndDate;
    @NonNull
    String bannerId;
    String bannerImageUrl;
    String bannerStartDate;
    String bannerType;
    int bannerWeightage;
    String buttonName;
    String createdBy;
    String createdTimestamp;
    String lastModifiedBy;
    String lastModifiedTimestamp;
    String targetPage;
    String targetPageType;

    public  BannerModel(){

    }

    public String getBannerCampaignName() {
        return bannerCampaignName;
    }

    public void setBannerCampaignName(String bannerCampaignName) {
        this.bannerCampaignName = bannerCampaignName;
    }

    public String getBannerEndDate() {
        return bannerEndDate;
    }

    public void setBannerEndDate(String bannerEndDate) {
        this.bannerEndDate = bannerEndDate;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public String getBannerStartDate() {
        return bannerStartDate;
    }

    public void setBannerStartDate(String bannerStartDate) {
        this.bannerStartDate = bannerStartDate;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public int getBannerWeightage() {
        return bannerWeightage;
    }

    public void setBannerWeightage(int bannerWeightage) {
        this.bannerWeightage = bannerWeightage;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(String lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public String getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(String targetPage) {
        this.targetPage = targetPage;
    }

    public String getTargetPageType() {
        return targetPageType;
    }

    public void setTargetPageType(String targetPageType) {
        this.targetPageType = targetPageType;
    }
}