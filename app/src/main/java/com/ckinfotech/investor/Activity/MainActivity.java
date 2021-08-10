package com.ckinfotech.investor.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.customfr.LoanAmmountFragment;
import com.ckinfotech.investor.customfr.LoanDetailFragment;
import com.ckinfotech.investor.customfr.LogOutFragment;
import com.ckinfotech.investor.customfr.UserDetailFragment;
import com.ckinfotech.investor.customfr.UserDocumentFragment;
import com.ckinfotech.investor.customfr.UserNomineeFragment;
import com.ckinfotech.investor.customfragment.ApplyLoanFragment;
import com.ckinfotech.investor.customfragment.LoanStatusFragment;
import com.ckinfotech.investor.customfragment.MemberShipByFragment;
import com.ckinfotech.investor.customfragment.OfferFragment;
import com.ckinfotech.investor.customfragment.PayLoansFragment;
import com.ckinfotech.investor.model.GeneralService.GeneralService;
import com.ckinfotech.investor.model.UserAllData.UserAllData;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    private FragmentManager fragmentManager;
    private ViewTreeObserver.OnGlobalLayoutListener listener;
    public static TextView text_toolbare;
    Toolbar toolbar;
    private MyPrefs myPrefs;
    public static ConstraintLayout constraintLayout;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    public static CircleImageView circleImageViewmain;
    public static LinearLayout lin_tranf;
    String dialogshoe = "";
   public ProgressDialognew progressDialog;

    public CircleImageView circleImageVieDilog;
    private TextView txtDilogName, txtDilogEmail, txtDilogShare, txtDilogContus, txtDilogOut, txtDilogLogoutService, txtLoanStatus;
private LinearLayout linLoanStatus,linPayLoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.abcdfer);
        circleImageViewmain = findViewById(R.id.circledrovebull);
        progressDialog = new ProgressDialognew();
//
        Util.createSnackBar(MainActivity.this, constraintLayout);
        myPrefs = new MyPrefs(this);
        toolbar = findViewById(R.id.toolbar);
        text_toolbare = findViewById(R.id.text_toolbare);
        text_toolbare.setText("CKINFOTECH");

