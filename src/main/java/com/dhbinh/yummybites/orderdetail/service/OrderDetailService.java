package com.dhbinh.yummybites.orderdetail.service;

import com.dhbinh.yummybites.base.security.jwt.JwtUtils;
import com.dhbinh.yummybites.diningtable.service.DiningTableService;
import com.dhbinh.yummybites.diningtable.service.mapper.DiningTableMapper;
import com.dhbinh.yummybites.employee.service.EmployeeService;
import com.dhbinh.yummybites.employee.service.mapper.EmployeeMapper;
import com.dhbinh.yummybites.menuitem.service.MenuItemService;
import com.dhbinh.yummybites.menuitem.service.mapper.MenuItemMapper;
import com.dhbinh.yummybites.order.entity.Order;
import com.dhbinh.yummybites.order.repository.OrderRepository;
import com.dhbinh.yummybites.order.service.dto.OrderDTO;
import com.dhbinh.yummybites.order.service.mapper.OrderMapper;
import com.dhbinh.yummybites.orderdetail.entity.OrderDetail;
import com.dhbinh.yummybites.orderdetail.service.dto.OrderDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDetailService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiningTableService diningTableService;

    private final MenuItemMapper menuItemMapper;

    private final EmployeeMapper employeeMapper;

    private final OrderMapper orderMapper;

    private final DiningTableMapper diningTableMapper;

    private final JwtUtils jwtUtils;

    public OrderDTO create(String token, List<OrderDetailDTO> orderDetailDTOList, Long tableId) {

        Order order = Order.builder()
                .employee(employeeMapper.toEntity(employeeService.findByEmail(jwtUtils.getUserNameFromToken(token))))
                .createdDate(LocalDateTime.now())
                .isPaid(false)
                .diningTable(diningTableMapper.toEntity(diningTableService.setOccupied(tableId)))
                .build();

        List<OrderDetail> detailList = new ArrayList<>();
        double orderTotalPrice = 0;
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .menuItem(menuItemMapper.toEntity(menuItemService.findByName(orderDetailDTO.getMenuItemName().trim())))
                    .quantity(orderDetailDTO.getQuantity())
                    .price(menuItemMapper.toEntity(menuItemService.findByName(orderDetailDTO.getMenuItemName().trim())).getPrice() * orderDetailDTO.getQuantity())
                    .build();

            orderTotalPrice += orderDetail.getPrice();
            detailList.add(orderDetail);
        }

        order.setOrderDetails(detailList);
        order.setTotalPrice(orderTotalPrice);

        return orderMapper.toDTO(orderRepository.save(order));
    }
}
