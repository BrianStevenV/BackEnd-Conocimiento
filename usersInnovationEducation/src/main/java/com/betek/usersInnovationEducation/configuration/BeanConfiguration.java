package com.betek.usersInnovationEducation.configuration;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.adapter.UserMysqlAdapter;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.mappers.IProfileEntityMapper;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories.ICountryRepository;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories.IProfileRepository;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.betek.usersInnovationEducation.domain.api.IAuthenticationUserInfoServicePort;
import com.betek.usersInnovationEducation.domain.api.IUserServicePort;
import com.betek.usersInnovationEducation.domain.spi.IUserPersistencePort;
import com.betek.usersInnovationEducation.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IProfileRepository profileRepository;
    private final ICountryRepository countryRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IProfileEntityMapper profileEntityMapper;
    private final PasswordEncoder passwordEncoder;


    private final IAuthenticationUserInfoServicePort authenticationUserInfoServicePort;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserMysqlAdapter(userRepository, profileRepository, countryRepository, userEntityMapper, profileEntityMapper, passwordEncoder);
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), authenticationUserInfoServicePort);
    }
}
