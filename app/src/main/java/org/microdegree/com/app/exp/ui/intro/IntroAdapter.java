package org.microdegree.com.app.exp.ui.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import org.microdegree.com.app.exp.data.model.OnBoardingModel;
import org.microdegree.com.app.exp.R;

import java.util.ArrayList;

public class IntroAdapter extends PagerAdapter {

    private Context mContext;
    ArrayList<OnBoardingModel> onBoardItems=new ArrayList<>();


    public IntroAdapter(Context mContext, ArrayList<OnBoardingModel> items) {
        this.mContext = mContext;
        this.onBoardItems = items;
    }

    @Override
    public int getCount() {
        return onBoardItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.slides_layout, container, false);

        OnBoardingModel item=onBoardItems.get(position);

        ImageView imageView =itemView.findViewById(R.id.image);
        imageView.setImageResource(item.getImage_url());

        TextView tv_title=itemView.findViewById(R.id.txt_title);
        tv_title.setText(item.getTitle());

        TextView tv_content=itemView.findViewById(R.id.txt_dec);
        tv_content.setText(item.getDescription());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

