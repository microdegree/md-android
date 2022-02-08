package org.microdegree.com.app.exp.ui.course.coursedetail.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import org.microdegree.com.app.exp.data.model.Course.CourseHighlight;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.ui.course.coursedetail.HighlightsAdapter;
import org.microdegree.com.app.exp.ui.course.coursedetail.PreRequisitesAdapter;
import org.microdegree.com.app.exp.ui.course.coursedetail.SubTitleArrowAdapter;
import org.microdegree.com.app.exp.ui.course.coursedetail.viewmodels.OverViewModel;


public class Overview extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CourseModel mCourseModel;
    public Overview(CourseModel mCourseModel) {
        // Required empty public constructor
        this.mCourseModel=mCourseModel;
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
    public static Overview newInstance(String param1, String param2) {
        Overview fragment = new Overview(new CourseModel());
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private OverViewModel overViewModel;
    RecyclerView recyclerViewSubtitle;
    RecyclerView recyclerViewHighlights;
    RecyclerView recyclerViewPre;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    RelativeLayout preRequisitiesView;
    private  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_overview, container, false);

        overViewModel = ViewModelProviders.of(this).get(OverViewModel.class);

        overViewModel.initHeaders(mCourseModel.getCourseId());

        overViewModel.getSubtitleModels().observe(getViewLifecycleOwner(), items -> {
            SubTitleArrowAdapter mAdapter = new SubTitleArrowAdapter(items);
            recyclerViewSubtitle.setAdapter(mAdapter);

        });
        initHeader();

        overViewModel.initHighLights(mCourseModel.getCourseId());

        overViewModel.getHighLightModels().observe(getViewLifecycleOwner(), items -> {

            HighlightsAdapter mAdapter = new HighlightsAdapter(items,getContext());
            recyclerViewHighlights.setAdapter(mAdapter);

        });
        initHighlights();

        overViewModel.initPreRequistis(mCourseModel.getCourseId());
        overViewModel.getPreRequistisModels().observe(getViewLifecycleOwner(), items -> {
            PreRequisitesAdapter mAdapter = new PreRequisitesAdapter(items);
            recyclerViewPre.setAdapter(mAdapter);
            if(items.size()>0){
                preRequisitiesView.setVisibility(View.VISIBLE);
            }

        });
        initPreRequisitis();

        return view;
    }

    private void initPreRequisitis() {
        TextView txt_pretitle=view.findViewById(R.id.txt_pretitle);
         preRequisitiesView=view.findViewById(R.id.preRequisitiesView);
        preRequisitiesView.setVisibility(View.GONE);

        txt_pretitle.setText("Pre-Requisitis "+mCourseModel.getCourseTitle());

        ImageView preImg=view.findViewById(R.id.preImg);

        Glide.with(getContext())
                .load(mCourseModel.getCourseRoadMapUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                        preImg.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .fitCenter()
                .centerCrop()
                .into(preImg);

        recyclerViewPre=view.findViewById(R.id.recyclerViewPre);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewPre.setLayoutManager(linearLayoutManager);
        recyclerViewPre.setHasFixedSize(true);
    }

    private void initHighlights() {
        recyclerViewHighlights=view.findViewById(R.id.recyclerViewHighlights);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewHighlights.setLayoutManager(linearLayoutManager);
        recyclerViewHighlights.setHasFixedSize(true);

    }

    private void initHeader() {
        TextView txt_title=view.findViewById(R.id.txt_title);
        TextView txt_subtitle=view.findViewById(R.id.txt_subtitle);
        txt_title.setText(mCourseModel.getCourseTitle());
        txt_subtitle.setText(mCourseModel.getCourseSubTitle());

        recyclerViewSubtitle=view.findViewById(R.id.recyclerViewSubtitle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewSubtitle.setLayoutManager(linearLayoutManager);
        recyclerViewSubtitle.setHasFixedSize(true);
    }
}
