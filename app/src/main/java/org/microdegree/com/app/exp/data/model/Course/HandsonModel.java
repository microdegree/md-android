package org.microdegree.com.app.exp.data.model.Course;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(primaryKeys = {"curriculumHandsonId"})
@IgnoreExtraProperties
public class HandsonModel {
    String courseId;
    @NonNull
    String curriculumHandsonId;
    String curriculumChapterId;
    String title;
    String createdTimestamp;
    String lastModifiedTimestamp;
    String createdBy;
    String lastModifiedBy;

    public HandsonModel(){};

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCurriculumHandsonId() {
        return curriculumHandsonId;
    }

    public void setCurriculumHandsonId(String curriculumHandsonId) {
        this.curriculumHandsonId = curriculumHandsonId;
    }

    public String getCurriculumChapterId() {
        return curriculumChapterId;
    }

    public void setCurriculumChapterId(String curriculumChapterId) {
        this.curriculumChapterId = curriculumChapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(String lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
