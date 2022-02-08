package org.microdegree.com.app.exp;

import org.junit.Test;
import org.microdegree.com.app.exp.ui.signin.SignInViewModel;
import org.microdegree.com.app.exp.ui.signin.model.SignInForm;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void fields_isCorrect() {
        SignInViewModel vm = new SignInViewModel();
        vm.init();
        SignInForm form = vm.getForm();
        form.getFields().setName("Vikranth Salian");
        form.getFields().setMobile("7019490980");
        form.getFields().setEmail("vikkysalian@gmail.com");
        form.getFields().setReferral("123456");
        assertTrue("Form is not valid", form.isValid());
    }

    @Test
    public void name_isShort() {
        SignInViewModel vm = new SignInViewModel();
        vm.init();
        SignInForm form = vm.getForm();
        form.getFields().setName("Vik");
        form.getFields().setMobile("7019490980");
        form.getFields().setEmail("vikkysalian@gmail.com");
        form.getFields().setReferral("123456");
        assertTrue("Name should be invalid", !form.isNameValid(true));
        assertTrue("Mobile should be valid", form.isMobileValid(false));
        assertTrue("Email should be valid", form.isEmailValid(false));
        assertTrue("Referral Code should be valid", form.isReferralValid(false));
        assertTrue("Form is valid, Name should be invalid", !form.isValid());
        assertEquals("Error message should be \"Too short\" not " + form.getNameError(), "Too short", form.getNameError());
    }


    @Test
    public void mobile_isShort() {
        SignInViewModel vm = new SignInViewModel();
        vm.init();
        SignInForm form = vm.getForm();
        form.getFields().setName("Vikranth Salian");
        form.getFields().setMobile("701949098");
        form.getFields().setEmail("vikkysalian@gmail.com");
        form.getFields().setReferral("123456");
        assertTrue("Name should be invalid", form.isNameValid(false));
        assertTrue("Mobile should be valid", !form.isMobileValid(true));
        assertTrue("Email should be valid", form.isEmailValid(false));
        assertTrue("Referral Code should be valid", form.isReferralValid(false));
        assertTrue("Form is valid, Mobile Number should be invalid", !form.isValid());
        assertEquals("Error message should be \"Too short\" not " + form.getMobileError(), "Too short", form.getMobileError());
    }



    @Test
    public void email_isShort() {
        SignInViewModel vm = new SignInViewModel();
        vm.init();
        SignInForm form = vm.getForm();
        form.getFields().setName("Vikranth Salian");
        form.getFields().setMobile("7019490980");
        form.getFields().setEmail("v@g.c");
        form.getFields().setReferral("123456");
        assertTrue("Name should be invalid", form.isNameValid(false));
        assertTrue("Mobile should be valid", form.isMobileValid(false));
        assertTrue("Email should be valid", !form.isEmailValid(true));
        assertTrue("Referral Code should be valid", form.isReferralValid(false));
        assertTrue("Form is valid, Name should be invalid", !form.isValid());
        assertEquals("Error message should be \"Too short\" not " + form.getEmailError(), "Too short", form.getEmailError());
    }




    @Test
    public void email_isWrongFormat() {
        SignInViewModel vm = new SignInViewModel();
        vm.init();
        SignInForm form = vm.getForm();
        form.getFields().setName("Vikranth Salian");
        form.getFields().setMobile("7019490980");
        form.getFields().setEmail("vikkysalian@aaa.com");
        form.getFields().setReferral("123456");
        assertTrue("Name should be invalid", form.isNameValid(false));
        assertTrue("Mobile should be valid", form.isMobileValid(false));
        assertTrue("Email should be valid", !form.isEmailValid(true));
        assertTrue("Referral Code should be valid", form.isReferralValid(false));
        assertTrue("Form is invalid, email should be invalid", !form.isValid());
        assertEquals("Error message should be \"Format is invalid\" not " + form.getEmailError(), "Format is invalid", form.getEmailError());
    }


    @Test
    public void referral_isShort() {
        SignInViewModel vm = new SignInViewModel();
        vm.init();
        SignInForm form = vm.getForm();
        form.getFields().setName("Vikranth Salian");
        form.getFields().setMobile("7019490980");
        form.getFields().setEmail("vikkysalian@gmail.com");
        form.getFields().setReferral("123");
        assertTrue("Name should be invalid", form.isNameValid(false));
        assertTrue("Mobile should be valid", form.isMobileValid(false));
        assertTrue("Email should be valid", form.isEmailValid(false));
        assertTrue("Referral Code should be valid", !form.isReferralValid(true));
        assertTrue("Form is valid, Referral Code should be invalid", !form.isValid());
        assertEquals("Error message should be \"Too short\" not " + form.getReferralError(), "Too short", form.getReferralError());
    }

}