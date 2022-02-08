package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.CategoryModel;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categoryModel ")
    LiveData<List<CategoryModel>> getAll();

    @Query("SELECT * FROM categoryModel WHERE isPopular='1'")
    LiveData<List<CategoryModel>> getItemPopular();

    @Query("SELECT * FROM categoryModel WHERE isExperienced='1'")
    LiveData<List<CategoryModel>> getItemExperienced();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CategoryModel> model);

    @Delete
    void deleteModel(CategoryModel model);

    @Query("DELETE FROM categoryModel")
    void delete();

    @Update
    void update(CategoryModel model);
}


