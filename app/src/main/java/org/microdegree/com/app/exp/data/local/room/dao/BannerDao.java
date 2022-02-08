package org.microdegree.com.app.exp.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.microdegree.com.app.exp.data.model.BannerModel;

import java.util.List;

@Dao
public interface  BannerDao {
    @Query("SELECT * FROM BannerModel ")
    LiveData<List<BannerModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<BannerModel> model);

    @Delete
    void deleteModel(BannerModel model);

    @Query("DELETE FROM BannerModel")
    void delete();

    @Update
    void update(BannerModel model);
}
