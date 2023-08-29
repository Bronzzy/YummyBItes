package com.dhbinh.yummybites.billdetail.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.base.security.jwt.JwtUtils;
import com.dhbinh.yummybites.bill.entity.Bill;
import com.dhbinh.yummybites.bill.repository.BillRepository;
import com.dhbinh.yummybites.bill.service.dto.BillDTO;
import com.dhbinh.yummybites.bill.service.mapper.BillMapper;
import com.dhbinh.yummybites.billdetail.entity.BillDetail;
import com.dhbinh.yummybites.billdetail.repository.BillDetailRepository;
import com.dhbinh.yummybites.billdetail.service.dto.BillDetailDTO;
import com.dhbinh.yummybites.billdetail.service.mapper.BillDetailMapper;
import com.dhbinh.yummybites.employee.service.EmployeeService;
import com.dhbinh.yummybites.employee.service.mapper.EmployeeMapper;
import com.dhbinh.yummybites.ingredients.entity.Ingredient;
import com.dhbinh.yummybites.ingredients.repository.IngredientRepository;
import com.dhbinh.yummybites.supplier.service.SupplierService;
import com.dhbinh.yummybites.supplier.service.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BillDetailService {

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private BillDetailMapper billDetailMapper;

    @Autowired
    private JwtUtils jwtUtils;

    public BillDTO create(String token, List<BillDetailDTO> billDetailDTOList, String supplierName) {

        Bill bill = Bill.builder()
                .supplier(supplierMapper.toEntity(supplierService.findByNameIgnoreCase(supplierName.trim())))
                .employee(employeeService.findByEmail(jwtUtils.getUserNameFromToken(token)))
                .createdDate(LocalDateTime.now())
                .build();

        List<BillDetail> detailList = new ArrayList<>();
        double billTotalPrice = 0;
        for (BillDetailDTO billDetailDTO : billDetailDTOList) {
            Ingredient ingredient = ingredientRepository.findByNameIgnoreCase((billDetailDTO.getIngredientName().trim())).
                    orElseThrow(() -> new ResourceNotFoundException(
                            ErrorMessage.KEY_INGREDIENT_NOT_FOUND,
                            ErrorMessage.INGREDIENT_NOT_FOUND));

            BillDetail billDetail = BillDetail.builder()
                    .bill(bill)
                    .ingredient(ingredient)
                    .quantity(billDetailDTO.getQuantity())
                    .pricePerUnit(billDetailDTO.getPricePerUnit())
                    .price(billDetailDTO.getPricePerUnit() * billDetailDTO.getQuantity())
                    .build();

            ingredient.setQuantity(ingredient.getQuantity() + billDetailDTO.getQuantity());
            ingredientRepository.save(ingredient);
            billTotalPrice += billDetail.getPrice();
            detailList.add(billDetail);
        }

        bill.setBillDetails(detailList);
        bill.setTotalPrice(billTotalPrice);

        return billMapper.toDTO(billRepository.save(bill));
    }

    public List<BillDetailDTO> findAll() {
        return billDetailMapper.toDTOList(billDetailRepository.findAll());
    }

    public BillDetailDTO findById(Long id) {
        return billDetailMapper.toDTO(billDetailRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_BILL_DETAIL_NOT_FOUND,
                        ErrorMessage.BILL_DETAIL_NOT_FOUND)));
    }
}
