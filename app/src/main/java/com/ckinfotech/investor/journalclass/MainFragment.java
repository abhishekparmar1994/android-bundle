package com.ckinfotech.investor.journalclass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckinfotech.investor.R;
import com.ckinfotech.investor.customfr.BaseFragment;

public class MainFragment extends BaseFragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }
}
