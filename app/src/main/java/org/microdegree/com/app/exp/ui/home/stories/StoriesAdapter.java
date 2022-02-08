package org.microdegree.com.app.exp.ui.home.stories;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.StoryModel;
import org.microdegree.com.app.exp.utils.Util;

import java.util.List;

import static org.microdegree.com.app.exp.utils.SetImageGlide.SetImageGlideError;
import static org.microdegree.com.app.exp.utils.SetImageGlide.SetImageGlideLocal;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {

private List<StoryModel> list;
private Context context;
private StoryListener listner;
public StoriesAdapter(List<StoryModel> list, Context mContext,StoryListener mListener) {
        this.list = list;
        this.context = mContext;
        this.listner = mListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_layout, parent, false);
                return new ViewHolder(view);
                }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
                final StoryModel data = list.get(position);
            Glide.with(context)
                    .load(data.getStoryImageUrl())
                    .fitCenter()
                    .error(R.drawable.loader)
                    .centerCrop()
                    .into(holder.imageView);
            holder.textView.setText(Util.Capitalize(data.getStoryType()));

            holder.imageView.setOnClickListener(view -> listner.setStory(holder.getAdapterPosition()));
                    }
        
        @Override
        public int getItemCount() {
                return list.size();
        }
            
        public static class ViewHolder extends RecyclerView.ViewHolder{
        
            private ImageView imageView;
            private TextView textView;


            public ViewHolder(View itemView) {
                super(itemView);
        
                imageView = itemView.findViewById(R.id.image);
                textView = itemView.findViewById(R.id.text);

                textView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            }
        }
}
