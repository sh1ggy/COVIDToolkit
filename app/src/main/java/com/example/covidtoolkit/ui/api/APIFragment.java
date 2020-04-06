package com.example.covidtoolkit.ui.api;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.covidtoolkit.R;

public class APIFragment extends Fragment {

    private APIViewModel APIViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        APIViewModel =
                ViewModelProviders.of(this).get(APIViewModel.class);
        View root = inflater.inflate(R.layout.fragment_api, container, false);
        //final TextView textView = root.findViewById(R.id.apiTest);
        APIViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}