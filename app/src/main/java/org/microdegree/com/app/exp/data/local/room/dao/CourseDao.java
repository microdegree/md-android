package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM courseModel WHERE isActive = 1  ORDER BY courseWeightage DESC")
    LiveData<List<CourseModel>> getAll();

    @Query("SELECT * from coursemodel where courseId=:id")
    LiveData<List<CourseModel>> getItem(String id);

    @Query("SELECT * from coursemodel where categoryId=:catID AND  isActive = 1  ORDER BY courseWeightage DESC")
    LiveData<List<CourseModel>> getCatCourse(String catID);

    @Query("SELECT * FROM coursemodel WHERE isSelf='1' AND isActive = 1  ORDER BY courseWeightage DESC")
    LiveData<List<CourseModel>> getSelfCourse();

    @Query("SELECT * FROM coursemodel WHERE isLive='1' AND  isActive = 1  ORDER BY courseWeightage DESC")
    LiveData<List<CourseModel>> getLiveCourse();

    @Query("SELECT * FROM coursemodel WHERE isFree='1' AND  isActive = 1  ORDER BY courseWeightage DESC")
    LiveData<List<CourseModel>> getFreeCourse();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CourseModel> model);

    @Delete
    void deleteModel(CourseModel model);

    @Query("DELETE FROM courseModel")
    void delete();

    @Update
    void update(CourseModel model);
}
