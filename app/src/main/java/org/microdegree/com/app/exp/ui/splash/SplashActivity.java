package org.microdegree.com.app.exp.ui.splash;


import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.RemoteMessage;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.local.MySharedPreference;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.bottomnav.BottomNavigation;
import org.microdegree.com.app.exp.ui.bottomnav.Fragments.HomeViewModel;
import org.microdegree.com.app.exp.ui.course.CourseViewModel;
import org.microdegree.com.app.exp.ui.course.coursedetail.viewmodels.CurriculumViewModel;
import org.microdegree.com.app.exp.ui.intro.AppIntroActivity;
import org.microdegree.com.app.exp.ui.intro.IntroActivity;
import org.microdegree.com.app.exp.ui.notification.NotificationViewModel;

import java.util.List;
import java.util.Map;


public class SplashActivity extends AppCompatActivity {
    private AppUpdateManager appUpdateManager;
    private static final int IMMEDIATE_APP_UPDATE_REQ_CODE = 124;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());
        checkUpdate();
    }

    private void dataset() {

        HomeViewModel  homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.initData();
        homeViewModel.getStoryModels();
        homeViewModel.getBannerModels();
        homeViewModel.getCategoryModels();

        CourseViewModel courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
        courseViewModel.initCourses();

        NotificationViewModel notificationViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        notificationViewModel.initNotifications();

        CurriculumViewModel curriculumViewModel = ViewModelProviders.of(this).get(CurriculumViewModel.class);
        curriculumViewModel.initCurricullum();

        AppController.setCourseViewModel(courseViewModel);
        AppController.setHomeViewModel(homeViewModel);
        AppController.setNotificationViewModel(notificationViewModel);
        AppController.setCurricullumViewModel(curriculumViewModel);

        onNewIntent(getIntent()) ;

    }

    private void checkUpdate() {

        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                startUpdateFlow(appUpdateInfo);
            }else{
                dataset();
            }
        });
    }
    private void startUpdateFlow(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, this, SplashActivity.IMMEDIATE_APP_UPDATE_REQ_CODE);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMMEDIATE_APP_UPDATE_REQ_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Update canceled by user!", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Update success!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Update Failed!", Toast.LENGTH_LONG).show();
                checkUpdate();
            }
        }
    }

    @Override
    protected void onNewIntent (Intent intent) {
        super .onNewIntent(intent) ;
        Bundle extras = intent.getExtras() ;
        if (extras != null ) {
            RemoteMessage msgData = extras.getParcelable( "NotificationMessage" ) ;
            new Handler().postDelayed(() -> {
                if (MySharedPreference.getFirstTime(getApplicationContext())) {
                    Intent i = new Intent(getApplicationContext(), AppIntroActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(getApplicationContext(), BottomNavigation.class);
                    i.putExtra( "NotificationMessage" , msgData ) ;
                    startActivity(i);
                }
                finish();
            }, 2000);
        }else{
            new Handler().postDelayed(() -> {

                if (MySharedPreference.getFirstTime(getApplicationContext())) {
                    Intent i = new Intent(getApplicationContext(), AppIntroActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(getApplicationContext(), BottomNavigation.class);
                    i.putExtra( "NotificationMessage" , "" ) ;
                    startActivity(i);
                }
                finish();
            }, 2000);
        }
    }


}
