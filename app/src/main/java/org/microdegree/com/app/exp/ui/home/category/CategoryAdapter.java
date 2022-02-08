package org.microdegree.com.app.exp.ui.home.category;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;


import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.CategoryModel;
import org.microdegree.com.app.exp.ui.course.CourseActivity;

import java.util.List;

import static org.microdegree.com.app.exp.utils.SetImageGlide.SetImageGlideError;
import static org.microdegree.com.app.exp.utils.SetImageGlide.SetImageGlideLocal;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

private List<CategoryModel> list;
private Context context;
private Activity activity;
private int width=0;
private int height=0;
private String type="";


public CategoryAdapter(List<CategoryModel> list, Context mContext,String type) {
        this.list = list;
        this.context = mContext;
        this.type = type;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
                width=Resources.getSystem().getDisplayMetrics().widthPixels;
                height=Resources.getSystem().getDisplayMetrics().heightPixels;
                return new ViewHolder(view,type);
                }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
                final CategoryModel data = list.get(position);


                if(position==4){
                    holder.txt_title.setText("See All");
                    holder.txt_title.setTypeface(null, Typeface.BOLD);
                    holder.imageView.setVisibility(View.GONE);

                    holder.parentRelative.setOnClickListener(view -> {

                        Gson gson = new Gson();
                        // convert your list to json
                        String jsonCartList = gson.toJson(list);

                        Intent intent = new Intent(context, CategoryActivity.class);
                        intent.putExtra("data",jsonCartList);
                        intent.putExtra("type",type);
                        context.startActivity(intent);
                    });

                }else{
                    holder.txt_title.setText(data.getCategoryName());
                    Glide.with(context)
                            .load(data.getCategoryImgUrl())
                            .fitCenter()
                            .error(R.drawable.loader)
                            .centerCrop()
                            .into(holder.imageView);


                    holder.parentRelative.setOnClickListener(view -> {

                        Gson gson = new Gson();
                        // convert your list to json
                        String jsonCartList = gson.toJson(data);

                        Intent intent = new Intent(context, CourseActivity.class);
                        intent.putExtra("data",jsonCartList);
                        intent.putExtra("type",type);
                        context.startActivity(intent);
                    });
                }
            }
        
        @Override
        public int getItemCount() {
            return Math.min(list.size(), 5);
                }
            
        public class ViewHolder extends RecyclerView.ViewHolder{
        
            private ImageView imageView;
            private TextView txt_title;
            private RelativeLayout parentRelative;


            public ViewHolder(View itemView,String type) {
                super(itemView);

                parentRelative = itemView.findViewById(R.id.parentRelative);
                imageView = itemView.findViewById(R.id.image);
                txt_title = itemView.findViewById(R.id.txt_title);
                imageView.setClipToOutline(true);
                parentRelative.getLayoutParams().width = (int)(width*0.22);
                parentRelative.getLayoutParams().height = (int)(width*0.3);
                txt_title.getLayoutParams().width = (int)(width*0.25);

                if(type.equals("Category By Experience")){
                    parentRelative.getLayoutParams().width = (int)(width*0.38);
                    parentRelative.getLayoutParams().height = (int)(width*0.35);

                    imageView.getLayoutParams().width = (int)(width*0.35);
                    imageView.getLayoutParams().height = (int)(width*0.35);
                }else{
                    imageView.getLayoutParams().width = (int)(width*0.2);
                    imageView.getLayoutParams().height = (int)(width*0.2);
                }


            }
        }
}
