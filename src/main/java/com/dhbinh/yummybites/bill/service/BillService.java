package com.dhbinh.yummybites.bill.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.bill.repository.BillRepository;
import com.dhbinh.yummybites.bill.service.dto.BillDTO;
import com.dhbinh.yummybites.bill.service.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper billMapper;

    public List<BillDTO> findAll() {
        return billMapper.toDTOList(billRepository.findAll());
    }

    public BillDTO findById(Long id) {
        return billMapper.toDTO(billRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_BILL_NOT_FOUND,
                        ErrorMessage.BILL_NOT_FOUND)));
    }
}
