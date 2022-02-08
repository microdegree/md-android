package org.microdegree.com.app.exp.ui.home.banner;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
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
import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.BannerModel;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

private final List<BannerModel> list;
private final Context context;
private int width=0;
private int height=0;
private final BannerListener mListener;

public BannerAdapter(List<BannerModel> list, Context mContext, BannerListener mListener) {
        this.list = list;
        this.context = mContext;
        this.mListener = mListener;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_layout, parent, false);
                width=Resources.getSystem().getDisplayMetrics().widthPixels;
                height=Resources.getSystem().getDisplayMetrics().heightPixels;
                return new ViewHolder(view);
                }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
                final BannerModel data = list.get(position);

                holder.txt_title.setText(data.getBannerCampaignName());


            if(data.getButtonName()!=null && !data.getButtonName().isEmpty()){
                holder.txt_subtitle.setVisibility(View.VISIBLE);
                holder.txt_subtitle.setText(data.getButtonName());
            }else {
                holder.txt_subtitle.setVisibility(View.GONE);
            }
            Glide.with(context)
                    .load(data.getBannerImageUrl())
                    .fitCenter()
                    .error(R.drawable.def_banner)
                    .centerCrop()
                    .into(holder.imageView);
            holder.parentRelative.setOnClickListener(view -> mListener.getData(data));
        }
        
        @Override
        public int getItemCount() {
                return list.size();
                }

        public class ViewHolder extends RecyclerView.ViewHolder{
        
            private final ImageView imageView;
            private final TextView txt_title;
            private final TextView txt_subtitle;
            private final RelativeLayout parentRelative;


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public ViewHolder(View itemView) {
                super(itemView);
        
                imageView = itemView.findViewById(R.id.image);
                parentRelative = itemView.findViewById(R.id.parentRelative);
                txt_title = itemView.findViewById(R.id.txt_title);
                txt_subtitle = itemView.findViewById(R.id.txt_subtitle);
                imageView.setClipToOutline(true);
                parentRelative.getLayoutParams().width = (int)(width*0.8);
                parentRelative.getLayoutParams().height = (int)(height*0.25);
            }
        }

}
