package org.microdegree.com.app.exp.data.model.Course;

public class CourseHighlight {
    String chIconUrl;
    String overview;
    String title;
    String courseId;

    CourseHighlight(){};

    public CourseHighlight(String chIconUrl, String overview, String title, String courseId) {
        this.chIconUrl = chIconUrl;
        this.overview = overview;
        this.title = title;
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChIconUrl() {
        return chIconUrl;
    }

    public void setChIconUrl(String chIconUrl) {
        this.chIconUrl = chIconUrl;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
