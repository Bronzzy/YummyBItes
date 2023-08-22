package com.dhbinh.yummybites.reservation.service;

import com.dhbinh.yummybites.reservation.entity.Reservation;
import com.dhbinh.yummybites.reservation.entity.VerificationToken;
import com.dhbinh.yummybites.reservation.repository.ReservationRepository;
import com.dhbinh.yummybites.reservation.repository.VerifyTokenRepository;
import com.dhbinh.yummybites.reservation.service.dto.ReservationDTO;
import com.dhbinh.yummybites.reservation.service.mapper.ReservationMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private VerifyTokenRepository verifyTokenRepository;

    public ReservationDTO save(ReservationDTO reservationDTO) {

        Reservation reservation = Reservation.builder()
                .reservationDate((reservationDTO.getReservationDate()))
                .name(reservationDTO.getName())
                .email(reservationDTO.getEmail())
                .numberOfGuests(reservationDTO.getNumberOfGuests())
                .note(reservationDTO.getNote())
                .verified(false)
                .build();

        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }

    public List<ReservationDTO> findAll() {
        return reservationMapper.toDTOList(reservationRepository.findAll());
    }

    public void createVerificationForReservation(Reservation reservation, String token) {
        VerificationToken myToken = new VerificationToken(reservation, token);
        verifyTokenRepository.save(myToken);
    }

    public VerificationToken getVerificationToken(String verificationToken) {
        return verifyTokenRepository.findByToken(verificationToken);
    }

    @Scheduled(cron = "0 1 * ? * *")
    public void deleteReservationNotVerify() {
        List<Reservation> reservationList = reservationRepository.findAll();

        LocalDateTime current = LocalDateTime.now();
        for (Reservation reservation : reservationList) {
            if (ChronoUnit.MINUTES.between(current, reservation.getCreatedDate()) > 60 && !reservation.isVerified()) {
                reservationRepository.delete(reservation);
            }
        }
    }

    public String confirmReservation(WebRequest request, Model model, String token) {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = getVerificationToken(token);
        if (verificationToken == null) {
            return "bad user";
        }

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0){
            return "bad user";
        }

        Reservation reservation = verificationToken.getReservation();
        reservation.setVerified(true);
        reservationRepository.save(reservation);
        return "verify success";
    }

    @Scheduled(cron = "0 33 00 * * *")
    public void exportTodayReservation() {
        List<Reservation> reservationList = reservationRepository.findByReservationDate(LocalDate.now().getDayOfMonth());
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reservation");

            int rowIdx = 0;
            Row titleRow = sheet.createRow(rowIdx++);
            titleRow.createCell(0).setCellValue("Reservation Time");
            titleRow.createCell(1).setCellValue("Guest name");
            titleRow.createCell(2).setCellValue("Number of Guests");
            titleRow.createCell(3).setCellValue("Note");

            for (Reservation reservation : reservationList) {
                Row row = sheet.createRow(rowIdx++);

                Cell cellReservationHour = row.createCell(0);
                cellReservationHour.setCellValue(reservation.getReservationDate().getHour() + ":" + reservation.getReservationDate().getMinute());

                Cell cellName = row.createCell(1);
                cellName.setCellValue(reservation.getName());

                Cell cellNumberOfGuests = row.createCell(2);
                cellNumberOfGuests.setCellValue(reservation.getNumberOfGuests());

                Cell cellNote = row.createCell(3);
                cellNote.setCellValue(reservation.getNote());
            }

            Row resultRow = sheet.createRow(rowIdx++);
            resultRow.createCell(0).setCellValue("Total Hour");
            resultRow.createCell(1).setCellValue("Total Name");
            resultRow.createCell(2).setCellValue(reservationList.stream().
                    mapToInt(Reservation::getNumberOfGuests).sum());
            resultRow.createCell(3).setCellValue("Total Note");

            try (FileOutputStream fileOut = new FileOutputStream("D:/Code/YummyBites/report/reservation/reservation_" + LocalDate.now() + ".xlsx")) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
