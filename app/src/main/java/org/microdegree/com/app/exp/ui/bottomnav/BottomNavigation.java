package org.microdegree.com.app.exp.ui.bottomnav;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;


import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.local.MySharedPreference;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.course.coursedetail.CourseDetailActivity;
import org.microdegree.com.app.exp.ui.intro.AppIntroActivity;
import org.microdegree.com.app.exp.utils.MicroFunctions;

import java.util.Map;

import io.sentry.Sentry;

public class BottomNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(getString(R.string.default_notification_channel_id),
                    getString(R.string.default_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
           // channel.setAllowBubbles(true);
            channel.enableVibration(true);
            channel.enableLights(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
        }


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();
                    FirebaseMessaging.getInstance().subscribeToTopic("all");
                    // Log and toast
                    String msg = getString(R.string.msg_token_fmt, token);


                });

        //Initialize Bottom Navigation View.
        BottomNavigationView navView = findViewById(R.id.bottomNav_view);

        //Pass the ID's of Different destinations
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_courses, R.id.navigation_more )
                .build();
        try{
        //Initialize NavController.
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
        onNewIntent(getIntent()) ;
    }

    public void onClickRefer(View view) {
        setWeb("https://pages.microdegree.work/rewards.html");
    }

    public void onClickContact(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel: 8310882795"));
        startActivity(callIntent);
    }

    public void onClickSubscribe(View view) {
        setWeb("https://courses.microdegree.work/pages/microdegree-pro");
    }

    public void onClickAbout(View view) {
        setWeb("https://pages.microdegree.work/about.html");
    }

    public void onClickTerms(View view) {
        setWeb("https://pages.microdegree.work/termsnconditions.html");
    }

    public void onClickCredit(View view) {
        setWeb("https://pages.microdegree.work/credits.html");
    }

    private void setWeb(String url) {
        try {
            new MicroFunctions().launchWeb(url,getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onNewIntent (Intent intent) {
        super .onNewIntent(intent) ;
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (extras.containsKey("NotificationMessage")) {
                    RemoteMessage msgData = extras.getParcelable("NotificationMessage");
                    Map<String, String> data = msgData.getData();
                     getRoute(data.get("value"), data.get("type"));
                }
            }
        }catch (Exception e){

        }
    }

    void getRoute(String value,String path){
        switch (path) {
            case "url":
                new MicroFunctions().launchWeb(value,getApplicationContext());
                break;
            case "course":
                new MicroFunctions().fromCourseID(value,this,this);
                break;
            default:
        }
    }
}
