package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.StoryModel;

import java.util.List;

@Dao
public interface StoryDao {
    @Query("SELECT * FROM storyModel  ORDER BY storyWeightage DESC")
    LiveData<List<StoryModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<StoryModel> model);

    @Delete
    void deleteModel(StoryModel model);

    @Query("DELETE FROM storyModel")
    void delete();

    @Update
    void update(StoryModel model);
}
