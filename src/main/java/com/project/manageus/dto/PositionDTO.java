package com.project.manageus.dto;

import com.project.manageus.entity.PositionEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionDTO {

    private Long id;
    private String name;

    @Builder
    public PositionDTO(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public PositionEntity toPositionEntity() {
        return PositionEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
