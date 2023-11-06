package com.dhbinh.restaurantservice.onlineorderdetail.controller;

import com.dhbinh.restaurantservice.onlineorder.service.dto.OnlineOrderDTO;
import com.dhbinh.restaurantservice.onlineorderdetail.service.OnlineOrderDetailService;
import com.dhbinh.restaurantservice.onlineorderdetail.service.dto.OnlineOrderDetailDTO;
import com.dhbinh.restaurantservice.utils.calculatedistance.CalculateDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/online-order-details")
public class OnlineOrderDetailResource {

    @Autowired
    private OnlineOrderDetailService onlineOrderDetailService;
    

    @PostMapping
    public ResponseEntity<OnlineOrderDTO> createOnlineOrder(@Valid @RequestBody List<OnlineOrderDetailDTO> onlineOrderDetailDTOList,
                                                            @RequestParam("origin") String origin,
                                                            @RequestParam("destination") String destination,
                                                            @RequestParam("customerPhone") String customerPhone) {
        OnlineOrderDTO dto = onlineOrderDetailService.createOnlineOrder(onlineOrderDetailDTOList, origin, destination, customerPhone);
        return ResponseEntity.created(URI.create("/api/orders" + dto.getId())).body(dto);
    }

    @GetMapping("/get-distance")
    public double getDistance(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        return CalculateDistance.calculateDistance(origin, destination);
    }
}
