package com.hr180027.cihat_aksu_final.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubResponse {


    public List<Car> getResponse() {
        return response;
    }

    public void setResponse(List<Car> response) {
        this.response = response;
    }

    @SerializedName("CarList")
    @Expose
    private List<Car> response;
}
