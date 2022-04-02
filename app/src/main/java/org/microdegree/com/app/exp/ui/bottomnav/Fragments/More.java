package org.microdegree.com.app.exp.ui.bottomnav.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.ui.notification.NotificationActivity;

public class More extends Fragment {
    public More() {
        // Required empty public constructor
    }
    private  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_more, container, false);


        LinearLayout enquiry = view.findViewById(R.id.enquiry);

        enquiry.setOnClickListener(view -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel: 8310882795"));
            startActivity(callIntent);
        });

        return view;
    }
}
