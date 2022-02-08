package org.microdegree.com.app.exp.data.model.Course;

public class LearnerReview {
    String reviewStarRating;
    String reviewerDescription;
    String reviewerImgUrl;
    String reviewerName;
    LearnerReview(){};

    public LearnerReview(String reviewStarRating, String reviewerDescription, String reviewerImgUrl, String reviewerName) {
        this.reviewStarRating = reviewStarRating;
        this.reviewerDescription = reviewerDescription;
        this.reviewerImgUrl = reviewerImgUrl;
        this.reviewerName = reviewerName;
    }

    public String getReviewStarRating() {
        return reviewStarRating;
    }

    public void setReviewStarRating(String reviewStarRating) {
        this.reviewStarRating = reviewStarRating;
    }

    public String getReviewerDescription() {
        return reviewerDescription;
    }

    public void setReviewerDescription(String reviewerDescription) {
        this.reviewerDescription = reviewerDescription;
    }

    public String getReviewerImgUrl() {
        return reviewerImgUrl;
    }

    public void setReviewerImgUrl(String reviewerImgUrl) {
        this.reviewerImgUrl = reviewerImgUrl;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
}
