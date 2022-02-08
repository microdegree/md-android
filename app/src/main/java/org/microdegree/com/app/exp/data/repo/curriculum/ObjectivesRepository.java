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
import org.microdegree.com.app.exp.data.model.Course.ObjectiveModel;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class ObjectivesRepository {

    private static ObjectivesRepository instance;
    private MutableLiveData<List<ObjectiveModel>> dataSet;
    // Write a message to the database
    private static final String TAG = "Curriculum Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("curriculumObjectives");


    public static ObjectivesRepository getInstance(){
        if(instance == null){
            instance = new ObjectivesRepository();
        }
        return instance;
    }
    public void setObjectiveItems() {
        dataSet = new MutableLiveData<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ObjectiveModel> list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ObjectiveModel model = postSnapshot.getValue(ObjectiveModel.class);
                    list.add(model);
                }
                new Save(
                        list,
                        AppController.getDatabase().objectiveDao()
                ).execute();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
    }

    public LiveData<List<ObjectiveModel>> getLocalObjectiveItems(String id){

        return AppController.getDatabase()
                .objectiveDao()
                .getItem(id);
    }



}











