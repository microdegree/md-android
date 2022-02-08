package org.microdegree.com.app.exp.ui.signin.model;

import org.microdegree.com.app.exp.BR;
import org.microdegree.com.app.exp.R;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;


public class SignInForm extends BaseObservable {
    private SignInFields fields = new SignInFields();
    private SignInErrorFields errors = new SignInErrorFields();
    private MutableLiveData<SignInFields> buttonClick = new MutableLiveData<>();

    @Bindable
    public boolean isValid() {
        boolean nameValid = isNameValid(false);
        boolean mobileValid = isMobileValid(false);
        boolean valid = isEmailValid(false);
        valid =  nameValid && mobileValid && valid;
     //  valid = isReferralValid(false) && nameValid && mobileValid && valid;
         valid =nameValid  && valid;
        notifyPropertyChanged(BR.nameError);
        notifyPropertyChanged(BR.mobileError);
        notifyPropertyChanged(BR.emailError);
     //   notifyPropertyChanged(BR.referralError);
        return valid;
    }
    public boolean isNameValid(boolean setMessage) {
        // Minimum 4 chars
        String name = fields.getName();
            if (name != null && name.length()>3) {
                errors.setName(null);
                notifyPropertyChanged(BR.valid);
                return true;
            } else {
                if (setMessage) {
                    errors.setName(R.string.error_format_invalid);
                    notifyPropertyChanged(BR.valid);
                }
                return false;
            }
    }

    public boolean isMobileValid(boolean setMessage) {
        // Minimum 10 chars
        String mobile = fields.getMobile();
        if (mobile != null && mobile.length()>9) {
            errors.setMobile(null);
            notifyPropertyChanged(BR.valid);
            return true;
        } else {
            if (setMessage) {
                errors.setMobile(R.string.error_format_invalid);
                notifyPropertyChanged(BR.valid);
            }
            return false;
        }
    }

    public boolean isEmailValid(boolean setMessage) {
        // Minimum a@b.c
        String email = fields.getEmail();
        if (email != null && email.length() > 5) {
            int indexOfAt = email.indexOf("@");
            int indexOfDot = email.lastIndexOf(".");
            if (indexOfAt > 0 && indexOfDot > indexOfAt && indexOfDot < email.length() - 1) {
                errors.setEmail(null);
                notifyPropertyChanged(BR.valid);
                return true;
            } else {
                if (setMessage) {
                    errors.setEmail(R.string.error_format_invalid);
                    notifyPropertyChanged(BR.valid);
                }
                return false;
            }
        }
        if (setMessage) {
            errors.setEmail(R.string.error_too_short);
            notifyPropertyChanged(BR.valid);
        }

        return false;
    }

    public boolean isReferralValid(boolean setMessage) {
        String referral = fields.getReferral();
        if (referral != null && (referral.length()==0 || referral.length() > 1)) {
            errors.setReferral(null);
            notifyPropertyChanged(BR.valid);
            return true;
        } else {
            if (setMessage) {
                errors.setReferral(R.string.error_too_short);
                notifyPropertyChanged(BR.valid);
            }

            return false;
        }
    }

    public void onClick() {
        if(isValid()) {
            fields.setErrorFields(false);
            buttonClick.setValue(fields);
        }else{
            fields.setErrorFields(true);
            buttonClick.setValue(fields);
        }
    }

    public MutableLiveData<SignInFields> getSignInFields() {
        return buttonClick;
    }

    public SignInFields getFields() {
        return fields;
    }

    @Bindable
    public Integer getNameError() {
        return errors.getName();
    }

    @Bindable
    public Integer getMobileError() {
        return errors.getMobile();
    }

    @Bindable
    public Integer getEmailError() {
        return errors.getEmail();
    }

    @Bindable
    public Integer getReferralError() {
        return errors.getReferral();
    }
}
