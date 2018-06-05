package com.cecyred.ww.cecyred;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("run_results.html")
    Call<JSONResponse> getJSON();

}
