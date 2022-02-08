package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.TestimonialModel;

import java.util.List;

@Dao
public interface TestimonialDao {
    @Query("SELECT * FROM testimonialmodel ")
    LiveData<List<TestimonialModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<TestimonialModel> model);

    @Delete
    void deleteModel(TestimonialModel model);

    @Query("DELETE FROM testimonialmodel")
    void delete();

    @Update
    void update(CategoryModel model);
}


