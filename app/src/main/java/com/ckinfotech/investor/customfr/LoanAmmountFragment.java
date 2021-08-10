package com.ckinfotech.investor.customfr;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ckinfotech.investor.Activity.ProgressDialognew;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.model.LoanSubmitResponce.LoanSubmitData;
import com.ckinfotech.investor.model.LoanSubmitResponce.LoanSubmitResponce;
import com.ckinfotech.investor.model.userLogin.UserModel;

import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;
import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class LoanAmmountFragment extends BaseFragment {

    View view;
    Button btnsubmit;
    Spinner spMonthEmi;
    private FragmentManager fragmentManager;
    private EditText edtrequirdloan, edtincome;
    private MyPrefs myPrefs;
    TextView txtdateEmi;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ProgressDialognew progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentloanamount, container, false);
        text_toolbare.setText("Loan Amount");

        init(view);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Error();
            }
        });
        spMonthEmi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        txtdateEmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {

                                int month = monthOfYear + 1;
                                String formattedMonth = "" + month;
                                String formattedDayOfMonth = "" + dayOfMonth;
                                if (month < 10) {
                                    formattedMonth = "0" + month;
                                }
                                if (dayOfMonth < 10) {
                                    formattedDayOfMonth = "0" + dayOfMonth;
                                }
//                                txtdateEmi.setText(formattedDayOfMonth + "/" + formattedMonth + "/" + year);
                                txtdateEmi.setText(year + "-" + formattedMonth + "-" + formattedDayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        return view;
    }

    private void init(View view) {

        myPrefs = new MyPrefs(activity);
        progressDialog = new ProgressDialognew();
        edtrequirdloan = view.findViewById(R.id.edtLoanAmount);
        edtincome = view.findViewById(R.id.edtLoanIncome);
        btnsubmit = view.findViewById(R.id.btnLoanSubmit);
        spMonthEmi = (Spinner) view.findViewById(R.id.spinneremi);
        txtdateEmi = view.findViewById(R.id.txtemidate);
//        edtnamesecond = view.findViewById(R.id.edtNomineeNameSecond);
//        edtnumbersecond = view.findViewById(R.id.edtNomineeNumberSecond);

    }

    private void Error() {
        if (edtrequirdloan.getText().toString().isEmpty()) {
            edtrequirdloan.setError("Enter Required Loan Amount");
            edtrequirdloan.requestFocus();
        } else if (edtincome.getText().toString().isEmpty()) {
            edtincome.setError("Enter Monthly Income");
            edtincome.requestFocus();
        } else if (spMonthEmi.getSelectedItemPosition() == 0) {
            Toast.makeText(activity, "Select Emi Month", Toast.LENGTH_SHORT).show();
        } else if (txtdateEmi.getText().toString().isEmpty()) {
            Toast.makeText(activity, "Select Emi Payment Date", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show(activity.getSupportFragmentManager(), "");
            submitDetailsfour();
        }
    }

    public void submitDetailsfour() {
        Log.e("submitDetailsfour", "getLoanId" + myPrefs.getLoanId());
        HashMap<String, String> map = new HashMap<>();
        map.put("step", "four");
        map.put("required_loan_amount", edtrequirdloan.getText().toString().trim());
        map.put("monthly_emi", spMonthEmi.getSelectedItem().toString());
        map.put("emi_payment_date", txtdateEmi.getText().toString().trim());
        map.put("monthly_income", edtincome.getText().toString().trim());
        map.put("loan_id", myPrefs.getLoanId());
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<LoanSubmitResponce> categoryList = apiInterface.userLoanSubmit(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<LoanSubmitResponce>() {
            @Override
            public void onResponse(Call<LoanSubmitResponce> call, Response<LoanSubmitResponce> response) {
                if (response.isSuccessful()) {
//                    if (response.body().getResult().equalsIgnoreCase("true")) {
//                    myPrefs.setLoanAmount(response.body().getloanSubmitData().getLoanAmount());
                    myPrefs.setEmiMonth(String.valueOf(response.body().getloanSubmitData().getEmiMonth()));
                    myPrefs.setMonthliIntrestAmount(String.valueOf(response.body().getloanSubmitData().getMonthlyInterestAmount()));
                    myPrefs.setEmiBounceCharge(response.body().getloanSubmitData().getEmiBounceCharge());
                    myPrefs.setloanTotalAmount(String.valueOf(response.body().getloanSubmitData().getLoanTotalAmount()));
                    myPrefs.setEmiDate(response.body().getloanSubmitData().getEmiDate());
                    myPrefs.setUserLoanStatus(response.body().getloanSubmitData().getloan_status());

                    fragmentManager = activity.getSupportFragmentManager();
                    LoanInfoFragment fr = new LoanInfoFragment();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.addToBackStack(fr.getTag());
                    transaction.replace(R.id.frameLayout, fr);
                    transaction.commit();

                    Log.e("submitDetailsfour", "submitDetailsfour" + response.body().getMessage());
                    progressDialog.dismiss();
                    Util.displayRightSnackbar(constraintLayout, activity, response.body().getMessage());
                    myPrefs.setUserLoanStep("Four");
//                    } else {
//                        progressDialog.dismiss();
//                        Util.displayRongSnackbar(constraintLayout, activity, response.body().getMessage());
//                    }

                } else {
                    progressDialog.dismiss();
                    Util.displayRongSnackbar(constraintLayout, activity, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoanSubmitResponce> call, Throwable t) {
                Log.e("Response", "getMsg" + t);
            }
        });
    }

}
