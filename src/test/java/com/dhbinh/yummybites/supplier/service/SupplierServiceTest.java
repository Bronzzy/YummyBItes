package com.dhbinh.yummybites.supplier.service;

import com.dhbinh.yummybites.employee.entity.Employee;
import com.dhbinh.yummybites.employee.service.dto.EmployeeDTO;
import com.dhbinh.yummybites.supplier.entity.Supplier;
import com.dhbinh.yummybites.supplier.repository.SupplierRepository;
import com.dhbinh.yummybites.supplier.service.dto.SupplierDTO;
import com.dhbinh.yummybites.supplier.service.mapper.SupplierMapper;
import com.dhbinh.yummybites.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

    private Supplier validSupplier(){
        return Supplier.builder()
                .name("helio")
                .address("4089 Charing Cross Drive")
                .email("helio@gmail.com")
                .phone("341-724-5075")
                .build();
    }

    private SupplierDTO validSupplierDTO(){
        return SupplierDTO.builder()
                .name("helio")
                .address("4089 Charing Cross Drive")
                .email("helio@gmail.com")
                .phone("341-724-5075")
                .build();
    }

    @Test
    void createSupplier_WithMandatoryFields_ReturnDTO(){
        Supplier supplier = validSupplier();

        SupplierDTO dto = validSupplierDTO();

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);
        when(supplierMapper.toDTO(supplier)).thenReturn(dto);

        SupplierDTO result = supplierService.create(dto);

        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getAddress(), result.getAddress());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getPhone(), result.getPhone());
    }

    @Test
    public void testFindAll_PositiveCase() {
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier());
        suppliers.add(new Supplier());
        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<SupplierDTO> supplierDTOs = new ArrayList<>();
        supplierDTOs.add(new SupplierDTO());
        supplierDTOs.add(new SupplierDTO());
        when(supplierMapper.toDTOList(suppliers)).thenReturn(supplierDTOs);

        List<SupplierDTO> result = supplierService.findAll();
        assertEquals(supplierDTOs, result);
    }


}