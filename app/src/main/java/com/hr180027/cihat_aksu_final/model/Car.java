package com.hr180027.cihat_aksu_final.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Car  implements Serializable {


    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getLogoUrl() {
        return LogoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        LogoUrl = logoUrl;
    }

    @SerializedName("CarName")
    @Expose
    String carName;

    @SerializedName("Year")
    @Expose
    int year;

    @SerializedName("Detail")
    @Expose
    String detail;

    @SerializedName("Horsepower")
    @Expose
    int horsePower;

    @SerializedName("LogoUrl")
    @Expose
    String LogoUrl;

}
