package org.microdegree.com.app.exp.ui.intro;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroCustomLayoutFragment;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.model.SliderPage;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.local.MySharedPreference;
import org.microdegree.com.app.exp.ui.signin.SignInActivity;

public class AppIntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide( AppIntroCustomLayoutFragment.newInstance(R.layout.intro_layout_one));
        addSlide( AppIntroCustomLayoutFragment.newInstance(R.layout.intro_layout_two));
        addSlide( AppIntroCustomLayoutFragment.newInstance(R.layout.intro_layout_three));


        setIndicatorColor(getResources().getColor(R.color.crystal_blue),getResources().getColor(R.color.grey));
        setColorDoneText(getResources().getColor(R.color.black));
        setSkipButtonEnabled(false);
        setColorSkipButton(getResources().getColor(R.color.black));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        MySharedPreference.setFirstTime(false,getApplicationContext());
        Intent i = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        MySharedPreference.setFirstTime(false,getApplicationContext());
        Intent i = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(i);
        finish();

    }
}
