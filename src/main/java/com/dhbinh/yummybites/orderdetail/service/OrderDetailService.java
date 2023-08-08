package com.dhbinh.yummybites.orderdetail.service;

import com.dhbinh.yummybites.base.security.jwt.JwtUtils;
import com.dhbinh.yummybites.diningtable.service.DiningTableService;
import com.dhbinh.yummybites.diningtable.service.mapper.DiningTableMapper;
import com.dhbinh.yummybites.employee.entity.Employee;
import com.dhbinh.yummybites.employee.service.EmployeeService;
import com.dhbinh.yummybites.employee.service.mapper.EmployeeMapper;
import com.dhbinh.yummybites.menuitem.service.MenuItemService;
import com.dhbinh.yummybites.menuitem.service.mapper.MenuItemMapper;
import com.dhbinh.yummybites.order.entity.Order;
import com.dhbinh.yummybites.order.repository.OrderRepository;
import com.dhbinh.yummybites.order.service.dto.OrderDTO;
import com.dhbinh.yummybites.order.service.mapper.OrderMapper;
import com.dhbinh.yummybites.orderdetail.entity.OrderDetail;
import com.dhbinh.yummybites.orderdetail.repository.OrderDetailRepository;
import com.dhbinh.yummybites.orderdetail.service.dto.OrderDetailDTO;
import com.dhbinh.yummybites.orderdetail.service.mapper.OrderDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiningTableService diningTableService;

    private final OrderDetailMapper orderDetailMapper;

    private final MenuItemMapper menuItemMapper;

    private final EmployeeMapper employeeMapper;

    private final OrderMapper orderMapper;

    private final DiningTableMapper diningTableMapper;

    private final JwtUtils jwtUtils;

    public OrderDTO create(String token, List<OrderDetailDTO> orderDetailDTOList, Long tableId) {

        List<OrderDetail> detailList = new ArrayList<>();

        double totalPrice = 0;

        Order order = Order.builder()
                .employee(findEmployeeByUsername(getUserNameFromToken(token)))
                .createdDate(LocalDateTime.now())
                .isPaid(false)
                .diningTable(diningTableMapper.toEntity(diningTableService.setOccupied(tableId)))
                .build();

        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .menuItem(menuItemMapper.toEntity(menuItemService.findByName(orderDetailDTO.getMenuItemName().trim())))
                    .quantity(orderDetailDTO.getQuantity())
                    .price(menuItemMapper.toEntity(menuItemService.findByName(orderDetailDTO.getMenuItemName().trim())).getPrice() * orderDetailDTO.getQuantity())
                    .build();

            totalPrice += orderDetail.getPrice();
            detailList.add(orderDetail);
        }

        order.setOrderDetails(detailList);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        orderDTO.setOrderDetails(orderDetailMapper.toDTOList(detailList));
        return orderDTO;
    }

    public String getUserNameFromToken(String token) {
        String nameToken = "";
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            nameToken = token.substring(7);
        }
        return jwtUtils.getUserNameFromJwtToken(nameToken);
    }

    public Employee findEmployeeByUsername(String username) {
        return employeeMapper.toEntity(employeeService.findByEmail(username));
    }
}
