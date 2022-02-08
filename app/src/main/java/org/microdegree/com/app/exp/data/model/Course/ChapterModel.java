package org.microdegree.com.app.exp.data.model.Course;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(primaryKeys = {"curriculumChapterId"})
@IgnoreExtraProperties
public class ChapterModel {
    String courseId;
    @NonNull
    String curriculumChapterId;
    String chapterGoal;
    String curriculumChapterTitle;
    String chapterPreviewLink;
    String createdTimestamp;
    String lastModifiedTimestamp;
    String createdBy;
    String lastModifiedBy;
    int sortID;

    public int getSortID() {
        return sortID;
    }

    public void setSortID(int sortID) {
        this.sortID = sortID;
    }

    public String getChapterGoal() {
        return chapterGoal;
    }

    public void setChapterGoal(String chapterGoal) {
        this.chapterGoal = chapterGoal;
    }

    public ChapterModel(){};

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCurriculumChapterId() {
        return curriculumChapterId;
    }

    public void setCurriculumChapterId(String curriculumChapterId) {
        this.curriculumChapterId = curriculumChapterId;
    }

    public String getCurriculumChapterTitle() {
        return curriculumChapterTitle;
    }

    public void setCurriculumChapterTitle(String curriculumChapterTitle) {
        this.curriculumChapterTitle = curriculumChapterTitle;
    }

    public String getChapterPreviewLink() {
        return chapterPreviewLink;
    }

    public void setChapterPreviewLink(String chapterPreviewLink) {
        this.chapterPreviewLink = chapterPreviewLink;
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

