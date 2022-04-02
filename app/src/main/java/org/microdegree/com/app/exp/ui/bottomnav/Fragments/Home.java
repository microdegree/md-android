package org.microdegree.com.app.exp.ui.bottomnav.Fragments;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.course.CourseAdapter;
import org.microdegree.com.app.exp.ui.course.coursedetail.CourseDetailActivity;
import org.microdegree.com.app.exp.ui.home.YoutubeActivity;
import org.microdegree.com.app.exp.ui.home.YoutubeAdapter;
import org.microdegree.com.app.exp.ui.home.banner.BannerListener;
import org.microdegree.com.app.exp.ui.home.stories.StoryListener;
import org.microdegree.com.app.exp.data.model.BannerModel;
import org.microdegree.com.app.exp.data.model.StoryModel;
import org.microdegree.com.app.exp.ui.home.banner.BannerAdapter;
import org.microdegree.com.app.exp.ui.home.category.CategoryAdapter;
import org.microdegree.com.app.exp.ui.home.stories.StoriesActivity;
import org.microdegree.com.app.exp.ui.home.stories.StoriesAdapter;
import org.microdegree.com.app.exp.ui.notification.NotificationActivity;
import org.microdegree.com.app.exp.utils.MicroFunctions;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import io.sentry.Sentry;

public class Home extends Fragment implements StoryListener, BannerListener {
    public Home() {
        // Required empty public constructor
    }

