package org.microdegree.com.app.exp.ui.course.coursedetail.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.microdegree.com.app.exp.data.model.Course.CourseFaq;
import org.microdegree.com.app.exp.data.model.Course.LearnerReview;
import org.microdegree.com.app.exp.data.repo.why.FAQRepository;
import org.microdegree.com.app.exp.data.repo.why.ReviewRepository;

import java.util.List;

public class WhyViewModel extends ViewModel {

    private MutableLiveData<List<LearnerReview>> mReviewsList;
    private MutableLiveData<List<CourseFaq>> mFaqsList;
    private ReviewRepository mReviewRepo;
    private FAQRepository mFAQRepo;

    
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void initReviews(String id){
        if(mReviewsList != null){
            return;
        }
        mReviewRepo = ReviewRepository.getInstance();
        mReviewsList = mReviewRepo.getReviewItems(id);

    }

    public void initFAQs(String id){

        if(mFaqsList != null){
            return;
        }
        mFAQRepo = FAQRepository.getInstance();
        mFaqsList = mFAQRepo.getFAQItems(id);

    }

    public LiveData<List<LearnerReview>> getReviewModels(){
        return mReviewsList;
    }
    public LiveData<List<CourseFaq>> getFAQModels(){
        return mFaqsList;
    }
    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
