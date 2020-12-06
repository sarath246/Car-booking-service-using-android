package com.example.demoapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarSearchAdapter extends RecyclerView.Adapter<CarSearchAdapter.MyViewHolder> {

    List<CarSearchBean>carSearchBeanList;

    CarSearchBean carSearchBean;

    public  CarSearchAdapter(List<CarSearchBean>carSearchBeans)
    {
        this.carSearchBeanList=carSearchBeans;
    }

    @NonNull
    @Override
    public CarSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecarsearch,parent,false);

        return new CarSearchAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarSearchAdapter.MyViewHolder holder, int position) {

        carSearchBean=carSearchBeanList.get(position);

        holder.name.setText(carSearchBean.getCarname());
        holder.type.setText(carSearchBean.getCartypename());
        holder.rate.setText(carSearchBean.getCarrate());
        holder.showid.setText(carSearchBean.getShowroomid());
    }

    //Showroom spinner adapter

    @Override
    public int getItemCount() {
        return carSearchBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,rate,type,showid;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tvcarname);
            rate=itemView.findViewById(R.id.tvrate);
            type=itemView.findViewById(R.id.tvcartype);
            showid=itemView.findViewById(R.id.tvshowid);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a=getAdapterPosition();
                    carSearchBean=carSearchBeanList.get(a);
                    String id=carSearchBean.getCarid();


                    Intent e=new Intent(v.getContext(),Car_details.class);
                    e.putExtra("carid",id);
                    v.getContext().startActivity(e);
                    Toast.makeText(v.getContext(), "Clicked"+id, Toast.LENGTH_SHORT).show();

//                    int b=getAdapterPosition();
//                    carSearchBean=carSearchBeanList.get(b);
//                    String showid=carSearchBean.getShowroomid();
//
//                    Intent p=new Intent(v.getContext(),Car_details.class);
//                    p.putExtra("showid",showid);
//                    v.getContext().startActivity(p);

                    //Toast.makeText(v.getContext(), "Showroomid==="+showid, Toast.LENGTH_SHORT).show();

                }

            });

        }
    }
}
