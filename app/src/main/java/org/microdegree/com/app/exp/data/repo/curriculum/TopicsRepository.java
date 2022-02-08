package org.microdegree.com.app.exp.data.repo.curriculum;

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
import org.microdegree.com.app.exp.data.model.Course.ChapterModel;
import org.microdegree.com.app.exp.data.model.Course.TopicModel;
import org.microdegree.com.app.exp.data.model.Course.ObjectiveModel;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class TopicsRepository {

    private static TopicsRepository instance;
    private MutableLiveData<List<TopicModel>> dataSet;
    // Write a message to the database
    private static final String TAG = "Curriculum Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("curriculumTopics");


    public static TopicsRepository getInstance(){
        if(instance == null){
            instance = new TopicsRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    public void setTopicItems() {
        dataSet = new MutableLiveData<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<TopicModel> list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    TopicModel model = postSnapshot.getValue(TopicModel.class);
                    list.add(model);
                }
                new Save(
                        list,
                        AppController.getDatabase().topicDao()
                ).execute();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
    }

    public LiveData<List<TopicModel>> getLocalTopicItems(String id){

        return AppController.getDatabase()
                .topicDao()
                .getItem(id);
    }


}











