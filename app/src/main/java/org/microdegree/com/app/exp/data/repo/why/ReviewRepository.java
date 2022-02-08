package org.microdegree.com.app.exp.data.repo.why;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.microdegree.com.app.exp.data.model.Course.LearnerReview ;
import org.microdegree.com.app.exp.data.model.Course.LearnerReview;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class ReviewRepository {

    private static ReviewRepository instance;
    private MutableLiveData<List<LearnerReview>> dataSet;
    // Write a message to the database
    private static final String TAG = "Why Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("learnerReviews");


    public static ReviewRepository getInstance(){
        if(instance == null){
            instance = new ReviewRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    public MutableLiveData<List<LearnerReview >> getReviewItems(String id){

        dataSet = new MutableLiveData<>();
        setItems();
        return dataSet;
    }

    private void setItems(){
        //   dataSet.clear();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<LearnerReview > list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    LearnerReview  model = postSnapshot.getValue(LearnerReview .class);
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











