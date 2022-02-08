package org.microdegree.com.app.exp.impl;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import org.microdegree.com.app.exp.data.local.room.AppDatabase;
import org.microdegree.com.app.exp.data.local.room.DatabaseClient;
import org.microdegree.com.app.exp.ui.bottomnav.Fragments.HomeViewModel;
import org.microdegree.com.app.exp.ui.course.CourseViewModel;
import org.microdegree.com.app.exp.ui.course.coursedetail.viewmodels.CurriculumViewModel;
import org.microdegree.com.app.exp.ui.notification.NotificationViewModel;
import org.microdegree.com.app.exp.utils.FontsOverride;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;

   static AppDatabase database;
   static HomeViewModel homeViewModel;
   static CourseViewModel courseViewModel;
   static NotificationViewModel notificationModel;
   static CurriculumViewModel curriculumViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
     //   FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/arial.ttf");
        //  This FontsOverride comes from the example I posted above

        mInstance = this;
        database= DatabaseClient.getInstance(this).getAppDatabase();
    }

    public static AppDatabase getDatabase() {
        return database;
    }

    public static void setHomeViewModel(HomeViewModel model) {
        homeViewModel=model;
    }

    public static void setNotificationViewModel(NotificationViewModel model) {
        notificationModel=model;
    }
    public static void setCurricullumViewModel(CurriculumViewModel model) {
        curriculumViewModel=model;
    }


    public static void setCourseViewModel(CourseViewModel model) {
        courseViewModel=model;
    }

    public static HomeViewModel getHomeViewModel() {
        return homeViewModel;
    }

    public static CourseViewModel getCourseViewModel() {
        return courseViewModel;
    }



    public static NotificationViewModel getNotificationModelViewModel() {
        return notificationModel;
    }

    public static CurriculumViewModel getCurricullumViewModel() {
        return curriculumViewModel;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


}
