package com.example.demoapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;

import java.net.URISyntaxException;

import static android.content.Context.MODE_PRIVATE;

public class SparePartsBookingBean {

        String carid,spareparts_name,spareparts_type,companyid,spareparts_cost,description,showroom,userid;
        Button btn_purchase;




    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getSpareparts_name() {
        return spareparts_name;
    }

    public void setSpareparts_name(String spareparts_name) {
        this.spareparts_name = spareparts_name;
    }

    public String getSpareparts_type() {
        return spareparts_type;
    }

    public void setSpareparts_type(String spareparts_type) {
        this.spareparts_type = spareparts_type;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getSpareparts_cost() {
        return spareparts_cost;
    }

    public void setSpareparts_cost(String spareparts_cost) {
        this.spareparts_cost = spareparts_cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getBtn_purchase() {
        return btn_purchase;
    }

    public void setBtn_purchase(Button btn_purchase) {
        this.btn_purchase = btn_purchase;
    }

    public String getShowroom() {
        return showroom;
    }
    public void setShowroom(String showroom) {
        this.showroom = showroom;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
