package com.dhbinh.yummybites.order.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.diningtable.service.DiningTableService;
import com.dhbinh.yummybites.order.entity.Order;
import com.dhbinh.yummybites.order.repository.OrderRepository;
import com.dhbinh.yummybites.order.service.dto.OrderDTO;
import com.dhbinh.yummybites.order.service.mapper.OrderMapper;
import com.dhbinh.yummybites.orderdetail.entity.OrderDetail;
import lombok.RequiredArgsConstructor;
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
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiningTableService diningTableService;

    @Autowired
    private OrderMapper orderMapper;

    @Value("${excel.file.location}")
    private String excelFileLocation;

    @Scheduled(cron = "00 21 16 8 * *")
    public List<OrderDTO> findAll() {
        return orderMapper.toDTOList(orderRepository.findAll());
    }

    public OrderDTO findById(Long id) {
        return orderMapper.toDTO(orderRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_ORDER_NOT_FOUND,
                        ErrorMessage.ORDER_NOT_FOUND)));
    }

    public OrderDTO checkout(Long id) {
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_ORDER_NOT_FOUND,
                        ErrorMessage.ORDER_NOT_FOUND));

        order.setIsPaid(true);

        diningTableService.setUnoccupied(order.getDiningTable().getId());
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Scheduled(cron = "00 06 00 * * *")
    public void exportOrderByDate() {
        List<Order> orderList = orderRepository.findAllOrderByDate(LocalDate.now().getDayOfMonth());
        try (FileInputStream fileInputStream = new FileInputStream(excelFileLocation + LocalDate.now() + ".xlsx");
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.createSheet("Daily Income");

            int rowIdx = 0;
            Row titleRow = sheet.createRow(rowIdx++);
            titleRow.createCell(0).setCellValue("Order Number");
            titleRow.createCell(1).setCellValue("Item Name");
            titleRow.createCell(2).setCellValue("Quantity");
            titleRow.createCell(3).setCellValue("Price");
            titleRow.createCell(4).setCellValue("Total Price");

            for (Order order : orderList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(order.getId());
                row.createCell(4).setCellValue(order.getTotalPrice());

                List<OrderDetail> orderDetailList = order.getOrderDetails();
                for (OrderDetail orderDetail : orderDetailList) {
                    Row rowDetail = sheet.createRow(rowIdx++);

                    rowDetail.createCell(1).setCellValue(orderDetail.getMenuItem().getName());
                    rowDetail.createCell(2).setCellValue(orderDetail.getQuantity());
                    rowDetail.createCell(3).setCellValue(orderDetail.getPrice());
                }
            }
            Row resultRow = sheet.createRow(rowIdx++);
            resultRow.createCell(3).setCellValue("Total");
            resultRow.createCell(4).setCellValue(orderList.stream()
                            .mapToDouble(Order::getTotalPrice).sum());

            try (FileOutputStream fileOut = new FileOutputStream(excelFileLocation + LocalDate.now() + ".xlsx")) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
