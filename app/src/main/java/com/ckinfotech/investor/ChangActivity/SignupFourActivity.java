package com.ckinfotech.investor.ChangActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ckinfotech.investor.Activity.MainActivity;
import com.ckinfotech.investor.Activity.ProgressDialognew;
import com.ckinfotech.investor.Activity.SignUpActivity;
import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.customfr.UserNomineeFragment;
import com.ckinfotech.investor.model.SignUp.SignUp;
import com.ckinfotech.investor.model.userLogin.UserModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;

public class SignupFourActivity extends AppCompatActivity implements View.OnClickListener {

    View view;
    Button btn_next;
    private FragmentManager fragmentManager;
    ImageView imgAdhareFrount, imgAdhareback, imgPancard;
    EditText edtAdhareNumbaer, edtPanNumber;
    private String Document_img1 = "";
    private String KEY_BUTTON_SELECT = "";
    Bitmap bitmap;
    private String adharFront = "";
    private String adharBack = "";
    private String panCard = "";
    private MyPrefs myPrefs;
    ProgressDialognew progressDialog;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    ConstraintLayout constraintLayoutOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_four);
        init(view);
        checkparmision();
    }

    private void init(View view) {
        myPrefs = new MyPrefs(this);
        progressDialog = new ProgressDialognew();
        imgAdhareFrount = findViewById(R.id.imgAdhareOneFour);
        imgAdhareback = findViewById(R.id.imgAdhareTwoFour);
        imgPancard = findViewById(R.id.imgPancardFour);
        edtAdhareNumbaer = findViewById(R.id.edtAdhareCardNumberFour);
        edtPanNumber = findViewById(R.id.edtPancardNumberFour);

        btn_next = findViewById(R.id.btnUserDocumentNextFour);

        imgAdhareFrount.setOnClickListener(this);
        imgAdhareback.setOnClickListener(this);
        imgPancard.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        imgAdhareFrount.setScaleType(ImageView.ScaleType.FIT_XY);
        imgAdhareback.setScaleType(ImageView.ScaleType.FIT_XY);
        imgPancard.setScaleType(ImageView.ScaleType.FIT_XY);

        constraintLayoutOne = findViewById(R.id.constrainFour);
        Util.createSnackBar(SignupFourActivity.this, constraintLayoutOne);
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

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
                    startActivityForResult(pickPhoto, 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        bitmap = (Bitmap) data.getExtras().get("data");
//                        imgAdhareFrount.setImageBitmap(bitmap);
//                        adharFront =   convertToString();
//                        Log.e("ImageString","adharFront.camera."+adharFront);
                        if (KEY_BUTTON_SELECT.equalsIgnoreCase("AdhareOne")) {
                            imgAdhareFrount.setImageBitmap(bitmap);
                            adharFront = convertToString();
                            Log.e("ImageString", "adharFront.." + adharFront);
                        } else if (KEY_BUTTON_SELECT.equalsIgnoreCase("AdhareTwo")) {
                            imgAdhareback.setImageBitmap(bitmap);
                            adharBack = convertToString();
                            Log.e("ImageString", "adharBack.." + adharBack);
                        } else if (KEY_BUTTON_SELECT.equalsIgnoreCase("Pancard")) {
                            imgPancard.setImageBitmap(bitmap);
                            panCard = convertToString();
                            Log.e("ImageString", "panCard.." + panCard);
                        }
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = this.getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                try {
                                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (KEY_BUTTON_SELECT.equalsIgnoreCase("AdhareOne")) {
                                    imgAdhareFrount.setImageBitmap(bitmap);
                                    adharFront = convertToString();
                                    Log.e("ImageString", "adharFront.." + adharFront);
                                } else if (KEY_BUTTON_SELECT.equalsIgnoreCase("AdhareTwo")) {
                                    imgAdhareback.setImageBitmap(bitmap);
                                    adharBack = convertToString();
                                    Log.e("ImageString", "adharBack.." + adharBack);
                                } else if (KEY_BUTTON_SELECT.equalsIgnoreCase("Pancard")) {
                                    imgPancard.setImageBitmap(bitmap);
                                    panCard = convertToString();
                                    Log.e("ImageString", "panCard.." + panCard);
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
            case R.id.imgAdhareOneFour:
                KEY_BUTTON_SELECT = "AdhareOne";
                checkparmision();
                selectImage(this);
                // do your code
                break;
            case R.id.imgAdhareTwoFour:
                KEY_BUTTON_SELECT = "AdhareTwo";
                checkparmision();
                selectImage(this);
                // do your code
                break;
            case R.id.imgPancardFour:
                KEY_BUTTON_SELECT = "Pancard";
                checkparmision();
                selectImage(this);
                // do your code
                break;
            case R.id.btnUserDocumentNextFour:
                Log.e("LoanId", "" + myPrefs.getLoanId());

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
        } else if (edtPanNumber.getText().toString().isEmpty()) {
            edtPanNumber.setError("Enter Pan Card Number");
            edtPanNumber.requestFocus();
        } else if (adharFront.isEmpty()) {
            Toast.makeText(this, "Select Adhare Image", Toast.LENGTH_SHORT).show();
        } else if (adharBack.isEmpty()) {
            Toast.makeText(this, "Select Adhare Back Image", Toast.LENGTH_SHORT).show();
        } else if (panCard.isEmpty()) {
            Toast.makeText(this, "Select Pancard Image", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show(this.getSupportFragmentManager(), "");
            submitDetailstwo();
        }
    }

    public void submitDetailstwo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("step", "four");
        map.put("aadhar_number", edtAdhareNumbaer.getText().toString().trim());
        map.put("pan_number", edtPanNumber.getText().toString().trim());
        map.put("aadhar_front_image", adharFront);
        map.put("aadhar_back_imag", adharBack);
        map.put("pan_card_image", panCard);
        map.put("id", myPrefs.getID());
        APIService apiInterface = APIClient.getClient().create(APIService.class);
        final Call<SignUp> categoryList = apiInterface.registeruserTwo(myPrefs.getToken(), map);
        categoryList.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult().equalsIgnoreCase("true")) {

                        myPrefs.setUserLoanStep("four");
                        Intent intent = new Intent(SignupFourActivity.this, MainActivity.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                        Util.displayRightSnackbar(constraintLayout, SignupFourActivity.this, response.body().getMessage());

                    } else {
                        progressDialog.dismiss();
                        Util.displayRongSnackbar(constraintLayout, SignupFourActivity.this, response.body().getMessage());
                    }
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                progressDialog.dismiss();
                Util.displayErrorSnackbar(constraintLayout, SignupFourActivity.this, "Server not responding");
            }
        });
    }

    public void checkparmision() {
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
    }

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(SignupFourActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(SignupFourActivity.this,
                    new String[]{permission},
                    requestCode);
//            checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
        } else {
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
            } else {
                checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);

            }
        }
    }
}