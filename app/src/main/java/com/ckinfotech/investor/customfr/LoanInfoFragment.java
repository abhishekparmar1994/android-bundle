package com.ckinfotech.investor.customfr;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.model.userLogin.UserModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class LoanInfoFragment extends BaseFragment {

    View view;
    private MyPrefs myPrefs;
    TextView txtLoanAmount,txtemimonth,txtmonthintres,txtemidate,txtemibounce,txtloantotal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentloaninfo, container, false);
        text_toolbare.setText("Loan Info");
        myPrefs = new MyPrefs(activity);
        init(view);

        return view;
    }

    private void init(View view) {

        txtLoanAmount = view.findViewById(R.id.txtInfoLoanAmount);
        txtemimonth = view.findViewById(R.id.txtEmiMonth);
        txtmonthintres = view.findViewById(R.id.txtMonthlyInterestAmount);
        txtemidate = view.findViewById(R.id.txtinfoEmiDate);
        txtemibounce = view.findViewById(R.id.txtEmiBounceCharge);
        txtloantotal = view.findViewById(R.id.txtLoanTotalAmount);

        txtLoanAmount.setText(myPrefs.getLoanAmount());
        txtemimonth.setText(myPrefs.getEmiMonth());
        txtmonthintres.setText(myPrefs.getMonthliIntrestAmount());
        txtemidate.setText(myPrefs.getEmiDate());
        txtloantotal.setText(myPrefs.getloanTotalAmount());
        txtemibounce.setText(myPrefs.getEmiBounceCharge());
        Log.e("txtemidate",""+myPrefs.getEmiDate());
    }

}
