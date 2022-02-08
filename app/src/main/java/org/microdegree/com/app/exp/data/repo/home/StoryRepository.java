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

import org.microdegree.com.app.exp.data.local.room.AppDatabase;
import org.microdegree.com.app.exp.data.local.room.Save;
import org.microdegree.com.app.exp.data.model.BannerModel;
import org.microdegree.com.app.exp.data.model.StoryModel;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Singleton pattern
 */
public class StoryRepository {

    private static StoryRepository instance;
    private MutableLiveData<List<StoryModel>> dataSet;
    // Write a message to the database
    private static final String TAG = "Stories Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("stories");

    public static StoryRepository getInstance(){
        if(instance == null){
            instance = new StoryRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    // Pretend to get data from a webservice or online source
    public void setStoryItems() {
        dataSet = new MutableLiveData<>();
        setItems();
    }
    public LiveData<List<StoryModel>> getLocalStoryItems(){
        return   AppController.getDatabase()
                .storyDao()
                .getAll();
    }
    private void setItems(){
        //dataSet.clear();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<StoryModel> list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    StoryModel model = postSnapshot.getValue(StoryModel.class);
                    list.add(model);
                }
                new Save(
                        list,
                        AppController.getDatabase().storyDao()
                ).execute();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }

}











