package com.dhbinh.yummybites.reservation.entity;

import com.dhbinh.yummybites.reservation.service.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnReservationCompleteEvent> {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void onApplicationEvent(OnReservationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnReservationCompleteEvent event) {
        ReservationDTO reservation = event.getReservation();
        String token = UUID.randomUUID().toString();

        String recipientAddress = reservation.getEmail();
        String subject = "Reservation complete";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message = "Thank you for reservation";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        javaMailSender.send(email);

    }
}
