package com.project.manageus.dto;

import com.project.manageus.entity.AuthEntity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthDTO {

    @Id
    private Long id;
    private String name;

    @Builder
    public AuthDTO(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public AuthEntity toAuthEntity() {
        return AuthEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}
