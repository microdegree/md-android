package org.microdegree.com.app.exp.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;


import org.microdegree.com.app.exp.R;

import java.io.File;

public class SetImageGlide {

    public static void SetImageGlideLocal(final ImageView imageView, final File path, Context context) {

        Glide.with(context)
                .load(path)
                .fitCenter()
                .error(R.drawable.loader)
                .centerCrop()
                .into(imageView);
    }

    public static void SetImageGlideLocalColored(final ImageView imageView, final File path, Context context) {

        Glide.with(context)
                .load(path)
                .fitCenter()
                .error(R.color.colorAccent)
                .centerCrop()
                .into(imageView);
    }

    public static void SetImageGlideError(final ImageView imageView, final File path, Context context) {
        try {
            Glide.with(context)
                    .load(path)
                    //.placeholder(R.drawable.md_sq_log)
                    .centerCrop()
                    .error(R.drawable.loader)
                    .fitCenter()
                    .into(imageView);
        }catch (Exception e){

        }

    }

}
