package com.sv.equipment.service.impl;

import com.sv.equipment.domain.Equipment;
import com.sv.equipment.domain.Job;
import com.sv.equipment.domain.enumeration.EquipmentStatus;
import com.sv.equipment.repository.EquipmentRepository;
import com.sv.equipment.service.EquipmentService;
import com.sv.equipment.domain.dto.EquipmentDTO;
import com.sv.equipment.service.mapper.EquipmentMapper;
import com.sv.equipment.util.exception.BadRequest;
import com.sv.equipment.util.exception.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link com.sv.equipment.domain.Equipment}.
 */
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    private final Logger log = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    private final EquipmentRepository equipmentRepository;

    private final EquipmentMapper equipmentMapper;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public EquipmentDTO save(EquipmentDTO equipmentDTO) {
        log.debug("Request to save Equipment : {}", equipmentDTO);
        Equipment equipment = equipmentMapper.toEntity(equipmentDTO);
        equipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(equipment);
    }

    @Override
    public EquipmentDTO update(EquipmentDTO equipmentDTO) {
        log.debug("Request to update Equipment : {}", equipmentDTO);
        Equipment equipment = equipmentMapper.toEntity(equipmentDTO);
        equipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(equipment);
    }

    @Override
    public Optional<EquipmentDTO> partialUpdate(EquipmentDTO equipmentDTO) {
        log.debug("Request to partially update Equipment : {}", equipmentDTO);

        return equipmentRepository
            .findById(equipmentDTO.id())
            .map(existingEquipment -> {
                equipmentMapper.partialUpdate(existingEquipment, equipmentDTO);

                return existingEquipment;
            })
            .map(equipmentRepository::save)
            .map(equipmentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EquipmentDTO> findAll(Pageable pageable, String equipmentStatus) {
        log.debug("Request to get all Equipment");
        if (null != equipmentStatus) return equipmentRepository.findByEquipmentStatus(pageable, equipmentStatus).map(equipmentMapper::toDto);
        return equipmentRepository.findAll(pageable).map(equipmentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EquipmentDTO> findOne(Long id) {
        log.debug("Request to get Equipment : {}", id);
        return equipmentRepository.findById(id).map(equipmentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Equipment : {}", id);
        equipmentRepository.deleteById(id);
    }

    @Override
    public void assignJob(Long jobId, Long equipmentId) {
        Optional<Equipment> result = equipmentRepository.findById(equipmentId);
        if (result.isEmpty()) throw new NotFound("Equipment not found", "Equipment not found");
        if (EquipmentStatus.IN_USE.name().equals(result.get().getEquipmentStatus())) throw new BadRequest("Equipment in use", "Invalid option");
        Equipment equipment = result.get();
        equipment.setJob(Job.builder().id(jobId).build());
        equipment.setEquipmentStatus(EquipmentStatus.IN_USE.name());
        save(equipmentMapper.toDto(equipment));
    }
}
