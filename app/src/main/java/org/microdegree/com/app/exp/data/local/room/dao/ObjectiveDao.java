package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.Course.ObjectiveModel;

import java.util.List;

@Dao
public interface ObjectiveDao {
    @Query("SELECT * FROM objectiveModel ")
    LiveData<List<ObjectiveModel>> getAll();

    @Query("SELECT * from objectiveModel where curriculumChapterId=:id")
    LiveData<List<ObjectiveModel>> getItem(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ObjectiveModel> model);

    @Delete
    void deleteModel(ObjectiveModel model);

    @Query("DELETE FROM objectiveModel")
    void delete();

    @Update
    void update(ObjectiveModel model);
}
