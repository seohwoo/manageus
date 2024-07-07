package com.project.manageus.dto;

import com.project.manageus.entity.StatusEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusDTO {

    private Long id;
    private String name;

    @Builder
    public StatusDTO(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public StatusEntity toStatusEntity() {
        return StatusEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}
