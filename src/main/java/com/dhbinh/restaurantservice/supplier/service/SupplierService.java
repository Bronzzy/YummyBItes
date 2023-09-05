package com.dhbinh.restaurantservice.supplier.service;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.base.exception.InputValidationException;
import com.dhbinh.restaurantservice.base.exception.ResourceNotFoundException;
import com.dhbinh.restaurantservice.supplier.entity.Supplier;
import com.dhbinh.restaurantservice.supplier.repository.SupplierRepository;
import com.dhbinh.restaurantservice.supplier.service.dto.SupplierDTO;
import com.dhbinh.restaurantservice.supplier.service.mapper.SupplierMapper;
import com.dhbinh.restaurantservice.utils.CommonConstant;
import com.dhbinh.restaurantservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    public Supplier create(SupplierDTO supplierDTO) {
        verifyAndModify(supplierDTO);

        Supplier supplier = Supplier.builder()
                .name(supplierDTO.getName())
                .address(supplierDTO.getAddress().trim())
                .phone(supplierDTO.getPhone().trim())
                .email(supplierDTO.getEmail().trim())
                .build();

        return supplierRepository.save(supplier);
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Supplier findById(Long ID) {
        return supplierRepository.findById(ID)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_SUPPLIER_NOT_FOUND,
                        ErrorMessage.SUPPLIER_NOT_FOUND));
    }

    public Supplier update(Long id, SupplierDTO supplierDTO) {
        verifyAndModify(supplierDTO);

        Supplier supplier = supplierRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_SUPPLIER_NOT_FOUND,
                        ErrorMessage.SUPPLIER_NOT_FOUND));

        supplierMapper.mapFromDto(supplierDTO, supplier);

        return supplier;
    }

    public Supplier findByNameIgnoreCase(String name) {
        return supplierRepository.findByNameIgnoreCase(name.trim()).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_SUPPLIER_NOT_FOUND,
                        ErrorMessage.SUPPLIER_NOT_FOUND));
    }

    public void verifyAndModify(SupplierDTO supplierDTO) {
        if (supplierDTO.getName() != null) {
            supplierDTO.setName(Utils.capitalizeFirstWordAndAfterWhitespace(supplierDTO.getName().trim()));
        }

        if (supplierDTO.getAddress() != null) {
            supplierDTO.setAddress(Utils.capitalizeFirstWordAndAfterWhitespace(supplierDTO.getAddress().trim()));
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
        String pattern = CommonConstant.PHONE_NUMBER_PATTERN;
        if (phone != null) {
            isPhoneValidFormat = phone.trim().matches(pattern);
        }
        return isPhoneValidFormat;
    }

    public static boolean isEmailValidFormat(String email) {
        String pattern = CommonConstant.EMAIL_PATTERN;
        if (email == null) {
            return false;
        }
        return email.matches(pattern);
    }
}
