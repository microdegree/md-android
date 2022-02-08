package org.microdegree.com.app.exp.ui.course.coursedetail;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.Course.CourseCurriculum;
import org.microdegree.com.app.exp.data.model.Course.CourseModel;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class CurriculumAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<CourseCurriculum>> expandableListDetail;

    public CurriculumAdapter(Context context, List<String> expandableListTitle,
                             HashMap<String, List<CourseCurriculum>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final CourseCurriculum courseCurriculum = (CourseCurriculum) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list__curriculum_item, null);
        }

        TextView txt_goal = (TextView) convertView.findViewById(R.id.txt_goal);
        if(courseCurriculum.getGoal()==null){
            txt_goal.setVisibility(View.GONE);
        }else{
            txt_goal.setText("Goal: "+courseCurriculum.getGoal());
        }

        //Objectives Setup
        LinearLayout baseLinear = (LinearLayout) convertView.findViewById(R.id.layoutObj);
        LinearLayout baseLinearTopic = (LinearLayout) convertView.findViewById(R.id.layoutTopic);
        LinearLayout baseLinearHandsOn= (LinearLayout) convertView.findViewById(R.id.layoutHandson);

        TextView txtObj =convertView.findViewById(R.id.txt_objective);
        TextView txtTop =  convertView.findViewById(R.id.txt_topic);
        TextView txtHandson= convertView.findViewById(R.id.txt_handson);


        baseLinear.removeAllViews();
        baseLinearTopic.removeAllViews();
        baseLinearHandsOn.removeAllViews();

        setViews(baseLinear,courseCurriculum.getCurriculumObjectives(),txtObj);
        setViews(baseLinearTopic,courseCurriculum.getCurriculumTopics(),txtTop);
        setViews(baseLinearHandsOn,courseCurriculum.getCurriculumHandson(),txtHandson);

        return convertView;
    }


    private void setViews(LinearLayout base, List data,TextView textView) {

        LinearLayout lv = new LinearLayout(context);
        lv.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 10);
        lv.setPadding(0,20,0,20);
        lv.setLayoutParams(params);

        int count=0;
        for(int i=0;i<data.size();i++)
        {
            LinearLayout innerlV = new LinearLayout(context);
            innerlV.setOrientation(LinearLayout.HORIZONTAL);
            innerlV.setGravity(Gravity.TOP);


            ImageView circle = new ImageView(context);
            RelativeLayout .LayoutParams circleParams = new RelativeLayout .LayoutParams(
                    10,
                    10
            );

            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.circle);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, context.getResources().getColor(R.color.black));
            circle.setImageDrawable(wrappedDrawable);
            circle.setLayoutParams(circleParams);

            TextView tv = new TextView(context);
            tv.setTextColor(context.getResources().getColor(R.color.black));

            Gson gson = new Gson();
            // convert your list to json
            String jsonCartList = gson.toJson(data.get(i));
            tv.setTextSize(16);
            tv.setPadding(20,-13,0,5);
            try {
                JSONObject jsonObject = new JSONObject(jsonCartList);
                    String title =jsonObject.getString("title");
                    if(title.equals("")||title.equals("null")){

                    }else{

                        tv.setText(jsonObject.getString("title"));
                        innerlV.addView(circle);
                        innerlV.addView(tv);
                        lv.addView(innerlV);
                        count++;
                    }

            }catch (JSONException err){
            }
        }
        if(count>0){
            base.addView(lv);
        }else{
            textView.setVisibility(View.GONE);
        }


    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group_curiculum, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        TextView preview = (TextView) convertView.findViewById(R.id.preview);

        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
