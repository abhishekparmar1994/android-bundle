package com.ckinfotech.investor.customfr;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ckinfotech.investor.R;
import com.ckinfotech.investor.Util.MyPrefs;

import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class LoanDetailFragment extends BaseFragment {

    View view;
    private MyPrefs myPrefs;
    TextView txtLoanAmount, txtemimonth, txtmonthintres, txtemidate, txtemibounce, txtloantotal;
    Button btnLoanAppli;
    private FragmentManager fragmentManager;
    LinearLayout linDetailLoan, linNotLoan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentformfragment, container, false);
        text_toolbare.setText("Loan Info");
        myPrefs = new MyPrefs(activity);
        init(view);

        return view;
    }

    private void init(View view) {

        txtLoanAmount = view.findViewById(R.id.txtInfoLoanAmount1);
        txtemimonth = view.findViewById(R.id.txtEmiMonth1);
        txtmonthintres = view.findViewById(R.id.txtMonthlyInterestAmount1);
        txtemidate = view.findViewById(R.id.txtinfoEmiDate1);
        txtemibounce = view.findViewById(R.id.txtEmiBounceCharge1);
        txtloantotal = view.findViewById(R.id.txtLoanTotalAmount1);

        btnLoanAppli = view.findViewById(R.id.btnLoanApply);

        linDetailLoan = view.findViewById(R.id.lindetailloan);
        linNotLoan = view.findViewById(R.id.linNotstart);

        btnLoanAppli.setOnClickListener(new View.OnClickListener() {
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

        if (myPrefs.getLoanAmount() != null) {
            linDetailLoan.setVisibility(View.VISIBLE);
            linNotLoan.setVisibility(View.GONE);
            txtLoanAmount.setText(myPrefs.getLoanAmount());
            txtemimonth.setText(myPrefs.getEmiMonth());
            txtmonthintres.setText(myPrefs.getMonthliIntrestAmount());
            txtemidate.setText(myPrefs.getEmiDate());
            txtloantotal.setText(myPrefs.getloanTotalAmount());
            txtemibounce.setText(myPrefs.getEmiBounceCharge());
            Log.e("txtemidate", "" + myPrefs.getEmiDate());
        } else {
//            Toast.makeText(activity, "" + myPrefs.getLoanAmount(), Toast.LENGTH_SHORT).show();
            linDetailLoan.setVisibility(View.GONE);
            linNotLoan.setVisibility(View.VISIBLE);
        }
//        txtLoanAmount.setText(myPrefs.getLoanAmount());
//        txtemimonth.setText(myPrefs.getEmiMonth());
//        txtmonthintres.setText(myPrefs.getMonthliIntrestAmount());
//        txtemidate.setText(myPrefs.getEmiDate());
//        txtloantotal.setText(myPrefs.getloanTotalAmount());
//        txtemibounce.setText(myPrefs.getEmiBounceCharge());
//        Log.e("txtemidate",""+myPrefs.getEmiDate());
    }


}
