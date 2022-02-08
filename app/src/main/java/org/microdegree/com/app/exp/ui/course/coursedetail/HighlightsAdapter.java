package org.microdegree.com.app.exp.ui.course.coursedetail;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.Course.CourseHighlight;

import java.util.List;


public class HighlightsAdapter extends RecyclerView.Adapter<HighlightsAdapter.ViewHolder> {

private final List<CourseHighlight> list;
private final Context context;
public HighlightsAdapter(List<CourseHighlight> list, Context mContext) {
        this.list = list;
        this.context = mContext;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.highlight_layout, parent, false);
                return new ViewHolder(view);
                }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
                final CourseHighlight data = list.get(position);

               holder.txt_title.setText(data.getTitle());
               holder.txt_subtitle.setText(data.getOverview());

            Glide.with(context)
                    .load(data.getChIconUrl())
                    .error(R.drawable.def_course)
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

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image);
                imageView.setClipToOutline(true);
                txt_title = itemView.findViewById(R.id.txt_title);
                txt_subtitle = itemView.findViewById(R.id.txt_subtitle);
            }
        }
}
