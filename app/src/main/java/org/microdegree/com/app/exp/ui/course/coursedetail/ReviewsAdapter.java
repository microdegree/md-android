package org.microdegree.com.app.exp.ui.course.coursedetail;

import android.content.Context;
import android.content.res.Resources;
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


import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.Course.LearnerReview;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

private final List<LearnerReview> list;
private final Context context;
public ReviewsAdapter(List<LearnerReview> list, Context mContext) {
        this.list = list;
        this.context = mContext;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_layout, parent, false);
                return new ViewHolder(view);
                }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
                final LearnerReview data = list.get(position);

               holder.txt_title.setText(data.getReviewerName());
               holder.txt_subtitle.setText(data.getReviewerDescription());
               
               holder.ratingBar.setRating(Float.parseFloat(data.getReviewStarRating()));

            Glide.with(context)
                    .load(data.getReviewerImgUrl())
                    .centerCrop()
                    .error(R.drawable.def_profile)
                    .into(holder.imageView);
            }
        
        @Override
        public int getItemCount() {
                return list.size();
                }
            
        public static class ViewHolder extends RecyclerView.ViewHolder{
        
            private final ImageView imageView;
            private final TextView txt_title;
            private final TextView txt_subtitle;
            private final RelativeLayout parentRelative;
            private final RatingBar ratingBar;

            public ViewHolder(View itemView) {
                super(itemView);
                int  width= Resources.getSystem().getDisplayMetrics().widthPixels;
                parentRelative = itemView.findViewById(R.id.parentRelative);
                imageView = itemView.findViewById(R.id.image);
                txt_title = itemView.findViewById(R.id.txt_title);
                txt_subtitle = itemView.findViewById(R.id.txt_subtitle);
                ratingBar = itemView.findViewById(R.id.ratingBar);

                parentRelative.getLayoutParams().width = (int)(width*0.8);
            }
        }
}
