package com.semafors.grzegorz.semafors;

/**
 * Created by grzegorz on 14.11.17.
 */

public class Reservation {
    private Long startTime;
    private Long endTime;
    private Boolean vissibility;
    private Long during;
    private ReservationPlace reservationPlace;

    public Reservation() {
    }

    public Reservation(Boolean vissibility, Long during, ReservationPlace reservationPlace) {
        this.vissibility = vissibility;
        this.during = during;
        this.reservationPlace = reservationPlace;
    }

    public Reservation(Long startTime, Long endTime, Boolean vissibility, Long during) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.vissibility = vissibility;
        this.during = during;
    }

    public ReservationPlace getReservationPlace() {
        return reservationPlace;
    }

    public void setReservationPlace(ReservationPlace reservationPlace) {
        this.reservationPlace = reservationPlace;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Boolean getVissibility() {
        return vissibility;
    }

    public void setVissibility(Boolean vissibility) {
        this.vissibility = vissibility;
    }

    public Long getDuring() {
        return during;
    }

    public void setDuring(Long during) {
        this.during = during;
    }
}
