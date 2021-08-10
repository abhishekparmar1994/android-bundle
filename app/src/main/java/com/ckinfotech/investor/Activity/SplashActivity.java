package com.ckinfotech.investor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ckinfotech.investor.ChangActivity.SignUpThreeActivity;
import com.ckinfotech.investor.ChangActivity.SignUpTwoActivity;
import com.ckinfotech.investor.ChangActivity.SignupFourActivity;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.Util.MyPrefs;

public class SplashActivity extends AppCompatActivity {

    private MyPrefs myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myPrefs = new MyPrefs(this);
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(4*1000);

                    // After 5 seconds redirect to another intent
                    if (myPrefs.getAccountId() == null) {
                        Intent i = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(i);
                    }else{
                        if(myPrefs.getUserLoanStep().equalsIgnoreCase("one")){
                            Intent i = new Intent(getBaseContext(), SignUpTwoActivity.class);
                            startActivity(i);
                        }else if(myPrefs.getUserLoanStep().equalsIgnoreCase("two")){
                            Intent i = new Intent(getBaseContext(), SignUpThreeActivity.class);
                            startActivity(i);
                        }else if(myPrefs.getUserLoanStep().equalsIgnoreCase("three")){
                            Intent i = new Intent(getBaseContext(), SignupFourActivity.class);
                            startActivity(i);
                        }else if(myPrefs.getUserLoanStep().equalsIgnoreCase("four")){
                            Intent i = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(i);
                        }

                    }

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }
}