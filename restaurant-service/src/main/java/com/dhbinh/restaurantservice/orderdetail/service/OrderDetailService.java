package com.dhbinh.restaurantservice.orderdetail.service;

import com.dhbinh.restaurantservice.base.security.jwt.JwtUtils;
import com.dhbinh.restaurantservice.diningtable.service.DiningTableService;
import com.dhbinh.restaurantservice.diningtable.service.mapper.DiningTableMapper;
import com.dhbinh.restaurantservice.employee.service.EmployeeService;
import com.dhbinh.restaurantservice.menuitem.service.MenuItemService;
import com.dhbinh.restaurantservice.menuitem.service.mapper.MenuItemMapper;
import com.dhbinh.restaurantservice.order.entity.Order;
import com.dhbinh.restaurantservice.order.repository.OrderRepository;
import com.dhbinh.restaurantservice.order.service.dto.OrderDTO;
import com.dhbinh.restaurantservice.order.service.mapper.OrderMapper;
import com.dhbinh.restaurantservice.orderdetail.entity.OrderDetail;
import com.dhbinh.restaurantservice.orderdetail.service.dto.OrderDetailDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDetailService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private DiningTableService diningTableService;

    @Autowired
    private MenuItemMapper menuItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DiningTableMapper diningTableMapper;

    @Autowired
    private JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailService.class);

    public OrderDTO create(String token, List<OrderDetailDTO> orderDetailDTOList, Long tableId) {
        Order order = Order.builder()
                .employee(employeeService.findByEmail(jwtUtils.getUserNameFromToken(token)))
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
