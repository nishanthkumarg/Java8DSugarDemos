package com.samples.devtools.demo.features.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by nishanthkumarg on 4/4/17.
 */

import com.samples.devtools.demo.BR;

import static com.samples.devtools.demo.features.databinding.PhonewordUtils.toNumber;

public class PhonewordViewModel extends BaseObservable{

    private final String TAG = PhonewordViewModel.class.getName();
    private boolean mIsTranslated = false;
    private String mPhoneNumber = "";
    private String mPhoneWord = "";
    private String mCallButtonText = "Call";

    @Bindable
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    @Bindable
    public String getCallButtonText() {
        return mCallButtonText;
    }

    @Bindable
    public boolean getIsTranslated() {
        return mIsTranslated;
    }

    @Bindable
    public String getPhoneWord() {
        return mPhoneWord;
    }


    public void setPhoneWord(String phoneWord) {
        mPhoneWord = phoneWord;
        notifyPropertyChanged(BR.phoneWord);
    }

    public void translatePhoneWord() {
        mPhoneNumber = toNumber(mPhoneWord);

        if (TextUtils.isEmpty(mPhoneNumber)) {
            mCallButtonText = "Call";
            mIsTranslated = false;
        } else {
            mIsTranslated = true;
            mCallButtonText = "Call " + mPhoneNumber + "?";
        }
        notifyPropertyChanged(BR.phoneNumber);
        notifyPropertyChanged(BR.isTranslated);
        notifyPropertyChanged(BR.callButtonText);

    }

    public void onTranslate(View v) {
        translatePhoneWord();
    }
}
