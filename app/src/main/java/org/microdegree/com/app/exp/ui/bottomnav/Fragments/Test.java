package org.microdegree.com.app.exp.ui.bottomnav.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.ui.bottomnav.BackListener;
import org.microdegree.com.app.exp.utils.MicroFunctions;

public class Test extends Fragment {

    private  View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_test, container, false);
        return view;


    }
}
