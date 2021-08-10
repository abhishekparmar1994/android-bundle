package com.ckinfotech.investor.Adepter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.ckinfotech.investor.R;
import com.ckinfotech.investor.Util.MyPrefs;
import com.ckinfotech.investor.customfragment.PayLoansFragment;
import com.ckinfotech.investor.model.LoanData.LoanEmiData;


import java.util.List;

import static java.security.AccessController.getContext;

public class PayLoansAdapter extends RecyclerView.Adapter<PayLoansAdapter.MyviewHolder> {
    Context context;
    List<LoanEmiData> loanEmiData;
    public static String child_id_get, childStockName;
    PayLoansFragment fr;
    public String CurrentMonth;
    public String Loanid;
    MyPrefs myPrefs;

    public PayLoansAdapter(String loanid, String CurrentMonth, final Context context, List<LoanEmiData> loanEmiData) {
        this.context = context;
        this.loanEmiData = loanEmiData;
        this.CurrentMonth = CurrentMonth;
        this.Loanid = loanid;
        fr = new PayLoansFragment();
        myPrefs = new MyPrefs(context);
    }

    public void setMovieList(List<LoanEmiData> loanEmiData) {
        this.loanEmiData = loanEmiData;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_payloans, parent, false);
        return new MyviewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder holder, final int position) {

        if (loanEmiData.get(position).getEmiPaymentRequestStatus().equalsIgnoreCase("success")) {
            holder.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.grien_60));
            holder.textEmiStatus.setText("Success");
        } else if (loanEmiData.get(position).getEmiPaymentRequestStatus().equalsIgnoreCase("emi_bounce")) {
            holder.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.red_60));
            holder.textEmiStatus.setText("Emi Bounce");
        } else if (loanEmiData.get(position).getEmiPaymentRequestStatus().equalsIgnoreCase("inactive")) {
            holder.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_60));
            holder.textEmiStatus.setText("Inactive");
        }else if (loanEmiData.get(position).getEmiPaymentRequestStatus().equalsIgnoreCase("pending")) {
            holder.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.yelo_60));
            holder.textEmiStatus.setText("Pending");
        }
        holder.textDuedate.setText(loanEmiData.get(position).getDueDate());
        holder.textEmiBounce.setText(loanEmiData.get(position).getEmiBounceCharge());
        holder.textEmiAmount.setText(loanEmiData.get(position).getEmiAmountWithIntrest());

//        Log.e("Adepter", "" + loanEmiData.get(position).getEmiPaymentRequestStatus());
        if ((CurrentMonth.equalsIgnoreCase(loanEmiData.get(position).getCheckDate())&&!loanEmiData.get(position).getEmiPaymentRequestStatus().equalsIgnoreCase("pending")) || loanEmiData.get(position).getEmiPaymentRequestStatus().equalsIgnoreCase("emi_bounce")) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fr.EmiPayRequest(v,(Activity)context,loanEmiData.get(position).getEmiAmountWithIntrest(),Loanid,loanEmiData.get(position).getCheckDate(),myPrefs.getToken());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (loanEmiData != null) {
            return loanEmiData.size();
        }
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView textDuedate, textEmiStatus, textEmiBounce, textEmiAmount;
        //        ImageView image;
        CardView cardView;

        //        Button btn_paid_adep;
        public MyviewHolder(View itemView) {
            super(itemView);
            textDuedate = (TextView) itemView.findViewById(R.id.txtpayLoanDueDate);
            textEmiStatus = (TextView) itemView.findViewById(R.id.txtpayLoanEmiStatus);
            textEmiBounce = (TextView) itemView.findViewById(R.id.txtpayLoanBounseCharge);
            textEmiAmount = (TextView) itemView.findViewById(R.id.txtpayLoanEmiAmount);

            cardView = (CardView) itemView.findViewById(R.id.cardpayLoanView);

//            template = itemView.findViewById(R.id.nativeTemplateViewadep);
        }
    }
}