package com.ckinfotech.investor.customfr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ckinfotech.investor.R;

import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class UserLoanInformationFragment extends BaseFragment {

    View view;
    Button btn_done;
    private FragmentManager fragmentManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_userloneinformasion, container, false);
        btn_done = view.findViewById(R.id.btnUserLoanDone);
        text_toolbare.setText("Loan Information");
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = activity.getSupportFragmentManager();
                UserDetailFragment fr = new UserDetailFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(fr.getTag());
                transaction.replace(R.id.frameLayout, fr);
                transaction.commit();
            }
        });
        return view;
    }

}
