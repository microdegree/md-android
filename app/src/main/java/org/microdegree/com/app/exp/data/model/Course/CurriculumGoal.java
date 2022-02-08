package org.microdegree.com.app.exp.data.model.Course;

public class CurriculumGoal {
    String courseId;
    String curriculumtitleId;
    String curriculumChapterId;
    String title;
    String createdTimestamp;
    String lastModifiedTimestamp;
    String createdBy;
    String lastModifiedBy;

    public CurriculumGoal(){};

    public CurriculumGoal(String courseId, String curriculumtitleId, String curriculumChapterId, String title, String createdTimestamp, String lastModifiedTimestamp, String createdBy, String lastModifiedBy) {
        this.courseId = courseId;
        this.curriculumtitleId = curriculumtitleId;
        this.curriculumChapterId = curriculumChapterId;
        this.title = title;
        this.createdTimestamp = createdTimestamp;
        this.lastModifiedTimestamp = lastModifiedTimestamp;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCurriculumtitleId() {
        return curriculumtitleId;
    }

    public void setCurriculumtitleId(String curriculumtitleId) {
        this.curriculumtitleId = curriculumtitleId;
    }

    public String getCurriculumChapterId() {
        return curriculumChapterId;
    }

    public void setCurriculumChapterId(String curriculumChapterId) {
        this.curriculumChapterId = curriculumChapterId;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
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
