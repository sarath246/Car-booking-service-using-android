package com.example.demoapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyViewHolder> {

    List<CarListBean>carListBeansList;

    CarListBean carListBean;

    public CarListAdapter(List<CarListBean> carListBeans){
        this.carListBeansList=carListBeans;
    }

    @NonNull
    @Override
    public CarListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.car_collection,parent,false);

        return new CarListAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarListAdapter.MyViewHolder holder, int position) {

        carListBean=carListBeansList.get(position);

        holder.carname.setText(carListBean.getCarname());
        holder.type.setText(carListBean.cartype);
        holder.showroomname.setText(carListBean.getShowroom());
        holder.price.setText(carListBean.getPrice());

    }

    @Override
    public int getItemCount() {
        return carListBeansList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView carname,type,showroomname,price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            carname=itemView.findViewById(R.id.car_name);
            type=itemView.findViewById(R.id.car_type);
            showroomname=itemView.findViewById(R.id.showroom);
            price=itemView.findViewById(R.id.car_price);

        }
    }
}
