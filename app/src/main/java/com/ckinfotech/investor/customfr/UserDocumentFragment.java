package com.ckinfotech.investor.customfr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ckinfotech.investor.Activity.ProgressDialognew;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.model.userLogin.UserModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;
import static com.ckinfotech.investor.Activity.MainActivity.text_toolbare;

public class UserDocumentFragment extends BaseFragment implements View.OnClickListener {

    View view;
    Button btn_next;
    private FragmentManager fragmentManager;
    ImageView imgAdhareFrount, imgAdhareback, imgPancard;
    EditText edtAdhareNumbaer, edtPanNumber;
    private String Document_img1="";
    private  String KEY_BUTTON_SELECT = "";
    Bitmap bitmap;
    private String adharFront = "";
    private String adharBack = "";
    private String panCard = "";
    private MyPrefs myPrefs;
    ProgressDialognew progressDialog;


    private final static int ALL_PERMISSIONS_RESULT = 107;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_userdocument, container, false);
        btn_next = view.findViewById(R.id.btnUserDocumentNext);
        text_toolbare.setText("Document");

        init(view);
        return view;
    }

    private void init(View view) {
        myPrefs = new MyPrefs(activity);
        progressDialog = new ProgressDialognew();
        imgAdhareFrount = view.findViewById(R.id.imgAdhareOne);
        imgAdhareback = view.findViewById(R.id.imgAdhareTwo);
        imgPancard = view.findViewById(R.id.imgPancard);
        edtAdhareNumbaer = view.findViewById(R.id.edtAdhareCardNumber);
        edtPanNumber = view.findViewById(R.id.edtPancardNumber);

        imgAdhareFrount.setOnClickListener(this);
        imgAdhareback.setOnClickListener(this);
        imgPancard.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        imgAdhareFrount.setScaleType(ImageView.ScaleType.FIT_XY);
        imgAdhareback.setScaleType(ImageView.ScaleType.FIT_XY);
        imgPancard.setScaleType(ImageView.ScaleType.FIT_XY);
    }


    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                         bitmap = (Bitmap) data.getExtras().get("data");
//                        imgAdhareFrount.setImageBitmap(bitmap);
//                        adharFront =   convertToString();
//                        Log.e("ImageString","adharFront.camera."+adharFront);
                        if(KEY_BUTTON_SELECT.equalsIgnoreCase("AdhareOne")) {
                            imgAdhareFrount.setImageBitmap(bitmap);
                            adharFront =   convertToString();
                            Log.e("ImageString","adharFront.."+adharFront);
                        }else if(KEY_BUTTON_SELECT.equalsIgnoreCase("AdhareTwo")){
                            imgAdhareback.setImageBitmap(bitmap);
                            adharBack =   convertToString();
                            Log.e("ImageString","adharBack.."+adharBack);
                        }else if(KEY_BUTTON_SELECT.equalsIgnoreCase("Pancard")){
                            imgPancard.setImageBitmap(bitmap);
                            panCard =   convertToString();
                            Log.e("ImageString","panCard.."+panCard);
                        }
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage =  data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                try {
                                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if(KEY_BUTTON_SELECT.equalsIgnoreCase("AdhareOne")) {
                                    imgAdhareFrount.setImageBitmap(bitmap);
                                    adharFront =   convertToString();
                                    Log.e("ImageString","adharFront.."+adharFront);
                                }else if(KEY_BUTTON_SELECT.equalsIgnoreCase("AdhareTwo")){
                                    imgAdhareback.setImageBitmap(bitmap);
                                    adharBack =   convertToString();
                                    Log.e("ImageString","adharBack.."+adharBack);
                                }else if(KEY_BUTTON_SELECT.equalsIgnoreCase("Pancard")){
                                    imgPancard.setImageBitmap(bitmap);
                                    panCard =   convertToString();
                                    Log.e("ImageString","panCard.."+panCard);
                                }
                                cursor.close();
                            }
                        }
                    }
                    break;
            }
        }
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgAdhareOne:
                KEY_BUTTON_SELECT ="AdhareOne";
                selectImage(getActivity());
                // do your code
                break;
            case R.id.imgAdhareTwo:
                KEY_BUTTON_SELECT ="AdhareTwo";
                selectImage(getActivity());
                // do your code
                break;
            case R.id.imgPancard:
                KEY_BUTTON_SELECT ="Pancard";
                selectImage(getActivity());
                // do your code
                break;
            case R.id.btnUserDocumentNext:
                Log.e("LoanId",""+myPrefs.getLoanId());

                Error();
                break;
            default:
                break;
        }
    }

    private void Error() {
        if (edtAdhareNumbaer.getText().toString().isEmpty()) {
            edtAdhareNumbaer.setError("Enter Adhare Card Numbaer");
            edtAdhareNumbaer.requestFocus();
        }else if (edtPanNumber.getText().toString().isEmpty()) {
            edtPanNumber.setError("Enter Pan Card Number");
            edtPanNumber.requestFocus();
        }else if (adharFront.isEmpty()) {
            Toast.makeText(activity, "Select Adhare Image", Toast.LENGTH_SHORT).show();
        }else if (adharBack.isEmpty()) {
            Toast.makeText(activity, "Select Adhare Back Image", Toast.LENGTH_SHORT).show();
        }else if (panCard.isEmpty()) {
            Toast.makeText(activity, "Select Pancard Image", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.show(activity.getSupportFragmentManager(), "");
            submitDetailstwo();
        }
    }

    public void submitDetailstwo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("step", "two");
        map.put("aadhar_card_number", edtAdhareNumbaer.getText().toString().trim());
        map.put("pan_card_number", edtPanNumber.getText().toString().trim());
        map.put("aadhar_card_front_img",adharFront);
        map.put("aadhar_card_back_img", adharBack);
        map.put("pan_card_img", panCard);
        map.put("loan_id", myPrefs.getLoanId());
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<UserModel> categoryList = apiInterface.userDetailone(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getResult().equalsIgnoreCase("true")) {

                        fragmentManager = activity.getSupportFragmentManager();
                        UserNomineeFragment fr = new UserNomineeFragment();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(fr.getTag());
                        transaction.replace(R.id.frameLayout, fr);
                        transaction.commit();
                        progressDialog.dismiss();
                        Util.displayRightSnackbar(constraintLayout, activity,response.body().getMessage());
                        myPrefs.setUserLoanStep("two");
                    }else {
                        progressDialog.dismiss();
                        Util.displayRongSnackbar(constraintLayout, activity,response.body().getMessage());
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


}
