package org.microdegree.com.app.exp.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.Course.LearnerReview;
import org.microdegree.com.app.exp.data.model.TestimonialModel;
import org.microdegree.com.app.exp.ui.course.CourseActivity;

import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.ViewHolder> {

private final List<TestimonialModel> list;
private final Context context;
private Activity activity;
public YoutubeAdapter(List<TestimonialModel> list, Context mContext) {
        this.list = list;
        this.context = mContext;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image, parent, false);
                return new ViewHolder(view);
                }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
                final TestimonialModel data = list.get(position);

                try {

                    String[] dataModel = data.getYoutubeLink().split(".be/");
                    String videoId = dataModel[1];
                    String url = "https://img.youtube.com/vi/" + videoId + "/0.jpg";
                    Glide.with(context)
                            .load(url)
                            .fitCenter()
                            .error(R.drawable.def_course)
                            .centerCrop()
                            .into(holder.imageView);

                    holder.parentRelative.setOnClickListener(view -> {
                        Intent intent = new Intent(context, YoutubeActivity.class);
                        intent.putExtra("data", videoId);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    });

                }catch (Exception e){
                    FirebaseCrashlytics.getInstance().recordException(e);
                }



            }

        @Override
        public int getItemCount() {
                return list.size();
                }
            
        public static class ViewHolder extends RecyclerView.ViewHolder{

            private final RelativeLayout parentRelative;
            private final ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                int  width= Resources.getSystem().getDisplayMetrics().widthPixels;
                parentRelative = itemView.findViewById(R.id.parentRelative);
                imageView = itemView.findViewById(R.id.imageView);
               parentRelative.getLayoutParams().width = (int)(width*0.8);
            }
        }
}
