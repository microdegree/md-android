package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.Course.TopicModel;

import java.util.List;

@Dao
public interface TopicDao {
    @Query("SELECT * FROM TopicModel ")
    LiveData<List<TopicModel>> getAll();

    @Query("SELECT * from TopicModel where curriculumChapterId=:id")
    LiveData<List<TopicModel>> getItem(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<TopicModel> model);

    @Delete
    void deleteModel(TopicModel model);

    @Query("DELETE FROM TopicModel")
    void delete();

    @Update
    void update(TopicModel model);
}
