package com.dhbinh.yummybites.table.service;

import com.dhbinh.yummybites.table.entity.Tables;
import com.dhbinh.yummybites.table.repository.TableRepository;
import com.dhbinh.yummybites.table.service.dto.TablesDTO;
import com.dhbinh.yummybites.table.service.mapper.TableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    private final TableMapper tableMapper;

    public TablesDTO create(TablesDTO tableDTO) {
        Tables table = Tables.builder()
                .number(tableDTO.getNumber())
                .isOccupied(false)
                .build();

        return tableMapper.toDTO(tableRepository.save(table));
    }
}
