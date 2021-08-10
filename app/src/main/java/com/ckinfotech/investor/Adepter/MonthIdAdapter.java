package com.ckinfotech.investor.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ckinfotech.investor.R;
import com.ckinfotech.investor.customfr.UserDetailFragment;
import com.ckinfotech.investor.customfragment.ApplyLoanFragment;
import com.ckinfotech.investor.model.IdModel;
import com.ckinfotech.investor.model.responceModel.CountryList;

import java.util.List;

public class MonthIdAdapter extends BaseAdapter {

    private List<IdModel> featureList;

    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;

    private ApplyLoanFragment userDetailFragment;


    public MonthIdAdapter(ApplyLoanFragment userDetailFragment, List<IdModel> featureList) {
        this.featureList = featureList;
        this.userDetailFragment = userDetailFragment;


    }

    public void setSortOrderList(List<IdModel> featureList) {
        this.featureList = featureList;
    }


    @Override
    public int getCount() {
        return featureList.size();
    }

    @Override
    public String getItem(int i) {
        return featureList.get(i).getMonthId();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            layoutInflater = (LayoutInflater) userDetailFragment.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.layout_name, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvPaymentName = (TextView) view.findViewById(R.id.titlename);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvPaymentName.setVisibility(View.VISIBLE);

//        if(i%3==0) {
//            viewHolder.tvPaymentName.setText("featureList.get(i).getName()");
//        }else {
        viewHolder.tvPaymentName.setText(featureList.get(i).getMonthId());
//        }



//        if (featureList.get(0).getName().equalsIgnoreCase("Select Country") && i >= 1) {
//            userDetailFragment.getState(userDetailFragment.token, String.valueOf(featureList.get(i).getId()));
//            Toast.makeText(context, "" + featureList.get(i).getId(), Toast.LENGTH_SHORT).show();
//        }

//        else if (featureList.get(0).getName().equalsIgnoreCase("Select Country") && i >= 1) {
////            userDetailFragment.Villag(userDetailFragment.token, featureList.get(i).getId());
//        }
//        Toast.makeText(context, ""+featureList.get(i).getId()+""+userDetailFragment.token, Toast.LENGTH_SHORT).show();


        return view;
    }

//    public int getPosition(int i) {
//        return 0;
//    }

//    public int getFueatureIndex(int orderId) {
//        for (int i = 0; i < orderFeatureType.size(); i++) {
//            int auction = orderFeatureType.get(i);
//            if (orderId == auction) {
//                return i;
//            }
//        }
//
//        return -1;
//    }


    private class ViewHolder {
        TextView tvPaymentName;
    }


}
