package com.sv.equipment.controller;

import com.sv.equipment.repository.JobRepository;
import com.sv.equipment.service.JobService;
import com.sv.equipment.domain.dto.JobDTO;
import com.sv.equipment.util.HeaderUtil;
import com.sv.equipment.util.PaginationUtil;
import com.sv.equipment.util.ResponseUtil;
import com.sv.equipment.util.exception.BadRequest;
import com.sv.equipment.util.exception.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * REST controller for managing {@link com.sv.equipment.domain.Job}.
 */
@RestController
@RequestMapping("/api/v1/jobs")
@CrossOrigin(methods = {POST, GET})
@Slf4j
public class JobController {

    private static final String ENTITY_NAME = "job";

    @Value("${spring.application.name}")
    private String applicationName;

    private final JobService jobService;

    private final JobRepository jobRepository;

    public JobController(JobService jobService, JobRepository jobRepository) {
        this.jobService = jobService;
        this.jobRepository = jobRepository;
    }

    /**
     * {@code POST  /jobs} : Create a new job.
     *
     * @param jobDTO the jobDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobDTO, or with status {@code 400 (Bad Request)} if the job has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO jobDTO) throws URISyntaxException {
        log.debug("REST request to save Job : {}", jobDTO);
        if (jobDTO.id() != null) {
            throw new BadRequest("A new jhob cannot already have an ID", applicationName);
        }
        JobDTO result = jobService.save(jobDTO);
        return ResponseEntity
            .created(new URI("/api/jobs/" + result.id()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.id().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jobs/:id} : Updates an existing job.
     *
     * @param id the id of the jobDTO to save.
     * @param jobDTO the jobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobDTO,
     * or with status {@code 400 (Bad Request)} if the jobDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable(value = "id", required = false) final Long id, @RequestBody JobDTO jobDTO)
        throws URISyntaxException {
        log.debug("REST request to update Job : {}, {}", id, jobDTO);
        if (jobDTO.id() == null) {
            throw new BadRequest("Invalid id: null", applicationName);
        }
        if (!Objects.equals(id, jobDTO.id())) {
            throw new BadRequest("Invalid ID", applicationName);
        }

        if (!jobRepository.existsById(id)) {
            throw new NotFound("Entity not found", applicationName);
        }

        JobDTO result = jobService.update(jobDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jobDTO.id().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jobs/:id} : Partial updates given fields of an existing job, field will ignore if it is null
     *
     * @param id the id of the jobDTO to save.
     * @param jobDTO the jobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobDTO,
     * or with status {@code 400 (Bad Request)} if the jobDTO is not valid,
     * or with status {@code 404 (Not Found)} if the jobDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the jobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JobDTO> partialUpdateJob(@PathVariable(value = "id", required = false) final Long id, @RequestBody JobDTO jobDTO)
        throws URISyntaxException {
        log.debug("REST request to partial update Job partially : {}, {}", id, jobDTO);
        if (jobDTO.id() == null) {
            throw new BadRequest("Invalid id: null", applicationName);
        }
        if (!Objects.equals(id, jobDTO.id())) {
            throw new BadRequest("Invalid id", applicationName);
        }

        if (!jobRepository.existsById(id)) {
            throw new NotFound("Entity not found", ENTITY_NAME);
        }

        Optional<JobDTO> result = jobService.partialUpdate(jobDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jobDTO.id().toString())
        );
    }

    /**
     * {@code GET  /jobs} : get all the jobs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<JobDTO>> getAllJobs(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Jobs");
        Page<JobDTO> page = jobService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jobs/:id} : get the "id" job.
     *
     * @param id the id of the jobDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable Long id) {
        log.debug("REST request to get Job : {}", id);
        Optional<JobDTO> jobDTO = jobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobDTO);
    }

    /**
     * {@code DELETE  /jobs/:id} : delete the "id" job.
     *
     * @param id the id of the jobDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        log.debug("REST request to delete Job : {}", id);
        jobService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
