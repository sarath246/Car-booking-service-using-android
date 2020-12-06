package com.example.demoapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyviewHolder> {

    List<MyOrderBean>myOrderBeanList;

    MyOrderBean myOrderBean;

    public MyOrderAdapter(List<MyOrderBean> myOrderBeans){

        this.myOrderBeanList=myOrderBeans;

    }

    @NonNull
    @Override
    public MyOrderAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_view,parent,false);

        return new MyOrderAdapter.MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.MyviewHolder holder, int position) {

        myOrderBean=myOrderBeanList.get(position);

        holder.carname.setText(myOrderBean.getCarname());
        holder.carcolor.setText(myOrderBean.getCarcolor());
        holder.carprice.setText(myOrderBean.getCarprice());
        holder.showroom.setText(myOrderBean.getShowroom());

    }

    @Override
    public int getItemCount() {
        return myOrderBeanList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView carname,carcolor,carprice,showroom;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            carname=itemView.findViewById(R.id.carname);
            carcolor=itemView.findViewById(R.id.carcolor);
            carprice=itemView.findViewById(R.id.carprice);
            showroom=itemView.findViewById(R.id.showroom);

        }
    }
}
