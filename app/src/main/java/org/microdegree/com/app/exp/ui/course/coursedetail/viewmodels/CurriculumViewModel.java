package org.microdegree.com.app.exp.ui.course.coursedetail.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.microdegree.com.app.exp.data.model.Course.ChapterModel;
import org.microdegree.com.app.exp.data.model.Course.CurriculumGoal;
import org.microdegree.com.app.exp.data.model.Course.HandsonModel;
import org.microdegree.com.app.exp.data.model.Course.ObjectiveModel;
import org.microdegree.com.app.exp.data.model.Course.TopicModel;
import org.microdegree.com.app.exp.data.repo.curriculum.ChapterRepository;
import org.microdegree.com.app.exp.data.repo.curriculum.GoalRepository;
import org.microdegree.com.app.exp.data.repo.curriculum.HandsOnRepository;
import org.microdegree.com.app.exp.data.repo.curriculum.ObjectivesRepository;
import org.microdegree.com.app.exp.data.repo.curriculum.TopicsRepository;

import java.util.List;

public class CurriculumViewModel extends ViewModel {

    private MutableLiveData<List<CurriculumGoal>> mGoalList;
    private MutableLiveData<List<ChapterModel>> mChapterList;
    private MutableLiveData<List<ObjectiveModel>> mObjectiveList;
    private MutableLiveData<List<TopicModel>> mTopicList;
    private MutableLiveData<List<HandsonModel>> mHandsOnList;
    private GoalRepository mGoalRepo;
    private ChapterRepository mChapterRepo;
    private ObjectivesRepository mObjectivesRepo;
    private TopicsRepository mTopicRepo;
    private HandsOnRepository mHandsOnRepo;

    
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void initCurricullum(){
        mChapterRepo = ChapterRepository.getInstance();
        mChapterRepo.setChapterItems();

        mObjectivesRepo = ObjectivesRepository.getInstance();
        mObjectivesRepo.setObjectiveItems();

        mTopicRepo = TopicsRepository.getInstance();
        mTopicRepo.setTopicItems();

        mHandsOnRepo = HandsOnRepository.getInstance();
        mHandsOnRepo.setHandsonItems();

    }

    public LiveData<List<ChapterModel>> getChapterModels(String id){
        return mChapterRepo.getLocalChapterItems(id);
    }
    public LiveData<List<CurriculumGoal>> getGoalModels(){
        return mGoalList;
    }
    public LiveData<List<ObjectiveModel>> getObjectiveModels(String id){
        return mObjectivesRepo.getLocalObjectiveItems(id);
    }
    public LiveData<List<TopicModel>> getTopicModels(String id){
        return mTopicRepo.getLocalTopicItems(id);
    }
    public LiveData<List<HandsonModel>> getHandsOnModels(String id){
        return mHandsOnRepo.getLocalHandsonItems(id);
    }


    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
