package org.microdegree.com.app.exp.utils;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.lifecycle.LifecycleOwner;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;
import com.razorpay.Checkout;

import org.json.JSONObject;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.impl.AppController;
import org.microdegree.com.app.exp.ui.course.coursedetail.CourseDetailActivity;

public class MicroFunctions {

    public  void startPayment(CourseModel mCourseModel, Activity context) {

        String str = mCourseModel.getCourseDiscountedPrice().replaceAll("[^\\d.]", "");

        /**
         * You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = context;
        final Checkout co = new Checkout();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "MicroDegree");
            options.put("description", mCourseModel.getCourseTitle());
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://cdn.razorpay.com/logos/FHAxf00oIh35AW_large.png");
            options.put("currency", "INR");
            //   String payment = orderamount.getText().toString();
            // amount is in paise so please multiple it by 100
            //Payment failed Invalid amount (should be passed in integer paise. Minimum value is 100 paise, i.e. ₹ 1)
            double total = Double.parseDouble( str+"");
            total = total * 100;
            options.put("amount", total);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "");
            preFill.put("contact", "");
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }

    public void logEvent(CourseModel mCourseModel, Activity context){

    }

    public void launchWeb(String url, Context context){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }

    public void fromCourseID(String courseId, Context activity,LifecycleOwner lifecycleOwner) {
        AppController.getCourseViewModel().getCourse(courseId).observe(lifecycleOwner, items -> {
             if(items.size()>0){
                Gson gson = new Gson();
                // convert your list to json
                String jsonCartList = gson.toJson(items.get(0));

                Intent intent = new Intent(activity, CourseDetailActivity.class);
                intent.putExtra("data",jsonCartList);
                activity.startActivity(intent);
            }

        });
    }
}
