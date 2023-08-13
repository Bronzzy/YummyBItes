package com.dhbinh.yummybites.order.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.diningtable.service.DiningTableService;
import com.dhbinh.yummybites.order.entity.Order;
import com.dhbinh.yummybites.order.repository.OrderRepository;
import com.dhbinh.yummybites.order.service.dto.OrderDTO;
import com.dhbinh.yummybites.order.service.mapper.OrderMapper;
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

    @Scheduled(cron = "00 00 00 * * *")
    public void exportOrderByDate() {
        List<Order> orderList = orderRepository.findAllOrderByDate(LocalDate.now().getDayOfMonth());
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Daily Income");

            int rowIdx = 0;
            Row titleRow = sheet.createRow(rowIdx++);
            titleRow.createCell(0).setCellValue("Order Number");
            titleRow.createCell(1).setCellValue("Item Name");
            titleRow.createCell(2).setCellValue("Order Price");

            for (Order order : orderList) {
                Row row = sheet.createRow(rowIdx++);

                Cell cellOrderNumber = row.createCell(0);
                cellOrderNumber.setCellValue(rowIdx - 1);

                Cell cellItemName = row.createCell(1);
                cellItemName.setCellValue(order.getOrderDetails().stream()
                        .map(o -> o.getMenuItem().getName())
                        .collect(Collectors.joining(", ")));

                Cell cellOrderPrice = row.createCell(2);
                cellOrderPrice.setCellValue(order.getTotalPrice());
            }

            Row resultRow = sheet.createRow(rowIdx++);
            resultRow.createCell(0).setCellValue("Total");
            resultRow.createCell(2).setCellValue(orderList.stream().
                    mapToDouble(Order::getTotalPrice).sum());

            try (FileOutputStream fileOut = new FileOutputStream("D:/Code/YummyBites/report/income/income_" + LocalDate.now() + ".xlsx")) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
