package com.ckinfotech.investor.customfr;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ckinfotech.investor.Activity.ProgressDialognew;
import com.ckinfotech.investor.Adepter.CountryCitySortAdapter;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
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

import static com.ckinfotech.investor.Activity.MainActivity.circleImageViewmain;
import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;
import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class UserDetailFragment extends BaseFragment {

    private EditText edt_name, edtEmail, edtNumber, edtNumberSecond, edtAddresh;
    private Spinner spicontri, spiState, spiDistric, spiTaluka, spiVilej;
    private View view;
    private Button btn_next;
    private FragmentManager fragmentManager;
    private CountryCitySortAdapter countryCitySortAdapter, stateAdapter, districtAdapter, talukaAdapter, villageAdapter;
    private String country_id, state_id, district_id, taluka_id, village_id;

    private MyPrefs myPrefs;
    ProgressDialognew progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        circleImageViewmain.setVisibility(View.VISIBLE);
        myPrefs = new MyPrefs(activity);
        init(view);
//        text_toolbare.setText("Detail");
        btn_next = view.findViewById(R.id.btnUserDetailNextOne);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Error();
            }
        });

        return view;
    }

    private void init(View view) {
        progressDialog = new ProgressDialognew();
        edt_name = view.findViewById(R.id.edtNameDet);
        edtEmail = view.findViewById(R.id.edtEmailDet);
        edtNumber = view.findViewById(R.id.edtMobileNumberDet);
        edtNumberSecond = view.findViewById(R.id.edtMobileNumberTwoDet);
        edtAddresh = view.findViewById(R.id.edtAddreshDet);
        spicontri = (Spinner) view.findViewById(R.id.spinnercontri);
        spiState = (Spinner) view.findViewById(R.id.spinnerState);
        spiDistric = view.findViewById(R.id.spinnerDistric);
        spiTaluka = view.findViewById(R.id.spinnerTaluka);
        spiVilej = view.findViewById(R.id.spinnerVilej);

        progressDialog.show(activity.getSupportFragmentManager(), "");
        country();
//        State(token, String.valueOf("101"));
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
        map.put("fullname", edt_name.getText().toString().trim());
        map.put("email", edtEmail.getText().toString().trim());
        map.put("mobile_number1", edtNumber.getText().toString().trim());
        map.put("mobile_number2", edtNumberSecond.getText().toString().trim());
        map.put("address", edtAddresh.getText().toString().trim());
        map.put("country_id", country_id);
        map.put("state_id", state_id);
        map.put("district_id", district_id);
        map.put("taluka_id", taluka_id);
        map.put("village_id", village_id);
        if(myPrefs.getLoanId()==null){
            map.put("loan_id", "");
        }else {
            map.put("loan_id", myPrefs.getLoanId());
        }
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<UserModel> categoryList = apiInterface.userDetailone(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if (response.isSuccessful()) {
                    if(response.body().getResult().equalsIgnoreCase("true")) {
                    myPrefs.setLoanId(response.body().getLoan_id());
                        myPrefs.setUserLoanStep("one");
                    fragmentManager = activity.getSupportFragmentManager();
                    UserDocumentFragment fr = new UserDocumentFragment();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.addToBackStack(fr.getTag());
                    transaction.replace(R.id.frameLayout, fr);
                    transaction.commit();
                    Log.e("Response", "getMsg" + response.body().getMessage());
                    Log.e("Response", "LoneId" + village_id);
                        Log.e("getLoanId", "LoneId" + myPrefs.getLoanId());
                    progressDialog.dismiss();
                    Util.displayRightSnackbar(constraintLayout, activity, response.body().getMessage());
                    }else {
                        progressDialog.dismiss();
                        Util.displayRongSnackbar(constraintLayout, activity,response.body().getMessage());
                        Log.e("Response", "getMsg" + response.body());
                        Log.e("getLoanId", "LoneId" + myPrefs.getLoanId());
                    }
                } else {
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                progressDialog.dismiss();
                Util.displayErrorSnackbar(constraintLayout, activity, "Server not responding");
            }
        });
    }

//////////////////////////////////////////////////////////

    private void CountrySpinner(List<CountryList> orderTypeSortArrayList) {

        if (orderTypeSortArrayList == null) {
            orderTypeSortArrayList = new ArrayList<>();
        }
        if (countryCitySortAdapter == null) {
            countryCitySortAdapter = new CountryCitySortAdapter(this, orderTypeSortArrayList);
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
            stateAdapter = new CountryCitySortAdapter(this, orderTypeSortArrayList);
            spiState.setAdapter(stateAdapter);
            spiState.setSelection(11);
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
            districtAdapter = new CountryCitySortAdapter(this, orderTypeSortArrayList);
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
            talukaAdapter = new CountryCitySortAdapter(this, orderTypeSortArrayList);
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
            villageAdapter = new CountryCitySortAdapter(this, orderTypeSortArrayList);
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

    private void Error() {
        if (edt_name.getText().toString().isEmpty()) {
            edt_name.setError("Enter FirstName");
            edt_name.requestFocus();
        } else if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Enter Email");
            edtEmail.requestFocus();
        } else if (edtNumber.getText().toString().isEmpty()) {
            edtNumber.setError("Enter Number");
            edtNumber.requestFocus();
        } else if (edtNumberSecond.getText().toString().isEmpty()) {
            edtNumberSecond.setError("Enter Number");
            edtNumberSecond.requestFocus();
        } else if (edtAddresh.getText().toString().isEmpty()) {
            edtAddresh.setError("Enter Addresh");
            edtAddresh.requestFocus();
        } else {
            progressDialog.show(activity.getSupportFragmentManager(), "");
            submitDetailsOne();
        }
    }
}
