package org.microdegree.com.app.exp.data.local.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.microdegree.com.app.exp.data.local.room.dao.BannerDao;
import org.microdegree.com.app.exp.data.local.room.dao.CategoryDao;
import org.microdegree.com.app.exp.data.local.room.dao.ChapterDao;
import org.microdegree.com.app.exp.data.local.room.dao.CourseDao;
import org.microdegree.com.app.exp.data.local.room.dao.HandsonDao;
import org.microdegree.com.app.exp.data.local.room.dao.NotificationDao;
import org.microdegree.com.app.exp.data.local.room.dao.ObjectiveDao;
import org.microdegree.com.app.exp.data.local.room.dao.StoryDao;
import org.microdegree.com.app.exp.data.local.room.dao.TestimonialDao;
import org.microdegree.com.app.exp.data.local.room.dao.TopicDao;
import org.microdegree.com.app.exp.data.model.BannerModel;
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.data.model.Course.ChapterModel;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.data.model.Course.HandsonModel;
import org.microdegree.com.app.exp.data.model.Course.ObjectiveModel;
import org.microdegree.com.app.exp.data.model.Course.TopicModel;
import org.microdegree.com.app.exp.data.model.NotificationModel;
import org.microdegree.com.app.exp.data.model.StoryModel;
import org.microdegree.com.app.exp.data.model.TestimonialModel;


@Database(entities = {BannerModel.class,
        StoryModel.class,
        CategoryModel.class,
        CourseModel.class,
        NotificationModel.class,
        ChapterModel.class,
        TopicModel.class,
        TestimonialModel.class,
        ObjectiveModel.class,
        HandsonModel.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BannerDao  bannerDao();

    public abstract StoryDao storyDao();

    public abstract CategoryDao categoryDao();

    public abstract CourseDao courseDao();

    public abstract NotificationDao notificationDao();

    public abstract ChapterDao chapterDao();

    public abstract TopicDao topicDao();

    public abstract TestimonialDao testimonialDao();

    public abstract HandsonDao handsonDao();

    public abstract ObjectiveDao objectiveDao();
}