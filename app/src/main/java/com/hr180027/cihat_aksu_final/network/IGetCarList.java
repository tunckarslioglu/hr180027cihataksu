package com.hr180027.cihat_aksu_final.network;
import com.hr180027.cihat_aksu_final.model.GithubResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;


public interface IGetCarList {

    @GET("carApi.json")
    Observable<GithubResponse> getCars();
}
