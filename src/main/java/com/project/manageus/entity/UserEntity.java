package com.project.manageus.entity;


import com.project.manageus.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER")
@DynamicInsert
@DynamicUpdate
public class UserEntity {

    @Id
    private Long id;
    private String password;
    @Column(name = "position_id")
    private Long positionId;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "status_id")
    private Long statusId;
    @Column(name = "auth_id")
    private Long authId;
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "reg_date")
    private Date regDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserInfoEntity userInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PositionEntity position;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", insertable = false, updatable = false)
    private StatusEntity status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DepartmentEntity department;

    @Builder
    public UserEntity(Long id, String password, Long positionId, Long companyId, Long statusId, Long authId, Long departmentId, Date regDate) {
        super();
        this.id = id;
        this.password = password;
        this.positionId = positionId;
        this.companyId = companyId;
        this.statusId = statusId;
        this.authId = authId;
        this.departmentId = departmentId;
        this.regDate = regDate;
    }

    public UserDTO toUserDTO() {
        return UserDTO.builder()
                .id(this.id)
                .password(this.password)
                .positionId(this.positionId)
                .companyId(this.companyId)
                .statusId(this.statusId)
                .authId(this.authId)
                .departmentId(this.departmentId)
                .regDate(this.regDate)
                .build();
    }

}
