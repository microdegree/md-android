package org.microdegree.com.app.exp.ui.course;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.microdegree.com.app.exp.data.repo.CourseRepository;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;

import java.util.List;

public class CourseViewModel extends ViewModel {

    private MutableLiveData<List<CourseModel>> mCourseList;
    private CourseRepository mCourseRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void initCourses() {
        if (mCourseList != null) {
            return;
        }
        mCourseRepo = CourseRepository.getInstance();
        mCourseRepo.setCourseItems();

    }

    public LiveData<List<CourseModel>> getCourseModels(){
        return mCourseRepo.getLocalCourseItems();
    }
    public LiveData<List<CourseModel>> getCourseFromCat(String catID){
        return mCourseRepo.getCatCourseItems(catID);
    }

    public LiveData<List<CourseModel>> getSelf(){
        return mCourseRepo.getSelf();
    }

    public LiveData<List<CourseModel>> getFree(){
        return mCourseRepo.getFree();
    }

    public LiveData<List<CourseModel>> getLive(){
        return mCourseRepo.getLive();
    }


    public LiveData<List<CourseModel>> getCourse(String id){
        return mCourseRepo.getLocalCourse(id);
    }


    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
