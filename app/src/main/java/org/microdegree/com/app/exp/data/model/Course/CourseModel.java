package org.microdegree.com.app.exp.data.model.Course;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(primaryKeys = {"id"})
@IgnoreExtraProperties
public class CourseModel {
    String categoryId;
    String courseDemoVideoUrl;
    String courseDetailsType;
    String courseDiscountedPrice;
    String courseDuration;
    @NonNull
    int id;
    String courseId;
    String courseImageUrl;
    String courseOriginalPrice;
    String courseRoadMapUrl;
    String courseScopeDescription;
    String courseScopeImgUrl;
    String courseStartDate;
    String courseSubTitle;
    String courseTiming;
    String courseTitle;
    String courseType;
    String courseWebPageUrl;
    int courseWeightage;
    String createdBy;
    String createdTimestamp;
    String isActive;
    String isLive;
    String isSelf;
    String isFree;
    String lastModifiedBy;
    String lastModifiedTimestamp;
    public CourseModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCourseDemoVideoUrl() {
        return courseDemoVideoUrl;
    }

    public void setCourseDemoVideoUrl(String courseDemoVideoUrl) {
        this.courseDemoVideoUrl = courseDemoVideoUrl;
    }

    public String getCourseDetailsType() {
        return courseDetailsType;
    }

    public void setCourseDetailsType(String courseDetailsType) {
        this.courseDetailsType = courseDetailsType;
    }

    public String getCourseDiscountedPrice() {
        return courseDiscountedPrice;
    }

