package com.sv.equipment.service;

import com.sv.equipment.domain.dto.JobDTO;
import com.sv.equipment.domain.Job;
import com.sv.equipment.repository.JobRepository;
import com.sv.equipment.service.impl.JobServiceImpl;
import com.sv.equipment.service.mapper.JobMapper;
import com.sv.equipment.service.mapper.JobMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @InjectMocks
    JobServiceImpl jobService;

    @Mock
    JobRepository jobRepository;


    @Mock
    private JobMapper jobMapperMock;

    private static final long DEFAULT_ID = 1l;
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    Job job;
    JobDTO jobDTO;

    private JobMapper jobMapper;

    @BeforeEach
    void setUp() {
        jobMapper = new JobMapperImpl();

        jobDTO = JobDTO.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .build();
        job = jobMapper.toEntity(jobDTO);

    }

    @Test
    void save() {
        Job savedJob = jobMapper.toEntity(jobDTO);
        given(jobRepository.save(any(Job.class))).willReturn(savedJob);
        given(jobMapperMock.toEntity(any(JobDTO.class))).willReturn(savedJob);
        given(jobMapperMock.toDto(any(Job.class))).willReturn(jobDTO);
        JobDTO result = jobService.save(jobDTO);
        assertEquals(DEFAULT_ID, result.id());
    }

    @Test
    void update() {
        Job savedJob = jobMapper.toEntity(jobDTO);
        given(jobRepository.save(any(Job.class))).willReturn(savedJob);
        given(jobMapperMock.toEntity(any(JobDTO.class))).willReturn(savedJob);
        given(jobMapperMock.toDto(any(Job.class))).willReturn(jobDTO);
        JobDTO result = jobService.update(jobDTO);
        assertEquals(DEFAULT_ID, result.id());
    }

    @Test
    void partialUpdate() {
        Job savedJob = jobMapper.toEntity(jobDTO);
        given(jobRepository.save(any(Job.class))).willReturn(savedJob);
        given(jobRepository.findById(any())).willReturn(Optional.of(savedJob));
        given(jobMapperMock.toDto(any(Job.class))).willReturn(jobDTO);
        Optional<JobDTO> result = jobService.partialUpdate(jobDTO);
        assertTrue(result.isPresent());
        assertEquals(DEFAULT_ID, result.get().id());
    }

    @Test
    void findAll() {
        List<Job> jobs = Stream.of(jobDTO).map(eq -> jobMapper.toEntity(eq)).toList();
        Page<Job> jobPage = new PageImpl<>(jobs);
        Pageable pageable = PageRequest.of(0, 20);
        given(jobRepository.findAll(any(Pageable.class))).willReturn(jobPage);
        given(jobMapperMock.toDto(any(Job.class))).willReturn(jobDTO);
        Page<JobDTO> result = jobService.findAll(pageable);
        assertFalse(result.getContent().isEmpty());
    }

    @Test
    void findOne() {
        Job job = jobMapper.toEntity(jobDTO);
        given(jobRepository.findById(DEFAULT_ID)).willReturn(Optional.of(job));
        given(jobMapperMock.toDto(any(Job.class))).willReturn(jobDTO);
        Optional<JobDTO> result = jobService.findOne(DEFAULT_ID);
        assertTrue(result.isPresent());

    }

    @Test
    void delete() {
        doNothing().when(jobRepository).deleteById(DEFAULT_ID);
        assertDoesNotThrow(() -> jobService.delete(DEFAULT_ID));
    }
}