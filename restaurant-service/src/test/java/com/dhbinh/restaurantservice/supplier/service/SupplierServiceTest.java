package com.dhbinh.restaurantservice.supplier.service;

import com.dhbinh.restaurantservice.base.exception.ResourceNotFoundException;
import com.dhbinh.restaurantservice.supplier.entity.Supplier;
import com.dhbinh.restaurantservice.supplier.repository.SupplierRepository;
import com.dhbinh.restaurantservice.supplier.service.dto.SupplierDTO;
import com.dhbinh.restaurantservice.supplier.service.mapper.SupplierMapper;
import com.dhbinh.restaurantservice.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SupplierServiceTest {

    @InjectMocks
    private SupplierService supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierMapper supplierMapper;

    @Mock
    private Utils utils;

    private Supplier validSupplier() {
        return Supplier.builder()
                .name("Helio")
                .address("4089 Charing Cross Drive")
                .email("helio@gmail.com")
                .phone("341-724-5075")
                .build();
    }

    private SupplierDTO validSupplierDTO() {
        return SupplierDTO.builder()
                .name("Helio")
                .address("4089 Charing Cross Drive")
                .email("helio@gmail.com")
                .phone("341-724-5075")
                .build();
    }

    @Test
    void createSupplier_WithMandatoryFields_ReturnEntity() {
        Supplier supplier = validSupplier();

        SupplierDTO dto = validSupplierDTO();

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier created = supplierService.create(dto);

        assertEquals(supplier.getName(), created.getName());
        assertEquals(supplier.getAddress(), created.getAddress());
        assertEquals(supplier.getEmail(), created.getEmail());
        assertEquals(supplier.getPhone(), created.getPhone());
    }

    @Test
    public void findAll_AvailableSuppliers_ReturnList() {
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier());
        suppliers.add(new Supplier());
        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> result = supplierService.findAll();

        assertEquals(result.size(), result.size());
    }

    @Test
    public void findSupplier_ExistedId_ReturnEntity() {
        Supplier supplier = validSupplier();

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        Supplier result = supplierService.findById(1L);

        assertEquals(result.getName(), supplier.getName());
        assertEquals(result.getPhone(), supplier.getPhone());
        assertEquals(result.getAddress(), supplier.getAddress());
        assertEquals(result.getEmail(), supplier.getEmail());
    }

    @Test
    public void findSupplier_NonExistedId_ThrowException() {
        when(supplierRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> supplierService.findById(999L));
    }

    @Test
    public void findSupplier_NameIgnoreCase_ReturnEntity() {
        String name = "Supplier Name";
        Supplier supplier = new Supplier();
        supplier.setName(name);

        when(supplierRepository.findByNameIgnoreCase(name.trim())).thenReturn(Optional.of(supplier));


        Supplier result = supplierService.findByNameIgnoreCase(name);

        assertEquals(result.getName(), supplier.getName());
        assertEquals(result.getPhone(), supplier.getPhone());
        assertEquals(result.getAddress(), supplier.getAddress());
        assertEquals(result.getEmail(), supplier.getEmail());
    }

    @Test
    public void findSupplier_NameIgnoreCase_NonExistedSupplier_ThrowException() {
        String name = "Non-existent Supplier";

        when(supplierRepository.findByNameIgnoreCase(name.trim())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            supplierService.findByNameIgnoreCase(name);
        });
    }
}