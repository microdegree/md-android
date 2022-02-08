  # Add this global rule
    -keepattributes Signature

    # This rule will properly ProGuard all the model classes in
    # the package com.yourcompany.models.
    # Modify this rule to fit the structure of your app.

    -keepclassmembers class * {
        @android.webkit.JavascriptInterface <methods>;
    }

    -keepattributes JavascriptInterface
    -keepattributes *Annotation*

    -dontwarn com.razorpay.**
    -keep class com.razorpay.** {*;}

    -optimizations !method/inlining/*

    -keepclassmembers class org.microdegree.com.app.exp.data.model.** {
      *;
    }