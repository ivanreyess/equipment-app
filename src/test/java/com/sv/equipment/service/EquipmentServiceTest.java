package com.sv.equipment.service;

import com.sv.equipment.domain.dto.EquipmentDTO;
import com.sv.equipment.domain.Equipment;
import com.sv.equipment.domain.enumeration.EquipmentStatus;
import com.sv.equipment.repository.EquipmentRepository;
import com.sv.equipment.service.impl.EquipmentServiceImpl;
import com.sv.equipment.service.mapper.EquipmentMapper;
import com.sv.equipment.service.mapper.EquipmentMapperImpl;
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
class EquipmentServiceTest {

    @InjectMocks
    EquipmentServiceImpl equipmentService;

    @Mock
    EquipmentRepository equipmentRepository;
    @Mock
    EquipmentMapper equipmentMapperMock;

    private static final long DEFAULT_ID = 1l;
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final EquipmentStatus DEFAULT_STATUS = EquipmentStatus.IN_USE;
    private static final EquipmentStatus UPDATED_STATUS = EquipmentStatus.AVAILABLE;
    EquipmentDTO equipmentDTO;
    Equipment equipment;
    private EquipmentMapper equipmentMapper;



    @BeforeEach
    void setUp() {
        equipmentMapper = new EquipmentMapperImpl();
        equipmentDTO = EquipmentDTO.builder()
                                        .id(DEFAULT_ID)
                                        .name(DEFAULT_NAME)
                                        .equipmentStatus(DEFAULT_STATUS)
                                    .build();
        equipment = equipmentMapper.toEntity(equipmentDTO);
    }

    @Test
    void save() {
        Equipment savedEquipment = equipmentMapper.toEntity(equipmentDTO);
        given(equipmentRepository.save(any(Equipment.class))).willReturn(savedEquipment);
        given(equipmentMapperMock.toEntity(any(EquipmentDTO.class))).willReturn(savedEquipment);
        given(equipmentMapperMock.toDto(any(Equipment.class))).willReturn(equipmentDTO);
        EquipmentDTO result = equipmentService.save(equipmentDTO);
        assertEquals(DEFAULT_ID, result.id());

    }

    @Test
    void update() {
        Equipment savedEquipment = equipmentMapper.toEntity(equipmentDTO);
        given(equipmentRepository.save(any(Equipment.class))).willReturn(savedEquipment);
        given(equipmentMapperMock.toEntity(any(EquipmentDTO.class))).willReturn(savedEquipment);
        given(equipmentMapperMock.toDto(any(Equipment.class))).willReturn(equipmentDTO);
        EquipmentDTO result = equipmentService.update(equipmentDTO);
        assertEquals(DEFAULT_ID, result.id());
    }

    @Test
    void partialUpdate() {
        Equipment savedEquipment = equipmentMapper.toEntity(equipmentDTO);
        given(equipmentRepository.save(any(Equipment.class))).willReturn(savedEquipment);
        given(equipmentRepository.findById(any())).willReturn(Optional.of(savedEquipment));
        given(equipmentMapperMock.toDto(any(Equipment.class))).willReturn(equipmentDTO);
        Optional<EquipmentDTO> result = equipmentService.partialUpdate(equipmentDTO);
        assertTrue(result.isPresent());
        assertEquals(DEFAULT_ID, result.get().id());
    }

    @Test
    void findAll() {
        List<Equipment> equipments = Stream.of(equipmentDTO).map(eq -> equipmentMapper.toEntity(eq)).toList();
        Page<Equipment> equipmentPage = new PageImpl<>(equipments);
        Pageable pageable = PageRequest.of(0, 20);
        given(equipmentRepository.findAll(any(Pageable.class))).willReturn(equipmentPage);
        given(equipmentMapperMock.toDto(any(Equipment.class))).willReturn(equipmentDTO);
        Page<EquipmentDTO> result = equipmentService.findAll(pageable, null);
        assertFalse(result.getContent().isEmpty());
    }

    @Test
    void findOne() {
        Equipment equipment = equipmentMapper.toEntity(equipmentDTO);
        given(equipmentRepository.findById(DEFAULT_ID)).willReturn(Optional.of(equipment));
        given(equipmentMapperMock.toDto(any(Equipment.class))).willReturn(equipmentDTO);
        Optional<EquipmentDTO> result = equipmentService.findOne(DEFAULT_ID);
        assertTrue(result.isPresent());
    }

    @Test
    void delete() {
        doNothing().when(equipmentRepository).deleteById(DEFAULT_ID);
        assertDoesNotThrow(() -> equipmentService.delete(DEFAULT_ID));
    }
}