package com.sv.equipment.service;

import com.sv.equipment.domain.dto.EquipmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.sv.equipment.domain.Equipment}.
 */
public interface EquipmentService {
    /**
     * Save a equipment.
     *
     * @param equipmentDTO the entity to save.
     * @return the persisted entity.
     */
    EquipmentDTO save(EquipmentDTO equipmentDTO);

    /**
     * Updates a equipment.
     *
     * @param equipmentDTO the entity to update.
     * @return the persisted entity.
     */
    EquipmentDTO update(EquipmentDTO equipmentDTO);

    /**
     * Partially updates a equipment.
     *
     * @param equipmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EquipmentDTO> partialUpdate(EquipmentDTO equipmentDTO);

    /**
     * Get all the equipment.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EquipmentDTO> findAll(Pageable pageable, String equipmentStatus);

    /**
     * Get the "id" equipment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EquipmentDTO> findOne(Long id);

    /**
     * Delete the "id" equipment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Delete the "id" job.
     *
     * @param jobId the id of the job entity.
     */
    void assignJob(Long jobId, Long equipmentId);

    void removeJob(EquipmentDTO equipmentDTO);
}
