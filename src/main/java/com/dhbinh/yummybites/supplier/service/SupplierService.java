package com.dhbinh.yummybites.supplier.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.supplier.entity.Supplier;
import com.dhbinh.yummybites.supplier.repository.SupplierRepository;
import com.dhbinh.yummybites.supplier.service.dto.SupplierDTO;
import com.dhbinh.yummybites.supplier.service.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    private final SupplierMapper supplierMapper;

    public SupplierDTO create(SupplierDTO supplierDTO) {

        Supplier supplier = Supplier.builder()
                .name(supplierDTO.getName().trim())
                .address(supplierDTO.getAddress().trim())
                .phone(supplierDTO.getPhone().trim())
                .email(supplierDTO.getEmail().trim())
                .build();

        return supplierMapper.toDTO(supplierRepository.save(supplier));
    }

    public List<SupplierDTO> findAll(){
        return supplierMapper.toDTOList(supplierRepository.findAll());
    }

    public SupplierDTO findByID(Long ID){
        Supplier supplier = supplierRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_SUPPLIER_NOT_FOUND,
                        ErrorMessage.SUPPLIER_NOT_FOUND));

        return supplierMapper.toDTO(supplier);
    }


}