//        fragmentManager = getSupportFragmentManager();
//        OfferFragment fr = new OfferFragment();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.addToBackStack(fr.getTag());
//        transaction.replace(R.id.frameLayout, fr);
//        transaction.commit();
//        if(myPrefs.getUserLoanStep()==null) {
//            userDataCall();
//        }
        generalService();
        imageSetCirl();


        circleImageViewmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressDialog.show(MainActivity.this.getSupportFragmentManager(),"");
                Rect displayRectangle = new Rect();
                Window window = MainActivity.this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialog);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.progress_dialog, viewGroup, false);

                SpinKitView spinKitView = dialogView.findViewById(R.id.progressBardilog);
                spinKitView.setVisibility(View.GONE);

                LinearLayout linearLayout = dialogView.findViewById(R.id.lindilogview);
                linearLayout.setVisibility(View.VISIBLE);

                LinearLayout linServiceJoin = dialogView.findViewById(R.id.linDilogServiceJoin);
                LinearLayout linService = dialogView.findViewById(R.id.linDilogService);
                if (!TextUtils.isEmpty(myPrefs.getMembershipStatus())&&myPrefs.getMembershipStatus().equalsIgnoreCase("inactive")) {
                    linServiceJoin.setVisibility(View.GONE);
                    linService.setVisibility(View.VISIBLE);
                } else {
                    linServiceJoin.setVisibility(View.VISIBLE);
                    linService.setVisibility(View.GONE);
                }

                circleImageVieDilog = dialogView.findViewById(R.id.ciecleimgdilog);
                txtDilogName = dialogView.findViewById(R.id.txtdilogName);
                txtDilogEmail = dialogView.findViewById(R.id.txtdilogEmail);
                txtDilogShare = dialogView.findViewById(R.id.txtdilogshare);
                txtDilogContus = dialogView.findViewById(R.id.txtdilogcontus);
                txtDilogOut = dialogView.findViewById(R.id.txtdiloglogout);

                linLoanStatus = dialogView.findViewById(R.id.lindilogloanstatus);
                linPayLoan = dialogView.findViewById(R.id.linDilogpayLoans);

                txtDilogLogoutService = dialogView.findViewById(R.id.txtdiloglogoutservice);

                TextView txtmembership = dialogView.findViewById(R.id.txtdilogmembershipactiv);

                LinearLayout linApplyLoan = dialogView.findViewById(R.id.linDilogApplyLoan);

                ImageView imageClosr = dialogView.findViewById(R.id.dilogimgclose);

                dialogView.setMinimumWidth((int) (displayRectangle.width() * 0f));
                dialogView.setMinimumHeight((int) (displayRectangle.height() * 0f));
                builder.setView(dialogView);
                builder.setCancelable(false);

                fragmentManager = getSupportFragmentManager();

                final AlertDialog alertDialog = builder.create();
                dilogSetItem();
                imageClosr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spinKitView.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.GONE);
                        alertDialog.dismiss();
                    }
                });
                txtDilogShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Investor : " + "\nReferral code : " + myPrefs.getRefrelCoad() + "\nhttps://play.google.com/store/apps/details?id=com.starappsworld.statussaver");
                        sendIntent.setType("text/plain");
                        Intent.createChooser(sendIntent, "Share via");
                        startActivity(sendIntent);
                        alertDialog.dismiss();
                    }
                });
                linApplyLoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ApplyLoanFragment fr = new ApplyLoanFragment();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(fr.getTag());
                        transaction.replace(R.id.frameLayout, fr);
                        transaction.commit();
                        alertDialog.dismiss();
                    }
                });
                txtDilogOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogOutFragment fr = new LogOutFragment();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(fr.getTag());
                        transaction.replace(R.id.frameLayout, fr);
                        transaction.commit();
                        alertDialog.dismiss();
                    }
                });
                txtDilogLogoutService.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LogOutFragment fr = new LogOutFragment();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(fr.getTag());
                        transaction.replace(R.id.frameLayout, fr);
                        transaction.commit();
                        alertDialog.dismiss();
                    }
                });
