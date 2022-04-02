package org.microdegree.com.app.exp.ui.bottomnav.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.ui.bottomnav.BackListener;
import org.microdegree.com.app.exp.ui.home.stories.StoryListener;
import org.microdegree.com.app.exp.utils.MicroFunctions;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Course extends Fragment {

    private BackListener listner;
    public Course() {
        // Required empty public constructor
    }

    public Course(BackListener listner) {
      this.listner=listner;
    }

    private  View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_courses, container, false);
        WebView webView = view.findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url ="https://courses.microdegree.work/enrollments";
        new MicroFunctions().launchWeb(url,getContext());
        listner.getback();
        return view;


    }
}
