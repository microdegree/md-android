package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.Course.ChapterModel;

import java.util.List;

@Dao
public interface ChapterDao {
    @Query("SELECT * FROM chapterModel ")
    LiveData<List<ChapterModel>> getAll();

    @Query("SELECT * from chapterModel where courseId=:id")
    LiveData<List<ChapterModel>> getItem(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ChapterModel> model);

    @Delete
    void deleteModel(ChapterModel model);

    @Query("DELETE FROM chapterModel")
    void delete();

    @Update
    void update(ChapterModel model);
}
