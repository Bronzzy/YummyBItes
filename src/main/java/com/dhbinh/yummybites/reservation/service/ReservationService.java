package com.dhbinh.yummybites.reservation.service;

import com.dhbinh.yummybites.reservation.entity.Reservation;
import com.dhbinh.yummybites.reservation.repository.ReservationRepository;
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

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    private static final LocalTime RUSH_HOUR_START = LocalTime.parse("17:30:00");
    private static final LocalTime RUSH_HOUR_END = LocalTime.parse("20:30:00");

    public ReservationDTO save(ReservationDTO reservationDTO) {

        Reservation reservation = Reservation.builder()
                .reservationDate((reservationDTO.getReservationDate()))
                .name(reservationDTO.getName())
                .numberOfGuests(reservationDTO.getNumberOfGuests())
                .note(reservationDTO.getNote())
                .build();

        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }

    public List<ReservationDTO> findAll() {
        return reservationMapper.toDTOList(reservationRepository.findAll());
    }

    @Scheduled(cron = "0 33 00 * * *")
    public void exportReservationTodayToExcel() {
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
