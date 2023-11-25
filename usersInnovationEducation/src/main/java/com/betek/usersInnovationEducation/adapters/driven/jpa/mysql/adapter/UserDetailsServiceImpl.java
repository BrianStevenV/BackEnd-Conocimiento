package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.adapter;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.UserEntity;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity usuario = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        if (usuario.getIs_admin() == null) {
            throw new UsernameNotFoundException("User not found with documentID: " + email);
        }

        //TODO: Error trhow UsernameNotFoundException ???
//        List<RoleEntity> roles = new ArrayList<>();
//        roles.add(usuario.getIdRole());

        return PrincipalUser.build(usuario);
    }
}
