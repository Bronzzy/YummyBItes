package com.dhbinh.yummybites.diningtable.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.diningtable.entity.DiningTable;
import com.dhbinh.yummybites.diningtable.repository.DiningTableRepository;
import com.dhbinh.yummybites.diningtable.service.dto.DiningTableDTO;
import com.dhbinh.yummybites.diningtable.service.mapper.DiningTableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiningTableService {

    @Autowired
    private DiningTableRepository diningTableRepository;

    private final DiningTableMapper diningTableMapper;

    public DiningTableDTO create(DiningTableDTO tableDTO) {
        DiningTable table = DiningTable.builder()
                .number(tableDTO.getNumber())
                .isOccupied(false)
                .build();

        return diningTableMapper.toDTO(diningTableRepository.save(table));
    }

    public DiningTableDTO findById(Long id) {
        return diningTableMapper.toDTO(diningTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_DINING_TABLE_NOT_FOUND,
                        ErrorMessage.DINING_TABLE_NOT_FOUND)));
    }

    public DiningTableDTO setIsOccupied(Long id) {
        DiningTableDTO dto = findById(id);
        dto.setOccupied(true);
        return dto;
    }
}
