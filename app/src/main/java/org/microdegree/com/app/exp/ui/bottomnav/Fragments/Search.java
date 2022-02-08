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

public class Search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Search() {
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
    public static Search newInstance(String param1, String param2) {
        Search fragment = new Search();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        AppController.getHomeViewModel().getPopularCategoryModels().observe(getViewLifecycleOwner(), items -> {
            CategoryTypeAdapter   mCategoryAdapter = new CategoryTypeAdapter(items, getContext(), "Popular Categories",true,5);
            recyclerView.setAdapter(mCategoryAdapter);
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);

    }

    private void initExp() {

        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewExp);
        RelativeLayout layout= view.findViewById(R.id.categoryExpView);
        layout.setVisibility(View.GONE);
        AppController.getHomeViewModel().getExpCategoryModels().observe(getViewLifecycleOwner(), items -> {

            if(items.size()>0){
                layout.setVisibility(View.VISIBLE);
            }

            CategoryAdapter adapter = new CategoryAdapter(items, getActivity(),"Category By Experience");
            recyclerView.setAdapter(adapter);
        });

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
        AppController.getCourseViewModel().getCourseModels().observe(getViewLifecycleOwner(), items -> {

            if(items.size()>0) {
                    mCourseList.clear();
                    mCourseList.addAll(items);

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<String> unqString=new ArrayList<>();
                List<CourseModel> list = new ArrayList<>();
                for (CourseModel item : mCourseList) {

                    if (item.getCourseTitle().toLowerCase().contains(query.toLowerCase())) {

                        if(!unqString.contains(item.getCourseId())){
                            unqString.add(item.getCourseId());
                             list.add(item);
                        }
                    }
                }

                if(list.size()>0){
                    CourseAdapter  mCourseAdapter = new CourseAdapter(list, getContext(),false,"",0);
                    recyclerViewCourse.setAdapter(mCourseAdapter);
                    layout.setVisibility(View.VISIBLE);
                    allView.setVisibility(View.GONE);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                List<String> unqString=new ArrayList<>();

                if(query.length()>1){
                    List<CourseModel> list = new ArrayList<>();
                    for (CourseModel item : mCourseList) {

                        if (item.getCourseTitle().toLowerCase().contains(query.toLowerCase())) {

                            if(!unqString.contains(item.getCourseTitle())){
                                unqString.add(item.getCourseTitle());
                                list.add(item);
                            }
                        }
                    }



                    if(list.size()>0){
                        CourseAdapter  mCourseAdapter = new CourseAdapter(list, getContext(),false,"",0);
                        recyclerViewCourse.setAdapter(mCourseAdapter);
                        layout.setVisibility(View.VISIBLE);
                        allView.setVisibility(View.GONE);
                    }

                }else{
                    layout.setVisibility(View.GONE);
                    allView.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });

    }

}