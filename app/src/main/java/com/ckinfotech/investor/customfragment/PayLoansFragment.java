package com.ckinfotech.investor.customfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ckinfotech.investor.Activity.MainActivity;
import com.ckinfotech.investor.Adepter.PayLoansAdapter;
import com.ckinfotech.investor.ChangActivity.SignUpTwoActivity;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.customfr.BaseFragment;
import com.ckinfotech.investor.model.LoanData.LoanData;
import com.ckinfotech.investor.model.emiRequest.EmiRequest;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;
import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class PayLoansFragment extends BaseFragment {
    View view;
    TextView txtID, txtAmount, txtMonth, txtEmiAmount, txtEMIDate, txtLoanMassage;
    LinearLayout linApplyDetails;
    MyPrefs myPrefs;
    private RecyclerView recyclerView;
    private PayLoansAdapter adapter;
    Rect displayRectangle;
    //    public String CurrentMonth;
    Window window;
    AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payloans, container, false);
        text_toolbare.setText("Pay Loans");
//        myPrefs = new MyPrefs(activity);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Intent intent = new Intent(activity, MainActivity.class);
                    startActivity(intent);
//                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
        recyclerView = view.findViewById(R.id.recPayLoans);


        PayLoanData();
    }

    private void click() {

    }

    public void PayLoanData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("id", myPrefs.getID());
//        map.put("token", myPrefs.getToken());
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<LoanData> categoryList = apiInterface.payLoanData(myPrefs.getToken(), map);
        Log.e("TipsListData", "TipsListData=" + categoryList.request().url());
        categoryList.enqueue(new Callback<LoanData>() {
            @Override
            public void onResponse(Call<LoanData> call, Response<LoanData> response) {

                Log.e("TipsListData", new Gson().toJson(response.body()));

                Log.e("TipsListData", "getToken:=" + myPrefs.getToken());
                Log.e("TipsListData", "TipsListData:=" + response.body());
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equalsIgnoreCase("true")) {
//                        recyclerView.setHasFixedSize(true);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//                        CurrentMonth = response.body().getLoanDataInfo().getCurrentdate();
//                        Log.e("Adepter", "....." + CurrentMonth);
//                        Log.e("Adepter", "*****" + response.body().getLoanDataInfo().getCurrentdate());
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
                        recyclerView.setLayoutManager(mLayoutManager);
//                        Collections.reverse(response.body().getLoanDataInfo().getEmiDataList());
                        adapter = new PayLoansAdapter(response.body().getLoanDataInfo().getLoanId(),response.body().getLoanDataInfo().getCurrentDate(), getActivity(), response.body().getLoanDataInfo().getLoanEmiData());
                        recyclerView.setAdapter(adapter);
//                        recyclerView.setItemViewCacheSize(response.body().getLoanDataInfo().getEmiDataList().size());
//                        progressBar.setVisibility(View.GONE);
                    } else {
//                        Toast.makeText(mainFragmentApi, "Your internet connection is slow.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (response.body() != null) {
//                        progressBar.setVisibility(View.GONE);
                    } else {
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(mainFragmentApi, "Your internet connection is slow.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoanData> call, Throwable t) {
//            progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
            }
        });
    }

//    public void EmiPayRequest(View view, String checkDate) {
//        Log.e("hello", "csdcjksdcjk");
//    }

    public void EmiPayRequest(View v, Activity context, String amountWithIntrest, String LoanId,String checkDate,String token) {

        Rect displayRectangle = new Rect();
        Window window =context.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        ViewGroup viewGroup = v.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dilog_loan_emi, viewGroup, false);

        TextView txtPayAmount = dialogView.findViewById(R.id.txtDiloGLoanEmiAmount);
        Button btnLoanEmi = dialogView.findViewById(R.id.btnDilogEmiLone);
        CheckBox check = dialogView.findViewById(R.id.cheLoanEmi);

        txtPayAmount.setText(amountWithIntrest);
        ImageView imageClosr = dialogView.findViewById(R.id.dilogloan_emi);

        dialogView.setMinimumWidth((int) (displayRectangle.width() * 0f));
        dialogView.setMinimumHeight((int) (displayRectangle.height() * 0f));
        builder.setView(dialogView);
        builder.setCancelable(false);


        alertDialog  = builder.create();
//        dilogSetItem();
        imageClosr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                spinKitView.setVisibility(View.VISIBLE);
//                linearLayout.setVisibility(View.GONE);
                alertDialog.dismiss();
            }
        });
        btnLoanEmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                spinKitView.setVisibility(View.VISIBLE);
//                linearLayout.setVisibility(View.GONE);
//                alertDialog.dismiss();
                if (!check.isChecked()) {
                    Toast.makeText(context, ""+context.getString(R.string.text_check), Toast.LENGTH_LONG).show();
                }else {
                    PayEmi(context,LoanId,checkDate,token);
                }
            }
        });
        alertDialog.show();
    }
    public void PayEmi(Activity context, String loanId, String checkDate, String Token) {
        HashMap<String, String> map = new HashMap<>();
        map.put("loan_id", loanId);
        map.put("check_date", checkDate);
        map.put("status", "");
//        map.put("token", myPrefs.getToken());
        Log.e("PayLoansFragment", "Token=" + Token);
        Log.e("PayLoansFragment", "loanId=" + loanId);
        Log.e("PayLoansFragment", "check_date=" + checkDate);

        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<EmiRequest> categoryList = apiInterface.emiRequest(Token, map);

        categoryList.enqueue(new Callback<EmiRequest>() {
            @Override
            public void onResponse(Call<EmiRequest> call, Response<EmiRequest> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()==true) {
                        Util.displayRightSnackbar(constraintLayout, activity, response.body().getMessage());
                        alertDialog.dismiss();
                    } else {
//                        Toast.makeText(mainFragmentApi, "Your internet connection is slow.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (response.body() != null) {
//                        progressBar.setVisibility(View.GONE);
                    } else {
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(mainFragmentApi, "Your internet connection is slow.", Toast.LENGTH_SHORT).show();
                        Util.displayRongSnackbar(constraintLayout, activity, "Your internet connection is slow.");
                    }
                }
            }

            @Override
            public void onFailure(Call<EmiRequest> call, Throwable t) {
//            progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
