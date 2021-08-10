package com.ckinfotech.investor.customfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ckinfotech.investor.R;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.customfr.BaseFragment;
import com.ckinfotech.investor.customfr.UserDocumentFragment;

import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class OfferFragment extends BaseFragment {

    View view;
    Button btnApply, btnShareRefrel;
    TextView txtAccountId, txtOffer, txtDocumentStatus;
    MyPrefs myPrefs;
    LinearLayout linOffer, linDocument, linApplyButton;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_offer, container, false);
        text_toolbare.setText("invester");
//        myPrefs = new MyPrefs(activity);
        init(view);
        click();
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    dialogShow();
//                    getFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });

//        if(myPrefs.getPaymentRequestStatush().equalsIgnoreCase("pending")){
//           activity.generalService();

//        }

        return view;
    }


    private void init(View view) {
        myPrefs = new MyPrefs(activity);
        txtAccountId = view.findViewById(R.id.txtOfferAccountId);
        txtOffer = view.findViewById(R.id.txtOfferDocument);
        txtDocumentStatus = view.findViewById(R.id.txtOfferDocumentStatus);

        linOffer = view.findViewById(R.id.linOffer);
        linDocument = view.findViewById(R.id.linOfferDocument);

        linApplyButton = view.findViewById(R.id.linOfferApply);
        btnShareRefrel = view.findViewById(R.id.btnOfferShare);
        btnApply = view.findViewById(R.id.btnOfferMemberApply);

//        if(myPrefs.getMembershipStatus().equalsIgnoreCase("active")){
//            txtAccountId.setVisibility(View.VISIBLE);
        if (myPrefs.getDocumentVerification().equalsIgnoreCase("pending") && myPrefs.getMembershipStatus().equalsIgnoreCase("inactive")) {
            linOffer.setVisibility(View.VISIBLE);
            linDocument.setVisibility(View.VISIBLE);
        } else if (myPrefs.getMembershipStatus().equalsIgnoreCase("inactive")) {
            linOffer.setVisibility(View.GONE);
            linDocument.setVisibility(View.GONE);
            linApplyButton.setVisibility(View.VISIBLE);
        } else if (myPrefs.getDocumentVerification().equalsIgnoreCase("approve") && myPrefs.getMembershipStatus().equalsIgnoreCase("active")) {
            linOffer.setVisibility(View.VISIBLE);
            linDocument.setVisibility(View.VISIBLE);
            linApplyButton.setVisibility(View.GONE);
        } else if (myPrefs.getMembershipStatus().equalsIgnoreCase("active")) {
            linOffer.setVisibility(View.VISIBLE);
            linDocument.setVisibility(View.VISIBLE);
            linApplyButton.setVisibility(View.GONE);
        }


        txtAccountId.setText(myPrefs.getAccountId());
        txtOffer.setText(myPrefs.getOfferDetails());
        txtDocumentStatus.setText(myPrefs.getDocumentVerification());
        Log.e("Offer", "AccountId" + myPrefs.getAccountId());
        Log.e("Offer", "OfferDetails" + myPrefs.getOfferDetails());
        Log.e("Offer", "DocumentVerification" + myPrefs.getDocumentVerification());
//        }
    }

    public void dialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.drawable.splace);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activity.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void click() {
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = activity.getSupportFragmentManager();
                MemberShipByFragment fr = new MemberShipByFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(fr.getTag());
                transaction.replace(R.id.frameLayout, fr);
                transaction.commit();
            }
        });
        btnShareRefrel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Investor : " + "\nReferral code : " + myPrefs.getRefrelCoad() + "\nhttps://play.google.com/store/apps/details?id=com.starappsworld.statussaver");
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent, "Share via");
                startActivity(sendIntent);
            }
        });
    }
}
