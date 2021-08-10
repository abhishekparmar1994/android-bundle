package com.ckinfotech.investor.RetrofitCallData;


import com.ckinfotech.investor.model.ApllyLoan.ApplyLoan;
import com.ckinfotech.investor.model.ApllyLoan.EmiAmmount;
import com.ckinfotech.investor.model.GeneralService.GeneralService;
import com.ckinfotech.investor.model.LoanData.LoanData;
import com.ckinfotech.investor.model.LoanSubmitResponce.LoanSubmitResponce;
import com.ckinfotech.investor.model.LoginUser.LoginUserList;
import com.ckinfotech.investor.model.Pasyment.PaymentService;
import com.ckinfotech.investor.model.SignUp.SignUp;
import com.ckinfotech.investor.model.UserAllData.UserAllData;
import com.ckinfotech.investor.model.emiRequest.EmiRequest;
import com.ckinfotech.investor.model.responceModel.CountryResponse;
import com.ckinfotech.investor.model.userLogin.UserModel;
import com.ckinfotech.investor.model.userSignUp.SignupModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
//    @GET("api/projectSubCategory/get")
//    Call<DataSub> doGetListResources();
//
//    @POST("/api/register-user")
//    @FormUrlEncoded
//    Call<ViewList> viewList(@Field("id") String Id,
//                            @Field("paginate") String paginate);
@FormUrlEncoded
    @POST("/investor/public/api/register-user")
    Call<SignupModel> uploadImage(@Field("user_image") String img, @Field("name") String name, @Field("email") String email, @Field("contact_number") String contact_number, @Field("password") String password, @Field("referral_by") String referral_by);


    @FormUrlEncoded
    @POST("/investor/public/api/auth/login")
    Call<LoginUserList> userLogin(@Field("email") String email, @Field("password") String password);

//    @FormUrlEncoded
//    @POST("/api/getcountry")
//    Call<UserLogin> usercountry(@Field("Authorization") String token);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("/investor/public/api/getcountry")
    Call<CountryResponse> usercountry(@Header("Authorization") String token);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/state-list")
    Call<CountryResponse> state(@Header("Authorization") String token,@Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/district-list")
    Call<CountryResponse> District(@Header("Authorization") String token,@Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/taluka-list")
    Call<CountryResponse> Taluka(@Header("Authorization") String token,@Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/village-list")
    Call<CountryResponse> village(@Header("Authorization") String token,@Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/loan")
    Call<UserModel> userDetailone(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/loan")
    Call<LoanSubmitResponce> userLoanSubmit(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/general_api")
    Call<UserAllData> userAllData(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/register-user")
    Call<SignUp> registeruser(@Body HashMap<String, String> body);
    //http://15.185.82.68/investor/public/api/register-user

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/register-user")
    Call<SignUp> registeruserTwo(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/general_api")
    Call<GeneralService> generalService(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/membershipPayment")
    Call<PaymentService> PaymentService(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/paymentRequest")
    Call<PaymentService> PaymentRequest(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/get_emi_amount")
    Call<EmiAmmount> emaiCalculasion(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/loan")
    Call<ApplyLoan> applyLoan(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/emi_data")
    Call<LoanData> payLoanData(@Header("Authorization") String token, @Body HashMap<String, String> body);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/investor/public/api/emi_request")
    Call<EmiRequest> emiRequest(@Header("Authorization") String token, @Body HashMap<String, String> body);
}