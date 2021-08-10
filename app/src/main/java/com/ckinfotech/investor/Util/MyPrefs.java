package com.ckinfotech.investor.Util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class MyPrefs {




    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String KEY_TOKEN = "key_token";
    private String KEY_EMAIL = "key_email";
    private String KEY_PASS = "key_password";
    private String KEY_SUCCESS_LOGIN = "KEY_success";
    private String KEY_LOANID = "KEY_loadid";
    private String KEY_LOAN_AMOUNT = "KEY_loanamount";
    private String KEY_EMI_MONTH = "KEY_EmiMonth";
    private String KEY_MONTHLY_INTEREST_AMOUNT = "KEY_Monthly_Interest_Amount";
    private String KEY_EMI_BOUNCE_CHARGE = "KEY_Emi_Bounce_Charge";
    private String KEY_LOAN_TOTAL_AMOUNT = "KEY_Loan_Total_Amount";

    private String KEY_EMI_DATE = "KEY_Emi_Date";
    private String KEY_APPLYFORM_STEP = "KEY_ApplyformStep";


    private String KEY_User_NAME = "KEY_UserNAME";
    private String KEY_User_Number = "KEY_UserNumber";
    private String KEY_User_Email = "KEY_UserEmail";
    private String KEY_User_Image = "KEY_UserImage";

    private String KEY_Referral_Code = "KEY_referralcode";

    private String KEY_UserLoan_Step = "KEY_UserLoanStep";

    private String KEY_UserLoan_Status = "KEY_UserLoanStatus";


    private String KEY_ID = "KEY_Id";

    private String ID = "Id";
    private String ACCOUNT_ID = "AccountId";

    private String JOINSERVICESTATUS = "JoinService";

    private String PAYMENTAMOUNT = "PaymentAmount";
    private String MEMBERSHIPSTATUS = "MembershipStatus";

    private String OFFERDETAILS = "OfferDetails";
    private String DOCUMENTVERIFICATION = "DocumentVerification";

    private String PAYMENTREQUEST = "PaymentRequest";

    private String SHAREMOBILENUMBER = "ShareMobileNumber";
    private String LOANMONTH = "LoanMonth";

    private String PAYMENTREQUESTSTATUS = "PaymentRequestStatus";

    private String APPLYLOANSTATUS = "ApplyLoanStatus";

    private String APPLYLOANMESSAGE = "ApplyLoanMessage";

    private String LOANAMOUNT = "LoanAmount";
    private String LOANMONTHAPPLY = "LoanMonthApply";
    private String LOANEMIAMOUNT = "LoanEmiAmount";
    private String LOANEMIDATE = "LoanEmiDate";




    public MyPrefs(Activity mActivity) {
        this.context = mActivity.getApplicationContext();
        this.preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    public MyPrefs(Context mActivity) {
        this.context = mActivity.getApplicationContext();
        this.preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }


    public void setToken(String token) {
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public String getToken() {
        return preferences.getString(KEY_TOKEN, null);
    }


    public void setemail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }


    public String getemail() {
        return preferences.getString(KEY_EMAIL, null);
    }

    public void setpass(String pass) {
        editor.putString(KEY_PASS, pass);
        editor.commit();
    }

    public String getpass() {

        return preferences.getString(KEY_PASS, null);
    }

    public void setSuccessLogin(String successlogin) {
        editor.putString(KEY_SUCCESS_LOGIN, successlogin);
        editor.commit();
    }
    public String getSuccessLogin() {
        return preferences.getString(KEY_SUCCESS_LOGIN, null);
    }

    public void setLoanId(String loadId) {
        editor.putString(KEY_LOANID, loadId);
        editor.commit();
    }
    public String getLoanId() {
        return preferences.getString(KEY_LOANID, null);
    }

    public void setLoanAmount(String loadId) {
        editor.putString(KEY_LOAN_AMOUNT, loadId);
        editor.commit();
    }
    public String getLoanAmount() {
        return preferences.getString(KEY_LOAN_AMOUNT, null);
    }

    public void setEmiMonth(String loadId) {
        editor.putString(KEY_EMI_MONTH, loadId);
        editor.commit();
    }
    public String getEmiMonth() {
        return preferences.getString(KEY_EMI_MONTH, null);
    }

    public void setMonthliIntrestAmount(String loadId) {
        editor.putString(KEY_MONTHLY_INTEREST_AMOUNT, loadId);
        editor.commit();
    }
    public String getMonthliIntrestAmount() {
        return preferences.getString(KEY_MONTHLY_INTEREST_AMOUNT, null);
    }

    public void setEmiBounceCharge(String loadId) {
        editor.putString(KEY_EMI_BOUNCE_CHARGE, loadId);
        editor.commit();
    }
    public String getEmiBounceCharge() {
        return preferences.getString(KEY_EMI_BOUNCE_CHARGE, null);
    }

    public void setloanTotalAmount(String loadId) {
        editor.putString(KEY_LOAN_TOTAL_AMOUNT, loadId);
        editor.commit();
    }
    public String getloanTotalAmount() {
        return preferences.getString(KEY_LOAN_TOTAL_AMOUNT, null);
    }

    public void setApplyformStep(String loadId) {
        editor.putString(KEY_APPLYFORM_STEP, loadId);
        editor.commit();
    }
    public String getApplyformStep() {
        return preferences.getString(KEY_APPLYFORM_STEP, null);
    }

    public void setEmiDate(String loadId) {
        editor.putString(KEY_EMI_DATE, loadId);
        editor.commit();
    }
    public String getEmiDate() {
        return preferences.getString(KEY_EMI_DATE, null);
    }

    public void setUserName(String loadId) {
        editor.putString(KEY_User_NAME, loadId);
        editor.commit();
    }
    public String getUserName() {
        return preferences.getString(KEY_User_NAME, null);
    }

    public void setUserEmail(String loadId) {
        editor.putString(KEY_User_Email, loadId);
        editor.commit();
    }
    public String getUserEmail() {
        return preferences.getString(KEY_User_Email, null);
    }

    public void setUserNumber(String loadId) {
        editor.putString(KEY_User_Number, loadId);
        editor.commit();
    }
    public String getUserNumber() {
        return preferences.getString(KEY_User_Number, null);
    }

    public void setUserImageLink(String loadId) {
        editor.putString(KEY_User_Image, loadId);
        editor.commit();
    }
    public String getUserImageLink() {
        return preferences.getString(KEY_User_Image, null);
    }

    public void setRefrelCoad(String loadId) {
        editor.putString(KEY_Referral_Code, loadId);
        editor.commit();
    }
    public String getRefrelCoad() {
        return preferences.getString(KEY_Referral_Code, null);
    }

    public void setUserLoanStep(String loadId) {
        editor.putString(KEY_UserLoan_Step, loadId);
        editor.commit();
    }
    public String getUserLoanStep() {
        return preferences.getString(KEY_UserLoan_Step, null);
    }

    public void setUserLoanStatus(String loadId) {
        editor.putString(KEY_UserLoan_Status, loadId);
        editor.commit();
    }
    public String getUserLoanStatus() {
        return preferences.getString(KEY_UserLoan_Status, null);
    }


    public void setKEY_ID(String loadId) {
        editor.putString(KEY_ID, loadId);
        editor.commit();
    }
    public String getKEY_ID() {
        return preferences.getString(KEY_ID, null);
    }

    public void setID(String loadId) {
        editor.putString(ID, loadId);
        editor.commit();
    }
    public String getID() {
        return preferences.getString(ID, null);
    }

    public void setAccountId(String loadId) {
        editor.putString(ACCOUNT_ID, loadId);
        editor.commit();
    }
    public String getAccountId() {
        return preferences.getString(ACCOUNT_ID, null);
    }

    public void setJoinServiceStatus(String loadId) {
        editor.putString(JOINSERVICESTATUS, loadId);
        editor.commit();
    }
    public String getJoinServiceStatus() {
        return preferences.getString(JOINSERVICESTATUS, null);
    }

    public void setPaymentAmount(String loadId) {
        editor.putString(PAYMENTAMOUNT, loadId);
        editor.commit();
    }
    public String getPaymentAmount() {
        return preferences.getString(PAYMENTAMOUNT, null);
    }

    public void setMembershipStatus(String loadId) {
        editor.putString(MEMBERSHIPSTATUS, loadId);
        editor.commit();
    }
    public String getMembershipStatus() {
        return preferences.getString(MEMBERSHIPSTATUS, null);
    }

    public void setOfferDetails(String loadId) {
        editor.putString(OFFERDETAILS, loadId);
        editor.commit();
    }
    public String getOfferDetails() {
        return preferences.getString(OFFERDETAILS, null);
    }

    public void setDocumentVerification(String loadId) {
        editor.putString(DOCUMENTVERIFICATION, loadId);
        editor.commit();
    }
    public String getDocumentVerification() {
        return preferences.getString(DOCUMENTVERIFICATION, null);
    }

    public void setPaymentRequest(String loadId) {
        editor.putString(PAYMENTREQUEST, loadId);
        editor.commit();
    }
    public String getPaymentRequest() {
        return preferences.getString(PAYMENTREQUEST, null);
    }

    public void setShareMobileNumber(String loadId) {
        editor.putString(SHAREMOBILENUMBER, loadId);
        editor.commit();
    }
    public String getShareMobileNumbert() {
        return preferences.getString(SHAREMOBILENUMBER, null);
    }

    public void setLoanMonth(String loadId) {
        editor.putString(LOANMONTH, loadId);
        editor.commit();
    }
    public String getLoanMonth() {
        return preferences.getString(LOANMONTH, null);
    }

    public void setPaymentRequestStatus(String loadId) {
        editor.putString(PAYMENTREQUESTSTATUS, loadId);
        editor.commit();
    }
    public String getPaymentRequestStatush() {
        return preferences.getString(PAYMENTREQUESTSTATUS, null);
    }

    public void setAppLoanStatus(String loadId) {
        editor.putString(APPLYLOANSTATUS, loadId);
        editor.commit();
    }
    public String getAppLoanStatus() {
        return preferences.getString(APPLYLOANSTATUS, null);
    }

    public void setApplyLoanMessage(String loadId) {
        editor.putString(APPLYLOANMESSAGE, loadId);
        editor.commit();
    }
    public String getApplyLoanMessage() {
        return preferences.getString(APPLYLOANMESSAGE, null);
    }


    public void setLoanMonthApply(String loadId) {
        editor.putString(LOANMONTHAPPLY, loadId);
        editor.commit();
    }
    public String getLoanMonthApply() {
        return preferences.getString(LOANMONTHAPPLY, null);
    }

    public void setLoanEmiAmount(String loadId) {
        editor.putString(LOANEMIAMOUNT, loadId);
        editor.commit();
    }
    public String getLoanEmiAmount() {
        return preferences.getString(LOANEMIAMOUNT, null);
    }

    public void setLoanEmiDate(String loadId) {
        editor.putString(LOANEMIDATE, loadId);
        editor.commit();
    }
    public String getLoanEmiDate() {
        return preferences.getString(LOANEMIDATE, null);
    }

    public void setLoanAmountApply(String loadId) {
        editor.putString(LOANAMOUNT, loadId);
        editor.commit();
    }
    public String getLoanAmountApply() {
        return preferences.getString(LOANAMOUNT, null);
    }



    public void clearAll() {
        editor.clear().apply();
    }


}

