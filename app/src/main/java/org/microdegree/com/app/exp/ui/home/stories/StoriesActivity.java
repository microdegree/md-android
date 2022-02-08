package org.microdegree.com.app.exp.ui.home.stories;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.bumptech.glide.Glide;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.StoryModel;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.course.coursedetail.CourseDetailActivity;

import org.microdegree.com.app.exp.utils.MicroFunctions;

import java.util.ArrayList;
import java.util.List;

import jp.shts.android.storiesprogressview.StoriesProgressView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class StoriesActivity extends AppCompatActivity  implements StoriesProgressView.StoriesListener  {

    private StoriesProgressView storiesProgressView;
    private ImageView imageView;

    private int counter = 0;
    private int pressCounter = 0;
    String data="";

    private final long[] durations = new long[]{
            500L, 1000L, 1500L, 4000L, 5000L, 1000,
    };

    long pressTime = 0L;
    long limit = 500L;

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    pressTime = System.currentTimeMillis();
                    storiesProgressView.pause();
                    return false;
                case MotionEvent.ACTION_UP:
                    long now = System.currentTimeMillis();
                    storiesProgressView.resume();
                    return limit < now - pressTime;
            }
            return false;
        }
    };
    List<StoryModel> storyList =new ArrayList<>();
    private LinearLayout buttonLay;
    private TextView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        data = getIntent().getStringExtra("data");
        pressCounter = getIntent().getIntExtra("pos",0);


        Gson gson = new Gson();
        storyList = gson.fromJson(data, new TypeToken<List<StoryModel>>() {}.getType());


        storiesProgressView = (StoriesProgressView) findViewById(R.id.stories);
        storiesProgressView.setStoriesCount(storyList.size());
        storiesProgressView.setStoryDuration(5000L);
        // or
        // storiesProgressView.setStoriesCountWithDurations(durations);
        storiesProgressView.setStoriesListener(this);
        storiesProgressView.startStories();

        imageView = (ImageView) findViewById(R.id.image);

        button = findViewById(R.id.button);
        buttonLay = findViewById(R.id.buttonLay);

        if(pressCounter!=0){
            StoryModel tempStory= storyList.get(pressCounter);
            StoryModel initStory= storyList.get(0);
            storyList.set(0,tempStory);
            storyList.set(pressCounter,initStory);
        }

        setImage(counter);
        // bind reverse view
        View reverse = findViewById(R.id.reverse);
        reverse.setOnClickListener(v -> storiesProgressView.reverse());
        reverse.setOnTouchListener(onTouchListener);

        // bind skip view
        View skip = findViewById(R.id.skip);
        skip.setOnClickListener(v -> storiesProgressView.skip());
        skip.setOnTouchListener(onTouchListener);

        buttonLay.setOnClickListener(v -> {
           //  storiesProgressView.pause();
            switch (storyList.get(counter).getTargetPageType()) {
                case "url":
                    new MicroFunctions().launchWeb(storyList.get(counter).getTargetPage(),getApplicationContext());
                    break;
                case "razorpaySDKLink":
                    getSDK(storyList.get(counter).getTargetPage());
                    break;
                case "courseId":
                    new MicroFunctions().fromCourseID(storyList.get(counter).getTargetPage(),this,this);
                    break;
                default:
                    FirebaseCrashlytics.getInstance().log("Invalid Link in Story for storyID "+storyList.get(counter).getStoryId());
                    Toast.makeText(getApplicationContext(), "Invalid Link", Toast.LENGTH_SHORT).show();
                    break;
            }

        });
    }

    private void getSDK(String courseId) {
        AppController.getCourseViewModel().getCourse(courseId).observe(this, items -> {
            if(items.size()==1){
                new MicroFunctions().startPayment(items.get(0),this);
            }else{
                Toast.makeText(getApplicationContext(), "Invalid Link", Toast.LENGTH_SHORT).show();
            }

        });
    }


    private void getCourses(String courseId) {
        AppController.getCourseViewModel().getCourse(courseId).observe(this, items -> {

            if(items.size()==1){
              Gson gson = new Gson();
            // convert your list to json
            String jsonCartList = gson.toJson(items.get(0));

            Intent intent = new Intent(getApplicationContext(), CourseDetailActivity.class);
            intent.putExtra("data",jsonCartList);
            startActivity(intent);
            finish();
            }

        });
    }

    @Override
    public void onNext() {
        setImage(++counter);
    }

    @Override
    public void onPrev() {
        if ((counter - 1) < 0) return;
        setImage(--counter);
    }

    @Override
    public void onComplete() {
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
      storiesProgressView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        storiesProgressView.pause();
    }

    @Override
    protected void onDestroy() {
        // Very important !
        storiesProgressView.destroy();
        super.onDestroy();
    }

    private void setImage(int counterValue){


        if(storyList.get(counterValue).getButtonName()!=null && !storyList.get(counterValue).getButtonName().isEmpty()){
            buttonLay.setVisibility(View.VISIBLE);
            button.setText(storyList.get(counterValue).getButtonName());
        }else {
            buttonLay.setVisibility(View.GONE);
        }          Glide.with(getApplicationContext())
                .load(storyList.get(counterValue).getStoryImageUrl())
                .fitCenter()
                .error(R.drawable.loader)
                .centerCrop()
                .into(imageView);

    }

}