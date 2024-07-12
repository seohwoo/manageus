package com.project.manageus.entity;

import com.project.manageus.dto.ApprovalDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name="approval")
@DynamicInsert
public class ApprovalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "status_id")
    private Long statusId;
    private String title;
    @Column(name = "approval_type_id")
    private Long approvalTypeId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    private String content;
    @Column(name = "sign_on")
    private Date signOn;
    @Column(name = "sign_off")
    private Date signOff;
    @Column(name = "company_id")
    private Long companyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ApprovalTypeEntity approvalType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "approval_id", insertable = false, updatable = false)
    private ApprovalDetailEntity approvalDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", insertable = false, updatable = false)
    private StatusEntity status;

    @Builder
    public ApprovalEntity(Long id, Long userId, Long statusId, String title, Long approvalTypeId,
                          Date startDate, Date endDate, String content, Date signOn, Date signOff, Long companyId) {
        super();
        this.id = id;
        this.userId = userId;
        this.statusId = statusId;
        this.title = title;
        this.approvalTypeId = approvalTypeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.signOn = signOn;
        this.signOff = signOff;
        this.companyId = companyId;
    }

    public ApprovalDTO toApprovalDTO() {
        return ApprovalDTO.builder()
                .id(this.id)
                .userId(this.userId)
                .statusId(this.statusId)
                .title(this.title)
                .approvalTypeId(this.approvalTypeId)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .content(this.content)
                .signOn(this.signOn)
                .signOff(this.signOff)
                .companyId(this.companyId)
                .build();
    } // 이거는 Entity를 DTO로 만드는 작업이다.
}     // DB에서 넘어올 때는 Entity로 넘어온다.


// @OneToOne = 연결되는 값이 1:1 일 때
// ex) 회원가입과 회원정보 디테일
// @ManyToOne = 나는 여럿 상대는 하나
// ex) Many = 결제내역, One = 결제한 사람
// @OneToMany = 나는 하나 상대는 여럿
// ex) One = 결제한 사람, Many = 결제내역
// @ManyToMany = 여럿 대 여럿
// ex) 쓸 일 거의 없음
