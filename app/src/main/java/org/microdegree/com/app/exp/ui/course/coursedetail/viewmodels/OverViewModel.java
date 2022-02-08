package org.microdegree.com.app.exp.ui.course.coursedetail.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.microdegree.com.app.exp.data.model.Course.CourseHighlight;
import org.microdegree.com.app.exp.data.repo.overview.HighlightsRepository;
import org.microdegree.com.app.exp.data.repo.overview.PrerequistisRepository;
import org.microdegree.com.app.exp.data.repo.overview.SubtitleRepository;

import java.util.List;

public class OverViewModel extends ViewModel {

    private MutableLiveData<List<String>> mSubtitleList;
    private MutableLiveData<List<CourseHighlight>> mHighListList;
    private MutableLiveData<List<String>> mPreReqList;
    private SubtitleRepository mSubtitleRepo;
    private HighlightsRepository mHighLightRepo;
    private PrerequistisRepository mPreReqRepo;

    
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void initHeaders(String id){
        if(mSubtitleList != null){
            return;
        }
        mSubtitleRepo = SubtitleRepository.getInstance();
        mSubtitleList = mSubtitleRepo.getSubtitleItems(id);

    }

    public void initHighLights(String id){

        if(mHighListList != null){
            return;
        }
        mHighLightRepo = HighlightsRepository.getInstance();
        mHighListList = mHighLightRepo.getHighLightItems(id);

    }

    public void initPreRequistis(String id){

        if(mPreReqList != null){
            return;
        }
        mPreReqRepo = PrerequistisRepository.getInstance();
        mPreReqList = mPreReqRepo.getPreRequisitisItems(id);

    }

    public LiveData<List<String>> getSubtitleModels(){
        return mSubtitleList;
    }
    public LiveData<List<CourseHighlight>> getHighLightModels(){
        return mHighListList;
    }
    public LiveData<List<String>> getPreRequistisModels(){
        return mPreReqList;
    }
    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
