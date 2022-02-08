package org.microdegree.com.app.exp.data.local.room;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Dao;
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
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.data.model.NotificationModel;
import org.microdegree.com.app.exp.data.model.TestimonialModel;
import org.microdegree.com.app.exp.impl.AppController;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Save extends AsyncTask<Void, Void, Void> {
     List list;
     BannerDao bannerDao;
     NotificationModel model;
     StoryDao storyDao;
     CategoryDao categoryDao;
     CourseDao courseDao;
     NotificationDao notificationDao;
     ChapterDao chapterDao;
     TopicDao topicDao;
     HandsonDao handsonDao;
     ObjectiveDao objectiveDao;
     TestimonialDao testimonialDao;

     public Save(List list, TestimonialDao testimonialDao){
         this.list=list;
         this.testimonialDao=testimonialDao;
     }

    public Save(List list, BannerDao bannerDao){
        this.list=list;
        this.bannerDao=bannerDao;
    }

     public Save(List list, CategoryDao categoryDao){
         this.list=list;
         this.categoryDao=categoryDao;
     }

     public  Save(List list, StoryDao storyDao){
         this.list=list;
         this.storyDao=storyDao;
     }

     public Save(List list, CourseDao courseDao){
         this.list=list;
         this.courseDao=courseDao;
     }

     public Save(NotificationModel model, NotificationDao notificationDao){
         this.model=model;
         this.notificationDao=notificationDao;
     }

     public Save(List list, ChapterDao chapterDao){
         this.list=list;
         this.chapterDao=chapterDao;
     }

     public Save(List list, TopicDao topicDao){
         this.list=list;
         this.topicDao=topicDao;
     }

     public Save(List list, HandsonDao handsonDao){
         this.list=list;
         this.handsonDao=handsonDao;
     }

     public Save(List list, ObjectiveDao objectiveDao){
         this.list=list;
         this.objectiveDao=objectiveDao;
     }


     @Override
    protected Void doInBackground(Void... voids) {

        //adding to database
        if (bannerDao!=null){
            bannerDao.insert(list);
        }else if (storyDao!=null){
            storyDao.insert(list);
        }else if (categoryDao!=null){
            categoryDao.insert(list);
        }else if (courseDao!=null){
            courseDao.insert(list);
        }else if (notificationDao!=null){
            notificationDao.insert(model);
        } else if (chapterDao!=null){
            chapterDao.insert(list);
        }else if (objectiveDao!=null){
            objectiveDao.insert(list);
        }else if (topicDao!=null){
            topicDao.insert(list);
        } else if (handsonDao!=null){
            handsonDao.insert(list);
        } else if (testimonialDao!=null){
            testimonialDao.insert(list);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}