package org.microdegree.com.app.exp.data.repo.overview;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.microdegree.com.app.exp.data.model.Course.CourseHighlight;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class HighlightsRepository {

    private static HighlightsRepository instance;
    private MutableLiveData<List<CourseHighlight>> dataSet;
    // Write a message to the database
    private static final String TAG = "Highlights Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("courseHighlights");


    public static HighlightsRepository getInstance(){
        if(instance == null){
            instance = new HighlightsRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    public MutableLiveData<List<CourseHighlight>> getHighLightItems(String id){

        dataSet = new MutableLiveData<>();
        setItems(id);
        return dataSet;
    }

    private void setItems(String id){
        //   dataSet.clear();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CourseHighlight> list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    CourseHighlight model = postSnapshot.getValue(CourseHighlight.class);
                    if(id.equals(model.getCourseId())){
                        list.add(model);
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











