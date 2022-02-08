package org.microdegree.com.app.exp.ui.home.category;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.course.CourseAdapter;
import org.microdegree.com.app.exp.ui.course.CourseViewModel;

import java.util.ArrayList;
import java.util.List;


public class CategoryActivity extends AppCompatActivity {
    private CategoryTypeAdapter mCourseAdapter;
    RecyclerView recyclerViewCourses;//recycler view for Courses
  //  CategoryModel mCategoryModel=new CategoryModel();
    List<CategoryModel> mCatList=new ArrayList<>();
    private CourseViewModel courseViewModel;
    String  header="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

         String  data = getIntent().getStringExtra("data");
           header = getIntent().getStringExtra("type");
        Gson gson = new Gson();
        // Converts JSON string into a List of Product object
        mCatList = gson.fromJson(data,
                new TypeToken<List<CategoryModel>>() {
                }.getType());


        TextView txt_title = findViewById(R.id.txt_title);
        TextView no_courses = findViewById(R.id.no_courses);
        if(mCatList.size()>0){
            no_courses.setVisibility(View.GONE);
        }



        TextView  txt_subtitle = findViewById(R.id.txt_subtitle);

        txt_title.setText("Category");
        txt_subtitle.setText(header);


        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(view -> finish());
        initCourses();
    }
    private void initCourses() {
        recyclerViewCourses = findViewById(R.id.recyclerViewCourses);
        recyclerViewCourses.setVisibility(View.VISIBLE);
        recyclerViewCourses.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewCourses.setHasFixedSize(true);

        mCourseAdapter = new CategoryTypeAdapter(mCatList, getApplicationContext(),header,true,0);
        recyclerViewCourses.setAdapter(mCourseAdapter);
    }

}
