package org.microdegree.com.app.exp.ui.bottomnav.Fragments;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.microdegree.com.app.exp.data.local.room.AppDatabase;
import org.microdegree.com.app.exp.data.local.room.DatabaseClient;
import org.microdegree.com.app.exp.data.model.TestimonialModel;
import org.microdegree.com.app.exp.data.repo.home.BannerRepository;
import org.microdegree.com.app.exp.data.repo.home.CategoryRepository;
import org.microdegree.com.app.exp.data.repo.home.StoryRepository;
import org.microdegree.com.app.exp.data.model.BannerModel;
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.StoryModel;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<StoryModel>> mStoryList;
    private MutableLiveData<List<BannerModel>> mBannerList;
    private MutableLiveData<List<CategoryModel>> mCategoryList;
    private StoryRepository mStoryRepo;
    private BannerRepository mBannerRepo;
    private CategoryRepository mCategoryRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void initData(){
        initStories();
        initBanners();
        initCategories();
    }


    public void initStories(){
        if(mStoryList != null){
            return;
        }
        mStoryRepo = StoryRepository.getInstance();
        mStoryRepo.setStoryItems();


    }

    public void initBanners(){

        if(mBannerList != null){
            return;
        }
        mBannerRepo = BannerRepository.getInstance();
        mBannerRepo.setBannerItems();

    }

    public void initCategories(){

        if(mCategoryList != null){
            return;
        }
        mCategoryRepo = CategoryRepository.getInstance();
        mCategoryRepo.setCategoryItems();

    }

    public LiveData<List<StoryModel>> getStoryModels(){
        return mStoryRepo.getLocalStoryItems();
    }
    public LiveData<List<BannerModel>> getBannerModels(){
        return mBannerRepo.getLocalBannerItems();
    }
    public LiveData<List<CategoryModel>> getCategoryModels(){
        return mCategoryRepo.getLocalCategoryItems();
    }

    public LiveData<List<CategoryModel>> getPopularCategoryModels(){
        return mCategoryRepo.getPopularCategoryItems();
    }

    public LiveData<List<CategoryModel>> getExpCategoryModels(){
        return mCategoryRepo.getExperiencedCategoryItems();
    }


    public LiveData<List<TestimonialModel>> getSuccessModels(){
        return mCategoryRepo.getTestimonialItems();
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
