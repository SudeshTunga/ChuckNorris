package com.example.chucknorris.entities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteService {

    @GET("random")
    Call<QuoteLoreResponse> getQuote(@Query("category") String category);
}
