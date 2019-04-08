package com.example.lifeadvice;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AdviceClient {
    @GET("advice")
    Call<Advice> getAdvice();

}
