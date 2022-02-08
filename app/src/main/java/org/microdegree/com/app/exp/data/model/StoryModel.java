package org.microdegree.com.app.exp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
@Entity(primaryKeys = {"storyId"})
@IgnoreExtraProperties
public class StoryModel{
    String buttonName;
    String createdBy;
    String createdTimestamp;
    String lastModifiedBy;
    String lastModifiedTimestamp;
    String storyCampaignName;
    String storyEndDate;
    @NonNull
    String storyId;
    String storyImageUrl;
    String storyStartDate;
    String storyType;
    int storyWeightage;
    String targetPage;
    String targetPageType;


    public  StoryModel(){

    };

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

    public String getStoryCampaignName() {
        return storyCampaignName;
    }

    public void setStoryCampaignName(String storyCampaignName) {
        this.storyCampaignName = storyCampaignName;
    }

    public String getStoryEndDate() {
        return storyEndDate;
    }

    public void setStoryEndDate(String storyEndDate) {
        this.storyEndDate = storyEndDate;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getStoryImageUrl() {
        return storyImageUrl;
    }

    public void setStoryImageUrl(String storyImageUrl) {
        this.storyImageUrl = storyImageUrl;
    }

    public String getStoryStartDate() {
        return storyStartDate;
    }

    public void setStoryStartDate(String storyStartDate) {
        this.storyStartDate = storyStartDate;
    }

    public String getStoryType() {
        return storyType;
    }

    public void setStoryType(String storyType) {
        this.storyType = storyType;
    }

    public int getStoryWeightage() {
        return storyWeightage;
    }

    public void setStoryWeightage(int storyWeightage) {
        this.storyWeightage = storyWeightage;
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


    @Override
    public String toString(){
        return this.storyId;
    }
}