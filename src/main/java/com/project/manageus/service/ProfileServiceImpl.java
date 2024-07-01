package com.project.manageus.service;

import com.project.manageus.dto.UserInfoDTO;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.repository.UserInfoRepository;
import com.project.manageus.repository.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final ResourceLoader resourceLoader;


    public ProfileServiceImpl(UserRepository userRepository,
                              UserInfoRepository userInfoRepository,
                              ResourceLoader resourceLoader) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void showUserProfile(Long id, Principal principal, Model model) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            model.addAttribute("isLoginUser", id == Long.parseLong(principal.getName()));
            model.addAttribute("userId", optionalUser.get().getUserInfo().getId());
            model.addAttribute("username", optionalUser.get().getUserInfo().getName());
            model.addAttribute("email", optionalUser.get().getUserInfo().getEmail());
            model.addAttribute("phone", optionalUser.get().getUserInfo().getPhone());
            model.addAttribute("address", optionalUser.get().getUserInfo().getAddress());
            model.addAttribute("birth", optionalUser.get().getUserInfo().getBirth());
            model.addAttribute("gender", optionalUser.get().getUserInfo().getGender());
            model.addAttribute("stamp", optionalUser.get().getUserInfo().getStamp() == null ? "직인없음" : "/img/stamp/"+ optionalUser.get().getUserInfo().getStamp());
            model.addAttribute("existStamp", optionalUser.get().getUserInfo().getStamp() == null);
            model.addAttribute("position", optionalUser.get().getPosition().getName());
            model.addAttribute("department", optionalUser.get().getDepartment().getName());
        }
    }

    @Override
    public void updateUser(UserInfoDTO userInfoDTO, MultipartFile multipartFile) {
        userInfoDTO.setStamp(fileUpload(multipartFile, userInfoDTO.getId()));
        userInfoRepository.save(userInfoDTO.toUserInfoEntity());
    }

    //stamp image가 저장되는 경로를 가져옴
    public String getRealPath() {
        try {
            // classpath: 뒤의 경로는 /src/main/resources/ 하위 경로를 의미합니다.
            Resource resource = resourceLoader.getResource("classpath:static/img/stamp");
            return resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 파일 업로드 메서드
    public String fileUpload(MultipartFile file, Long id) {
        String filename = null;
        String path = getRealPath();

        if (file != null) {
            filename = file.getOriginalFilename();
            String ext = filename.substring(filename.lastIndexOf("."));
            filename = id.toString() + ext;

            // 디렉토리가 존재하지 않으면 생성
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File copy = new File(directory, filename);

            try {
                file.transferTo(copy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filename;
    }

}
