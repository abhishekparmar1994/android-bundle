package com.ckinfotech.investor.customfragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ckinfotech.investor.Activity.LoginActivity;
import com.ckinfotech.investor.Activity.MainActivity;
import com.ckinfotech.investor.Adepter.CountryCitySortAdapter;
import com.ckinfotech.investor.Adepter.MonthIdAdapter;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.customfr.BaseFragment;
import com.ckinfotech.investor.model.ApllyLoan.ApplyLoan;
import com.ckinfotech.investor.model.ApllyLoan.EmiAmmount;
import com.ckinfotech.investor.model.IdModel;
import com.ckinfotech.investor.model.Pasyment.PaymentService;
import com.ckinfotech.investor.model.responceModel.CountryList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;
import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class ApplyLoanFragment extends BaseFragment {
    View view;
    EditText edtAmmount;
    TextView txtAmmount, txtLoanStatus, txtLoanStatusTxt;
    Button butProcced, btnApply;
    LinearLayout linEmiShow, linStatus, linAmmount, linSelectMonth;
    IdModel SpinnerModel;
    private MonthIdAdapter monthIdAdapter;
    private Spinner spiMonthId;
    List<IdModel> list;
    MyPrefs myPrefs;
    String monthSelect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_applyloan, container, false);
        text_toolbare.setText("Apply Loan");
