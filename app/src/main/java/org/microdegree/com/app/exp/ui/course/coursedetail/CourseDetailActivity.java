package org.microdegree.com.app.exp.ui.course.coursedetail;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.razorpay.PaymentResultListener;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.utils.MicroFunctions;

public class CourseDetailActivity extends AppCompatActivity  implements PaymentResultListener {
    TabLayout tabLayout;
    FCViewPager viewPager;

    CourseModel mCourseModel=new CourseModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        FirebaseAnalytics mFirebaseAnalytics=  FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, mCourseModel.getCourseId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,  mCourseModel.getCourseTitle());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, mCourseModel.getCourseImageUrl());
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(view -> finish());

        String  data = getIntent().getStringExtra("data");
        LinearLayout startpayment = findViewById(R.id.payment);

        startpayment.setOnClickListener(view -> {

            if(mCourseModel.getIsFree().equals("1")){
                new MicroFunctions().launchWeb(mCourseModel.getCourseWebPageUrl(),getApplicationContext());
            }else{
                new MicroFunctions().startPayment(mCourseModel, CourseDetailActivity.this);
            }
        });

        Gson gson = new Gson();
        // Converts JSON string into a List of Product object
        mCourseModel = gson.fromJson(data,
                new TypeToken<CourseModel>() {
                }.getType());

        initYoutube();
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Curriculum"));
        tabLayout.addTab(tabLayout.newTab().setText("FAQs"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final CourseDetailAdapter adapter = new CourseDetailAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount(),mCourseModel);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void initYoutube() {
        try {

            YouTubePlayerView youtubePlayerView = findViewById(R.id.youtube_player_view);
            getLifecycle().addObserver(youtubePlayerView);
            String[] data = mCourseModel.getCourseDemoVideoUrl().split(".be/");

            youtubePlayerView.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady() {
                    String videoId = data[1];
                    initializedYouTubePlayer.cueVideo(videoId, 0);
                }
            }), true);
        }catch (Exception e){
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }
    public static class FCViewPager extends ViewPager {

        private boolean enableSwipe;

        public FCViewPager(Context context) {
            super(context);
            init();
        }

        public FCViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            enableSwipe = false;
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent event) {
            // Never allow swiping to switch between pages
            return enableSwipe && super.onInterceptTouchEvent(event);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // Never allow swiping to switch between pages
            return enableSwipe && super.onTouchEvent(event);

        }

        public void setEnableSwipe(boolean enableSwipe) {
            this.enableSwipe = enableSwipe;
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment successfully done! " +s, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }
}