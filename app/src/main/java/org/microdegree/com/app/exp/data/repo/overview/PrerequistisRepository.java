package org.microdegree.com.app.exp.data.repo.overview;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.microdegree.com.app.exp.data.model.Course.CoursePreRequisitis;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class PrerequistisRepository {

    private static PrerequistisRepository instance;
    private MutableLiveData<List<String>> dataSet;
    // Write a message to the database
    private static final String TAG = "Prerequistis Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("coursePrerequistiesPoints");


    public static PrerequistisRepository getInstance(){
        if(instance == null){
            instance = new PrerequistisRepository();
        }
        return instance;
    }



    // Pretend to get data from a webservice or online source
    public MutableLiveData<List<String>> getPreRequisitisItems(String id){

        dataSet = new MutableLiveData<>();
        seItems(id);
        return dataSet;
    }

    private void seItems(String id){
        //   dataSet.clear();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    CoursePreRequisitis model = postSnapshot.getValue(CoursePreRequisitis.class);
                    if(model.getCourseId().equals(id)){
                        list.add(model.getPrerequisite());
                    }
                }
                dataSet.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }
}











