package com.semafors.grzegorz.semafors;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by grzegorz on 06.11.17.
 */

public interface RetrofitService {
    @POST("/user/login")
    Call<Token> login(@Body User user);

    @GET("/reservation/byPlace/{placeId}/{duration}/{tokenValue}")
    Call<Long> getAvaliableStartTime(@Path("placeId") Long placeId, @Path("duration") Long duration,
                                     @Path("tokenValue")UUID tokenValue);

    @GET("reservationPlace/all/{tokenValue}")
    Call<List<ReservationPlace>> getReservationPlace(@Path("tokenValue")UUID tokenValue);
}
