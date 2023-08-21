package com.dhbinh.yummybites.utils.eventlistener.reservation;

import com.dhbinh.yummybites.reservation.service.dto.ReservationDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnReservationCompleteEvent extends ApplicationEvent {

    private String appUrl;

    private Locale locale;

    private ReservationDTO reservation;

    public OnReservationCompleteEvent(ReservationDTO reservation, Locale locale, String appUrl) {
        super(reservation);

        this.reservation = reservation;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}


