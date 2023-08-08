package com.dhbinh.yummybites.billdetail.service;

import com.dhbinh.yummybites.bill.entity.Bill;
import com.dhbinh.yummybites.bill.repository.BillRepository;
import com.dhbinh.yummybites.bill.service.dto.BillDTO;
import com.dhbinh.yummybites.bill.service.mapper.BillMapper;
import com.dhbinh.yummybites.billdetail.entity.BillDetail;
import com.dhbinh.yummybites.billdetail.repository.BillDetailRepository;
import com.dhbinh.yummybites.billdetail.service.dto.BillDetailDTO;
import com.dhbinh.yummybites.billdetail.service.mapper.BillDetailMapper;
import com.dhbinh.yummybites.orderdetail.service.OrderDetailService;
import com.dhbinh.yummybites.supplier.service.SupplierService;
import com.dhbinh.yummybites.supplier.service.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailService {

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private BillRepository billRepository;

    private final BillDetailMapper billDetailMapper;

    private final SupplierMapper supplierMapper;

    private final BillMapper billMapper;

    public BillDTO save(String token, List<BillDetailDTO> billDetailDTOList, Long supplierId) {
        List<BillDetail> billDetails = new ArrayList<>();

        double billTotalPrice = 0;

        Bill bill = Bill.builder()
                .supplier(supplierMapper.toEntity(supplierService.findByID(supplierId)))
                .employee(orderDetailService.findEmployeeByUsername(token))
                .createdDate(LocalDateTime.now())
                .build();

        for (BillDetailDTO billDetailDTO : billDetailDTOList) {
            BillDetail billDetail = BillDetail.builder()
                    .ingredient(billDetailDTO.getIngredient())
                    .quantity(billDetailDTO.getQuantity())
                    .pricePerUnit(billDetailDTO.getPricePerUnit())
                    .price(billDetailDTO.getPricePerUnit() * billDetailDTO.getQuantity())
                    .build();

            billTotalPrice += billDetail.getPrice();
            billDetails.add(billDetail);
        }

        bill.setTotalPrice(billTotalPrice);

        billRepository.save(bill);
        BillDTO billDTO = billMapper.toDTO(bill);
        billDTO.setBillDetails(billDetailMapper.toDTOList(billDetails));

        return billDTO;
    }
}
