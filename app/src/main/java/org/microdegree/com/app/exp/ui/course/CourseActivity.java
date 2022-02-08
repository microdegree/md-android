package org.microdegree.com.app.exp.ui.course;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;
import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.home.category.CategoryTypeAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CourseActivity extends AppCompatActivity {
    private CourseAdapter  mCourseAdapter;
    RecyclerView recyclerViewCourses;//recycler view for Courses
    CategoryModel mCategoryModel=new CategoryModel();
    List<CourseModel> mCourseList=new ArrayList<>();
    List<CourseModel> mList=new ArrayList<>();
    private CourseViewModel courseViewModel;
    String  header="";
    int list=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Gson gson = new Gson();
        TextView no_courses = findViewById(R.id.no_courses);
        TextView txt_title = findViewById(R.id.txt_title);
        TextView  txt_subtitle = findViewById(R.id.txt_subtitle);
        ImageView back = findViewById(R.id.back);

         String  data = getIntent().getStringExtra("data");
           header = getIntent().getStringExtra("type");
           list = getIntent().getIntExtra("list",111);

        if(list==111){
            mCategoryModel = gson.fromJson(data,
                    new TypeToken<CategoryModel>() {
                    }.getType());


            txt_title.setText(header);
            txt_subtitle.setText(mCategoryModel.getCategoryName());

            no_courses.setVisibility(View.VISIBLE);
            AppController.getCourseViewModel().getCourseFromCat(mCategoryModel.getCategoryId()).observe(this, new Observer<List<CourseModel>>() {
                @Override
                public void onChanged(@Nullable List<CourseModel> items) {
                    mCourseList.clear();

                    if(items.size()>0){
                        no_courses.setVisibility(View.GONE);
                        recyclerViewCourses.setVisibility(View.VISIBLE);
                        mCourseList.addAll(items);
                        mCourseAdapter = new CourseAdapter(mCourseList, getApplicationContext(),false,header,0);
                        recyclerViewCourses.setAdapter(mCourseAdapter);
                    }else{
                        no_courses.setVisibility(View.VISIBLE);
                        recyclerViewCourses.setVisibility(View.GONE);
                    }
                }
            });

        }else{
            txt_title.setText("Courses");
            txt_subtitle.setText(header);

            mList = gson.fromJson(data,
                    new TypeToken<List<CourseModel>>() {
                    }.getType());
            no_courses.setVisibility(View.GONE);
        }

        back.setOnClickListener(view -> finish());

         initCourses();
    }
    private void initCourses() {
        recyclerViewCourses = findViewById(R.id.recyclerViewCourses);
        recyclerViewCourses.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCourses.setLayoutManager(linearLayoutManager);
        recyclerViewCourses.setHasFixedSize(true);
        if(list==222){
            mCourseAdapter = new CourseAdapter(mList, getApplicationContext(),false,header,0);
            recyclerViewCourses.setAdapter(mCourseAdapter);
        }
    }

}
