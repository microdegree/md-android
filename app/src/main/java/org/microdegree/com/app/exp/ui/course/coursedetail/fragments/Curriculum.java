package org.microdegree.com.app.exp.ui.course.coursedetail.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.Course.CourseCurriculum;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.data.model.Course.ChapterModel;
import org.microdegree.com.app.exp.data.model.Course.CurriculumGoal;
import org.microdegree.com.app.exp.data.model.Course.TopicModel;
import org.microdegree.com.app.exp.data.model.Course.HandsonModel;
import org.microdegree.com.app.exp.data.model.Course.ObjectiveModel;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.course.CourseAdapter;
import org.microdegree.com.app.exp.ui.course.coursedetail.CurriculumAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Curriculum extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CourseModel mCourseModel;
    public Curriculum(CourseModel mCourseModel) {
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
    public static Curriculum newInstance(String param1, String param2) {
        Curriculum fragment = new Curriculum(new CourseModel());
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
    ExpandableListView expandableListView;
    CurriculumAdapter curriculumAdapter;
    List<String> expandableListTitle=new ArrayList<>();
    List<ChapterModel> chapterModel=new ArrayList<>();

    HashMap<String, List<CourseCurriculum>> expandableListDetail = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_curriculum, container, false);
        TextView no_courses = view.findViewById(R.id.no_courses);
        no_courses.setVisibility(View.VISIBLE);
        AppController.getCurricullumViewModel().getChapterModels(mCourseModel.getCourseId()).observe(getViewLifecycleOwner(),
                items -> {
                    if(items.size()>0){
                        expandableListTitle.clear();
                        chapterModel.addAll(items);
                        no_courses.setVisibility(View.GONE);
                        for(ChapterModel item : items){
                            expandableListTitle.add(item.getCurriculumChapterTitle());
                           setData(item);
                        }


                    }

                });
        initCurriculum();

        return view;
    }

    private void setData(ChapterModel items) {

        List<CourseCurriculum> list = new ArrayList<>();
        CourseCurriculum courseCurriculum= new CourseCurriculum();

        courseCurriculum.setChapterPreviewLink(items.getChapterPreviewLink());
        courseCurriculum.setCurriculumChapterTitle(items.getCurriculumChapterTitle());

        courseCurriculum.setGoal(items.getChapterGoal());

        AppController.getCurricullumViewModel().getHandsOnModels(items.getCurriculumChapterId()).observe(getViewLifecycleOwner(), items1 -> {
                courseCurriculum.setCurriculumHandson(items1);
                AppController.getCurricullumViewModel().getObjectiveModels(items.getCurriculumChapterId()).observe(getViewLifecycleOwner(), items12 -> {
                courseCurriculum.setCurriculumObjectives(items12);

                AppController.getCurricullumViewModel().getTopicModels(items.getCurriculumChapterId()).observe(getViewLifecycleOwner(), items13 -> {
                    courseCurriculum.setCurriculumTopics(items13);
                    list.add(courseCurriculum);
                    expandableListDetail.put(items.getCurriculumChapterTitle(), list);

                });
            });
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            curriculumAdapter = new CurriculumAdapter(getContext(), expandableListTitle, expandableListDetail);
            expandableListView.setAdapter(curriculumAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initCurriculum() {
        expandableListView = view.findViewById(R.id.expandableListView);
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> true);
    }
}
