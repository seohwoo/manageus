package com.project.manageus.entity;

import com.project.manageus.dto.PositionDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "POSITION")
public class PositionEntity {

    @Id
    private Long id;
    private String name;

    @Builder
    public PositionEntity(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public PositionDTO toPositionDTO() {
        return PositionDTO.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
