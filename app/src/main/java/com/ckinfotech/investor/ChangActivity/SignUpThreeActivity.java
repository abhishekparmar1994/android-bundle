package com.ckinfotech.investor.ChangActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ckinfotech.investor.Activity.ProgressDialognew;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.model.SignUp.SignUp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;

public class SignUpThreeActivity extends AppCompatActivity {

    private EditText edtnem, edtrelasion, edtadharecard, edtmobilenunber;
    Button btnNext;
    ConstraintLayout constraintLayoutOne;
    ProgressDialognew progressDialog;
    MyPrefs myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_three);
        init();
        click();
    }


    private void init() {
        progressDialog = new ProgressDialognew();
        edtnem = findViewById(R.id.edtNameTheree);
        edtrelasion = findViewById(R.id.edtRelasionThree);
        edtadharecard = findViewById(R.id.edtAdharecardNumberThreee);
        edtmobilenunber = findViewById(R.id.edtNumberThreee);
        btnNext = findViewById(R.id.btnNextThree);

        constraintLayoutOne = findViewById(R.id.constrenSignThree);
        Util.createSnackBar(SignUpThreeActivity.this, constraintLayoutOne);

        myPrefs = new MyPrefs(this);
    }

    private void click() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Error();
            }
        });
    }

    private void Error() {
        if (edtnem.getText().toString().isEmpty()) {
            edtnem.setError("Enter Name");
            edtnem.requestFocus();
        } else if (edtrelasion.getText().toString().isEmpty()) {
            edtrelasion.setError("Enter Relation");
            edtrelasion.requestFocus();
        } else if (edtadharecard.getText().toString().isEmpty()) {
            edtadharecard.setError("Enter Aadhar Care Number");
            edtadharecard.requestFocus();
        } else if (edtmobilenunber.getText().toString().isEmpty()) {
            edtmobilenunber.setError("Enter Mobile Number");
            edtmobilenunber.requestFocus();
        } else {
            submitDetailsOne();
        }
    }

    public void submitDetailsOne() {
        progressDialog.show(getSupportFragmentManager(), "");
        HashMap<String, String> map = new HashMap<>();
        map.put("step", "three");
        map.put("id", myPrefs.getID());
        map.put("nominee_name", edtnem.getText().toString().trim());
        map.put("nominee_relation", edtrelasion.getText().toString().trim());
        map.put("nominee_contact_number", edtmobilenunber.getText().toString().trim());
        map.put("nominee_aadhar_number", edtadharecard.getText().toString().trim());

        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<SignUp> categoryList = apiInterface.registeruserTwo(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                Log.e("SignUpThreeActivity", "getID" + myPrefs.getID());
                if (response.isSuccessful()) {
                    if (response.body().getResult().equalsIgnoreCase("true")) {
//                        myPrefs.setLoanId(response.body().getLoan_id());
                        myPrefs.setUserLoanStep("three");

                        Intent intent = new Intent(SignUpThreeActivity.this,SignupFourActivity.class);
                        startActivity(intent);
                        Log.e("SignUpThreeActivity", "getMsg" + response.body().getMessage());
//                        Log.e("Response", "LoneId" + village_id);
//                        Log.e("getLoanId", "LoneId" + myPrefs.getLoanId());
                        Util.displayRightSnackbar(constraintLayout, SignUpThreeActivity.this, response.body().getMessage());

                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                        Util.displayRongSnackbar(constraintLayout, SignUpThreeActivity.this, response.body().getMessage());
                        Log.e("SignUpThreeActivity", "getMsg" + response.body());
//                        Log.e("getLoanId", "LoneId" + myPrefs.getLoanId());
                    }
                } else {
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                progressDialog.dismiss();
                Util.displayErrorSnackbar(constraintLayout, SignUpThreeActivity.this, "Server not responding");
            }
        });
    }

}