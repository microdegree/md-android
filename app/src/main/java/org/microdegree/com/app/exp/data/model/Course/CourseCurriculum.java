package org.microdegree.com.app.exp.data.model.Course;

import java.util.List;

public class CourseCurriculum {
    String chapterPreviewLink;
    String curriculumChapterTitle;
    String goal;
    List<HandsonModel> curriculumHandson;
    List<ObjectiveModel> curriculumObjectives;
    List<TopicModel> curriculumTopics;

    public CourseCurriculum(){};

    public String getChapterPreviewLink() {
        return chapterPreviewLink;
    }

    public void setChapterPreviewLink(String chapterPreviewLink) {
        this.chapterPreviewLink = chapterPreviewLink;
    }

    public String getCurriculumChapterTitle() {
        return curriculumChapterTitle;
    }

    public void setCurriculumChapterTitle(String curriculumChapterTitle) {
        this.curriculumChapterTitle = curriculumChapterTitle;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public List<HandsonModel> getCurriculumHandson() {
        return curriculumHandson;
    }

    public void setCurriculumHandson(List<HandsonModel> curriculumHandson) {
        this.curriculumHandson = curriculumHandson;
    }

    public List<ObjectiveModel> getCurriculumObjectives() {
        return curriculumObjectives;
    }

    public void setCurriculumObjectives(List<ObjectiveModel> curriculumObjectives) {
        this.curriculumObjectives = curriculumObjectives;
    }

    public List<TopicModel> getCurriculumTopics() {
        return curriculumTopics;
    }

    public void setCurriculumTopics(List<TopicModel> curriculumTopics) {
        this.curriculumTopics = curriculumTopics;
    }
}