    public void setCourseDiscountedPrice(String courseDiscountedPrice) {
        this.courseDiscountedPrice = courseDiscountedPrice;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    @NonNull
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(@NonNull String courseId) {
        this.courseId = courseId;
    }

    public String getCourseImageUrl() {
        return courseImageUrl;
    }

    public void setCourseImageUrl(String courseImageUrl) {
        this.courseImageUrl = courseImageUrl;
    }

    public String getCourseOriginalPrice() {
        return courseOriginalPrice;
    }

    public void setCourseOriginalPrice(String courseOriginalPrice) {
        this.courseOriginalPrice = courseOriginalPrice;
    }

    public String getCourseRoadMapUrl() {
        return courseRoadMapUrl;
    }

    public void setCourseRoadMapUrl(String courseRoadMapUrl) {
        this.courseRoadMapUrl = courseRoadMapUrl;
    }

    public String getCourseScopeDescription() {
        return courseScopeDescription;
    }

    public void setCourseScopeDescription(String courseScopeDescription) {
        this.courseScopeDescription = courseScopeDescription;
    }

    public String getCourseScopeImgUrl() {
        return courseScopeImgUrl;
    }

    public void setCourseScopeImgUrl(String courseScopeImgUrl) {
        this.courseScopeImgUrl = courseScopeImgUrl;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseSubTitle() {
        return courseSubTitle;
    }

    public void setCourseSubTitle(String courseSubTitle) {
        this.courseSubTitle = courseSubTitle;
    }

    public String getCourseTiming() {
        return courseTiming;
    }

    public void setCourseTiming(String courseTiming) {
        this.courseTiming = courseTiming;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseWebPageUrl() {
        return courseWebPageUrl;
    }

    public void setCourseWebPageUrl(String courseWebPageUrl) {
        this.courseWebPageUrl = courseWebPageUrl;
    }

    public int getCourseWeightage() {
        return courseWeightage;
    }

    public void setCourseWeightage(int courseWeightage) {
        this.courseWeightage = courseWeightage;
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

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    public String getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(String isSelf) {
        this.isSelf = isSelf;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
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
}
//public class CourseModel {
//
//    String categoryId;
//    String courseDemoVideoUrl;
//    String courseDetailsType;
//    double courseDiscountedPrice;
//    String courseDuration;
//    String courseFilterType;
//    String courseFilterTypeValue;
//    @NonNull
//    String courseId;
//    String courseImageUrl;
//    double courseOriginalPrice;
//    String courseRoadMapUrl;
//    String courseScopeDescription;
//    String courseScopeImgUrl;
//    String courseStartDate;
//    String courseSubTitle;
//    String courseTiming;
//    String courseTitle;
//    String courseType;
//    String courseWebPageUri;
//    String courseWeightage;
//    String createdBy;
//    String createdTimestamp;
//    String isActive;
//    String lastModifiedBy;
//    String lastModifiedTimestamp;
//
//    public CourseModel(){
//    }
//
//    public String getCourseFilterTypeValue() {
//        return courseFilterTypeValue;
//    }
//
//    public void setCourseFilterTypeValue(String courseFilterTypeValue) {
//        this.courseFilterTypeValue = courseFilterTypeValue;
//    }
//
//    public String getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(String categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getCourseDemoVideoUrl() {
//        return courseDemoVideoUrl;
//    }
//
//    public void setCourseDemoVideoUrl(String courseDemoVideoUrl) {
//        this.courseDemoVideoUrl = courseDemoVideoUrl;
//    }
//
//    public String getCourseDetailsType() {
//        return courseDetailsType;
//    }
//
//    public void setCourseDetailsType(String courseDetailsType) {
//        this.courseDetailsType = courseDetailsType;
//    }
//
//    public double getCourseDiscountedPrice() {
//        return courseDiscountedPrice;
//    }
//
//    public void setCourseDiscountedPrice(double courseDiscountedPrice) {
//        this.courseDiscountedPrice = courseDiscountedPrice;
//    }
//
//    public String getCourseDuration() {
//        return courseDuration;
//    }
//
//    public void setCourseDuration(String courseDuration) {
//        this.courseDuration = courseDuration;
//    }
//
//    public String getCourseFilterType() {
//        return courseFilterType;
//    }
//
//    public void setCourseFilterType(String courseFilterType) {
//        this.courseFilterType = courseFilterType;
//    }
//
//    public String getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(String courseId) {
//        this.courseId = courseId;
//    }
//
//    public String getCourseImageUrl() {
//        return courseImageUrl;
//    }
//
//    public void setCourseImageUrl(String courseImageUrl) {
//        this.courseImageUrl = courseImageUrl;
//    }
//
//    public double getCourseOriginalPrice() {
//        return courseOriginalPrice;
//    }
//
//    public void setCourseOriginalPrice(double courseOriginalPrice) {
//        this.courseOriginalPrice = courseOriginalPrice;
//    }
//
//    public String getCourseRoadMapUrl() {
//        return courseRoadMapUrl;
//    }
//
//    public void setCourseRoadMapUrl(String courseRoadMapUrl) {
//        this.courseRoadMapUrl = courseRoadMapUrl;
//    }
//
//    public String getCourseScopeDescription() {
//        return courseScopeDescription;
//    }
//
//    public void setCourseScopeDescription(String courseScopeDescription) {
//        this.courseScopeDescription = courseScopeDescription;
//    }
//
//    public String getCourseScopeImgUrl() {
//        return courseScopeImgUrl;
//    }
//
//    public void setCourseScopeImgUrl(String courseScopeImgUrl) {
//        this.courseScopeImgUrl = courseScopeImgUrl;
//    }
//
//    public String getCourseStartDate() {
//        return courseStartDate;
//    }
//
//    public void setCourseStartDate(String courseStartDate) {
//        this.courseStartDate = courseStartDate;
//    }
//
//    public String getCourseSubTitle() {
//        return courseSubTitle;
//    }
//
//    public void setCourseSubTitle(String courseSubTitle) {
//        this.courseSubTitle = courseSubTitle;
//    }
//
//    public String getCourseTiming() {
//        return courseTiming;
//    }
//
//    public void setCourseTiming(String courseTiming) {
//        this.courseTiming = courseTiming;
//    }
//
//    public String getCourseTitle() {
//        return courseTitle;
//    }
//
//    public void setCourseTitle(String courseTitle) {
//        this.courseTitle = courseTitle;
//    }
//
//    public String getCourseType() {
//        return courseType;
//    }
//
//    public void setCourseType(String courseType) {
//        this.courseType = courseType;
//    }
//
//    public String getCourseWebPageUri() {
//        return courseWebPageUri;
//    }
//
//    public void setCourseWebPageUri(String courseWebPageUri) {
//        this.courseWebPageUri = courseWebPageUri;
//    }
//
//    public String getCourseWeightage() {
//        return courseWeightage;
//    }
//
//    public void setCourseWeightage(String courseWeightage) {
//        this.courseWeightage = courseWeightage;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public String getCreatedTimestamp() {
//        return createdTimestamp;
//    }
//
//    public void setCreatedTimestamp(String createdTimestamp) {
//        this.createdTimestamp = createdTimestamp;
//    }
//
//    public String getIsActive() {
//        return isActive;
//    }
//
//    public void setIsActive(String isActive) {
//        this.isActive = isActive;
//    }
//
//    public String getLastModifiedBy() {
//        return lastModifiedBy;
//    }
//
//    public void setLastModifiedBy(String lastModifiedBy) {
//        this.lastModifiedBy = lastModifiedBy;
//    }
//
//    public String getLastModifiedTimestamp() {
//        return lastModifiedTimestamp;
//    }
//
//    public void setLastModifiedTimestamp(String lastModifiedTimestamp) {
//        this.lastModifiedTimestamp = lastModifiedTimestamp;
//    }
//}

