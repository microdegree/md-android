package org.microdegree.com.app.exp.data.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.microdegree.com.app.exp.data.local.room.Save;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class CourseRepository {

    private static CourseRepository instance;
    private MutableLiveData<List<CourseModel>> dataSet;
    // Write a message to the database
    private static final String TAG = "Course Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("coursesList");


    public static CourseRepository getInstance(){
        if(instance == null){
            instance = new CourseRepository();
        }
        return instance;
    }

    public void setCourseItems() {
        dataSet = new MutableLiveData<>();
        setItems();
    }
    public LiveData<List<CourseModel>> getLocalCourseItems(){
        return   AppController.getDatabase()
                .courseDao()
                .getAll();
    }
    public LiveData<List<CourseModel>> getCatCourseItems(String catID){
        return   AppController.getDatabase()
                .courseDao()
                .getCatCourse(catID);
    }

    public LiveData<List<CourseModel>> getSelf(){
        return   AppController.getDatabase()
                .courseDao()
                .getSelfCourse();
    }

    public LiveData<List<CourseModel>> getLive(){
        return   AppController.getDatabase()
                .courseDao()
                .getLiveCourse();
    }

    public LiveData<List<CourseModel>> getFree(){
        return   AppController.getDatabase()
                .courseDao()
                .getFreeCourse();
    }

    public LiveData<List<CourseModel>> getLocalCourse(String id){

        return AppController.getDatabase()
                .courseDao()
                .getItem(id);

    }

    private void setItems(){
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<CourseModel> list = new ArrayList<>();

                int count=1;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    CourseModel model = postSnapshot.getValue(CourseModel.class);
                    model.setId(count);
                    list.add(model);
                    count++;
                }
                new Save(
                        list,
                        AppController.getDatabase().courseDao()
                ).execute();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }

}











