package org.microdegree.com.app.exp.data.model.Course;

public class CoursePreRequisitis {
    String prerequisite;
    String courseId;
    String coursePreReqPointId;
    String createdBy;
    String lastModifiedBy;
    String createdTimestamp;
    String lastModifiedTimestamp;
    public CoursePreRequisitis(){};

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCoursePreReqPointId() {
        return coursePreReqPointId;
    }

    public void setCoursePreReqPointId(String coursePreReqPointId) {
        this.coursePreReqPointId = coursePreReqPointId;
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
}
