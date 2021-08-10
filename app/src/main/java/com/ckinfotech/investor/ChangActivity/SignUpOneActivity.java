package com.ckinfotech.investor.ChangActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ckinfotech.investor.Activity.ProgressDialognew;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.model.SignUp.SignUp;
import com.ckinfotech.investor.model.responceModel.CountryList;
import com.ckinfotech.investor.model.responceModel.CountryResponse;
import com.ckinfotech.investor.model.userLogin.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;

public class SignUpOneActivity extends AppCompatActivity {

    private EditText edt_name,edt_nameMiddle,edt_nameLast, edtEmail, edtNumber, edtNumberSecond, edtAddresh,edtPasswrod,edtRefrel;
    private Spinner spicontri, spiState, spiDistric, spiTaluka, spiVilej;
    private View view;
    private Button btnOnenext;
    private FragmentManager fragmentManager;
    private CountryCitySortChangeAdapter countryCitySortAdapter, stateAdapter, districtAdapter, talukaAdapter, villageAdapter;
    private String country_id, state_id, district_id, taluka_id, village_id;

    private MyPrefs myPrefs;
    ProgressDialognew progressDialog;
    ConstraintLayout constraintLayoutOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_one);

        myPrefs = new MyPrefs(this);
        init(view);
//        text_toolbare.setText("Detail");


        btnOnenext = findViewById(R.id.btnUserDetailNextOne);
        btnOnenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Error();
            }
        });
    }

    private void init(View view) {
        progressDialog = new ProgressDialognew();
        edt_name = findViewById(R.id.edtNameDetOne);
        edt_nameMiddle = findViewById(R.id.edtMiddleNameDet);
        edt_nameLast = findViewById(R.id.edtLastNameDet);
        edtEmail = findViewById(R.id.edtEmailDetONe);
        edtNumber = findViewById(R.id.edtMobileNumberDetOne);
        edtPasswrod = findViewById(R.id.edtsignOnePasswordOne);
        edtAddresh = findViewById(R.id.edtAddreshDetOne);
        spicontri = (Spinner) findViewById(R.id.spinnercontriOne);
        spiState = (Spinner) findViewById(R.id.spinnerStateOne);
        spiDistric = findViewById(R.id.spinnerDistricOne);
        spiTaluka = findViewById(R.id.spinnerTalukaOne);
        spiVilej = findViewById(R.id.spinnerVilejOne);

        edtRefrel = findViewById(R.id.edtsignOneRefrel);

        constraintLayoutOne = findViewById(R.id.signOneConstrain);
        Util.createSnackBar(SignUpOneActivity.this, constraintLayoutOne);
        progressDialog.show(getSupportFragmentManager(), "");
        country();
    }
    private void Error() {
        if (edt_name.getText().toString().isEmpty()) {
            edt_name.setError("Enter FirstName");
            edt_name.requestFocus();
        }
        else if (edt_nameMiddle.getText().toString().isEmpty()) {
            edt_nameMiddle.setError("Enter Middle Name");
            edt_nameMiddle.requestFocus();
        }
        else if (edt_nameLast.getText().toString().isEmpty()) {
            edt_nameLast.setError("Enter Last Name");
            edt_nameLast.requestFocus();
        }else if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Enter Email");
            edtEmail.requestFocus();
        } else if (edtNumber.getText().toString().isEmpty()) {
            edtNumber.setError("Enter Number");
            edtNumber.requestFocus();
        } else if (edtPasswrod.getText().toString().isEmpty()) {
            edtPasswrod.setError("Enter Password");
            edtPasswrod.requestFocus();
        } else if (edtAddresh.getText().toString().isEmpty()) {
            edtAddresh.setError("Enter Addresh");
            edtAddresh.requestFocus();
        } else {
            progressDialog.show(getSupportFragmentManager(), "");
            submitDetailsOne();
        }
    }
    public void country() {

        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<CountryResponse> categoryList = apiInterface.usercountry(myPrefs.getToken());
        Log.e("call", "" + categoryList.request().url());
        categoryList.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {

                Log.e("myresponceinMain", new Gson().toJson(response.body()));

                if (response.isSuccessful()) {
                    CountrySpinner(response.body().getData());
                } else {
                    progressDialog.dismiss();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public void getState(String country_id) {

        HashMap<String, String> map = new HashMap<>();
        map.put("country_id", String.valueOf(country_id));
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<CountryResponse> categoryList = apiInterface.state(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {

                Log.e("myresponceinMain", new Gson().toJson(response.body()));

//                arr.clear();
                if (response.isSuccessful()) {

                    stateSpinner(response.body().getData());
//                    Toast.makeText(mainFragment, "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public void getDistrict(String country_id) {

        HashMap<String, String> map = new HashMap<>();
        map.put("state_id", String.valueOf(country_id));
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<CountryResponse> categoryList = apiInterface.District(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {

                Log.e("myresponceinMain", new Gson().toJson(response.body()));

//                arr.clear();
                if (response.isSuccessful()) {

                    DistrictSpinner(response.body().getData());
//                    Toast.makeText(mainFragment, "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();

                } else {

                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {

            }
        });
    }

    public void getTaluka(String country_id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("district_id", String.valueOf(country_id));
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<CountryResponse> categoryList = apiInterface.Taluka(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {

                Log.e("myresponceinMain", new Gson().toJson(response.body()));

//                arr.clear();
                if (response.isSuccessful()) {

                    TalukaSpinner(response.body().getData());
//                    Toast.makeText(getActivity(), "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    Log.e("Response", "getTaluka" + response.body());

                } else {

                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
//                progressDialog.dismiss();
            }
        });
    }

    public void getVillage(String country_id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("taluka_id", String.valueOf(country_id));
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<CountryResponse> categoryList = apiInterface.village(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {

                Log.e("myresponceinMain", new Gson().toJson(response.body()));

//                arr.clear();
                if (response.isSuccessful()) {

                    VillageSpinner(response.body().getData());
//                    Toast.makeText(getActivity(), "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    Log.e("Response", "getTaluka" + response.body());
//                    progressDialog.dismiss();
                } else {
//                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
//                progressDialog.dismiss();
            }
        });
    }

    public void submitDetailsOne() {
        HashMap<String, String> map = new HashMap<>();
        map.put("step", "one");
        map.put("user_image", "");
        map.put("first_name", edt_name.getText().toString().trim());
        map.put("middle_name", edt_nameMiddle.getText().toString().trim());
        map.put("last_name", edt_nameLast.getText().toString().trim());
        map.put("email", edtEmail.getText().toString().trim());
        map.put("contact_number", edtNumber.getText().toString().trim());
        map.put("address", edtAddresh.getText().toString().trim());
        map.put("password", edtPasswrod.getText().toString().trim());
            map.put("country_id", "110");
        map.put("state_id", "11");
        map.put("district_id", "1");
        map.put("taluka_id", "1");
        map.put("village_id", "1");
        map.put("referral_by", edtRefrel.getText().toString());
//        if(myPrefs.getLoanId()==null){
//            map.put("loan_id", "");
//        }else {
//            map.put("loan_id", myPrefs.getLoanId());
//        }
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<SignUp> categoryList = apiInterface.registeruser(map);
        categoryList.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                Log.e("SignUpOne", "Response" + response.body());
                if (response.isSuccessful()) {
                    if(response.body().getResult().equalsIgnoreCase("true")) {
                        myPrefs.setID(String.valueOf(response.body().getSignUpData().getId()));
                        myPrefs.setToken(String.valueOf(response.body().getToken()));
                        myPrefs.setUserLoanStep("one");
                        myPrefs.setRefrelCoad(response.body().getSignUpData().getReferralCode());
                        myPrefs.setAccountId(String.valueOf(response.body().getSignUpData().getAccountId()));
                        Intent intent = new Intent(SignUpOneActivity.this,SignUpTwoActivity.class);
                        startActivity(intent);
                        Log.e("SignUpOne", "getMsg" + response.body().getMessage());
                        Log.e("SignUpOne", "Id" + String.valueOf(response.body().getSignUpData().getId()));
                        Log.e("SignUpOne", "getToken" +String.valueOf(response.body().getToken()));
                        Util.displayRightSnackbar(constraintLayout, SignUpOneActivity.this, response.body().getMessage());
                        progressDialog.dismiss();
                    }else if(response.body().getResult().equalsIgnoreCase("false")){
                        progressDialog.dismiss();
                        Util.displayRongSnackbar(constraintLayout, SignUpOneActivity.this,response.body().getMessage());
                        Log.e("SignUpOne", "getMsg" + response.body());
                        Log.e("SignUpOne", "LoneId" + myPrefs.getLoanId());
                    }
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                progressDialog.dismiss();
                Util.displayErrorSnackbar(constraintLayout, SignUpOneActivity.this, "Server not responding");
                Log.e("SignUpOne", "t" + t);
            }
        });
    }

//////////////////////////////////////////////////////////

    private void CountrySpinner(List<CountryList> orderTypeSortArrayList) {

        if (orderTypeSortArrayList == null) {
            orderTypeSortArrayList = new ArrayList<>();
        }
        if (countryCitySortAdapter == null) {
            countryCitySortAdapter = new CountryCitySortChangeAdapter(SignUpOneActivity.this, orderTypeSortArrayList);
            spicontri.setAdapter(countryCitySortAdapter);
            spicontri.setSelection(100);
        } else {
            countryCitySortAdapter.setSortOrderList(orderTypeSortArrayList);

            countryCitySortAdapter.notifyDataSetChanged();
        }
        List<CountryList> finalOrderTypeSortArrayList = orderTypeSortArrayList;
        spicontri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                finalOrderTypeSortArrayList.get(i).getId();
                if (!finalOrderTypeSortArrayList.get(i).getName().equals("Select Country")) {
                    getState(String.valueOf(finalOrderTypeSortArrayList.get(i).getId()));
                    country_id = String.valueOf(finalOrderTypeSortArrayList.get(i).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do with view
            }
        });
    }

    private void stateSpinner(List<CountryList> orderTypeSortArrayList) {

        if (orderTypeSortArrayList == null) {
            orderTypeSortArrayList = new ArrayList<>();
        }
        if (stateAdapter == null) {
            stateAdapter = new CountryCitySortChangeAdapter(SignUpOneActivity.this, orderTypeSortArrayList);
            spiState.setAdapter(stateAdapter);
//            spiState.setSelection(11);
        } else {
            stateAdapter.setSortOrderList(orderTypeSortArrayList);

            stateAdapter.notifyDataSetChanged();
        }
        List<CountryList> finalOrderTypeSortArrayList = orderTypeSortArrayList;
        spiState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                finalOrderTypeSortArrayList.get(i).getId();

                if (!finalOrderTypeSortArrayList.get(i).getName().equals("Select State")) {
                    getDistrict(String.valueOf(finalOrderTypeSortArrayList.get(i).getId()));
                    state_id = String.valueOf(finalOrderTypeSortArrayList.get(i).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do with view
            }
        });
    }

    private void DistrictSpinner(List<CountryList> orderTypeSortArrayList) {

        if (orderTypeSortArrayList == null) {
            orderTypeSortArrayList = new ArrayList<>();
        }
        if (districtAdapter == null) {
            districtAdapter = new CountryCitySortChangeAdapter(SignUpOneActivity.this, orderTypeSortArrayList);
            spiDistric.setAdapter(districtAdapter);
        } else {
            districtAdapter.setSortOrderList(orderTypeSortArrayList);

            districtAdapter.notifyDataSetChanged();
        }
        List<CountryList> finalOrderTypeSortArrayList = orderTypeSortArrayList;
        spiDistric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                finalOrderTypeSortArrayList.get(i).getId();

                if (!finalOrderTypeSortArrayList.get(i).getName().equals("Select State")) {
                    getTaluka(String.valueOf(finalOrderTypeSortArrayList.get(i).getId()));
                    district_id = String.valueOf(finalOrderTypeSortArrayList.get(i).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do with view
            }
        });
    }


    private void TalukaSpinner(List<CountryList> orderTypeSortArrayList) {

        if (orderTypeSortArrayList == null) {
            orderTypeSortArrayList = new ArrayList<>();
        }
        if (talukaAdapter == null) {
            talukaAdapter = new CountryCitySortChangeAdapter(SignUpOneActivity.this, orderTypeSortArrayList);
            spiTaluka.setAdapter(talukaAdapter);
//            spiTaluka.setSelection(Integer.parseInt(talukaAdapter.getItem(3)));
        } else {
            talukaAdapter.setSortOrderList(orderTypeSortArrayList);

            talukaAdapter.notifyDataSetChanged();
        }
        List<CountryList> finalOrderTypeSortArrayList = orderTypeSortArrayList;
        spiTaluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                finalOrderTypeSortArrayList.get(i).getId();

                if (!finalOrderTypeSortArrayList.get(i).getName().equals("Select State")) {
                    taluka_id = String.valueOf(finalOrderTypeSortArrayList.get(i).getId());
                    getVillage(String.valueOf(finalOrderTypeSortArrayList.get(i).getId()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do with view
            }
        });
    }

    private void VillageSpinner(List<CountryList> orderTypeSortArrayList) {

        if (orderTypeSortArrayList == null) {
            orderTypeSortArrayList = new ArrayList<>();
        }
        if (villageAdapter == null) {
            villageAdapter = new CountryCitySortChangeAdapter(SignUpOneActivity.this, orderTypeSortArrayList);
            spiVilej.setAdapter(villageAdapter);
//            spiTaluka.setSelection(Integer.parseInt(talukaAdapter.getItem(3)));
        } else {
            villageAdapter.setSortOrderList(orderTypeSortArrayList);

            villageAdapter.notifyDataSetChanged();
        }
        List<CountryList> finalOrderTypeSortArrayList = orderTypeSortArrayList;
        spiTaluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                finalOrderTypeSortArrayList.get(i).getId();

                if (!finalOrderTypeSortArrayList.get(i).getName().equals("Select State")) {
//                    getTaluka(String.valueOf(finalOrderTypeSortArrayList.get(i).getId()));
                    village_id = String.valueOf(finalOrderTypeSortArrayList.get(i).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do with view
            }
        });
    }
}