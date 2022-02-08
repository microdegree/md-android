package org.microdegree.com.app.exp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.local.MySharedPreference;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.bottomnav.BottomNavigation;
import org.microdegree.com.app.exp.ui.bottomnav.Fragments.HomeViewModel;
import org.microdegree.com.app.exp.ui.course.CourseViewModel;
import org.microdegree.com.app.exp.ui.course.coursedetail.viewmodels.CurriculumViewModel;
import org.microdegree.com.app.exp.ui.intro.AppIntroActivity;
import org.microdegree.com.app.exp.ui.notification.NotificationViewModel;


public class YoutubeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_youtube);
        String  data = getIntent().getStringExtra("data");
        YouTubePlayerView  youtube_player_view = findViewById(R.id.youtube_player_view);

            youtube_player_view.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady() {
                initializedYouTubePlayer.loadVideo(data,0);
            }
        }), true);


    }
}
