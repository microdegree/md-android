package org.microdegree.com.app.exp.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.microdegree.com.app.exp.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.microdegree.com.app.exp.databinding.ActivitySigninBinding;
import org.microdegree.com.app.exp.ui.bottomnav.BottomNavigation;
import org.microdegree.com.app.exp.ui.intro.IntroActivity;
import org.microdegree.com.app.exp.ui.signin.model.SignInFields;
import org.microdegree.com.app.exp.utils.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class SignInActivity extends  AppCompatActivity {
    private SignInViewModel viewModel;

    //our database reference object
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting the reference of node
        databaseReference = FirebaseDatabase.getInstance().getReference("firstTimeUserInfo");
        setupBindings(savedInstanceState);

        TextView skip=findViewById(R.id.skip);
        skip.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), BottomNavigation.class);
            startActivity(i);
            finish();
        });

    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivitySigninBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin);
        viewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        setupButtonClick();
    }

    private void setupButtonClick() {
        viewModel.getSignInFields().observe(this, new Observer<SignInFields>() {
            @Override
            public void onChanged(SignInFields signInModel) {

                //Saving the Students
                if(signInModel.isErrorFields()){
                    Toast.makeText(getApplicationContext(),"Please enter all data",
                            Toast.LENGTH_SHORT).show();

                }else{

                    Date currentTime = Calendar.getInstance().getTime();
                    String rand= random();
                    signInModel.setId("MCR"+Constants.APP_VERSION+"-"+rand);
                    signInModel.setCreatedTime(currentTime.toString());
                    signInModel.setUpdatedTime(currentTime.toString());

                    databaseReference.child(signInModel.getMobile()).setValue(signInModel);
                    Toast.makeText(getApplicationContext(),"Registered Successfully",
                            Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), BottomNavigation.class);
                    startActivity(i);
                    finish();
                }


            }
        });
    }
    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(6);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }
}