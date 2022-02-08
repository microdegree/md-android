package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.StoryViewedModel;

import java.util.List;

@Dao
public interface StoryViewDao {
    @Query("SELECT * FROM StoryViewedModel")
    LiveData<List<StoryViewedModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(StoryViewedModel model);

    @Delete
    void deleteModel(StoryViewedModel model);

    @Query("DELETE FROM StoryViewedModel")
    void delete();

    @Update
    void update(StoryViewedModel model);
}
