package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity;

import com.betek.usersInnovationEducation.configuration.Constants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;


public class PrincipalUser implements UserDetails {
    private String email;
    private String password;
    private Long id;
    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalUser(String email, String password, Long id, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.authorities = authorities;
    }

    public static PrincipalUser build(UserEntity user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user.getIs_admin()){
            authorities.add(new SimpleGrantedAuthority(Constants.ROLE_ADMINSITRATOR));
        }   else{
            authorities.add(new SimpleGrantedAuthority(Constants.ROLE_MEMBER));
        }
        return new PrincipalUser(user.getEmail(), user.getPassword(), user.getId(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }
}
