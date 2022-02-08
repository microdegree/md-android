package org.microdegree.com.app.exp.data.repo.home;

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
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.TestimonialModel;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class CategoryRepository {

    private static CategoryRepository instance;
    private MutableLiveData<List<CategoryModel>> dataSet;
    private static final String TAG = "Category Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("categories");
    DatabaseReference myRefTestimonials = database.getReference("testimonials");

    public static CategoryRepository getInstance(){
        if(instance == null){
            instance = new CategoryRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    public void setCategoryItems() {
        dataSet = new MutableLiveData<>();
        setItems();
    }
    public LiveData<List<CategoryModel>> getLocalCategoryItems(){
        return   AppController.getDatabase()
                .categoryDao()
                .getAll();
    }

    public LiveData<List<CategoryModel>> getPopularCategoryItems(){
        return   AppController.getDatabase()
                .categoryDao()
                .getItemPopular();
    }


    public LiveData<List<CategoryModel>> getExperiencedCategoryItems(){
        return   AppController.getDatabase()
                .categoryDao()
                .getItemExperienced();
    }

    public LiveData<List<TestimonialModel>> getTestimonialItems(){
        return   AppController.getDatabase()
                .testimonialDao()
                .getAll();
    }




    private void setItems(){
        // Read from the database

        setYoutubeItems();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CategoryModel> list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    CategoryModel model = postSnapshot.getValue(CategoryModel.class);
                    list.add(model);
                }
                new Save(
                        list,
                        AppController.getDatabase().categoryDao()
                ).execute();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }

    private void setYoutubeItems(){
        // Read from the database
        myRefTestimonials.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<TestimonialModel> list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    TestimonialModel model = postSnapshot.getValue(TestimonialModel.class);
                    list.add(model);
                }
                new Save(
                        list,
                        AppController.getDatabase().testimonialDao()
                ).execute();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }
}











