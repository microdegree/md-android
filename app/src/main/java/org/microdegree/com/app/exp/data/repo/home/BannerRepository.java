package org.microdegree.com.app.exp.data.repo.home;

import android.content.Context;
import android.os.AsyncTask;
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
import org.microdegree.com.app.exp.data.local.room.DatabaseClient;
import org.microdegree.com.app.exp.data.local.room.Save;
import org.microdegree.com.app.exp.data.local.room.dao.BannerDao;
import org.microdegree.com.app.exp.data.model.BannerModel;
import org.microdegree.com.app.exp.data.model.StoryModel;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class BannerRepository {

    private static BannerRepository instance;
    private  MutableLiveData<List<BannerModel>> dataSet;
    private static final String TAG = "Banner Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("adBanners");
    public static BannerRepository getInstance(){
        if(instance == null){
            instance = new BannerRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    public void setBannerItems() {
        dataSet = new MutableLiveData<>();
        setItems();
    }
    public LiveData<List<BannerModel>> getLocalBannerItems(){
        return   AppController.getDatabase()
                .bannerDao()
                .getAll();
    }

    private void setItems(){
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<BannerModel> list = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    BannerModel model = postSnapshot.getValue(BannerModel.class);
                    list.add(model);
                }

                new Save(
                        list,
                        AppController.getDatabase().bannerDao()
                ).execute();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }
}












