package com.sv.equipment.domain.dto;

import lombok.Builder;

@Builder
public record JobDTO(Long id, String name, EquipmentDTO equipment) {
}
