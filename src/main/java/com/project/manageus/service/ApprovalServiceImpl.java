package com.project.manageus.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.manageus.dto.*;
import com.project.manageus.entity.*;
import com.project.manageus.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalJPARepository approvalJPA;
    private final ApprovalTypeJPARepository approvalTypeJPA;
    private final ApprovalDetailJPARepository approvalDetailJPA;

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    private final CalendarJPARepository calendarJPARepository;
    private final CalendarDetailRepository calendarDetailRepository;

    @Autowired
    public ApprovalServiceImpl(ApprovalJPARepository approvalJPA,
                               ApprovalTypeJPARepository approvalTypeJPA,
                               ApprovalDetailJPARepository approvalDetailJPA,
                               UserRepository userRepository,
                               DepartmentRepository departmentRepository,
                               CalendarJPARepository calendarJPARepository,
                               CalendarDetailRepository calendarDetailRepository) {

        this.approvalJPA = approvalJPA;
        this.approvalTypeJPA = approvalTypeJPA;
        this.approvalDetailJPA = approvalDetailJPA;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.calendarJPARepository = calendarJPARepository;
        this.calendarDetailRepository = calendarDetailRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(ApprovalServiceImpl.class);

    // 결재 리스트 가져오기
    @Override
    public void selectApprovalList(Model model, Long companyId) {
        List<ApprovalEntity> approvalList = approvalJPA.findByCompanyIdOrderBySignOnDesc(companyId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(new Date());

        List<Long> matchingIds = new ArrayList<>();

        for (ApprovalEntity approval : approvalList) {
            Date signOff = approval.getSignOff();
            if (signOff != null) {
                String signOffStr = sdf.format(signOff);
                if (todayStr.equals(signOffStr)) {
                    matchingIds.add(approval.getId());
                }
            }
        }

        for (Long id : matchingIds) {
            List<ApprovalDetailEntity> adEntityList = approvalDetailJPA.findByApprovalId(id);
            for (ApprovalDetailEntity adEntity : adEntityList) {
                adEntity.setStatusId(1004L);
            }
            approvalDetailJPA.saveAll(adEntityList);

            Optional<ApprovalEntity> approvalEntity = approvalJPA.findById(id);
            if (approvalEntity.isPresent()) {
                ApprovalEntity aEntity = approvalEntity.get();
                // statusId 필드가 맞는지 확인 후 수정
                aEntity.setStatusId(1004L);
                approvalJPA.save(aEntity);
            }
        }

        model.addAttribute("approvalList", approvalList);
    }

    // 결재 종류 가져오기
    @Override
    public void selectApprovalType(Model model) {
        List<ApprovalTypeEntity> approvalType = approvalTypeJPA.findAll();
        model.addAttribute("approvalType", approvalType);
    }

    // 결재 테이블 인서트
    @Override
    public void insertApproval(@PathVariable Long companyId,
                               @PathVariable Long id,
                               ApprovalDTO Adto, List<Long> personId) {

        // 휴가 마감 날짜
        Date startDateUtil = Adto.getStartDate();
        LocalDate startDate = startDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate signOffLocalDate = startDate.minusDays(2);
        Date signOff = Date.from(signOffLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Adto.setStatusId(1001L);
        Adto.setUserId(id);
        Adto.setSignOff(signOff);
        Adto.setCompanyId(companyId);
        // 결재 인서트
        approvalJPA.save(Adto.toApprovalEntity());

        ApprovalDetailDTO ADdto = new ApprovalDetailDTO();

        // 마지막 글번호 가져오기
        Optional<ApprovalEntity> approvalId = approvalJPA.findFirstByOrderByIdDesc();
        if (approvalId.isPresent()) {
            Long approvalEntityId = approvalId.get().getId(); // 값 추출
            ADdto.setApprovalId(approvalEntityId);
        }

        Long statusId = 1001L;
        ADdto.setStatusId(statusId);

        // 결재_디테일 인서트
        for (Long personIds : personId) {
            ADdto.setUserId(personIds);
            approvalDetailJPA.save(ADdto.toApprovalEntity());
        }
    }

    // 회사 부서 가져오기
    @Override
    public void selectDepartment(Long companyId, Model model) {
        List<DepartmentEntity> selectDepartment = departmentRepository.findAllByCompanyId(companyId);
        model.addAttribute("selectDepartment", selectDepartment);
    }

    // 부서에 맞는 사람 가져오기
    @Override
    public JsonObject selectPositionPeople(DepartmentDTO dto, Long userId) {
        JsonObject Json = new JsonObject();

        List<UserEntity> UE = userRepository.findAllByDepartmentId(dto.getId());

        JsonArray JA = new JsonArray();
        // 본인 아이디 제외
        for (UserEntity UEA : UE) {
            if (UEA.getId().equals(userId)) {
                continue;
            }
            // List<UserEntity> 수 만큼 반복
            JsonObject JsonO = new JsonObject();
            String people = UEA.getUserInfo().getName() + " " + UEA.getPosition().getName();
            Long Uid = UEA.getId();
            JsonO.addProperty("userId", Uid);
            JsonO.addProperty("fullName", people);
            JA.add(JsonO);
        }
        Json.add("DepartmentMember", JA);

        return Json;
    }

    // 글번호에 맞는 정보 가져오기
    @Override
    public void selectApprovalDetail(Long approvalId, Long id, Model model) {
        List<ApprovalDetailEntity> approvalDetail = approvalDetailJPA.findByApprovalId(approvalId);
        model.addAttribute("approvalDetail", approvalDetail);

        // approvalDetail에서 userId 값을 String으로 변환하여 새로운 리스트 생성
        List<String> userIdStrings = approvalDetail.stream()
                .map(detail -> String.valueOf(detail.getUserId()))
                .collect(Collectors.toList());
        // 변환된 userId 리스트를 모델에 추가
        model.addAttribute("userIdStrings", userIdStrings);

        Optional<ApprovalEntity> optionalApproval = approvalJPA.findById(approvalId);
        if (optionalApproval.isPresent()) {
            ApprovalEntity approvalEntity = optionalApproval.get();
            String approvalTypeName = approvalEntity.getApprovalType().getName();
            model.addAttribute("approvalTypeName", approvalTypeName);
            model.addAttribute("approvalInfo", approvalEntity);
        }

        Optional<UserEntity> optionalUser = userRepository.findById(optionalApproval.get().getUserId());
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            String departmentName = userEntity.getDepartment().getName();
            String positionName = userEntity.getPosition().getName();
            String userInfoName = userEntity.getUserInfo().getName();
            String puFullName = userInfoName + " " + positionName;

            model.addAttribute("departmentName", departmentName);
            model.addAttribute("positionName", positionName);
            model.addAttribute("userInfoName", userInfoName);
            model.addAttribute("puFullName", puFullName);
        }

        Optional<ApprovalDetailEntity> optionalADEntity = approvalDetailJPA.findByApprovalIdAndUserId(approvalId, id);
        if (optionalADEntity.isPresent()) {
            ApprovalDetailEntity adEntity = optionalADEntity.get();
            Long status = adEntity.getStatusId();
            model.addAttribute("status", status);
        }
    }

    @Override
    public void approvalUpdate(Long approvalId, Long userId) {

        Optional<ApprovalDetailEntity> optionalAD = approvalDetailJPA.findByApprovalIdAndUserId(approvalId, userId);
        if (optionalAD.isPresent()) {
            ApprovalDetailEntity ADentity = optionalAD.get();

            ADentity.setStatusId(1002L);
            approvalDetailJPA.save(ADentity);
        }


        List<ApprovalDetailEntity> approvalDetails = approvalDetailJPA.findByApprovalId(approvalId);
        // 휴가 최종 승인 확인
        boolean status = true;
        for (ApprovalDetailEntity detail : approvalDetails) {
            if (detail.getStatusId() != 1002L) {
                status = false;
                break;
            }
        }

        // 휴가 최종 승인
        if (status) {
            ApprovalEntity approvalEntity = approvalJPA.findById(approvalId)
                    .orElseThrow(() -> new RuntimeException("Approval not found"));
            approvalEntity.setStatusId(1002L);
            approvalJPA.save(approvalEntity);
        }
    }

    // 결재 반려 업데이트
    @Override
    public void approvalReject(Long approvalId, Long status) {
        if (status == 1004) {
            List<ApprovalDetailEntity> adEntityList = approvalDetailJPA.findByApprovalId(approvalId);
            for (ApprovalDetailEntity adEntity : adEntityList) {
                adEntity.setStatusId(1004L);
            }
            approvalDetailJPA.saveAll(adEntityList);

            Optional<ApprovalEntity> approvalEntity = approvalJPA.findById(approvalId);
            if (approvalEntity.isPresent()) {
                ApprovalEntity aEntity = approvalEntity.get();
                aEntity.setStatusId(1004L);
                approvalJPA.save(aEntity);
            }
        }
    }

    @Override
    public Long findUserByAprovalId(Long approvalId) {
        Long result = 0L;
        Optional<ApprovalEntity> optionalApproval = approvalJPA.findById(approvalId);
        if(optionalApproval.isPresent()) {
            result = optionalApproval.get().getUserId();
        }
        return result;
    }

    @Override
    public void updateForCalendar(Long approvalId) {

        Optional<ApprovalEntity> approvalEntity = approvalJPA.findById(approvalId);
        Long id = approvalJPA.findById(approvalId).get().getUserId();
        Long companyId = userRepository.findById(id).get().getCompanyId();
        Long departmentId = userRepository.findById(id).get().getDepartmentId();
        Long departmentCalendarType = 1L;
        Long myCalendarType = 2L;
        String userName = userRepository.findById(id).get().getUserInfo().getName();
        int departmentCalendarCount = calendarJPARepository.countByDepartmentIdAndCalendarType(departmentId,departmentCalendarType);
        int myCalendarCount = calendarJPARepository.countByUserIdAndCalendarType(id,myCalendarType);

        if(departmentCalendarCount == 0){
            CalendarDTO calendarDTO = new CalendarDTO();
            calendarDTO.setCompanyId(companyId);
            calendarDTO.setDepartmentId(departmentId);
            calendarDTO.setCalendarType(departmentCalendarType);
            calendarJPARepository.save(calendarDTO.toCalendarEntity());
        }

        if(myCalendarCount == 0){
            CalendarDTO calendarDTO = new CalendarDTO();
            calendarDTO.setCompanyId(companyId);
            calendarDTO.setDepartmentId(departmentId);
            calendarDTO.setUserId(id);
            calendarDTO.setCalendarType(myCalendarType);
            calendarJPARepository.save(calendarDTO.toCalendarEntity());
        }


        Long myCalendarId = calendarJPARepository.findByUserId(id).getId();

        Long departmentCalendarId = calendarJPARepository.findByDepartmentIdAndCalendarType(departmentId,departmentCalendarType).getId();

        if(myCalendarId != null){
            CalendarDetailDTO calendarDetailDTO = new CalendarDetailDTO();
            calendarDetailDTO.setCalendarId(myCalendarId);
            calendarDetailDTO.setStartDate(approvalEntity.get().getStartDate());
            calendarDetailDTO.setEndDate(approvalEntity.get().getEndDate());
            calendarDetailDTO.setContent(userName+" 휴가");
            calendarDetailRepository.save(calendarDetailDTO.toCalendarDetailEntity());
        }

        if(departmentCalendarId != null){
            CalendarDetailDTO calendarDetailDTO = new CalendarDetailDTO();
            calendarDetailDTO.setCalendarId(departmentCalendarId);
            calendarDetailDTO.setStartDate(approvalEntity.get().getStartDate());
            calendarDetailDTO.setEndDate(approvalEntity.get().getEndDate());
            calendarDetailDTO.setContent(userName+" 휴가");
            calendarDetailRepository.save(calendarDetailDTO.toCalendarDetailEntity());

        }

    }
}
/*
    // ApprovalDetailDTO를 ApprovalDetailEntity로 변환하는 메서드
    private ApprovalDetailEntity toEntity(ApprovalDetailDTO dto) {
        ApprovalDetailEntity entity = new ApprovalDetailEntity();
        entity.setId(dto.getId());
        entity.setApprovalId(dto.getApprovalId());
        entity.setUserId(dto.getUserId());
        entity.setStatusId(dto.getStatusId());
        return entity;
    }
    }
*/
    /*
        @Override
    public String findUserCompanyId(String username) {
        String result = "";
        Long id = Long.parseLong(username);
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
           result = optionalUser.get().getCompanyId().toString();
        }
        return result;
    }
     */
/*
    // 회사 번호가 같은 정보 다 가져오기
    @Override
    public void selectCompanyId(Long companyId, Model model) {
        List<UserEntity> selectCompanyId = userRepository.findAllByCompanyId(companyId);
        model.addAttribute("selectCompanyId",selectCompanyId);
    }
*/




/*
    // ajax 연습
    @Override
    public void selectDepartment(Model model) {
        List<UserEntity> selectDepartment = userRepository.findAll();
        model.addAttribute("selectDepartment", selectDepartment);
    }

    @Override
    public void selectPeople(Long departmentId, Model model) {
        List<UserEntity> selectPeople = userRepository.findAllByDepartmentId(departmentId);
        model.addAttribute("selectPeople", selectPeople);
    }
*/
//    @Autowired
//    private final ApprovalJPARepository service;
//    private ApprovalJPARepository ApprovalJPA;
/*
    @Override
    public void write() {
        // 실질적으로 구현할 코드 ( DB 연결 )
    }

    @Override
    public void selectApproval(String id, int pw, Model model) {
        System.out.println("id=" + id);
        System.out.println(("pw=" + pw));

        model.addAttribute("us", JPA.us(id, pw));
    }

    @Override
    public void isnertApproval(ApprovalDTO dto) {
        JPA.insertApproval(dto);
    }

 */

