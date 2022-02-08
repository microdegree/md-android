package org.microdegree.com.app.exp.ui.notification;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.microdegree.com.app.exp.data.model.NotificationModel;
import org.microdegree.com.app.exp.data.model.NotificationModel;
import org.microdegree.com.app.exp.data.repo.CourseRepository;
import org.microdegree.com.app.exp.data.repo.NotificationsRepository;

import java.util.List;

public class NotificationViewModel extends ViewModel {

    private NotificationsRepository mNotificationRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void initNotifications() {
        mNotificationRepo = NotificationsRepository.getInstance();
     //   mNotificationRepo.setNotificationItems();

    }


    public LiveData<List<NotificationModel>> getNotificationModels(){
        return mNotificationRepo.getLocalNotificationItems();
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
