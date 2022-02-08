package org.microdegree.com.app.exp.data.model.Course;

public class CourseFaq {
    String courseFaqDescription;
    String courseFaqTitle;

    public CourseFaq(){};

    public CourseFaq(String courseFaqDescription, String courseFaqTitle) {
        this.courseFaqDescription = courseFaqDescription;
        this.courseFaqTitle = courseFaqTitle;
    }

    public String getCourseFaqDescription() {
        return courseFaqDescription;
    }

    public void setCourseFaqDescription(String courseFaqDescription) {
        this.courseFaqDescription = courseFaqDescription;
    }

    public String getCourseFaqTitle() {
        return courseFaqTitle;
    }

    public void setCourseFaqTitle(String courseFaqTitle) {
        this.courseFaqTitle = courseFaqTitle;
    }
}
