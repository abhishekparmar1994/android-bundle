package com.ckinfotech.investor.customfr;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ckinfotech.investor.Activity.MainActivity;
import com.ckinfotech.investor.Activity.ProgressDialognew;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.model.userLogin.UserModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;
import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class UserNomineeFragment extends BaseFragment {

    View view;
    Button btn_next;
    private FragmentManager fragmentManager;
    private EditText edtnamefirst, edtnumberfirst, edtnamesecond, edtnumbersecond;
    private MyPrefs myPrefs;
    ConstraintLayout constraintLayout;
    ProgressDialognew progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_usernominee, container, false);
        text_toolbare.setText("Nominee");

        init(view);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Error();
            }
        });
        return view;
    }

    private void init(View view) {
        myPrefs = new MyPrefs(activity);
        progressDialog = new ProgressDialognew();
        edtnamefirst = view.findViewById(R.id.edtNomineeNameFirst);
        edtnumberfirst = view.findViewById(R.id.edtNomineeNumberFirst);
        edtnamesecond = view.findViewById(R.id.edtNomineeNameSecond);
        edtnumbersecond = view.findViewById(R.id.edtNomineeNumberSecond);
        btn_next = view.findViewById(R.id.btnUserNominnext);

//        constraintLayout = view.findViewById(R.id.abcdfer);

//        Util.createSnackBar(getActivity(), constraintLayout);
//        Util.displaySnackbar(constraintLayout, getContext(),"Bhai Bhai");
    }

    private void Error() {
        if (edtnamefirst.getText().toString().isEmpty()) {
            edtnamefirst.setError("Enter Name");
            edtnamefirst.requestFocus();
        }else if (edtnumberfirst.getText().toString().isEmpty()) {
            edtnumberfirst.setError("Enter Mobile Number");
            edtnumberfirst.requestFocus();
        }else if (edtnamesecond.getText().toString().isEmpty()) {
            edtnamesecond.setError("Enter Name");
            edtnamesecond.requestFocus();
        }else if (edtnumbersecond.getText().toString().isEmpty()) {
            edtnumbersecond.setError("Enter Mobile Number");
            edtnumbersecond.requestFocus();
        }else {
            progressDialog.show(activity.getSupportFragmentManager(),"");
            submitDetailsOne();
        }
    }

    public void submitDetailsOne() {
        HashMap<String, String> map = new HashMap<>();
        map.put("step", "three");
        map.put("nominee_name1", edtnamefirst.getText().toString().trim());
        map.put("nominee_mobile_number1", edtnumberfirst.getText().toString().trim());
        map.put("nominee_name2", edtnamesecond.getText().toString().trim());
        map.put("nominee_mobile_number2", edtnumbersecond.getText().toString().trim());
        map.put("loan_id", myPrefs.getLoanId());
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<UserModel> categoryList = apiInterface.userDetailone(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getResult().equalsIgnoreCase("true")) {
                        myPrefs.setLoanId(response.body().getLoan_id());
                        fragmentManager = activity.getSupportFragmentManager();
                        LoanAmmountFragment fr = new LoanAmmountFragment();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(fr.getTag());
                        transaction.replace(R.id.frameLayout, fr);
                        transaction.commit();
                        progressDialog.dismiss();
                        Log.e("Response", "getMsg" + response.body().getMessage());
                        Util.displayRightSnackbar(constraintLayout, activity,response.body().getMessage());
                        myPrefs.setUserLoanStep("three");
                    }else {
                        progressDialog.dismiss();
                        Util.displayRongSnackbar(constraintLayout, activity,response.body().getMessage());
                    }
                } else {
                    progressDialog.dismiss();
                    Util.displayRongSnackbar(constraintLayout, activity,response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                progressDialog.dismiss();
                Util.displayErrorSnackbar(constraintLayout, activity, "Server not responding");
            }
        });
    }

}
