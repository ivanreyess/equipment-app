package com.sv.equipment.controller;

import com.sv.equipment.repository.EquipmentRepository;
import com.sv.equipment.service.EquipmentService;
import com.sv.equipment.domain.dto.EquipmentDTO;
import com.sv.equipment.util.HeaderUtil;
import com.sv.equipment.util.PaginationUtil;
import com.sv.equipment.util.ResponseUtil;
import com.sv.equipment.util.exception.BadRequest;
import com.sv.equipment.util.exception.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link com.sv.equipment.domain.Equipment}.
 */
@RestController
@RequestMapping("/api/v1/equipment")
@Slf4j
public class EquipmentController {

    private static final String ENTITY_NAME = "equipment";

    @Value("${spring.application.name}")
    private String applicationName;

    private final EquipmentService equipmentService;

    private final EquipmentRepository equipmentRepository;

    public EquipmentController(EquipmentService equipmentService, EquipmentRepository equipmentRepository) {
        this.equipmentService = equipmentService;
        this.equipmentRepository = equipmentRepository;
    }

    /**
     * {@code POST  /equipment} : Create a new equipment.
     *
     * @param equipmentDTO the equipmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new equipmentDTO, or with status {@code 400 (Bad Request)} if the equipment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EquipmentDTO> createEquipment(@RequestBody EquipmentDTO equipmentDTO) throws URISyntaxException {
        log.debug("REST request to save Equipment : {}", equipmentDTO);
        if (equipmentDTO.id() != null) {
            throw new BadRequest("A new equipment cannot already have an ID", applicationName);
        }
        EquipmentDTO result = equipmentService.save(equipmentDTO);
        return ResponseEntity
            .created(new URI("/api/equipment/" + result.id()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.id().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /equipment/:id} : Updates an existing equipment.
     *
     * @param id the id of the equipmentDTO to save.
     * @param equipmentDTO the equipmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated equipmentDTO,
     * or with status {@code 400 (Bad Request)} if the equipmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the equipmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> updateEquipment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EquipmentDTO equipmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Equipment : {}, {}", id, equipmentDTO);
        if (equipmentDTO.id() == null) {
            throw new BadRequest("Invalid id: null", applicationName);
        }
        if (!Objects.equals(id, equipmentDTO.id())) {
            throw new BadRequest("Invalid ID", applicationName);
        }

        if (!equipmentRepository.existsById(id)) {
            throw new BadRequest("Entity not found", applicationName);
        }
        EquipmentDTO result = equipmentService.update(equipmentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, equipmentDTO.id().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /equipment/:id} : Partial updates given fields of an existing equipment, field will ignore if it is null
     *
     * @param id the id of the equipmentDTO to save.
     * @param equipmentDTO the equipmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated equipmentDTO,
     * or with status {@code 400 (Bad Request)} if the equipmentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the equipmentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the equipmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EquipmentDTO> partialUpdateEquipment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EquipmentDTO equipmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Equipment partially : {}, {}", id, equipmentDTO);
        if (equipmentDTO.id() == null) {
            throw new BadRequest("Invalid id: null", applicationName);
        }
        if (!Objects.equals(id, equipmentDTO.id())) {
            throw new BadRequest("Invalid ID", applicationName);
        }

        if (!equipmentRepository.existsById(id)) {
            throw new NotFound("Entity not found", applicationName);
        }

        Optional<EquipmentDTO> result = equipmentService.partialUpdate(equipmentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, equipmentDTO.id().toString())
        );
    }

    /**
     * {@code GET  /equipment} : get all the equipment.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of equipment in body.
     */
    @GetMapping("")
    public ResponseEntity<List<EquipmentDTO>> getAllEquipment(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Equipment");
        Page<EquipmentDTO> page = equipmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /equipment/:id} : get the "id" equipment.
     *
     * @param id the id of the equipmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the equipmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipment(@PathVariable Long id) {
        log.debug("REST request to get Equipment : {}", id);
        Optional<EquipmentDTO> equipmentDTO = equipmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(equipmentDTO);
    }

    /**
     * {@code DELETE  /equipment/:id} : delete the "id" equipment.
     *
     * @param id the id of the equipmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        log.debug("REST request to delete Equipment : {}", id);
        equipmentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
