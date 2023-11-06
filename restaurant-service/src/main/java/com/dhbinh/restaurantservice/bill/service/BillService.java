package com.dhbinh.restaurantservice.bill.service;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.base.exception.ResourceNotFoundException;
import com.dhbinh.restaurantservice.bill.entity.Bill;
import com.dhbinh.restaurantservice.bill.repository.BillRepository;
import com.dhbinh.restaurantservice.bill.service.dto.BillDTO;
import com.dhbinh.restaurantservice.bill.service.mapper.BillMapper;
import com.dhbinh.restaurantservice.bill.specification.BillSpecification;
import com.dhbinh.restaurantservice.billdetail.entity.BillDetail;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    public List<BillDTO> findWithSpecifications(String employeeName, String supplierName, int day, int month, int year, double priceLessThan, double priceGreaterThan) {
        Specification<Bill> spec = BillSpecification.findWithSpecifications(employeeName, supplierName, day, month, year, priceLessThan, priceGreaterThan);
        return billMapper.toDTOList(billRepository.findAll(spec));
    }

    public void delete(Long id){
        Bill bill = billRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_BILL_NOT_FOUND,
                        ErrorMessage.BILL_NOT_FOUND));
        billRepository.delete(bill);
    }

    @Scheduled(cron = "00 57 14 * * *")
    public void exportBillByDate() throws IOException {
        List<Bill> billList = billRepository.findAllBillByDate(LocalDate.now().getDayOfMonth());
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Daily Ingredient Import");

            int rowIdx = 0;
            Row titleRow = sheet.createRow(rowIdx++);
            titleRow.createCell(0).setCellValue("Bill Number");
            titleRow.createCell(1).setCellValue("Supplier");
            titleRow.createCell(2).setCellValue("Item");
            titleRow.createCell(3).setCellValue("Quantity");
            titleRow.createCell(4).setCellValue("Price per Unit");
            titleRow.createCell(5).setCellValue("Total Bill");

            for (Bill bill : billList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(bill.getId());
                row.createCell(1).setCellValue(bill.getSupplier().getName());
                row.createCell(5).setCellValue(bill.getTotalPrice());

                List<BillDetail> detailList = bill.getBillDetails();
                for (BillDetail billDetail : detailList) {
                    Row rowDetail = sheet.createRow(rowIdx++);

                    rowDetail.createCell(2).setCellValue(billDetail.getIngredient().getName());
                    rowDetail.createCell(3).setCellValue(billDetail.getQuantity());
                    rowDetail.createCell(4).setCellValue(billDetail.getPricePerUnit());
                    rowDetail.createCell(5).setCellValue(billDetail.getPrice());
                }
            }
            Row resultRow = sheet.createRow(rowIdx++);
            resultRow.createCell(4).setCellValue("Total");
            resultRow.createCell(5).setCellValue(billList.stream()
                    .mapToDouble(Bill::getTotalPrice).sum());

            try (FileOutputStream fileOutputStream = new FileOutputStream(excelFileLocation + LocalDate.now() + ".xlsx")) {
                workbook.write(fileOutputStream);
                fileOutputStream.close();

            } catch (FileNotFoundException e) {
                throw new FileNotFoundException(e.getMessage());
            }
        }
    }


}
