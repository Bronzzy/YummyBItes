package com.dhbinh.yummybites.reservation.service;

import com.dhbinh.yummybites.reservation.entity.Reservation;
import com.dhbinh.yummybites.reservation.repository.ReservationRepository;
import com.dhbinh.yummybites.reservation.service.dto.ReservationDTO;
import com.dhbinh.yummybites.reservation.service.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    public ReservationDTO save(ReservationDTO reservationDTO) {

        Reservation reservation = Reservation.builder()
                .reservationDate((reservationDTO.getReservationDate()))
                .name(reservationDTO.getName())
                .numberOfGuests(reservationDTO.getNumberOfGuests())
                .note(reservationDTO.getNote())
                .build();

        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }

    @Scheduled(cron = "0 38 18 * * *")
    public void exportReservationTodayToExcel() {
        List<Reservation> reservationList = reservationRepository.findByReservationDate(LocalDate.now());
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

                Cell cellFirstName = row.createCell(0);
                cellFirstName.setCellValue(reservation.getReservationDate());

                Cell cellLastName = row.createCell(1);
                cellLastName.setCellValue(reservation.getName());

                Cell cellAddress = row.createCell(2);
                cellAddress.setCellValue(reservation.getNumberOfGuests());

                Cell cellPhone = row.createCell(3);
                cellPhone.setCellValue(reservation.getNote());
            }
            try (FileOutputStream fileOut = new FileOutputStream("reservation_" + LocalDate.now() + ".xlsx")) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
