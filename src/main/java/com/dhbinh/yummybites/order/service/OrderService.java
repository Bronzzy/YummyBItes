package com.dhbinh.yummybites.order.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.diningtable.entity.DiningTable;
import com.dhbinh.yummybites.diningtable.service.DiningTableService;
import com.dhbinh.yummybites.diningtable.service.dto.DiningTableDTO;
import com.dhbinh.yummybites.order.entity.Order;
import com.dhbinh.yummybites.order.repository.OrderRepository;
import com.dhbinh.yummybites.order.service.dto.OrderDTO;
import com.dhbinh.yummybites.order.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiningTableService diningTableService;

    private final OrderMapper orderMapper;

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
}
