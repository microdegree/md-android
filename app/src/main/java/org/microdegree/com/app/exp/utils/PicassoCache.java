package org.microdegree.com.app.exp.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class PicassoCache {

   static Context mContext;
    public interface SavedToCache{
        void  onSavedToCache();
    }

    public static void SaveToCache(final String urll, final String id,Context context, final SavedToCache savedToCache) {
        mContext=context;
        new GetBitmapFromURLAsync().execute(urll, savedToCache,id,context);
    }

    private static class GetBitmapFromURLAsync extends AsyncTask<Object, Void, Bitmap> {
        String name;
        SavedToCache cache;
        Context context;


        @Override
        protected Bitmap doInBackground(Object... params) {
            name=(String)params[2];
            cache=(SavedToCache)params[1];

            return getBitmapFromURL((String)params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap image) {

            if(createDirectoryAndSaveFile(image,name + ".png")){
                cache.onSavedToCache();
            }

//            if(writeMediaStoreFile(context,image,name + ".png")){
//              cache.onSavedToCache();
//         }
        }


    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File path = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +"/.MD-V4/");
        } else
        {
            path = new File(Environment.getExternalStorageDirectory() + "/.MD-V4/");
        }
      //  File direct = new File(Environment.getExternalStorageDirectory() + "/.MD-V4");

        // Make sure the path directory exists.
        if (!path.exists())
        {
            // Make it, if it doesn't exit
            boolean success = path.mkdirs();
            if (!success)
            {
                path = null;
            }
        }
//
//        if (!path.exists()) {
//            File wallpaperDirectory = new File("/sdcard/.MD-V4/");
//            wallpaperDirectory.mkdirs();
//        }

        File file = new File(path, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }


    public static File  getFromFileCahce(String id)  {
        File path = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + ".MD-V4/" + id + ".png");
        } else
        {
            path = new File(Environment.getExternalStorageDirectory() + "/" + ".MD-V4/" + id + ".png");
        }
   //     File path = new File(Environment.getExternalStorageDirectory(),".MD-V4/" + id + ".png");
        return  path;

    }

    public  static  boolean ifFileExtists(String id){
        File path = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + ".MD-V4/" + id + ".png");
        } else
        {
            path = new File(Environment.getExternalStorageDirectory() + "/" + ".MD-V4/" + id + ".png");
        }

      //  File path = new File(Environment.getExternalStorageDirectory(),".MD-V4/" + id + ".png");
        return path.exists();
    }


}