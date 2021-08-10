package com.ckinfotech.investor.Network;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.OnClickListener;
import com.google.android.material.snackbar.Snackbar;

public class Util {
    static Snackbar snackbar;
    static View customSnackView;
    static Context context;
    static View view;
    CoordinatorLayout coordinatorLayout;
    private static Button bGotoWebsite;
    static TextView textView1;
    static OnClickListener onClickListener;
    static ImageView imgicon;

    public Util(Context ctx, View view1) {


        // inflate the custom_snackbar_view created previously
//        snackbar = Snackbar.make(customSnackView, "", Snackbar.LENGTH_LONG);

//        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator_layout_login);

    }


    public static void createSnackBar(Context ctx, View view1) {

        context = ctx;
        view = view1;
//        onClickListener = onClickListene;
        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
        customSnackView = LayoutInflater.from(context).inflate(R.layout.custom_snackbar_view, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        // now change the layout of the snackbar
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        // set padding of the all corners as 0
        snackbarLayout.setPadding(0, 0, 0, 0);

        // register the button from the custom_snackbar_view layout file
        bGotoWebsite = customSnackView.findViewById(R.id.gotoWebsiteButton);
        textView1 = customSnackView.findViewById(R.id.textView1);
        imgicon = customSnackView.findViewById(R.id.imageViewUtil);


        snackbarLayout.addView(customSnackView, 0);

        // now handle the same button with onClickListener


    }


    public static void btnClick(String fragmentMsg) {
        bGotoWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(fragmentMsg);
//                Toast.makeText(context, "Heloo......", Toast.LENGTH_SHORT).show();
                snackbar.dismiss();
            }
        });
    }


    public static void showSnakbarTypeOne(View rootView, String mMessage) {
        Snackbar.make(rootView, mMessage, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

    public static void displaySnackbar(View view1, Context context, String s) {
        imgicon.setVisibility(View.GONE);
        textView1.setText(s);
        snackbar.show();
    }
    public static void displayRightSnackbar(View view1, Context context, String s) {
        imgicon.setImageResource(R.drawable.ic_right);
        textView1.setText(s);
        snackbar.show();
    }
    public static void displayRongSnackbar(View view1, Context context, String s) {
        try {
            imgicon.setImageResource(R.drawable.ic_rong);
            textView1.setText(s);
            snackbar.show();
        }catch (Exception e){

        }

    }
    public static void displayErrorSnackbar(View view1, Context context, String s) {
        imgicon.setImageResource(R.drawable.ic_rong);
        textView1.setText(s);
        snackbar.show();
    }
}
