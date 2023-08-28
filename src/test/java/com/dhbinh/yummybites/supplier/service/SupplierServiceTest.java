package com.dhbinh.yummybites.supplier.service;

import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
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
                .name("helio")
                .address("4089 Charing Cross Drive")
                .email("helio@gmail.com")
                .phone("341-724-5075")
                .build();
    }

    private SupplierDTO validSupplierDTO() {
        return SupplierDTO.builder()
                .name("helio")
                .address("4089 Charing Cross Drive")
                .email("helio@gmail.com")
                .phone("341-724-5075")
                .build();
    }

    @Test
    void createSupplier_WithMandatoryFields_ReturnDTO() {
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

    @Test
    public void testFindByNameIgnoreCase_Positive() {
        String name = "Supplier Name";
        Supplier supplier = new Supplier();
        supplier.setName(name);
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setName(name);

        when(supplierRepository.findByNameIgnoreCase(name.trim())).thenReturn(Optional.of(supplier));
        when(supplierMapper.toDTO(supplier)).thenReturn(supplierDTO);

        SupplierDTO result = supplierService.findByNameIgnoreCase(name);

        assertEquals(supplierDTO, result);
    }

    @Test
    public void testFindByNameIgnoreCase_Negative() {
        String name = "Non-existent Supplier";

        when(supplierRepository.findByNameIgnoreCase(name.trim())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            supplierService.findByNameIgnoreCase(name);
        });
    }

    @Test
    public void findSupplier_ExistedId_ReturnDTO(){
        Supplier supplier = validSupplier();

        SupplierDTO dto = validSupplierDTO();

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(supplierMapper.toDTO(supplier)).thenReturn(dto);

        SupplierDTO result = supplierService.findById(1L);

        assertEquals(dto, result);
    }

    @Test
    public void findSupplier_NonExistedId_ThrowException(){
        when(supplierRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> supplierService.findById(999L));
    }
}