//                if(!myPrefs.getAppLoanStatus().equalsIgnoreCase("inactive")&&!TextUtils.isEmpty(myPrefs.getAppLoanStatus()))
                linLoanStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoanStatusFragment fr = new LoanStatusFragment();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(fr.getTag());
                        transaction.replace(R.id.frameLayout, fr);
                        transaction.commit();
                        alertDialog.dismiss();
                    }
                });

                linPayLoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PayLoansFragment fr = new PayLoansFragment();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(fr.getTag());
                        transaction.replace(R.id.frameLayout, fr);
                        transaction.commit();
                        alertDialog.dismiss();
                    }
                });

                txtmembership.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MemberShipByFragment fr = new MemberShipByFragment();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(fr.getTag());
                        transaction.replace(R.id.frameLayout, fr);
                        transaction.commit();
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

    }

    public void generalService() {
//        Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();
        Log.e("MainActivity", "ID" +myPrefs.getID());
        Log.e("MainActivity", "Token" + myPrefs.getToken());
        progressDialog.show(MainActivity.this.getSupportFragmentManager(), "");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(myPrefs.getID()));
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<GeneralService> categoryList = apiInterface.generalService(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<GeneralService>() {
            @Override
            public void onResponse(Call<GeneralService> call, Response<GeneralService> response) {

                Log.e("MainActivity", new Gson().toJson(response.body()));

//                arr.clear();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("true")) {
//                        Toast.makeText(MainActivity.this, "22", Toast.LENGTH_SHORT).show();
                        myPrefs.setAccountId(String.valueOf(response.body().getGeneralServiceData().getGeneralServiceUser().getAccountId()));
                        myPrefs.setPaymentAmount(String.valueOf(response.body().getGeneralServiceData().getPaymentAmount()));
                        myPrefs.setMembershipStatus(response.body().getGeneralServiceData().getGeneralServiceMembership().getMembershipStatus());
                        myPrefs.setOfferDetails(response.body().getGeneralServiceData().getOfferDetails());
                        myPrefs.setDocumentVerification(response.body().getGeneralServiceData().getGeneralServiceUser().getDocumentVerification());
                        myPrefs.setLoanMonth(response.body().getGeneralServiceData().getEmiMonth());
                        myPrefs.setShareMobileNumber(response.body().getGeneralServiceData().getShareMobileNumber());
                        myPrefs.setPaymentRequestStatus(response.body().getGeneralServiceData().getPaymentRequestStatus());

                        myPrefs.setAppLoanStatus(response.body().getGeneralServiceData().getGeneralServiceLoan().getLoanStatus());
                        myPrefs.setApplyLoanMessage(response.body().getGeneralServiceData().getGeneralServiceLoan().getLoanStatusMessage());

                        myPrefs.setLoanAmountApply(response.body().getGeneralServiceData().getGeneralServiceLoan().getLoanAmount());
                        myPrefs.setLoanMonthApply(response.body().getGeneralServiceData().getGeneralServiceLoan().getLoanEmiMonth());
                        myPrefs.setLoanEmiAmount(response.body().getGeneralServiceData().getGeneralServiceLoan().getMonthlyEmiAmount());
                        myPrefs.setLoanEmiDate(response.body().getGeneralServiceData().getGeneralServiceLoan().getEmiDate());

                        Log.e("MainActivity","LoanMonth:-"+myPrefs.getLoanMonth());
                        Log.e("MainActivity","ShareMobileNumbert:-"+myPrefs.getShareMobileNumbert());
                        Log.e("MainActivity","PaymentRequestStatush:-"+myPrefs.getPaymentRequestStatush());
                        Log.e("MainActivity","AppLoanStatus:-"+myPrefs.getAppLoanStatus());
                        Log.e("MainActivity","AppLoanStatus:-"+myPrefs.getApplyLoanMessage());
//                        Log.e("MainActivity","LoanStatus:-"+myPrefs.getAppLoanStatus());
//                    stateSpinner(response.body().getData());
//                    Toast.makeText(MainActivity.this, "" + response.body().getGeneralServiceData().getOfferDetails(), Toast.LENGTH_SHORT).show();
//                        Log.e("8498498449494", "----" + response.body().getGeneralServiceData().getGeneralServiceUser().getDocumentVerification());
                        fragmentManager = getSupportFragmentManager();
                        OfferFragment fr = new OfferFragment();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(fr.getTag());
                        transaction.replace(R.id.frameLayout, fr);
                        transaction.commit();
                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                    }

//                    Log.e("getUserLoanStep", "getKEY_ID---" + myPrefs.getKEY_ID());
//                    Log.e("AccountI", "" + myPrefs.getID());

                }
            }

            @Override
            public void onFailure(Call<GeneralService> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MainActivity", "+++++" + t);
            }
        });

    }

    private void loanStep() {
        Log.e("getUserLoanStep", "99999" + myPrefs.getUserLoanStep());
        if (myPrefs.getUserLoanStep() == null) {
            fragmentManager = getSupportFragmentManager();
            UserDetailFragment fr = new UserDetailFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(fr.getTag());
            transaction.replace(R.id.frameLayout, fr);
            transaction.commit();
        } else if (myPrefs.getUserLoanStep().equalsIgnoreCase("one")) {
//            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            fragmentManager = getSupportFragmentManager();
            UserDocumentFragment fr = new UserDocumentFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(fr.getTag());
            transaction.replace(R.id.frameLayout, fr);
            transaction.commit();
        } else if (myPrefs.getUserLoanStep().equalsIgnoreCase("two")) {
//            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
            fragmentManager = getSupportFragmentManager();
            UserNomineeFragment fr = new UserNomineeFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(fr.getTag());
            transaction.replace(R.id.frameLayout, fr);
            transaction.commit();
        } else if (myPrefs.getUserLoanStep().equalsIgnoreCase("three")) {
//            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
            fragmentManager = getSupportFragmentManager();
            LoanAmmountFragment fr = new LoanAmmountFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(fr.getTag());
            transaction.replace(R.id.frameLayout, fr);
            transaction.commit();
        } else if (myPrefs.getUserLoanStep().equalsIgnoreCase("Four")) {
//            Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
            fragmentManager = getSupportFragmentManager();
            LoanDetailFragment fr = new LoanDetailFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(fr.getTag());
            transaction.replace(R.id.frameLayout, fr);
            transaction.commit();
        }

    }

    public void imageSetCirl() {

//        Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
        if (myPrefs.getUserImageLink() == null) {
            Glide.with(this)
                    .load("http://via.placeholder.com/300.png")
                    .override(60, 60)
                    .centerCrop()
                    .into(circleImageViewmain);
        } else {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.usercircle);
//            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//            roundedBitmapDrawable.setCircular(true);
//            circleImageViewmain.setImageDrawable(roundedBitmapDrawable);
            circleImageViewmain.setImageResource(R.drawable.usercircle);
        }
    }

    public void dilogSetItem() {

//        Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
        if (myPrefs.getUserImageLink() == null) {
            Glide.with(this)
                    .load("http://via.placeholder.com/300.png")
                    .override(80, 80)
                    .centerCrop()
                    .into(circleImageVieDilog);
            txtDilogName.setText(myPrefs.getUserName());
            txtDilogEmail.setText(myPrefs.getUserEmail());
        } else {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.usercircle);
//            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//            roundedBitmapDrawable.setCircular(true);
//            circleImageViewmain.setImageDrawable(roundedBitmapDrawable);
            txtDilogName.setText(myPrefs.getUserName());
            txtDilogEmail.setText(myPrefs.getUserEmail());
            circleImageVieDilog.setImageResource(R.drawable.diloguser);
        }
    }

    public void userDataCall() {
        progressDialog.show(MainActivity.this.getSupportFragmentManager(), "");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(myPrefs.getKEY_ID()));
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<UserAllData> categoryList = apiInterface.userAllData(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<UserAllData>() {
            @Override
            public void onResponse(Call<UserAllData> call, Response<UserAllData> response) {

                Log.e("myresponceinMain", new Gson().toJson(response.body()));

//                arr.clear();
                if (response.isSuccessful()) {
                    if (!response.body().getData().getLoans().isEmpty()) {
//                        Toast.makeText(MainActivity.this, "22", Toast.LENGTH_SHORT).show();
                        myPrefs.setUserLoanStep(String.valueOf(response.body().getData().getLoans().get(0).getStep()));
                        myPrefs.setLoanId(String.valueOf(response.body().getData().getLoans().get(0).getId()));
                        loanStep();
                    } else {
                        myPrefs.setUserLoanStep(null);
                        myPrefs.setLoanId("");
//                        Toast.makeText(MainActivity.this, "33", Toast.LENGTH_SHORT).show();
//                        myPrefs.setUserLoanStep(String.valueOf(response.body().getData().getLoans().get(0).getStep()));
                        loanStep();

                    }
//                    stateSpinner(response.body().getData());
//                    Toast.makeText(mainFragment, "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                }
                Log.e("getUserLoanStep", "" + myPrefs.getUserLoanStep());
                Log.e("getUserLoanStep", "getKEY_ID---" + myPrefs.getKEY_ID());
            }

            @Override
            public void onFailure(Call<UserAllData> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
//    @Override
//    public void onBackPressed() {
//        finishAffinity();
//    }
}

