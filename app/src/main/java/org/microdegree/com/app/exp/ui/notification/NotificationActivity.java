package org.microdegree.com.app.exp.ui.notification;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.data.model.NotificationModel;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.course.CourseAdapter;
import org.microdegree.com.app.exp.ui.course.CourseViewModel;

import java.util.ArrayList;
import java.util.List;


public class NotificationActivity extends AppCompatActivity {
    private NotificationAdapter mNotificationAdapter;
    RecyclerView recyclerViewNotifications;//recycler view for Notifications
    List<NotificationModel> mNotificationList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(view -> finish());
        TextView no_items = findViewById(R.id.no_items);
        no_items.setVisibility(View.VISIBLE);
        AppController.getNotificationModelViewModel().getNotificationModels().observe(this, new Observer<List<NotificationModel>>() {
            @Override
            public void onChanged(@Nullable List<NotificationModel> items) {
                mNotificationList.clear();
                if(items.size()>0){
                    no_items.setVisibility(View.GONE);
                    recyclerViewNotifications.setVisibility(View.VISIBLE);
                    mNotificationList.addAll(items);
                    mNotificationAdapter = new NotificationAdapter(mNotificationList, getApplicationContext());
                    recyclerViewNotifications.setAdapter(mNotificationAdapter);
                }else{
                    no_items.setVisibility(View.VISIBLE);
                    recyclerViewNotifications.setVisibility(View.GONE);
                }
            }
        });
        initCourses();
    }
    private void initCourses() {
        recyclerViewNotifications = findViewById(R.id.recyclerViewNotifications);
        recyclerViewNotifications.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewNotifications.setLayoutManager(linearLayoutManager);
        recyclerViewNotifications.setHasFixedSize(true);
    }

}
