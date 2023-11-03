package com.sv.equipment.domain.dto;


import lombok.Builder;

@Builder
public record SimpleJobDTO(Long id, String name) {
}
