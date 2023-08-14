package com.dhbinh.yummybites.bill.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.bill.entity.Bill;
import com.dhbinh.yummybites.bill.repository.BillRepository;
import com.dhbinh.yummybites.bill.service.dto.BillDTO;
import com.dhbinh.yummybites.bill.service.mapper.BillMapper;
import com.dhbinh.yummybites.billdetail.entity.BillDetail;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper billMapper;

    @Value("${excel.file.location}")
    private String excelFileLocation;

    public List<BillDTO> findAll() {
        return billMapper.toDTOList(billRepository.findAll());
    }

    public BillDTO findById(Long id) {
        return billMapper.toDTO(billRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_BILL_NOT_FOUND,
                        ErrorMessage.BILL_NOT_FOUND)));
    }

    @Scheduled(cron = "00 00 00 * * *")
    public void exportBillByDate() throws IOException {
        List<Bill> billList = billRepository.findAllOrderByDate(LocalDate.now().getDayOfMonth());
//        try (FileInputStream fileInputStream = new FileInputStream(excelFileLocation + LocalDate.now() + ".xlsx");
//             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Daily Ingredient Import");

            int rowIdx = 0;
            Row titleRow = sheet.createRow(rowIdx++);
            titleRow.createCell(0).setCellValue("Bill Number");
            titleRow.createCell(1).setCellValue("Supplier");
            titleRow.createCell(2).setCellValue("Item");
            titleRow.createCell(3).setCellValue("Quantity");
            titleRow.createCell(4).setCellValue("Price per Unit");
            titleRow.createCell(5).setCellValue("Total Price");

            for (Bill bill : billList) {
                Row row = sheet.createRow(rowIdx++);

                Cell cellBillNumber = row.createCell(0);
                cellBillNumber.setCellValue(bill.getId());

                Cell cellSupplier = row.createCell(1);
                cellSupplier.setCellValue(bill.getSupplier().getName());

                Cell cellTotalPrice = row.createCell(5);
                cellTotalPrice.setCellValue(bill.getTotalPrice());

                List<BillDetail> detailList = bill.getBillDetails();
                for (BillDetail billDetail : detailList) {
                    Row rowDetail = sheet.createRow(rowIdx++);

                    Cell cellItem = rowDetail.createCell(2);
                    cellItem.setCellValue(billDetail.getIngredient().getName());

                    Cell cellQuantity = rowDetail.createCell(3);
                    cellQuantity.setCellValue(billDetail.getQuantity());

                    Cell cellPricePerUnit = rowDetail.createCell(4);
                    cellPricePerUnit.setCellValue(billDetail.getPricePerUnit());

                    Cell cellPrice = rowDetail.createCell(5);
                    cellPrice.setCellValue(billDetail.getPrice());
                }
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(excelFileLocation + LocalDate.now() + ".xlsx")) {
                workbook.write(fileOutputStream);
            }
        } catch (InputCoercionException e) {
            throw new IOException(ErrorMessage.FILE_NOT_FOUND);
        }
    }
}
