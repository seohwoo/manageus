package com.project.manageus.service;


import com.project.manageus.dto.AlarmDTO;
import com.project.manageus.dto.DepartmentDTO;
import com.project.manageus.entity.AlarmEntity;

import com.project.manageus.entity.DepartmentEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.entity.UserInfoEntity;
import com.project.manageus.repository.AlarmJPARepository;
import com.project.manageus.repository.DepartmentRepository;
import com.project.manageus.repository.UserInfoRepository;
import com.project.manageus.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlarmServiceImpl implements AlarmService{

    private final AlarmJPARepository alarmJPARepository;

    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;

    private final DepartmentRepository departmentRepository ;

    public AlarmServiceImpl(AlarmJPARepository alarmJPARepository, UserInfoRepository userInfoRepository, UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.alarmJPARepository = alarmJPARepository;
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void insert(AlarmDTO alarmDTO) {   //쪽지 쓰기
        alarmJPARepository.save(alarmDTO.toAlarmEntity());
    }



    @Override
    public void spendalarm(Long userId, Model model) {  //보낸 내역 전체가져오기

        Sort sort = Sort.by(Sort.Order.desc("readDate"));
        List<AlarmEntity> spend= alarmJPARepository.findByUserId(userId, sort); //김지환이 보낸 메세지를 찾음

        model.addAttribute("spend",spend);

    }

    @Override
    public void receive(Long userId, Model model) {  //받은 내역 전체가져오기

        Sort sort = Sort.by(Sort.Order.desc("readDate"));
        List<AlarmEntity> receive = alarmJPARepository.findByReader(userId, sort);

        model.addAttribute("receive", receive);

    }

    @Override
    public void readreceive(Long messageId, Model model) {  //받은 내용 상세보기

        Optional<AlarmEntity> optionalAlarm = alarmJPARepository.findById(messageId); //번호에 맞는 메세지 가져오기

        if (optionalAlarm.isPresent()) {
            AlarmEntity alarm = optionalAlarm.get();
            //System.out.println("Alarm found: " + alarm.toString());  // 선택된 값 확인
            model.addAttribute("alarm", alarm);


            Long userId = alarm.getUserId();
            Optional<UserInfoEntity> optionalUserInfo = userInfoRepository.findById(userId);
            UserInfoEntity userInfo = optionalUserInfo.get();  // 해당 회원번호에 맞는 회원 이름 가져오기위해 작업
            model.addAttribute("userName", userInfo.getName());  //번호에 맞는 이름 가져오기
        } else {
            System.out.println("No alarm found with ID: " + messageId);
            model.addAttribute("error", "Message not found");
        }
    }

    @Override
    public void readspend(Long messageId, Model model) { //보낸내용 상세보기
        
        Optional<AlarmEntity> optionalAlarm = alarmJPARepository.findById(messageId); //번호에 맞는 레코드추출
        if (optionalAlarm.isPresent()) {
            AlarmEntity alarm = optionalAlarm.get();
            //System.out.println("Alarm found: " + alarm.toString());  // 선택된 값 확인
            model.addAttribute("alarm", alarm);


            Long receiveId = alarm.getReader();    //보낸 쪽지함에서 받은사람의 이름을 가져올때
            Optional<UserInfoEntity> optionalUserInfo = userInfoRepository.findById(receiveId);
            UserInfoEntity userInfo = optionalUserInfo.get();  // 해당 회원번호에 맞는 회원 이름 가져오기위해 작업
            model.addAttribute("userName", userInfo.getName());  //번호에 맞는 이름 가져오기

            Long spendId = alarm.getUserId(); //보낸 쪽지함에서 보낸사람의 이름을 가져올때
            Optional<UserInfoEntity> spendoptionalUserInfo = userInfoRepository.findById(spendId);
            UserInfoEntity spenduserInfo = spendoptionalUserInfo.get();  // 해당 회원번호에 맞는 회원 이름 가져오기위해 작업
            model.addAttribute("spendName", spenduserInfo.getName());  //번호에 맞는 이름 가져오기


        } else {
            System.out.println("No alarm found with ID: " + messageId);
            model.addAttribute("error", "Message not found");
        }
    }

    @Override
    @Transactional
    public void readcount(Long messageId) { // 글 읽음처리하기

        AlarmEntity alarm = entityManager.find(AlarmEntity.class, messageId);  //메니저.find는 테이블이있는지 찾고,내부는 해당테이블의 @id와 messageId를 맞춰주는과정
        alarm.setReadType(2001L); // read_type 업데이트
        entityManager.merge(alarm); // merge - >  + Transactional 필요 !! 업데이트된 엔티티를 다시 저장

    }

    @Override
    public void getdepartment(Long companyId, Model model) {  // 회사아이디로 부서찾기


        List<DepartmentEntity> getcomname = departmentRepository.findByCompanyId(companyId);//회사 부서명

        Long str = 100302L;   // 임이의 값
        List<UserEntity> getperson = userRepository.findByDepartmentId(str); //부서속 사람(임이의값)


        model.addAttribute("getcomname",getcomname); //회사 부서명 뽑기

        model.addAttribute("getperson",getperson); // 회사 사용자 뽑기 (임이의값)


    }


}
