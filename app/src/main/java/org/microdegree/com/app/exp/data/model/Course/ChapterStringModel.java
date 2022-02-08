package org.microdegree.com.app.exp.data.model.Course;

public  class ChapterStringModel {
    String title;
    String type;
    String goal;

    public ChapterStringModel(){

    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
