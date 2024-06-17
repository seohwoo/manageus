package com.project.manageus.entity;


import com.project.manageus.dto.StatusDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "STATUS")
public class StatusEntity {

    @Id
    private Long id;
    private String name;

    @Builder
    public StatusEntity(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public StatusDTO toStatusDTO() {
        return StatusDTO.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}
