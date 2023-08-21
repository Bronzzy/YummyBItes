package com.dhbinh.yummybites.utils.eventlistener.reservation;

import com.dhbinh.yummybites.reservation.service.ReservationService;
import com.dhbinh.yummybites.reservation.service.dto.ReservationDTO;
import com.dhbinh.yummybites.reservation.service.mapper.ReservationMapper;
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

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public void onApplicationEvent(OnReservationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnReservationCompleteEvent event) {
        ReservationDTO dto = event.getReservation();
        String token = UUID.randomUUID().toString();
        reservationService.createVerificationForReservation(reservationMapper.toEntity(dto), token);

        String recipientAddress = dto.getEmail();
        String subject = "YummyBites Confirmation for Reservation";
        String confirmationUrl = event.getAppUrl() + "/reservations/reservation-confirm?token=" + token;
        String message = "Thank you for reservation. Please click at the link below to complete you registration \n" +
                "This link will only be available in 1 hour.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        javaMailSender.send(email);

    }
}
