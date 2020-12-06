package com.example.demoapplication;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.MyViewHolder> {


    List<HomePageBean>homePageBeanList;

    HomePageBean homePageBean;

    public HomePageAdapter(List<HomePageBean> homePageBeans){

        this.homePageBeanList=homePageBeans;

    }


    @NonNull
    @Override
    public HomePageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.hompage_collection,parent,false);
        return new HomePageAdapter.MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull HomePageAdapter.MyViewHolder holder, int position) {

        homePageBean=homePageBeanList.get(position);

        holder.carname.setText(homePageBean.getCarname());
        holder.cartypeid.setText(homePageBean.getCartypeid());
        holder.showroomid.setText(homePageBean.getShowroomid());
        holder.carprice.setText(homePageBean.getCarprice());


    }

    @Override
    public int getItemCount() {
        return homePageBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //implements View.OnClickListener

        TextView carname,cartypeid,showroomid,carprice;
        Button btn_add,btn_book;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            carname=itemView.findViewById(R.id.car_name);
            cartypeid=itemView.findViewById(R.id.car_type);
            showroomid=itemView.findViewById(R.id.showroom);
            carprice=itemView.findViewById(R.id.car_price);

           // btn_add.setOnClickListener(this);
            //btn_book.setOnClickListener(this);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a=getAdapterPosition();
                    homePageBean=homePageBeanList.get(a);
                    String id=homePageBean.cartypeid;

                    int b=getAdapterPosition();
                    homePageBean=homePageBeanList.get(b);
                    String showid=homePageBean.showroomid;

                    Intent e=new Intent(v.getContext(),Car_details.class);
                    e.putExtra("carid",id);
                    v.getContext().startActivity(e);


                }

            });


        }

//        @Override
//        public void onClick(View v) {
//
//            //Toast.makeText(v.getContext(), "", Toast.LENGTH_SHORT).show();
//            if (v==btn_add) {
//                Intent i = new Intent(v.getContext(), Car_addfav.class);
//                v.getContext().startActivity(i);
//            }
//            else if (v==btn_book)
//            {
//                Intent j=new Intent(v.getContext(),Car_details.class);
//                v.getContext().startActivity(j);
//            }
        }
    }
//}
