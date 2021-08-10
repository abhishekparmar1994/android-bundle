package com.ckinfotech.investor.Activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import com.ckinfotech.investor.ChangActivity.SignUpOneActivity;
import com.ckinfotech.investor.ChangActivity.SignUpThreeActivity;
import com.ckinfotech.investor.ChangActivity.SignUpTwoActivity;
import com.ckinfotech.investor.ChangActivity.SignupFourActivity;
import com.ckinfotech.investor.Network.MyReceiver;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.model.LoginUser.LoginUserList;
import com.ckinfotech.investor.model.userLogin.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;

public class LoginActivity extends AppCompatActivity {

    View view;
    Button button_login, button_signup;
    EditText edt_email, edt_password;
    private FragmentManager fragmentManager;
    APIService apiInterface;
    private BroadcastReceiver MyReceiver = null;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String token;
    private MyPrefs myPrefs;
    ProgressDialognew progressDialog;
    public ConstraintLayout loginConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        init();

        broadcastIntent();


        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignUpOneActivity.class);
                startActivity(intent);
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Error();
            }
        });
    }

    private void init() {
        progressDialog = new ProgressDialognew();
        MyReceiver = new MyReceiver();
        myPrefs = new MyPrefs(this);
        button_login = findViewById(R.id.btnLoginLogin);
        button_signup = findViewById(R.id.btnLoginSignup);
        edt_email = findViewById(R.id.edtLoginMobile);
        edt_password = findViewById(R.id.edtLoginPassword);
        loginConstraintLayout = findViewById(R.id.constrainlogin);
        Util.createSnackBar(LoginActivity.this, loginConstraintLayout);
    }

    public void sendUserLogin(String email, String password) {
//        Toast.makeText(activity, "1111", Toast.LENGTH_SHORT).show();
        Call<LoginUserList> mCall;
        apiInterface = APIClient.getClient().create(APIService.class);
        mCall = apiInterface.userLogin(email, password);
        Log.e("api", "" + mCall.request().url());
        mCall.enqueue(new Callback<LoginUserList>() {
            @Override
            public void onResponse(Call<LoginUserList> call, Response<LoginUserList> response) {

                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equalsIgnoreCase("true")) {
                        myPrefs.setID(String.valueOf(response.body().getData().getId()));
                        myPrefs.setAccountId(String.valueOf(response.body().getData().getAccountId()));
                        myPrefs.setToken(response.body().getToken());
                        myPrefs.setSuccessLogin(response.body().getSuccess());
                        myPrefs.setUserName(response.body().getData().getFirstName());
                        myPrefs.setUserEmail(response.body().getData().getEmail());
                        myPrefs.setUserNumber(response.body().getData().getContactNumber());
//                        myPrefs.setUserImageLink(String.valueOf(response.body().getData().getUserImage()));
                        myPrefs.setRefrelCoad(response.body().getData().getReferralCode());
                        myPrefs.setUserLoanStep(response.body().getData().getStep());
                        if (!response.body().getData().getStep().isEmpty()) {
                            loanStep();
                        }
                        progressDialog.dismiss();
                        Util.displayRightSnackbar(constraintLayout, LoginActivity.this, response.body().getMsg());
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
                    } else {
                        progressDialog.dismiss();
                        Util.displayRongSnackbar(constraintLayout, LoginActivity.this, response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginUserList> call, Throwable t) {
                progressDialog.dismiss();
                Util.displayErrorSnackbar(constraintLayout, LoginActivity.this, "Server not responding");
            }
        });
    }

    public void broadcastIntent() {
        LoginActivity.this.registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
//        mainFragment.unregisterReceiver(MyReceiver);
    }

    private void Error() {
        if (edt_email.getText().toString().isEmpty()) {
            edt_email.setError("Enter FirstName");
            edt_email.requestFocus();
        } else if (edt_password.getText().toString().isEmpty()) {
            edt_password.setError("Enter Password");
            edt_password.requestFocus();
        } else {
            progressDialog.show(LoginActivity.this.getSupportFragmentManager(), "");
            sendUserLogin(edt_email.getText().toString(), edt_password.getText().toString());
        }
    }

    private void loanStep() {
        if (myPrefs.getUserLoanStep().equalsIgnoreCase("one")) {
            Intent intent = new Intent(this, SignUpTwoActivity.class);
           startActivity(intent);
        }
        else if (myPrefs.getUserLoanStep().equalsIgnoreCase("two")) {
            Intent intent = new Intent(this, SignUpThreeActivity.class);
            startActivity(intent);
        }
        else if (myPrefs.getUserLoanStep().equalsIgnoreCase("three")) {
            Intent intent = new Intent(this, SignupFourActivity.class);
            startActivity(intent);
        }else if (myPrefs.getUserLoanStep().equalsIgnoreCase("four")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


}
