package org.microdegree.com.app.exp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.microdegree.com.app.exp.utils.Constants;

import java.util.List;

public class MySharedPreference {
    public static SharedPreferences getPreference(Context con) {
        SharedPreferences pref = con.getSharedPreferences(Constants.SESSION_ID, 0);
        return pref;
    }

    public static SharedPreferences.Editor getEditor(SharedPreferences pref) {
        SharedPreferences.Editor edit = pref.edit();
        return edit;
    }

    public static void commit(SharedPreferences.Editor edit) {
        edit.commit();
    }

    public  static boolean getFirstTime(Context con){
        SharedPreferences pref = getPreference(con);
        return pref.getBoolean(Constants.PREF_FIRSTTIME,true);
    }

    public static  void setFirstTime(boolean flag, Context con){
        SharedPreferences pref = getPreference(con);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean(Constants.PREF_FIRSTTIME, flag);
        edit.commit();
    }

    public static  void setStoryView(List<String> data, Context con){
        Gson gson = new Gson();
        // convert your list to json
        String value = gson.toJson(data);
        SharedPreferences pref = getPreference(con);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(Constants.PREF_STORYVIEW, value);
        edit.commit();
    }


    public static String getStoryView(Context con){
        SharedPreferences pref = getPreference(con);
        return pref.getString(Constants.PREF_STORYVIEW,"");
    }

}

