package com.ckinfotech.investor.customfr;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ckinfotech.investor.Activity.LoginActivity;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.customfragment.OfferFragment;

import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class LogOutFragment extends BaseFragment {

    View view;
    Button btnCall, btnWatch;
    MyPrefs myPrefs;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_logout, container, false);
        text_toolbare.setText("LogOut");

        myPrefs = new MyPrefs(activity);
//        init(view);
//        click();
        showCustomDialog(view);
        return view;
    }

    private void showCustomDialog(View view) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.my_dialog, viewGroup, false);

        Button button = dialogView.findViewById(R.id.buttonOk);
        TextView textView = dialogView.findViewById(R.id.diglogmessage);
        ImageView imageView = dialogView.findViewById(R.id.imgdilogview);
        textView.setText("Are you sure you want to logout?");
        button.setVisibility(View.GONE);

        LinearLayout linearLayout = dialogView.findViewById(R.id.linlogout);
        linearLayout.setVisibility(View.VISIBLE);
        Button buttonyes = dialogView.findViewById(R.id.buttonyes);
        Button buttonno = dialogView.findViewById(R.id.buttonno);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();

        buttonyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPrefs.clearAll();
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
            }
        });
        buttonno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(activity, "222", Toast.LENGTH_SHORT).show();
                fragmentManager = activity.getSupportFragmentManager();
                OfferFragment fr = new OfferFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(fr.getTag());
                transaction.replace(R.id.frameLayout, fr);
                transaction.commit();
                alertDialog.dismiss();
            }
        });

        //Now we need an AlertDialog.Builder object

        alertDialog.show();
    }

}