package com.dhbinh.yummybites.supplier.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.supplier.entity.Supplier;
import com.dhbinh.yummybites.supplier.repository.SupplierRepository;
import com.dhbinh.yummybites.supplier.service.dto.SupplierDTO;
import com.dhbinh.yummybites.supplier.service.mapper.SupplierMapper;
import com.dhbinh.yummybites.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    private final SupplierMapper supplierMapper;

    private final Utils utils;

    public SupplierDTO create(SupplierDTO supplierDTO) {
        verify(supplierDTO);

        Supplier supplier = Supplier.builder()
                .name(supplierDTO.getName())
                .address(supplierDTO.getAddress().trim())
                .phone(supplierDTO.getPhone().trim())
                .email(supplierDTO.getEmail().trim())
                .build();

        return supplierMapper.toDTO(supplierRepository.save(supplier));
    }

    public List<SupplierDTO> findAll() {
        return supplierMapper.toDTOList(supplierRepository.findAll());
    }

    public SupplierDTO findById(Long ID) {
        Supplier supplier = supplierRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_SUPPLIER_NOT_FOUND,
                        ErrorMessage.SUPPLIER_NOT_FOUND));

        return supplierMapper.toDTO(supplier);
    }

    public SupplierDTO update(Long id, SupplierDTO supplierDTO) {
        verify(supplierDTO);

        Supplier supplier = supplierRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_SUPPLIER_NOT_FOUND,
                        ErrorMessage.SUPPLIER_NOT_FOUND));

        supplierMapper.mapFromDto(supplierDTO, supplier);

        return supplierMapper.toDTO(supplier);
    }

    public void verify(SupplierDTO supplierDTO) {

        if (supplierDTO.getName() != null) {
            supplierDTO.setName(utils.capitalizeFirstWordAndAfterWhitespace(supplierDTO.getName().trim()));
        }

        if (supplierDTO.getAddress() != null) {
            supplierDTO.setAddress(utils.capitalizeFirstWordAndAfterWhitespace(supplierDTO.getAddress().trim()));
        }

        if (isNameExist(supplierDTO.getName())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_SUPPLIER_NAME_EXIST,
                    ErrorMessage.SUPPLIER_NAME_EXIST);
        }

        if (isAddressAlreadyUsed(supplierDTO.getAddress())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_SUPPLIER_ADDRESS_EXIST,
                    ErrorMessage.SUPPLIER_ADDRESS_EXIST);
        }

        if (!isPhoneValidFormat(supplierDTO.getPhone())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_PHONE_WRONG_FORMAT,
                    ErrorMessage.PHONE_WRONG_FORMAT);
        }

        if (!isEmailValidFormat(supplierDTO.getEmail())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_EMAIL_WRONG_FORMAT,
                    ErrorMessage.EMAIL_WRONG_FORMAT);
        }
    }

    public boolean isNameExist(String name) {
        boolean isNameExist = false;
        if (name != null) {
            isNameExist = supplierRepository.findByNameIgnoreCase(name).isPresent();
        }
        return isNameExist;
    }

    public boolean isAddressAlreadyUsed(String address) {
        boolean isAddressAlreadyUsed = false;
        if (address != null) {
            isAddressAlreadyUsed = supplierRepository.findByAddressIgnoreCase(address.trim()).isPresent();
        }
        return isAddressAlreadyUsed;
    }

    public boolean isPhoneValidFormat(String phone) {
        boolean isPhoneValidFormat = false;
        String pattern = "^[0-9\\s()-]+$";
        if (phone != null) {
            isPhoneValidFormat = phone.trim().matches(pattern);
        }
        return isPhoneValidFormat;
    }


    public static boolean isEmailValidFormat(String email) {
        String pattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        if (email == null) {
            return false;
        }
        return email.matches(pattern);
    }

    public SupplierDTO findByNameIgnoreCase(String name) {
        return supplierMapper.toDTO(supplierRepository.findByNameIgnoreCase(name.trim()).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_SUPPLIER_NOT_FOUND,
                        ErrorMessage.SUPPLIER_NOT_FOUND)));
    }
}
