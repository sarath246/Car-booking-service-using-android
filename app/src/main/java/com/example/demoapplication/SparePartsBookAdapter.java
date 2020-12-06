package com.example.demoapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SparePartsBookAdapter extends RecyclerView.Adapter<SparePartsBookAdapter.MyViewHolder> {

    List<SparePartsBookingBean>sparePartsBookingBeanList;

    SparePartsBookingBean sparePartsBookingBean;

    public SparePartsBookAdapter(List<SparePartsBookingBean>sparePartsBookingBeans){

        this.sparePartsBookingBeanList=sparePartsBookingBeans;
    }

    @NonNull
    @Override
    public SparePartsBookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_spare_parts_view_details,parent,false);

        return new SparePartsBookAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SparePartsBookAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return sparePartsBookingBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView spareparts_name,spareparts_type,companyid,spareparts_cost,description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            spareparts_name=itemView.findViewById(R.id.name2);
            spareparts_type=itemView.findViewById(R.id.type2);
            companyid=itemView.findViewById(R.id.company2);
            spareparts_cost=itemView.findViewById(R.id.rate2);
            description=itemView.findViewById(R.id.description2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int a=getAdapterPosition();
                    sparePartsBookingBean=sparePartsBookingBeanList.get(a);
                    String id=sparePartsBookingBean.getCarid();


                }

            });


        }
    }
}