//        myPrefs = new MyPrefs(activity);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                   Intent intent = new Intent(activity,MainActivity.class);
                   startActivity(intent);
                        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
        init(view);
        click();

        return view;
    }

    private void init(View view) {
        myPrefs = new MyPrefs(activity);
        edtAmmount = view.findViewById(R.id.edtApplyLoanAmount);
        txtAmmount = view.findViewById(R.id.txtApplyLoanAmmount);
        butProcced = view.findViewById(R.id.btnApplyLoanproceed);
        btnApply = view.findViewById(R.id.btnApplyLoanApply);

        linEmiShow = view.findViewById(R.id.linApplyLoanAmmount);
        linAmmount = view.findViewById(R.id.linApplyAmount);
        linSelectMonth = view.findViewById(R.id.linApplyMonth);

        linStatus = view.findViewById(R.id.linApplyLoanStatus);
        txtLoanStatusTxt = view.findViewById(R.id.txtApplyLoanStatusTxt);
        txtLoanStatus = view.findViewById(R.id.txtApplyLoanStatus);

        spiMonthId = view.findViewById(R.id.spiApplyLoanMonth);
        list = new ArrayList<IdModel>();
        if (myPrefs.getLoanMonth() != null) {
            int abc = Integer.parseInt(myPrefs.getLoanMonth());
            Log.e("getEmiMonth", "" + myPrefs.getLoanMonth());
            list.clear();
            for (int i = 0; i <= abc; i++) {
//            list=new IdModel();
                SpinnerModel = new IdModel();
                SpinnerModel.setMonthId(String.valueOf(i));
                list.add(SpinnerModel);
                Log.e("IDSpinner", "" + list);
            }
            MonthSpinner(list);
        }
        if (myPrefs.getAppLoanStatus().equalsIgnoreCase("inactive")) {
            btnApply.setVisibility(View.GONE);

//            txtLoanStatusTxt.setVisibility(View.GONE);
//            txtLoanStatus.setVisibility(View.GONE);

            linStatus.setVisibility(View.GONE);
            linEmiShow.setVisibility(View.GONE);

            butProcced.setVisibility(View.VISIBLE);

        }else if (myPrefs.getAppLoanStatus().equalsIgnoreCase("complete")) {
            btnApply.setVisibility(View.GONE);

//            txtLoanStatusTxt.setVisibility(View.GONE);
//            txtLoanStatus.setVisibility(View.GONE);

            linStatus.setVisibility(View.GONE);
            linEmiShow.setVisibility(View.GONE);

            butProcced.setVisibility(View.VISIBLE);
        }
        else {
            linAmmount.setVisibility(View.GONE);
            linSelectMonth.setVisibility(View.GONE);
            linEmiShow.setVisibility(View.GONE);

            btnApply.setVisibility(View.GONE);
            butProcced.setVisibility(View.GONE);

            linStatus.setVisibility(View.VISIBLE);

            txtLoanStatusTxt.setVisibility(View.VISIBLE);
            txtLoanStatus.setVisibility(View.VISIBLE);

//            txtLoanStatus.setText(myPrefs.getAppLoanStatus());
            txtLoanStatusTxt.setText(myPrefs.getApplyLoanMessage());

            if (myPrefs.getAppLoanStatus().equalsIgnoreCase("pending")) {
                txtLoanStatus.setText("Pending");
            } else if (myPrefs.getAppLoanStatus().equalsIgnoreCase("approve")) {
                txtLoanStatus.setText("Approved");
            } else if (myPrefs.getAppLoanStatus().equalsIgnoreCase("reject")) {
                txtLoanStatus.setText("Rejected");
            } else if (myPrefs.getAppLoanStatus().equalsIgnoreCase("complete")) {
                txtLoanStatus.setText("Completed");
            }
        }
        Log.e("ApplyLoanFragment", "AppLoanStatus" + myPrefs.getAppLoanStatus());
        Log.e("ApplyLoanFragment", "ApplyLoanMessage" + myPrefs.getApplyLoanMessage());
    }

    private void click() {
        butProcced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Error();
            }
        });
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyLoan();
            }
        });
    }

    private void MonthSpinner(List<IdModel> orderTypeSortArrayList) {

        if (orderTypeSortArrayList == null) {
            orderTypeSortArrayList = new ArrayList<>();
        }
        if (monthIdAdapter == null) {
            monthIdAdapter = new MonthIdAdapter(this, orderTypeSortArrayList);
            spiMonthId.setAdapter(monthIdAdapter);
//            spiMonthId.setSelection(100);
        } else {
            monthIdAdapter.setSortOrderList(orderTypeSortArrayList);

            monthIdAdapter.notifyDataSetChanged();
        }
        List<IdModel> finalOrderTypeSortArrayList = orderTypeSortArrayList;
        spiMonthId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                finalOrderTypeSortArrayList.get(i).getMonthId();

//                if (!finalOrderTypeSortArrayList.get(i).getName().equals("Select Country")) {
//                    getState(String.valueOf(finalOrderTypeSortArrayList.get(i).getId()));
                monthSelect = finalOrderTypeSortArrayList.get(i).getMonthId();
//                Toast.makeText(activity, "" + String.valueOf(finalOrderTypeSortArrayList.get(i).getMonthId()), Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do with view
            }
        });
    }

    private void EmiCalculasion() {
        activity.progressDialog.show(activity.getSupportFragmentManager(), "");
        HashMap<String, String> map = new HashMap<>();
        map.put("amount", edtAmmount.getText().toString());
        map.put("emi_month", monthSelect);
//        map.put("amount", myPrefs.getPaymentAmount());
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<EmiAmmount> categoryList = apiInterface.emaiCalculasion(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<EmiAmmount>() {
            @Override
            public void onResponse(Call<EmiAmmount> call, Response<EmiAmmount> response) {

                Log.e("myresponceinMain", new Gson().toJson(response.body()));

//                arr.clear();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("true")) {
//                        myPrefs.setPaymentRequest("true");
//                        Util.displayRightSnackbar(constraintLayout, activity, response.body().getMessage());
//                        Intent intent = new Intent(activity, MainActivity.class);
//                        startActivity(intent);

                        linEmiShow.setVisibility(View.VISIBLE);

                        btnApply.setVisibility(View.VISIBLE);

                        txtAmmount.setText(response.body().getMonthlyEmiAmount());
                        butProcced.setVisibility(View.GONE);
                        edtAmmount.setFocusable(false);
                        spiMonthId.setEnabled(false);

                        activity.progressDialog.dismiss();
                    } else {
                        activity.progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<EmiAmmount> call, Throwable t) {
                activity.progressDialog.dismiss();
                Log.e("AccountI", "" + t);
            }
        });

    }

    private void ApplyLoan() {
        activity.progressDialog.show(activity.getSupportFragmentManager(), "");
        HashMap<String, String> map = new HashMap<>();
        map.put("amount", edtAmmount.getText().toString());
        map.put("emi_month", monthSelect);
        map.put("user_id", myPrefs.getID());
//        map.put("amount", myPrefs.getPaymentAmount());
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<ApplyLoan> categoryList = apiInterface.applyLoan(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<ApplyLoan>() {
            @Override
            public void onResponse(Call<ApplyLoan> call, Response<ApplyLoan> response) {

                Log.e("myresponceinMain", new Gson().toJson(response.body()));

//                arr.clear();
                if (response.isSuccessful()) {
                    if (response.body().getsuccess().equals("true")) {
//                        myPrefs.setPaymentRequest("true");
//                        Util.displayRightSnackbar(constraintLayout, activity, response.body().getMessage());
//                        Intent intent = new Intent(activity, MainActivity.class);
//                        startActivity(intent);
//                        txtAmmount.setText(response.body().getMonthlyEmiAmount());
//                        butProcced.setVisibility(View.GONE);
//                        edtAmmount.setFocusable(false);
//                        spiMonthId.setEnabled(false);
//                        btnApply.setVisibility(View.VISIBLE);

                        btnApply.setVisibility(View.GONE);
                        linAmmount.setVisibility(View.GONE);
                        linSelectMonth.setVisibility(View.GONE);
                        linEmiShow.setVisibility(View.GONE);

                        linStatus.setVisibility(View.VISIBLE);

                        txtLoanStatus.setText("pending");
                        txtLoanStatusTxt.setText(response.body().getMessage());
                        myPrefs.setApplyLoanMessage(response.body().getMessage());
                        myPrefs.setAppLoanStatus("pending");
                        activity.progressDialog.dismiss();
                        Intent intent = new Intent(activity, MainActivity.class);
                        startActivity(intent);
                    } else {
                        activity.progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApplyLoan> call, Throwable t) {
                activity.progressDialog.dismiss();
                Log.e("AccountI", "" + t);
            }
        });

    }

    private void Error() {
        if (edtAmmount.getText().toString().isEmpty()) {
            edtAmmount.setError("Enter Amount");
            edtAmmount.requestFocus();
        } else if (TextUtils.isEmpty(monthSelect) || monthSelect.equalsIgnoreCase("0")) {
//            spiMonthId.setError("Enter Password");
//            edt_pasword.requestFocus();
            Toast.makeText(activity, "Select Month", Toast.LENGTH_SHORT).show();
        } else {
//            progressDialog.show(LoginActivity.this.getSupportFragmentManager(), "");
//            sendUserLogin(edt_email.getText().toString(), edt_password.getText().toString());
            EmiCalculasion();
        }
    }

}