package org.microdegree.com.app.exp.ui.notification;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
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
import org.microdegree.com.app.exp.data.model.NotificationModel;
import org.microdegree.com.app.exp.ui.course.coursedetail.CourseDetailActivity;
import org.microdegree.com.app.exp.utils.CircleImageView;
import org.microdegree.com.app.exp.utils.Util;


import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

private final List<NotificationModel> list;
private final Context context;
public NotificationAdapter(List<NotificationModel> list, Context mContext) {
        this.list = list;
        this.context = mContext;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_layout, parent, false);
                return new ViewHolder(view);
                }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
                final NotificationModel data = list.get(position);

               holder.txt_title.setText(data.getNotificationTitle());
               holder.txt_desc.setText(data.getNotificationBody());
               holder.ago.setText(Util.CovertTimeToText(data.getCreatedTimestamp()));
            Glide.with(context)
                    .load(data.getNotificationImgUrl())
                    .fitCenter()
                    .error(R.drawable.loader)
                    .centerCrop()
                    .into(holder.imageView);
            }
        
        @Override
        public int getItemCount() {
                return list.size();
                }
            
        public static class ViewHolder extends RecyclerView.ViewHolder{
        
            private final CircleImageView icon_img;
            private final ImageView imageView;
            private final TextView txt_title;
            private final TextView txt_desc;
            private final TextView ago;
            private final RelativeLayout parentRelative;
            private final LinearLayout base_lay;


            public ViewHolder(View itemView) {
                super(itemView);

                parentRelative = itemView.findViewById(R.id.parentRelative);
                icon_img = itemView.findViewById(R.id.icon_img);
                base_lay = itemView.findViewById(R.id.base_lay);
                imageView = itemView.findViewById(R.id.image);
                txt_title = itemView.findViewById(R.id.txt_title);
                txt_desc = itemView.findViewById(R.id.txt_desc);
                ago = itemView.findViewById(R.id.ago);

            }
        }
}
