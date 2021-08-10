package com.ckinfotech.investor.customfragment;

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

import com.ckinfotech.investor.Activity.MainActivity;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.customfr.BaseFragment;
import com.ckinfotech.investor.model.Pasyment.PaymentService;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;
import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class MemberShipByFragment extends BaseFragment {

    View view;
    Button btnMember, btnWatch;
    private FragmentManager fragmentManager;
    MyPrefs myPrefs;
    TextView txtpayment;
    LinearLayout linApplyMemership,linMembership;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_membershipby, container, false);
        text_toolbare.setText("Active Membership");

//        myPrefs = new MyPrefs(activity);
        init(view);
        click();

        return view;
    }

    private void init(View view) {
        myPrefs = new MyPrefs(activity);
        btnMember = view.findViewById(R.id.btnMemberApply);
        txtpayment = view.findViewById(R.id.txtPayment);
        linApplyMemership = view.findViewById(R.id.linMembershipTrue);
        linMembership = view.findViewById(R.id.linMembership);
        fragmentManager = activity.getSupportFragmentManager();


        txtpayment.setText(myPrefs.getPaymentAmount());
//        myPrefs.setPaymentRequest(null);
        Log.e("MemberShipByFragment","PaymentRequestStatush:-"+myPrefs.getPaymentRequestStatush());
        if(myPrefs.getPaymentRequestStatush().equalsIgnoreCase("inactive")){
            linApplyMemership.setVisibility(View.GONE);
            linMembership.setVisibility(View.VISIBLE);
        }else {
            linApplyMemership.setVisibility(View.VISIBLE);
            linMembership.setVisibility(View.GONE);
        }
    }

    private void click() {
        btnMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                OfferFragment fr = new OfferFragment();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.addToBackStack(fr.getTag());
//                transaction.replace(R.id.frameLayout, fr);
//                transaction.commit();
//                Util.displayRightSnackbar(constraintLayout, activity, "Join successful membership");
                payment();
            }
        });
    }

    private void payment() {
        activity.progressDialog.show(activity.getSupportFragmentManager(), "");
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(myPrefs.getID()));
        map.put("status", "pending");
        map.put("amount", myPrefs.getPaymentAmount());
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<PaymentService> categoryList = apiInterface.PaymentRequest(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<PaymentService>() {
            @Override
            public void onResponse(Call<PaymentService> call, Response<PaymentService> response) {

                Log.e("myresponceinMain", new Gson().toJson(response.body()));

//                arr.clear();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("true")) {
//                        myPrefs.setPaymentRequest("true");
                        Util.displayRightSnackbar(constraintLayout, activity, response.body().getMessage());
                        Intent intent = new Intent(activity,MainActivity.class);
                        startActivity(intent);
                        activity.progressDialog.dismiss();
                    } else {
                        activity.progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentService> call, Throwable t) {
                activity.progressDialog.dismiss();
                Log.e("AccountI", "" + t);
            }
        });

    }

//    private void payment() {
//        activity.progressDialog.show(activity.getSupportFragmentManager(), "");
//        HashMap<String, String> map = new HashMap<>();
//        map.put("user_id", String.valueOf(myPrefs.getID()));
//        map.put("reference_id", "11111111");
//        map.put("amount", myPrefs.getPaymentAmount());
//        APIService apiInterface = APIClient.getClient().create(APIService.class);
//        final Call<PaymentService> categoryList = apiInterface.PaymentService(myPrefs.getToken(), map);
//        categoryList.enqueue(new Callback<PaymentService>() {
//            @Override
//            public void onResponse(Call<PaymentService> call, Response<PaymentService> response) {
//
//                Log.e("myresponceinMain", new Gson().toJson(response.body()));
//
////                arr.clear();
//                if (response.isSuccessful()) {
//                    if (response.body().getSuccess().equals("true")) {
//                        Util.displayRightSnackbar(constraintLayout, activity, response.body().getMessage());
//                        Intent intent = new Intent(activity,MainActivity.class);
//                        startActivity(intent);
//                        activity.progressDialog.dismiss();
//                    } else {
//                        activity.progressDialog.dismiss();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PaymentService> call, Throwable t) {
//                activity.progressDialog.dismiss();
//                Log.e("AccountI", "" + t);
//            }
//        });
//
//    }

}