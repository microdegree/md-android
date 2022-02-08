package org.microdegree.com.app.exp.ui.course.coursedetail;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.microdegree.com.app.exp.data.model.Course.CourseModel;
import org.microdegree.com.app.exp.ui.course.coursedetail.fragments.Curriculum;
import org.microdegree.com.app.exp.ui.course.coursedetail.fragments.Overview;
import org.microdegree.com.app.exp.ui.course.coursedetail.fragments.Why;

public class CourseDetailAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    CourseModel mCourseModel;
    public CourseDetailAdapter(Context context, FragmentManager fm, int totalTabs,CourseModel mCourseModel) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.mCourseModel = mCourseModel;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Overview overview = new Overview(mCourseModel);
                return overview;
            case 1:
                Curriculum curriculum = new Curriculum(mCourseModel);
                return curriculum;
            case 2:
                Why why = new Why(mCourseModel);
                return why;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
