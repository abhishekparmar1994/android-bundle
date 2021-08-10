package com.ckinfotech.investor.customfr;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ckinfotech.investor.Activity.LoginActivity;
import com.ckinfotech.investor.Activity.MainActivity;
import com.ckinfotech.investor.Activity.SignUpActivity;

public abstract class BaseFragment extends Fragment {
   public MainActivity activity;
//    public LoginActivity loginActivity;
//    public SignUpActivity signUpActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = ((MainActivity)getActivity());
//        loginActivity = ((LoginActivity)getActivity());
//        signUpActivity = ((SignUpActivity)getActivity());
    }

    //    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//
//        getActivity().setRequestedOrientation(
//                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
