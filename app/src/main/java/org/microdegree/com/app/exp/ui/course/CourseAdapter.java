package org.microdegree.com.app.exp.ui.course;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;


import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.ui.course.coursedetail.CourseDetailActivity;

import java.util.List;

import static org.microdegree.com.app.exp.utils.SetImageGlide.SetImageGlideError;
import static org.microdegree.com.app.exp.utils.SetImageGlide.SetImageGlideLocal;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

private final List<CourseModel> list;
private final Context context;
private final boolean misHome;
private final String type;
private final int limit;
public CourseAdapter(List<CourseModel> list, Context mContext,boolean misHome,String type,int limit) {
        this.list = list;
        this.context = mContext;
        this.misHome = misHome;
        this.type = type;
        this.limit = limit;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_layout, parent, false);
                return new ViewHolder(view,misHome);
                }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            final CourseModel data = list.get(position);


            if (limit!=0) {

            if (position == limit - 1) {
                holder.parentRelative.getLayoutParams().width = 400;
                holder.parentRelative.getLayoutParams().height = 300;
                holder.parentRelative.setGravity(Gravity.CENTER);
                holder.txt_title.setText("See All");
                holder.txt_title.setGravity(Gravity.CENTER_HORIZONTAL);
                holder.txt_title.setTypeface(null, Typeface.BOLD);
                holder.txt_subtitle.setVisibility(View.GONE);
                holder.txt_session.setVisibility(View.GONE);
                holder.txt_price.setVisibility(View.GONE);
                holder.txt_dsc_price.setVisibility(View.GONE);
                holder.view.setVisibility(View.GONE);
                holder.enroll.setVisibility(View.GONE);
                holder.imageView.setVisibility(View.GONE);

                holder.parentRelative.setOnClickListener(view -> {
                    Gson gson = new Gson();
                    // convert your list to json
                    String jsonData = gson.toJson(list);
                    Intent intent = new Intent(context, CourseActivity.class);
                    intent.putExtra("data", jsonData);
                    intent.putExtra("type", type);
                    intent.putExtra("list", 222);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                });

            }
            else {
                holder.txt_title.setText(data.getCourseTitle());
                holder.txt_subtitle.setText(data.getCourseTiming() + " | " + data.getCourseDuration());
                holder.txt_session.setText(data.getCourseType());

                holder.txt_price.setText(data.getCourseOriginalPrice() + "");

                if(data.getCourseOriginalPrice()==null){
                    holder.txt_price.setVisibility(View.GONE);
                }


                holder.txt_dsc_price.setText(data.getCourseDiscountedPrice() + "");
                Glide.with(context)
                        .load(data.getCourseImageUrl())
                        .fitCenter()
                        .error(R.drawable.def_course)
                        .centerCrop()
                        .into(holder.imageView);

                holder.parentRelative.setOnClickListener(view -> {
                    Gson gson = new Gson();
                    // convert your list to json
                    String jsonData = gson.toJson(data);

                    Intent intent = new Intent(context, CourseDetailActivity.class);
                    intent.putExtra("data", jsonData);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                });
            }
        }else{

                holder.txt_title.setText(data.getCourseTitle());
                holder.txt_subtitle.setText(data.getCourseTiming() + " | " + data.getCourseDuration());
                holder.txt_session.setText(data.getCourseType());

                holder.txt_price.setText(data.getCourseOriginalPrice() + "");
                if(data.getCourseOriginalPrice()==null){
                    holder.txt_price.setVisibility(View.GONE);
                }




                holder.txt_dsc_price.setText(data.getCourseDiscountedPrice() + "");
                Glide.with(context)
                        .load(data.getCourseImageUrl())
                        .fitCenter()
                        .error(R.drawable.def_course)
                        .centerCrop()
                        .into(holder.imageView);

                holder.parentRelative.setOnClickListener(view -> {
                    Gson gson = new Gson();
                    // convert your list to json
                    String jsonData = gson.toJson(data);

                    Intent intent = new Intent(context, CourseDetailActivity.class);
                    intent.putExtra("data", jsonData);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                });
            }


        }
        
        @Override
        public int getItemCount() {
                if(limit==0){
                    return list.size();
                }else{
                    return Math.min(list.size(), limit);
                }

                }
            
        public static class ViewHolder extends RecyclerView.ViewHolder{
        
            private final ImageView imageView;
            private final TextView txt_title;
            private final TextView txt_subtitle;
            private final TextView txt_session;
            private final TextView txt_price;
            private final TextView txt_dsc_price;
            private final TextView enroll;
            private final View view;
            private final RelativeLayout parentRelative;
            private final LinearLayout base_lay;


            public ViewHolder(View itemView,boolean isHome) {
                super(itemView);

                parentRelative = itemView.findViewById(R.id.parentRelative);
                view = itemView.findViewById(R.id.view);
                enroll = itemView.findViewById(R.id.enroll);
                base_lay = itemView.findViewById(R.id.base_lay);
                imageView = itemView.findViewById(R.id.image);
                imageView.setClipToOutline(true);
                txt_title = itemView.findViewById(R.id.txt_title);
                txt_subtitle = itemView.findViewById(R.id.txt_timing);
                txt_session = itemView.findViewById(R.id.txt_session);
                txt_price = itemView.findViewById(R.id.txt_price);
                txt_price.setPaintFlags(txt_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txt_dsc_price = itemView.findViewById(R.id.txt_dsc_price);

                int height=Resources.getSystem().getDisplayMetrics().heightPixels;
                if(isHome){
                  int  width= Resources.getSystem().getDisplayMetrics().widthPixels;

                    parentRelative.getLayoutParams().width = (int)(width*0.8);

                }
            //     parentRelative.getLayoutParams().height = 600;

            }
        }
}
