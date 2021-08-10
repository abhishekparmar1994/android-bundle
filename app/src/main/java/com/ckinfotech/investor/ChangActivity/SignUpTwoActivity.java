package com.ckinfotech.investor.ChangActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ckinfotech.investor.Activity.ProgressDialognew;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.model.SignUp.SignUp;
import com.ckinfotech.investor.model.userLogin.UserModel;

import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;

public class SignUpTwoActivity extends AppCompatActivity {

    private EditText edtdesignation, edtcompany_name, edtsalary, job_start_date;
    ProgressDialognew progressDialog;
    ConstraintLayout constraintLayoutOne;
    private Button btnOnenext;
    MyPrefs myPrefs;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_two);
        init();
        click();

    }

    private void click() {

        btnOnenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Error();
            }
        });
        job_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpTwoActivity.this,
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
                                    job_start_date.setText(year + "-" + formattedMonth + "-" + formattedDayOfMonth);
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });
    }

    private void init() {
        progressDialog = new ProgressDialognew();
        edtdesignation = findViewById(R.id.edtDesignationTwo);
        edtcompany_name = findViewById(R.id.edtDesignationTwo);
        edtsalary = findViewById(R.id.edtsalaryTwo);
        job_start_date = findViewById(R.id.edtjob_start_dateTwo);
        btnOnenext = findViewById(R.id.btnUserDetailNextTwo);

        constraintLayoutOne = findViewById(R.id.constrenSignTwo);
        Util.createSnackBar(SignUpTwoActivity.this, constraintLayoutOne);

        myPrefs = new MyPrefs(this);
        job_start_date.setFocusable(false);
    }

    private void Error() {
        if (edtdesignation.getText().toString().isEmpty()) {
            edtdesignation.setError("Enter Designation");
            edtdesignation.requestFocus();
        } else if (edtcompany_name.getText().toString().isEmpty()) {
            edtcompany_name.setError("Enter Company Name");
            edtcompany_name.requestFocus();
        } else if (edtsalary.getText().toString().isEmpty()) {
            edtsalary.setError("Enter Salary");
            edtsalary.requestFocus();
        } else if (job_start_date.getText().toString().isEmpty()) {
            job_start_date.setError("Enter Job Start Date");
            job_start_date.requestFocus();
        } else {

            submitDetailsOne();
        }
    }

    public void submitDetailsOne() {
        progressDialog.show(getSupportFragmentManager(), "");
        HashMap<String, String> map = new HashMap<>();
        map.put("step", "two");
        map.put("id", myPrefs.getID());
        map.put("designation", edtdesignation.getText().toString().trim());
        map.put("company_name", edtcompany_name.getText().toString().trim());
        map.put("salary", edtsalary.getText().toString().trim());
        map.put("job_start_date", job_start_date.getText().toString().trim());

        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<SignUp> categoryList = apiInterface.registeruserTwo(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                Log.e("SignUpTwoActivity", "getID" + myPrefs.getID());
                if (response.isSuccessful()) {
                    if (response.body().getResult().equalsIgnoreCase("true")) {
//                        myPrefs.setLoanId(response.body().getLoan_id());
                        myPrefs.setUserLoanStep("two");
                        Intent intent = new Intent(SignUpTwoActivity.this,SignUpThreeActivity.class);
                        startActivity(intent);
                        Log.e("SignUpTwoActivity", "getMsg" + response.body().getMessage());
//                        Log.e("Response", "LoneId" + village_id);
//                        Log.e("getLoanId", "LoneId" + myPrefs.getLoanId());
                        Util.displayRightSnackbar(constraintLayout, SignUpTwoActivity.this, response.body().getMessage());

                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                        Util.displayRongSnackbar(constraintLayout, SignUpTwoActivity.this, response.body().getMessage());
                        Log.e("SignUpTwoActivity", "getMsg" + response.body());
//                        Log.e("getLoanId", "LoneId" + myPrefs.getLoanId());
                    }
                } else {
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                progressDialog.dismiss();
                Util.displayErrorSnackbar(constraintLayout, SignUpTwoActivity.this, "Server not responding");
            }
        });
    }

}