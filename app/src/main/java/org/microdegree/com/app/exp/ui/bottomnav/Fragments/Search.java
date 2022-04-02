package org.microdegree.com.app.exp.ui.bottomnav.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.Course.ChapterModel;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.home.category.CategoryAdapter;
import org.microdegree.com.app.exp.ui.home.category.CategoryTypeAdapter;
import org.microdegree.com.app.exp.ui.course.CourseAdapter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.sentry.Sentry;

public class Search extends Fragment {
    public Search() {
        // Required empty public constructor
    }
    private View view;
    List<CourseModel>  mCourseList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_search, container, false);

        initSearch();

        initPopular();

        initExp();

        return view;
    }

    private void initPopular() {
        RecyclerView  recyclerView = view.findViewById(R.id.recyclerViewCategory);
        try {
            AppController.getHomeViewModel().getPopularCategoryModels().observe(getViewLifecycleOwner(), items -> {
                CategoryTypeAdapter mCategoryAdapter = new CategoryTypeAdapter(items, getContext(), "Popular Categories", true, 5);
                recyclerView.setAdapter(mCategoryAdapter);
            });
        }catch (Exception e){

        }

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);

    }

    private void initExp() {

        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewExp);
        RelativeLayout layout= view.findViewById(R.id.categoryExpView);
        layout.setVisibility(View.GONE);
        try{
        AppController.getHomeViewModel().getExpCategoryModels().observe(getViewLifecycleOwner(), items -> {

            if(items.size()>0){
                layout.setVisibility(View.VISIBLE);
            }

            CategoryAdapter adapter = new CategoryAdapter(items, getActivity(),"Category By Experience");
            recyclerView.setAdapter(adapter);
        });
        }catch (Exception e){

        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

    }

    private void initSearch() {
        SearchView searchView=view.findViewById(R.id.search);
        searchView.setIconifiedByDefault(false);
        RelativeLayout layout=view.findViewById(R.id.courseView);
        LinearLayout allView=view.findViewById(R.id.view);

        RecyclerView  recyclerViewCourse = view.findViewById(R.id.recyclerViewCourse);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewCourse.setLayoutManager(linearLayoutManager);
        recyclerViewCourse.setHasFixedSize(true);
        try {
            AppController.getCourseViewModel().getCourseModels().observe(getViewLifecycleOwner(), items -> {

                if (items.size() > 0) {
                    mCourseList.clear();
                    mCourseList.addAll(items);

                }
            });

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    List<String> unqString = new ArrayList<>();
                    List<CourseModel> list = new ArrayList<>();
                    for (CourseModel item : mCourseList) {

                        if (item.getCourseTitle().toLowerCase().contains(query.toLowerCase())) {

                            if (!unqString.contains(item.getCourseId())) {
                                unqString.add(item.getCourseId());
                                list.add(item);
                            }
                        }
                    }

                    if (list.size() > 0) {
                        CourseAdapter mCourseAdapter = new CourseAdapter(list, getContext(), false, "", 0);
                        recyclerViewCourse.setAdapter(mCourseAdapter);
                        layout.setVisibility(View.VISIBLE);
                        allView.setVisibility(View.GONE);
                    }

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    List<String> unqString = new ArrayList<>();

                    if (query.length() > 1) {
                        List<CourseModel> list = new ArrayList<>();
                        for (CourseModel item : mCourseList) {

                            if (item.getCourseTitle().toLowerCase().contains(query.toLowerCase())) {

                                if (!unqString.contains(item.getCourseTitle())) {
                                    unqString.add(item.getCourseTitle());
                                    list.add(item);
                                }
                            }
                        }


                        if (list.size() > 0) {
                            CourseAdapter mCourseAdapter = new CourseAdapter(list, getContext(), false, "", 0);
                            recyclerViewCourse.setAdapter(mCourseAdapter);
                            layout.setVisibility(View.VISIBLE);
                            allView.setVisibility(View.GONE);
                        }

                    } else {
                        layout.setVisibility(View.GONE);
                        allView.setVisibility(View.VISIBLE);
                    }

                    return false;
                }
            });
        }catch (Exception e){
            Sentry.captureMessage(String.valueOf(e));
        }

    }

}