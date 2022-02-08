package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.Course.HandsonModel;

import java.util.List;

@Dao
public interface HandsonDao {
    @Query("SELECT * FROM HandsonModel ")
    LiveData<List<HandsonModel>> getAll();

    @Query("SELECT * from HandsonModel where curriculumChapterId=:id")
    LiveData<List<HandsonModel>> getItem(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<HandsonModel> model);

    @Delete
    void deleteModel(HandsonModel model);

    @Query("DELETE FROM HandsonModel")
    void delete();

    @Update
    void update(HandsonModel model);
}
