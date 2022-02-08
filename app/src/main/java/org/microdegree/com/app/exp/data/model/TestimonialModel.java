package org.microdegree.com.app.exp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(primaryKeys = {"testimonialsId"})
@IgnoreExtraProperties
public class TestimonialModel {
    String createdBy;
    String createdTimestamp;
    String lastModifiedBy;
    String lastModifiedTimestamp;
    @NonNull
    String testimonialsId;
    String youtubeLink;
    public TestimonialModel(){

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

    public String getTestimonialsId() {
        return testimonialsId;
    }

    public void setTestimonialsId(String testimonialsId) {
        this.testimonialsId = testimonialsId;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }
}