package com.ckinfotech.investor.customfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ckinfotech.investor.Activity.MainActivity;
import com.ckinfotech.investor.Adepter.MonthIdAdapter;
import com.ckinfotech.investor.Adepter.PayLoansAdapter;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.customfr.BaseFragment;
import com.ckinfotech.investor.customfr.LogOutFragment;
import com.ckinfotech.investor.model.IdModel;
import com.ckinfotech.investor.model.LoanData.LoanData;
import com.ckinfotech.investor.model.emiRequest.EmiRequest;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;
import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class LoanStatusFragment extends BaseFragment {
    View view;
    TextView txtID, txtAmount, txtMonth, txtEmiAmount, txtEMIDate, txtLoanMassage, txtProsesingFee, txtPayableAmount, txtPendingAmount;
    LinearLayout linApplyDetails;
    MyPrefs myPrefs;
    Button btnLoanClose;
    String LoanId,CurrentDate;
    AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loanstatus, container, false);
        text_toolbare.setText("Loan Status");
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
        txtID = view.findViewById(R.id.txtLoanStatusLoanID);
        txtAmount = view.findViewById(R.id.txtLoanStatusAmount);
        txtMonth = view.findViewById(R.id.txtLoanStatusMonth);
        txtEmiAmount = view.findViewById(R.id.txtLoanStatusEMIAmount);
        txtEMIDate = view.findViewById(R.id.txtLoanStatusEMIDate);
        txtProsesingFee = view.findViewById(R.id.txtLoanStatusProsesingFiss);
        txtPayableAmount = view.findViewById(R.id.txtLoanStatusPayloanamount);
        txtPendingAmount = view.findViewById(R.id.txtLoanStatuspendingAmmount);

        btnLoanClose = view.findViewById(R.id.btnLoanStatusCloseLone);

        txtLoanMassage = view.findViewById(R.id.txtLoanStatusMasage);
        linApplyDetails = view.findViewById(R.id.linLoanStatusApplyDetail);


//        if(!TextUtils.isEmpty(myPrefs.getAppLoanStatus()) &&myPrefs.getAppLoanStatus().equalsIgnoreCase("approve")) {
//            txtID.setText(myPrefs.getID());
//            txtAmount.setText(myPrefs.getLoanAmountApply());
//            txtMonth.setText(myPrefs.getLoanMonthApply() + " Month");
//            txtEmiAmount.setText(myPrefs.getLoanEmiAmount());
//            txtEMIDate.setText(myPrefs.getLoanEmiDate());
//
//            txtLoanMassage.setVisibility(View.GONE);
//
//        }else {
//            txtLoanMassage.setVisibility(View.VISIBLE);
//            linApplyDetails.setVisibility(View.GONE);
//            txtLoanMassage.setText(myPrefs.getApplyLoanMessage());
//        }
        if (!TextUtils.isEmpty(myPrefs.getAppLoanStatus()) && myPrefs.getAppLoanStatus().equalsIgnoreCase("approve")) {
            PayLoanData();
        } else {
            txtLoanMassage.setVisibility(View.VISIBLE);
            btnLoanClose.setVisibility(View.GONE);
            linApplyDetails.setVisibility(View.GONE);
            txtLoanMassage.setText(myPrefs.getApplyLoanMessage());
        }
    }

    private void click() {
        btnLoanClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoanCloseDilog(v);
            }
        });
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
                txtID.setText(myPrefs.getID());
                txtAmount.setText(response.body().getLoanDataInfo().getTotalLoanAmount());
                txtProsesingFee.setText(response.body().getLoanDataInfo().getTotalProcessingFee());
                txtEmiAmount.setText(myPrefs.getLoanEmiAmount());
                txtMonth.setText(myPrefs.getLoanMonthApply() + " Month");
                txtEMIDate.setText(response.body().getLoanDataInfo().getEmiBounceDate());
                txtPayableAmount.setText(response.body().getLoanDataInfo().getTotalLoanAmountWithInterest());
                txtPendingAmount.setText(response.body().getLoanDataInfo().getPendingAmount());

                LoanId =  response.body().getLoanDataInfo().getLoanId();
                CurrentDate =  response.body().getLoanDataInfo().getCurrentDate();
                Log.e("LoanStatusFragment", new Gson().toJson(response.body()));

                Log.e("LoanStatusFragment", "getToken:=" + myPrefs.getToken());
                Log.e("LoanStatusFragment", "LoanStatusFragment:=" + response.body());
                Log.e("LoanStatusFragment", "LoanId:=" + response.body().getLoanDataInfo().getLoanId());
                Log.e("LoanStatusFragment", "CurrentDate:=" + response.body().getLoanDataInfo().getCurrentDate());
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equalsIgnoreCase("true")) {

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

    public void LoanCloseDilog(View v) {
        Rect displayRectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.CustomAlertDialog);
        ViewGroup viewGroup = v.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dilog_loan_close, viewGroup, false);
        TextView txtPayAmount = dialogView.findViewById(R.id.txtDiloGLoanCloseAmount);
        Button btnLoanClose = dialogView.findViewById(R.id.btnDilogCloseLone);
        CheckBox check = dialogView.findViewById(R.id.cheLoanClose);

        txtPayAmount.setText(txtPendingAmount.getText().toString());
        ImageView imageClosr = dialogView.findViewById(R.id.dilogloan_close);

        dialogView.setMinimumWidth((int) (displayRectangle.width() * 0f));
        dialogView.setMinimumHeight((int) (displayRectangle.height() * 0f));
        builder.setView(dialogView);
        builder.setCancelable(false);


         alertDialog = builder.create();
//        dilogSetItem();
        imageClosr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                spinKitView.setVisibility(View.VISIBLE);
//                linearLayout.setVisibility(View.GONE);
                alertDialog.dismiss();
            }
        });
        btnLoanClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                spinKitView.setVisibility(View.VISIBLE);
//                linearLayout.setVisibility(View.GONE);
//                alertDialog.dismiss();
                if (!check.isChecked()) {
                    Toast.makeText(activity, getResources().getString(R.string.text_check), Toast.LENGTH_LONG).show();
                }else {
                    if(!LoanId.isEmpty()&&!CurrentDate.isEmpty()) {
                        LoanClose();
                    }
                }
            }
        });
        alertDialog.show();
    }
    public void LoanClose() {

        HashMap<String, String> map = new HashMap<>();
        map.put("loan_id", LoanId);
        map.put("check_date", CurrentDate);
        map.put("status", "loan_close");
//        map.put("token", myPrefs.getToken());
        Log.e("LoanStatusFragment", "Token=" + LoanId);
        Log.e("LoanStatusFragment", "loanId=" + CurrentDate);

        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<EmiRequest> categoryList = apiInterface.emiRequest(myPrefs.getToken(), map);

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