    private  View view;
    List<StoryModel> storyList =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home, container, false);


        ImageView notifications = view.findViewById(R.id.notifications);


        notifications.setOnClickListener(view -> {

            Intent intent = new Intent(getContext(), NotificationActivity.class);
            startActivity(intent);
        });

        ImageView call = view.findViewById(R.id.call);

        call.setOnClickListener(view -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel: 8310882795"));
            startActivity(callIntent);

        });
        ImageView telegram = view.findViewById(R.id.telegram);


        telegram.setOnClickListener(view -> {

            final String appName = "org.telegram.messenger";
            final boolean isAppInstalled = isAppAvailable(getContext(), appName);
            if(isAppInstalled)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/microdegreekannada"));
                startActivity(intent);
            }
            else
            {
                String urlString = "https://telegram.me/microdegreekannada";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(urlString));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    getActivity().startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    setWeb("https://telegram.me/microdegreekannada");
                }
            }

        });
        initStories();

        initBanners();

        initCategory();

        initCourseList();

        initSuccessList();

        initYoutube();
        return view;

    }

    public static boolean isAppAvailable(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }
    private void initYoutube() {

        try {

            ImageView imageView = view.findViewById(R.id.imageView);
            String videoId = "_axHfJcVKaM";
            String url = "https://img.youtube.com/vi/" + videoId + "/0.jpg";
            Glide.with(getContext())
                    .load(url)
                    .fitCenter()
                    .error(R.drawable.def_course)
                    .centerCrop()
                    .into(imageView);

            imageView.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), YoutubeActivity.class);
                intent.putExtra("data", videoId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
    }


    private void initSelfBased() {
        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewSelfBased);
        RelativeLayout layout= view.findViewById(R.id.selfBasedView);
        layout.setVisibility(View.GONE);
        try {
            AppController.getCourseViewModel().getSelf().observe(getViewLifecycleOwner(), items -> {


                if (items.size() > 0) {
                    layout.setVisibility(View.VISIBLE);
                }
                CourseAdapter adapter = new CourseAdapter(items, getContext(), true, "Pre-Recorded", 5);
                recyclerView.setAdapter(adapter);
            });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }


    private void initLiveCourses() {
        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewlive);
        RelativeLayout layout= view.findViewById(R.id.liveView);
        layout.setVisibility(View.GONE);
        try{
        AppController.getCourseViewModel().getLive().observe(getViewLifecycleOwner(), items -> {
            if(items.size()>0){
                layout.setVisibility(View.VISIBLE);
            }
            CourseAdapter adapter = new CourseAdapter(items,getContext(),true,"Live Courses",5);
            recyclerView.setAdapter(adapter);
        });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void initFreeCourses() {
        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewJob);
        RelativeLayout layout= view.findViewById(R.id.jobView);
        layout.setVisibility(View.GONE);
        try{
        AppController.getCourseViewModel().getFree().observe(getViewLifecycleOwner(), items -> {

        if(items.size()>0){
            layout.setVisibility(View.VISIBLE);
        }
            CourseAdapter adapter = new CourseAdapter(items,getContext(),true,"Free Courses",5);
            recyclerView.setAdapter(adapter);
        });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void initPopularCategory() {
        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewPopularCategory);
        RelativeLayout layout= view.findViewById(R.id.categoryPopularView);
        layout.setVisibility(View.GONE);
        try{
        AppController.getHomeViewModel().getPopularCategoryModels().observe(getViewLifecycleOwner(), items -> {

            if(items.size()>0){
                layout.setVisibility(View.VISIBLE);
            }
            CategoryAdapter adapter = new CategoryAdapter(items, getActivity(),"Popular Category");
            recyclerView.setAdapter(adapter);
        });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void initExpCategory() {

        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewExpCategory);
        RelativeLayout layout= view.findViewById(R.id.categoryExpView);
        layout.setVisibility(View.GONE);
        try{
        AppController.getHomeViewModel().getExpCategoryModels().observe(getViewLifecycleOwner(), items -> {
            if(items.size()>0){
                layout.setVisibility(View.VISIBLE);
            }
            CategoryAdapter   adapter = new CategoryAdapter(items, getActivity(),"Category By Experience");
            recyclerView.setAdapter(adapter);
        });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void initCourseList() {
        try{
    AppController.getCourseViewModel().getCourseModels().observe(getViewLifecycleOwner(), items -> {
         initSelfBased();
        initLiveCourses();
        initFreeCourses();
    });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
    }

    private void initSuccessList() {
            RecyclerView recyclerView= view.findViewById(R.id.recyclerViewSuccess);
            RelativeLayout layout= view.findViewById(R.id.successView);
            layout.setVisibility(View.GONE);
            try{
            AppController.getHomeViewModel().getSuccessModels().observe(getViewLifecycleOwner(), items -> {
                if(items.size()>0){
                    layout.setVisibility(View.VISIBLE);
                }
                YoutubeAdapter adapter = new YoutubeAdapter(items,getContext());
                recyclerView.setAdapter(adapter);
            });
            }catch (Exception e){
                Sentry.captureMessage(String.valueOf(e));
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

    }

    private void initStories() {
        try {
            RecyclerView recyclerViewStory = view.findViewById(R.id.recyclerViewStory);
            AppController.getHomeViewModel().getStoryModels().observe(getViewLifecycleOwner(), items -> {
                if (items != null) {
                    storyList.clear();
                    storyList.addAll(items);
                }
                StoriesAdapter mStoryAdapter = new StoriesAdapter(storyList, getActivity(), Home.this);
                recyclerViewStory.setAdapter(mStoryAdapter);
            });


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewStory.setLayoutManager(linearLayoutManager);
            recyclerViewStory.setHasFixedSize(true);
        }catch(Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
    }

    private void initBanners() {
        RecyclerView  recyclerViewBanner = view.findViewById(R.id.recyclerViewBanner);
        try{
        AppController.getHomeViewModel().getBannerModels().observe(getViewLifecycleOwner(), items -> {
            BannerAdapter mBannerAdapter = new BannerAdapter(items, getActivity(),Home.this);
            recyclerViewBanner.setAdapter(mBannerAdapter);

        });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBanner.setLayoutManager(linearLayoutManager);
        recyclerViewBanner.setHasFixedSize(true);
    }

    private void initCategory() {
        try{
        AppController.getHomeViewModel().getCategoryModels().observe(getViewLifecycleOwner(), items -> {
            initPopularCategory();
            initExpCategory();
        });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
    }


    @Override
    public void setStory(int pos) {

        Gson gson = new Gson();
        // convert your list to json
        String jsonData = gson.toJson(storyList);
        Intent intent = new Intent(getContext(), StoriesActivity.class);
        intent.putExtra("data", jsonData);
        intent.putExtra("pos",pos);
        startActivity(intent);
    }

    @Override
    public void getData(BannerModel model) {

        switch (model.getTargetPageType()) {
            case "url":
                setWeb(model.getTargetPage());
                break;
            case "razorpaySDKLink":
                getSDK(model.getTargetPage());
                break;
            case "courseId":
                    new MicroFunctions().fromCourseID(model.getTargetPage(),getActivity(),getViewLifecycleOwner());
                break;
            default:
                FirebaseCrashlytics.getInstance().log("Invalid Link in Banner for bannerID "+model.getBannerId());
                Toast.makeText(getContext(), "Invalid Link", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private void getSDK(String courseId) {
        try{
        AppController.getCourseViewModel().getCourse(courseId).observe(this, items -> {

            if(items.size()>0){
                new MicroFunctions().startPayment(items.get(0),getActivity());
            }else{
                Toast.makeText(getContext(), "Invalid Link", Toast.LENGTH_SHORT).show();
            }

        });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }
    }

    private void setWeb(String url) {
        new MicroFunctions().launchWeb(url,getContext());
    }
}
