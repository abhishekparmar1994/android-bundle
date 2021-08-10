package com.ckinfotech.investor.Activity;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.ckinfotech.investor.Network.Util;
import com.ckinfotech.investor.R;
import com.ckinfotech.investor.RetrofitCallData.APIClient;
import com.ckinfotech.investor.RetrofitCallData.APIService;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.model.userSignUp.SignupModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ckinfotech.investor.Activity.MainActivity.constraintLayout;

public class SignUpActivity extends AppCompatActivity {

    View view;
    Button btn_signup;
    CircleImageView circleImageView;
    EditText edtName, edtMobile, ediEmail, edtPassword, edtConfordPassword,edtRefrel;
    private FragmentManager fragmentManager;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    Bitmap bitmap = null;
    APIService apiInterface;
    String user_image="";
    ProgressDialognew progressDialog;
    public ConstraintLayout signupConstraintLayout;
    MyPrefs myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);
        checkparmision();
        init();


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Error();
            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(SignUpActivity.this);
            }
        });
    }

    private void init() {
        btn_signup = findViewById(R.id.btnSignupSignin);
        edtName = findViewById(R.id.edtSignupName);
        edtMobile = findViewById(R.id.edtSignupMobile);
        edtPassword = findViewById(R.id.edtSignupPassword);
        edtConfordPassword = findViewById(R.id.edtSignupConformpassword);
        ediEmail = findViewById(R.id.edtSignupEmail);
        circleImageView = findViewById(R.id.cirSignup);
        edtRefrel = findViewById(R.id.edtSignuprefrel);
        progressDialog = new ProgressDialognew();
        signupConstraintLayout  = findViewById(R.id.constrainsignup);
        Util.createSnackBar(SignUpActivity.this, signupConstraintLayout);
        bitmap = null;

        myPrefs = new MyPrefs(this);
    }

    private void selectImage(Context context) {
        checkparmision();
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


    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        circleImageView.setImageBitmap(bitmap);
                        user_image =  convertToString();
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage =  data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = SignUpActivity.this.getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                try {
                                    bitmap = MediaStore.Images.Media.getBitmap(SignUpActivity.this.getContentResolver(), selectedImage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                    circleImageView.setImageBitmap(bitmap);
                                user_image =   convertToString();

                                cursor.close();
                            }
                        }
                    }
                    break;
            }
        }
    }

    public void sendPost(String img, String name, String email, String contact_number, String password,String referral_by) {
//        Toast.makeText(activity, "1111", Toast.LENGTH_SHORT).show();
        Call<SignupModel> mCall;
        apiInterface = APIClient.getClient().create(APIService.class);
        mCall = apiInterface.uploadImage(img, name, email, contact_number, password,referral_by);
        mCall.enqueue(new Callback<SignupModel>() {
            @Override
            public void onResponse(Call<SignupModel> call, Response<SignupModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("true")) {
                    Log.e("response","signup"+response.body());
                    myPrefs.setKEY_ID(String.valueOf(response.body().getSignupModelData().getId()));
                        myPrefs.setToken(response.body().getToken());
                        myPrefs.setUserName(response.body().getSignupModelData().getName());
                        myPrefs.setUserEmail(response.body().getSignupModelData().getEmail());
                        myPrefs.setUserNumber(response.body().getSignupModelData().getContactNumber());
                        myPrefs.setUserImageLink(response.body().getSignupModelData().getUserImagePath());
                        myPrefs.setRefrelCoad(response.body().getSignupModelData().getReferralCode());
                        progressDialog.dismiss();
                        Util.displayRightSnackbar(constraintLayout, SignUpActivity.this, response.body().getMessage());
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                        Log.e("onResponse", "Signup" + response.body().getMessage());
                    } else {
                        progressDialog.dismiss();
                        Util.displayRongSnackbar(constraintLayout, SignUpActivity.this, response.body().getMessage());
                    }
                }else {
                    progressDialog.dismiss();
                    Util.displayRongSnackbar(constraintLayout, SignUpActivity.this, "response.body().getMessage()");
                }
            }
            @Override
            public void onFailure(Call<SignupModel> call, Throwable t) {
                progressDialog.dismiss();
                Util.displayErrorSnackbar(constraintLayout, SignUpActivity.this, "Server not responding");
//                if (call.isCanceled()) {
//                    Toast.makeText(activity, "3", Toast.LENGTH_SHORT).show();
//                    Log.e("Post25242454", "request was aborted" + call);
//                } else {
//                    Log.e("Post25242454", "Unable to submit post to API." + t);
//                }
//                    showErrorMessage();
            }

        });
    }

    private void Error() {
         if (user_image.isEmpty()) {
             checkparmision();
            Toast.makeText(SignUpActivity.this, "Select Image", Toast.LENGTH_SHORT).show();
        } else  if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Enter FirstName");
            edtName.requestFocus();
        }else if (ediEmail.getText().toString().isEmpty()) {
            ediEmail.setError("Enter Email");
            ediEmail.requestFocus();
        }else if (edtMobile.getText().toString().isEmpty()) {
            edtMobile.setError("Enter Number");
            edtMobile.requestFocus();
        }else if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("Enter Password");
            edtPassword.requestFocus();
        }else if (edtConfordPassword.getText().toString().isEmpty()) {
            edtConfordPassword.setError("Enter Confirm Password");
            edtConfordPassword.requestFocus();
        }else if (!edtPassword.getText().toString().equalsIgnoreCase(edtConfordPassword.getText().toString())) {
            Toast.makeText(SignUpActivity.this, "Password don't match", Toast.LENGTH_SHORT).show();
        }
        else {
             progressDialog.show(SignUpActivity.this.getSupportFragmentManager(),"");
             sendPost(user_image, edtName.getText().toString(), ediEmail.getText().toString(), edtMobile.getText().toString(), edtPassword.getText().toString(),edtRefrel.getText().toString());
             Log.e("user_image","user_image"+user_image);
        }
    }

    public void checkparmision() {
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
    }

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(SignUpActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(SignUpActivity.this,
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
