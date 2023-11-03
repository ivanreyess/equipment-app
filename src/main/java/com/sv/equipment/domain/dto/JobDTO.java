package com.sv.equipment.domain.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record JobDTO(Long id, String name, Set<SimpleEquipmentDTO> equipment) {
}
