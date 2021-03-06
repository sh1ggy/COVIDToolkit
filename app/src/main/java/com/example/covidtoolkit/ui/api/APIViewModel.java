package com.example.covidtoolkit.ui.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class APIViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public APIViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}