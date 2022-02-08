package org.microdegree.com.app.exp.data.repo;

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
import org.microdegree.com.app.exp.data.model.NotificationModel;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class NotificationsRepository {

    private static NotificationsRepository instance;
    private MutableLiveData<List<NotificationModel>> dataSet;
    // Write a message to the database
    private static final String TAG = "Notifications Database";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("notifications");


    public static NotificationsRepository getInstance(){
        if(instance == null){
            instance = new NotificationsRepository();
        }
        return instance;
    }

    public void setNotificationItems() {
//        dataSet = new MutableLiveData<>();
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                List<NotificationModel> list = new ArrayList<>();
//
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    NotificationModel model = postSnapshot.getValue(NotificationModel.class);
//                    list.add(model);
//                }
//                new Save(
//                        list,
//                        AppController.getDatabase().notificationDao()
//                ).execute();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//
//        });
    }


    public void setNotification(NotificationModel model) {
        new Save(
                model,
                AppController.getDatabase().notificationDao()
        ).execute();
    }
    public LiveData<List<NotificationModel>> getLocalNotificationItems(){
        return   AppController.getDatabase()
                .notificationDao()
                .getAll();
    }

}











