package com.semafors.grzegorz.semafors;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by grzegorz on 06.11.17.
 */

public class ConnectionService {

    private static RetrofitService retrofitService;
    private static ConnectionService connectionService;
    private static User user;

    private ConnectionService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configuration.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static ConnectionService getConnectionService() {
        if(connectionService == null){
            connectionService = new ConnectionService();
        }
        return connectionService;
    }

    public void login(final String login, String password, final LoginActivity loginActivity){

        User user = new User(login, password);
        setUser(user);
        final Call<Token> call = retrofitService.login(user);
        call.enqueue(new retrofit2.Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, retrofit2.Response<Token> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Token token = response.body();
                        ConnectionService connectionService = ConnectionService.getConnectionService();
                        User u = connectionService.getUser();
                        u.setToken(token);
                        connectionService.setUser(u);
                        Toast.makeText(loginActivity, "login succesfully", Toast.LENGTH_SHORT).show();
                        loginActivity.goToTheMainPage();
                    }
                    else{
                        Toast.makeText(loginActivity, "Login failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(loginActivity, "Login failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setEstimationWaitingTime(final MainActivity mainActivity, Long placeId, Long duration){
        final Call<Long> call = retrofitService.getAvaliableStartTime(placeId, duration, getUser().getToken().getValue());
        call.enqueue(new retrofit2.Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, retrofit2.Response<Long> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Long time = response.body();
                        mainActivity.setEstmatedWaitingTime(time);
                    }
                }
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Toast.makeText(mainActivity, "Problem with connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void setReservationPlaces(final MainActivity mainActivity){
        final Call<List<ReservationPlace>> call = retrofitService.getReservationPlace(getUser().getToken().getValue());
        call.enqueue(new retrofit2.Callback<List<ReservationPlace>>() {
            @Override
            public void onResponse(Call<List<ReservationPlace>> call, retrofit2.Response<List<ReservationPlace>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<ReservationPlace> reservationPlaces = response.body();
                        mainActivity.setReservationPlaces(reservationPlaces);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ReservationPlace>> call, Throwable t) {
                Toast.makeText(mainActivity, "Problem with connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addReservation(Reservation reservation, final MainActivity mainActivity){
        Toast.makeText(mainActivity, "Added", Toast.LENGTH_SHORT).show();
        final Call<Boolean> call = retrofitService.addReservation(reservation,ConnectionService.getUser().getToken().getValue());
        call.enqueue(new retrofit2.Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, retrofit2.Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void getReservationsByUser(final MainActivity mainActivity){
        final Call<List<Reservation>> call = retrofitService.getReservationByUser(ConnectionService.getUser().getToken().getValue());
        call.enqueue(new retrofit2.Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, retrofit2.Response<List<Reservation>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Reservation> reservation = response.body();
                        mainActivity.setFutureReservations(reservation);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
            }
        });
    }

    public static void setUser(User u){
        user = u;
    }

    public static User getUser(){
        return user;
    }

}
