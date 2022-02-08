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
import org.microdegree.com.app.exp.impl.AppController;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class ChapterRepository {

    private static ChapterRepository instance;
    private MutableLiveData<List<ChapterModel>> dataSet;
    // Write a message to the database
    private static final String TAG = "Curriculum Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("curriculumChapters");

    public static ChapterRepository getInstance(){
        if(instance == null){
            instance = new ChapterRepository();
        }
        return instance;
    }

    public void setChapterItems() {
        dataSet = new MutableLiveData<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ChapterModel> list = new ArrayList<>();

                int count=1;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ChapterModel model = postSnapshot.getValue(ChapterModel.class);
                    model.setSortID(count);
                    list.add(model);
                    count++;
                }
                new Save(
                        list,
                        AppController.getDatabase().chapterDao()
                ).execute();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
              }

        });
    }

    public LiveData<List<ChapterModel>> getLocalChapterItems(String id){

        return AppController.getDatabase()
                .chapterDao()
                .getItem(id);
    }

}











