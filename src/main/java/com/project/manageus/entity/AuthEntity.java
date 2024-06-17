package com.project.manageus.entity;

import com.project.manageus.dto.AuthDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "AUTH")
public class AuthEntity {

    @Id
    private Long id;
    private String name;

    @Builder
    public AuthEntity(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public AuthDTO toAuthDTO() {
        return AuthDTO.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
