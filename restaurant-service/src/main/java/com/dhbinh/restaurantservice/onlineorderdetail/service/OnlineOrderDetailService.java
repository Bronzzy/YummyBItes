package com.dhbinh.restaurantservice.onlineorderdetail.service;

import com.dhbinh.restaurantservice.menuitem.service.MenuItemService;
import com.dhbinh.restaurantservice.menuitem.service.mapper.MenuItemMapper;
import com.dhbinh.restaurantservice.onlineorder.entity.OnlineOrder;
import com.dhbinh.restaurantservice.onlineorder.repository.OnlineOrderRepository;
import com.dhbinh.restaurantservice.onlineorder.service.dto.OnlineOrderDTO;
import com.dhbinh.restaurantservice.onlineorder.service.mapper.OnlineOrderMapper;
import com.dhbinh.restaurantservice.onlineorderdetail.entity.OnlineOrderDetail;
import com.dhbinh.restaurantservice.onlineorderdetail.repository.OnlineOrderDetailRepository;
import com.dhbinh.restaurantservice.onlineorderdetail.service.dto.OnlineOrderDetailDTO;
import com.dhbinh.restaurantservice.utils.calculatedistance.CalculateDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineOrderDetailService {

    @Autowired
    private OnlineOrderDetailRepository onlineOrderDetailRepository;

    @Autowired
    private MenuItemMapper menuItemMapper;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private OnlineOrderRepository onlineOrderRepository;

    @Autowired
    private OnlineOrderMapper onlineOrderMapper;

    public OnlineOrderDTO createOnlineOrder(List<OnlineOrderDetailDTO> orderDetailDTOList, String origin, String destination, String customerPhone) {

        OnlineOrder onlineOrder = OnlineOrder.builder()
                .destination(destination)
                .customerPhone(customerPhone)
                .isPaid(false)
                .deliveryFee(calculateDeliveryFee(origin, destination))
                .build();

        List<OnlineOrderDetail> detailList = new ArrayList<>();
        double orderTotalPrice = 0;
        for (OnlineOrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            OnlineOrderDetail orderDetail = OnlineOrderDetail.builder()
                    .onlineOrder(onlineOrder)
                    .menuItem(menuItemMapper.toEntity(menuItemService.findByName(orderDetailDTO.getMenuItemName().trim())))
                    .quantity(orderDetailDTO.getQuantity())
                    .price(menuItemMapper.toEntity(menuItemService.findByName(orderDetailDTO.getMenuItemName().trim())).getPrice() * orderDetailDTO.getQuantity())
                    .build();

            orderTotalPrice += orderDetail.getPrice();
            detailList.add(orderDetail);
        }
        onlineOrder.setOnlineOrderDetails(detailList);
        onlineOrder.setTotalPrice(orderTotalPrice);

        return onlineOrderMapper.toDTO(onlineOrderRepository.save(onlineOrder));
    }

    public double calculateDeliveryFee(String origin, String destination){
       return CalculateDistance.calculateDistance(origin, destination);
    }
}
