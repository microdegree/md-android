package org.microdegree.com.app.exp.ui.home.category;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.ui.course.CourseActivity;

import java.util.List;

public class CategoryTypeAdapter extends RecyclerView.Adapter<CategoryTypeAdapter.ViewHolder> {

private final List<CategoryModel> list;
private final Context context;
private final String header;
private int width=0;
private final int limit;
private final boolean isSearchView;

public CategoryTypeAdapter(List<CategoryModel> list, Context mContext,String header,boolean isSearchView,int limit) {
        this.list = list;
        this.context = mContext;
        this.header = header;
        this.isSearchView = isSearchView;
        this.limit = limit;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                  View view;
                  if(isSearchView){
                      view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_search_layout, parent, false);

                  }else  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);

                width=Resources.getSystem().getDisplayMetrics().widthPixels;
                return new ViewHolder(view,isSearchView);
        }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
                final CategoryModel data = list.get(position);

                holder.txt_title.setText(data.getCategoryName());
                Glide.with(context)
                        .load(data.getCategoryImgUrl())
                        .error(R.drawable.loader)
                        .centerCrop()
                        .into(holder.imageView);

                holder.parentRelative.setOnClickListener(view -> {
                Gson gson = new Gson();
                String jsonCartList = gson.toJson(data);
                            Intent intent = new Intent(context, CourseActivity.class);
                            intent.putExtra("data",jsonCartList);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("type",header);
                            context.startActivity(intent);
                        });
            }
        
        @Override
        public int getItemCount() {
            if(limit==0){
                return list.size();
            }else{
                return Math.min(list.size(), limit);
            }

        }
            
        public class ViewHolder extends RecyclerView.ViewHolder{
        
            private final ImageView imageView;
            private final TextView txt_title;
            private final RelativeLayout parentRelative;


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public ViewHolder(View itemView, boolean isSearchView) {
                super(itemView);
        
                imageView = itemView.findViewById(R.id.image);
                parentRelative = itemView.findViewById(R.id.parentRelative);
                txt_title = itemView.findViewById(R.id.txt_title);
                imageView.setClipToOutline(true);

                if(!isSearchView){
                    parentRelative.getLayoutParams().width = (int)(width*0.25);
                    parentRelative.getLayoutParams().height = (int)(width*0.3);
                    txt_title.getLayoutParams().width = (int)(width*0.25);
                    imageView.getLayoutParams().width = (int)(width*0.2);
                    imageView.getLayoutParams().height = (int)(width*0.2);
                }else{
                    ImageView view = itemView.findViewById(R.id.view);
                    view.setClipToOutline(true);
                }


            }
        }
}
