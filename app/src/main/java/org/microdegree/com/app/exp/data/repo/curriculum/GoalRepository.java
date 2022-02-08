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
import org.microdegree.com.app.exp.data.model.Course.CurriculumGoal;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class GoalRepository {

    private static GoalRepository instance;
    private MutableLiveData<List<CurriculumGoal>> dataSet;
    // Write a message to the database
    private static final String TAG = "Curriculum Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("curriculumGoals");


    public static GoalRepository getInstance(){
        if(instance == null){
            instance = new GoalRepository();
        }
        return instance;
    }
    private void seItems(){
     //   dataSet.clear();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CurriculumGoal> list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    CurriculumGoal model = postSnapshot.getValue(CurriculumGoal.class);

                        list.add(model);

                }
                dataSet.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }

}











