package com.semafors.grzegorz.semafors;

import android.content.Intent;

/**
 * Created by grzegorz on 13.11.17.
 */

public enum Time {
    SPINNER_TIME_5_MIN ("5 min", 5*60*1000l),
    SPINNER_TIME_10_MIN ("10 min", 10*60*1000l),
    SPINNER_TIME_20_MIN ("20 min", 20*60*1000l),
    SPINNER_TIME_30_MIN ("30 min", 30*60*1000l),
    SPINNER_TIME_45_MIN ("45 min", 45*60*1000l),
    SPINNER_TIME_60_MIN ("60 min", 60*60*1000l);


    private String name;
    private long value;

    Time(String name, Long value){
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    public Long getValue(){
        return this.value;
    }
}
