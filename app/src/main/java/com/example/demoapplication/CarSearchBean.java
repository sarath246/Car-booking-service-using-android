package com.example.demoapplication;

import android.content.Intent;

public class CarSearchBean {

    String carid,carname,cartypename,cartypeid,carrate,showroomid;

    public String getCarid() {
        return carid;
    }

    public String getShowroomid() {
        return showroomid;
    }

    public void setShowroomid(String showroomid) {
        this.showroomid = showroomid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCartypename() {
        return cartypename;
    }

    public void setCartypename(String cartypename) {
        this.cartypename = cartypename;
    }

    public String getCartypeid() {
        return cartypeid;
    }

    public void setCartypeid(String cartypeid) {
        this.cartypeid = cartypeid;
    }

    public String getCarrate() {
        return carrate;
    }

    public void setCarrate(String carrate) {
        this.carrate = carrate;
    }


}

