package com.project.manageus.dto;

import com.project.manageus.entity.AuthEntity;
import com.project.manageus.entity.CompanyEntity;
import com.project.manageus.repository.AuthRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class JoinCompanyDTO implements UserDetails {

    private final CompanyEntity companyEntity;
    private final AuthRepository authRepository;

    public JoinCompanyDTO(CompanyEntity companyEntity, AuthRepository authRepository) {
        this.companyEntity = companyEntity;
        this.authRepository = authRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                String auth = "";
                Optional<AuthEntity> optionalAuth = authRepository.findById(companyEntity.getAuthId());
                if(optionalAuth.isPresent()) {
                    auth = optionalAuth.get().getName();
                }
                return auth;
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return companyEntity.getPw();
    }

    @Override
    public String getUsername() {
        return companyEntity.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
