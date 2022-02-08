package org.microdegree.com.app.exp.ui.signin;

import android.view.View;
import android.widget.EditText;


import androidx.annotation.VisibleForTesting;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.microdegree.com.app.exp.ui.signin.model.SignInFields;
import org.microdegree.com.app.exp.ui.signin.model.SignInForm;

public class SignInViewModel extends ViewModel {
    private SignInForm signIn;
    private View.OnFocusChangeListener onFocusName;
    private View.OnFocusChangeListener onFocusMobile;
    private View.OnFocusChangeListener onFocusEmail;
    private View.OnFocusChangeListener onFocusReferral;

    @VisibleForTesting
    public void init() {
        signIn = new SignInForm();

        onFocusName = new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    signIn.isNameValid(true);
                }
            }
        };

        onFocusMobile =  new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    signIn.isMobileValid(true);
                }
            }
        };

//        onFocusEmail =  new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View view, boolean focused) {
//                EditText et = (EditText) view;
//                if (et.getText().length() > 0 && !focused) {
//                    signIn.isEmailValid(true);
//                }
//            }
//        };
//
//
//        onFocusReferral =  new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View view, boolean focused) {
//                EditText et = (EditText) view;
//                if (et.getText().length() > 0 && !focused) {
//                    signIn.isReferralValid(true);
//                }
//            }
//        };


    }

    public SignInForm getSignIn() {
        return signIn;
    }

    public View.OnFocusChangeListener getNameOnFocusChangeListener() {
        return onFocusName;
    }

    public View.OnFocusChangeListener getMobileOnFocusChangeListener() {
        return onFocusMobile;
    }

    public View.OnFocusChangeListener getEmailOnFocusChangeListener() {
        return onFocusEmail;
    }

    public View.OnFocusChangeListener getReferralOnFocusChangeListener() {
        return onFocusReferral;
    }

    public void onButtonClick() {
        signIn.onClick();
    }

    public MutableLiveData<SignInFields> getSignInFields() {
        return signIn.getSignInFields();
    }

    public SignInForm getForm() {
        return signIn;
    }

    @BindingAdapter("error")
    public static void setError(EditText editText, Object strOrResId) {
        if (strOrResId instanceof Integer) {
            editText.setError(
                    editText.getContext().getString((Integer) strOrResId));
        } else {
            editText.setError((String) strOrResId);
        }
    }

    @BindingAdapter("onFocus")
    public static void bindFocusChange(EditText editText, View.OnFocusChangeListener onFocusChangeListener) {
        if (editText.getOnFocusChangeListener() == null) {
            editText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }
}