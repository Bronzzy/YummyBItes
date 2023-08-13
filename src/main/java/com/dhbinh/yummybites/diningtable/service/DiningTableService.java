package com.dhbinh.yummybites.diningtable.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.diningtable.entity.DiningTable;
import com.dhbinh.yummybites.diningtable.repository.DiningTableRepository;
import com.dhbinh.yummybites.diningtable.service.dto.DiningTableDTO;
import com.dhbinh.yummybites.diningtable.service.mapper.DiningTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    DiningTableService {

    @Autowired
    private DiningTableRepository diningTableRepository;

    @Autowired
    private DiningTableMapper diningTableMapper;

    public DiningTableDTO create(DiningTableDTO tableDTO) {
        DiningTable table = DiningTable.builder()
                .number(tableDTO.getNumber())
                .occupied(false)
                .build();

        return diningTableMapper.toDTO(diningTableRepository.save(table));
    }

    public List<DiningTableDTO> findAll() {
        return diningTableMapper.toDTOList(diningTableRepository.findAll());
    }

    public DiningTableDTO findById(Long id) {
        DiningTable diningTable = findEntityById(id);

        return diningTableMapper.toDTO(diningTable);
    }

    private DiningTable findEntityById(Long id) {
        return diningTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_DINING_TABLE_NOT_FOUND,
                        ErrorMessage.DINING_TABLE_NOT_FOUND));
    }

    public DiningTableDTO setOccupied(Long id) {
        DiningTable diningTable = findEntityById(id);

        if (diningTable.getOccupied()) {
            throw new InputValidationException(
                    ErrorMessage.KEY_TABLE_IS_OCCUPIED,
                    ErrorMessage.TABLE_IS_OCCUPIED);
        }
            diningTable.setOccupied(true);

        return diningTableMapper.toDTO(diningTableRepository.save(diningTable));
    }

    public void setUnoccupied(Long id) {
        DiningTable diningTable = findEntityById(id);

        diningTable.setOccupied(false);

        diningTableRepository.save(diningTable);
    }
}
