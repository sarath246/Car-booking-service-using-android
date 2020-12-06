package com.example.demoapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SparePartsBookingAdapter extends RecyclerView.Adapter<SparePartsBookingAdapter.MyViewHolder> {

    List<SparePartsBookingBean>sparePartsBookingBeanList;

    SparePartsBookingBean sparePartsBookingBean;



    public SparePartsBookingAdapter(List<SparePartsBookingBean> sparePartsBookingBeans){

        this.sparePartsBookingBeanList=sparePartsBookingBeans;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_spare_parts_view_details,parent,false);

        return new SparePartsBookingAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SparePartsBookingAdapter.MyViewHolder holder, int position) {

        sparePartsBookingBean=sparePartsBookingBeanList.get(position);

        holder.spareparts_name.setText(sparePartsBookingBean.getSpareparts_name());
        holder.spareparts_type.setText(sparePartsBookingBean.getSpareparts_type());
        holder.companyid.setText(sparePartsBookingBean.getCompanyid());
        holder.spareparts_cost.setText(sparePartsBookingBean.getSpareparts_cost());
        holder.description.setText(sparePartsBookingBean.getDescription());
        holder.showroom.setText(sparePartsBookingBean.getShowroom());

    }


    @Override
    public int getItemCount() {
        return sparePartsBookingBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView spareparts_name,spareparts_type,companyid,spareparts_cost,description,showroom;
        Button btn_purchase;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            spareparts_name=itemView.findViewById(R.id.name2);
            spareparts_type=itemView.findViewById(R.id.type2);
            companyid=itemView.findViewById(R.id.company2);
            spareparts_cost=itemView.findViewById(R.id.rate2);
            description=itemView.findViewById(R.id.description2);
            showroom=itemView.findViewById(R.id.showroom);

            btn_purchase=itemView.findViewById(R.id.btn_purchase);

            btn_purchase.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a=getAdapterPosition();
                    sparePartsBookingBean=sparePartsBookingBeanList.get(a);
                    String id=sparePartsBookingBean.getCarid();

                    String spareparts_name=sparePartsBookingBean.getSpareparts_name();



//                    Intent e=new Intent(v.getContext(),Car_details.class);
//                    e.putExtra("carid",id);
//                    v.getContext().startActivity(e);
//                    Toast.makeText(v.getContext(), "Clicked"+id, Toast.LENGTH_SHORT).show();


//                    Intent i=new Intent(v.getContext(),spare_parts_view_details.class);
//                    i.putExtra("sparepartsname",spareparts_name);
//                    v.getContext().startActivity(i);

                    //Intent p=new Intent(SparePartsBookingAdapter.this,spare_parts_view_details.class);
//        p.putExtra("sparepartsname",spareparts_name);
//        startActivity(p);

                }

            });

        }


        @Override
        public void onClick(View v) {

            int a=getAdapterPosition();
            sparePartsBookingBean=sparePartsBookingBeanList.get(a);
            String id=sparePartsBookingBean.getCarid();

            String spareparts_name=sparePartsBookingBean.getSpareparts_name();
            String showroomid=sparePartsBookingBean.getShowroom();
            String type=sparePartsBookingBean.getSpareparts_type();
            String compid=sparePartsBookingBean.getCompanyid();
            String cost=sparePartsBookingBean.getSpareparts_cost();
            String usersid=sparePartsBookingBean.getUserid();

            Toast.makeText(v.getContext(), "spareparts name=="+spareparts_name, Toast.LENGTH_SHORT).show();

            Intent i=new Intent(v.getContext(),SpareParts_orderNow.class);
            i.putExtra("carid",id);
            i.putExtra("spareparts_name",spareparts_name);
            i.putExtra("showroomid",showroomid);
            i.putExtra("sparepartstype",type);
            i.putExtra("companyid",compid);
            i.putExtra("sparepartscost",cost);
            v.getContext().startActivity(i);

            Toast.makeText(v.getContext(), "carid=="+id, Toast.LENGTH_SHORT).show();
            Toast.makeText(v.getContext(), "sparepartsname=="+spareparts_name, Toast.LENGTH_SHORT).show();


            Toast.makeText(v.getContext(), "type=="+type, Toast.LENGTH_SHORT).show();

            //SparePartsBooking.Purchased p=new SparePartsBooking.Purchased();
            //p.execute(spareparts_name,type,id,showroomid,cost,compid,usersid);
//,compid,costshowroomid,id,
        }
    }
}
