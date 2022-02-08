package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.NotificationModel;

import java.util.List;

@Dao
public interface NotificationDao {
    @Query("SELECT * FROM notificationmodel  ORDER BY ago DESC")
    LiveData<List<NotificationModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NotificationModel model);

    @Delete
    void deleteModel(NotificationModel model);

    @Query("DELETE FROM notificationmodel")
    void delete();

    @Update
    void update(NotificationModel model);
}
