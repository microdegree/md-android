package org.microdegree.com.app.exp.ui.course.coursedetail.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.Course.CourseFaq;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.data.model.Course.LearnerReview;
import org.microdegree.com.app.exp.ui.course.coursedetail.FAQAdapter;
import org.microdegree.com.app.exp.ui.course.coursedetail.ReviewsAdapter;
import org.microdegree.com.app.exp.ui.course.coursedetail.viewmodels.WhyViewModel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.microdegree.com.app.exp.utils.SetImageGlide.SetImageGlideError;
import static org.microdegree.com.app.exp.utils.SetImageGlide.SetImageGlideLocal;

public class Why extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CourseModel mCourseModel;
    public Why(CourseModel mCourseModel) {
        this.mCourseModel=mCourseModel;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Why newInstance(String param1, String param2) {
        Why fragment = new Why(new CourseModel());
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private  View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    WhyViewModel whyViewModel;
    RecyclerView recyclerViewReview;
    ExpandableListView expandableListView;
    FAQAdapter faqAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_why, container, false);


        initScope();

        whyViewModel = ViewModelProviders.of(this).get(WhyViewModel.class);

        whyViewModel.initFAQs(mCourseModel.getCourseId());

        whyViewModel.getFAQModels().observe(getViewLifecycleOwner(), new Observer<List<CourseFaq>>() {
            @Override
            public void onChanged(@Nullable List<CourseFaq> items) {


                expandableListDetail = getData(items);
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                faqAdapter = new FAQAdapter(getContext(), expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(faqAdapter);
            }
        });
        expandableListView = view.findViewById(R.id.expandableListView);


        whyViewModel.initReviews(mCourseModel.getCourseId());

        whyViewModel.getReviewModels().observe(getViewLifecycleOwner(), new Observer<List<LearnerReview>>() {
            @Override
            public void onChanged(@Nullable List<LearnerReview> items) {

                ReviewsAdapter mAdapter = new ReviewsAdapter(items,getContext());
                recyclerViewReview.setAdapter(mAdapter);

            }
        });
        initReviews();



        return view;
    }

    private void initReviews() {
        recyclerViewReview=view.findViewById(R.id.recyclerViewReviews);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewReview.setLayoutManager(linearLayoutManager);
        recyclerViewReview.setHasFixedSize(true);
    }

    private void initScope() {
        TextView txt_scope=view.findViewById(R.id.txt_scope);

        txt_scope.setText(mCourseModel.getCourseScopeDescription());


        ImageView scopeImg=view.findViewById(R.id.scopeImg);

        Glide.with(getContext())
                .load(mCourseModel.getCourseScopeImgUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                        scopeImg.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                         return false;
                    }
                })
                .fitCenter()
                .centerCrop()
                .into(scopeImg);
    }

    private HashMap<String, List<String>> getData(List<CourseFaq> items) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        for(int i=0;i<items.size();i++)
        {
            List<String> list = new ArrayList<String>();
            list.add(items.get(i).getCourseFaqDescription());
            expandableListDetail.put(items.get(i).getCourseFaqTitle(), list);
        }
        return expandableListDetail;
    }

}
