package com.ckinfotech.investor.customfr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.ckinfotech.investor.Activity.MainActivity;
import com.ckinfotech.investor.R;

import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class ContactFragment extends BaseFragment  {

    View view;
    Button btnCall,btnWatch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact, container, false);
        text_toolbare.setText("Contact Us");
//        myPrefs = new MyPrefs(activity);
        init(view);
        click();

        return view;
    }

    private void init(View view) {
        btnCall = view.findViewById(R.id.btncontcall);
        btnWatch = view.findViewById(R.id.btncontwatch);
    }
    private void click() {
        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse
                        ("https://wa.me/+917874805480?text=Investor"));
                startActivity(intent);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAtRuntime();
            }
        });
    }
    private void callAtRuntime() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && activity.checkSelfPermission(Manifest.permission.CALL_PHONE)

                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
        else {
            String phoneNumber = "07874805480";

            Intent intent = new Intent(Intent.ACTION_CALL);

            intent.setData(Uri.parse("tel:"+ phoneNumber));

            startActivity(intent);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                callAtRuntime();

            }

            else {

                Toast.makeText(activity, "5454", Toast.LENGTH_SHORT).show();

            }

        }

    }
}
