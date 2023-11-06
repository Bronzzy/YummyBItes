package com.dhbinh.restaurantservice.onlineorder.service;

import com.dhbinh.restaurantservice.onlineorder.repository.OnlineOrderRepository;
import com.dhbinh.restaurantservice.onlineorder.service.mapper.OnlineOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineOrderService {

    @Autowired
    private OnlineOrderRepository onlineOrderRepository;
}
