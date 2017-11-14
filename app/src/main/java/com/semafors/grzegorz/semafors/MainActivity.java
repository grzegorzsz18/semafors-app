package com.semafors.grzegorz.semafors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    UserService userService;
    ConnectionService connectionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userService = UserService.getUserService();
        setContentView(R.layout.activity_main);
        connectionService = ConnectionService.getConnectionService();
        TabHost host = (TabHost)findViewById(R.id.tabhost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Reserve");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Reserve");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Future");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Future");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Expired");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Expired");
        host.addTab(spec);
        initializeTab1();

    }

    private void initializeTab1(){
        connectionService.setReservationPlaces(this);
        Spinner selectTimeSpinner = (Spinner) findViewById(R.id.spinnerSelectDuration);
        selectTimeSpinner.setAdapter(new ArrayAdapter<Time>(this,android.R.layout.simple_spinner_item,Time.values()));
        selectTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getInformationAboutWaitingTime();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinner = (Spinner)findViewById(R.id.spinnerListOfPlaces);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getInformationAboutWaitingTime();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    public void getInformationAboutWaitingTime() {
        Spinner selectTimeSpinner = (Spinner) findViewById(R.id.spinnerSelectDuration);
        Time enumTime = (Time) selectTimeSpinner.getSelectedItem();
        Long time = enumTime.getValue();

        Spinner selectReservationPlace = (Spinner) findViewById(R.id.spinnerListOfPlaces);
        ReservationPlace reservationPlace = (ReservationPlace) selectReservationPlace.getSelectedItem();
        if (reservationPlace != null) {
            connectionService.setEstimationWaitingTime(this, reservationPlace.getId(), time);
            return;
        }
        connectionService.setEstimationWaitingTime(this, 1l, time);
    }

    public void setEstmatedWaitingTime(Long time){
        TextView estimatedTime = (TextView) findViewById(R.id.textViewWaitingTime);
        estimatedTime.setText(convertMilisToTime(time));
    }

    private String convertMilisToTime(Long time){
        return String.format("%d h %d min",
                TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time))
        );
    }

    public void setReservationPlaces(List<ReservationPlace> reservationPlaces){
        Spinner spinner = (Spinner)findViewById(R.id.spinnerListOfPlaces);
        ArrayAdapter<ReservationPlace> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.addAll(reservationPlaces);
    }
}
