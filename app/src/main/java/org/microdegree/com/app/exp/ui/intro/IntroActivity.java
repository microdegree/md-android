package org.microdegree.com.app.exp.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;


import org.microdegree.com.app.exp.data.local.MySharedPreference;
import org.microdegree.com.app.exp.data.model.OnBoardingModel;
import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.ui.signin.SignInActivity;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {

    private LinearLayout pager_indicator;

    private ViewPager onboard_pager;

    private IntroAdapter mAdapter;

    private Button btn_get_started;

    int previous_pos=0;


    ArrayList<OnBoardingModel> onBoardItems=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btn_get_started = (Button) findViewById(R.id.btn_get_started);
        onboard_pager = (ViewPager) findViewById(R.id.pager_introduction);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        loadData();

        mAdapter = new IntroAdapter(this,onBoardItems);
        onboard_pager.setAdapter(mAdapter);
        onboard_pager.setCurrentItem(0);

        setUiPageViewController();
        setupCurrentIndicator(0);

        onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Change the current position intimation
                setupCurrentIndicator(position);
                if(position == 2){
                    show_animation();
                }else if(position == 1){
                    if(previous_pos==2){
                        btn_get_started.setVisibility(View.INVISIBLE);
                    }
                }

               previous_pos=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreference.setFirstTime(false,getApplicationContext());
                Intent i = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(i);
                finish();
            }
        });



    }


    private void setupCurrentIndicator(int index) {

        int count=pager_indicator.getChildCount();
        for (int i=0; i<count; i++){
            ImageView imageView=(ImageView)pager_indicator.getChildAt(i);
            if (i==index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator_active));
            }else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator_inactive));
            }
        }

//        if (index==adapter.getItemCount()-1){
//            actionButton.setText("Start");
//
//        }else {
//            actionButton.setText("Next");
//
//        }


    }

    // Load data into the viewpager

    public void loadData()
    {

        String[] header = {
                "Learn the most In-demand Skills for Free",
                "Learn the most In-demand Skills for Free",
                "Learn the most In-demand Skills for Free"
        };
        String[] desc = {"","",""};
        int[] imageId = {R.drawable.onboard1, R.drawable.onboard2, R.drawable.onboard3};

        for(int i=0;i<imageId.length;i++)
        {
            OnBoardingModel item=new OnBoardingModel();
            item.setImage_url(imageId[i]);
            item.setTitle(header[i]);
            item.setDescription(desc[i]);
            //item.setDescription(getResources().getString(desc[i]));

            onBoardItems.add(item);
        }
    }

    // Button bottomUp animation

    public void show_animation()
    {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);

        btn_get_started.startAnimation(show);

        show.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                btn_get_started.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                btn_get_started.clearAnimation();

            }

        });


    }

    // Button Topdown animation

    public void hide_animation()
    {
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.slide_down_anim);

        btn_get_started.startAnimation(hide);

        hide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

             //   btn_get_started.clearAnimation();
                btn_get_started.setVisibility(View.GONE);

            }

        });


    }

    // setup the
    private void setUiPageViewController() {
        ImageView[] indicator=new ImageView[mAdapter.getCount()];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i=0; i<indicator.length; i++){
            indicator[i]=new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator_inactive));
            indicator[i].setLayoutParams(layoutParams);
            pager_indicator.addView(indicator[i]);
        }

    }


}