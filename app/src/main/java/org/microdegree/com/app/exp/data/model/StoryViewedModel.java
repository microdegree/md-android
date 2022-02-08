package org.microdegree.com.app.exp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(primaryKeys = {"storyId"})
@IgnoreExtraProperties
public class StoryViewedModel {
    String storyCampaignName;
    String storyEndDate;
    @NonNull
    String storyId;


    public StoryViewedModel(){
    };

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

    @NonNull
    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(@NonNull String storyId) {
        this.storyId = storyId;
    }

    @Override
    public String toString(){
        return this.storyId;
    }
}