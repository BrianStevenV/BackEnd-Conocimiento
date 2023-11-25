package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.adapter;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.CountryEntity;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.ProfileEntity;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.UserEntity;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.exceptions.FailedToUpdateUserAndProfileException;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.mappers.IProfileEntityMapper;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories.ICountryRepository;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories.IProfileRepository;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.ChooseUserResponseDto;
import com.betek.usersInnovationEducation.configuration.Constants;
import com.betek.usersInnovationEducation.domain.model.Profile;
import com.betek.usersInnovationEducation.domain.model.User;
import com.betek.usersInnovationEducation.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IProfileRepository profileRepository;
    private final ICountryRepository countryRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IProfileEntityMapper profileEntityMapper;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public void createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        if (userRepository.existsByEmail(user.getEmail())){
            throw new MailAlreadyExistsException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntityMapper.toUserEntity(user));
    }
    //TODO: DUPLICATION IN VALIDATION IF'S

    @Transactional
    @Override
    public void createProfile(Profile profile) {
        ProfileEntity profileEntity = profileEntityMapper.toProfileEntity(profile);
//        profileRepository.save(profileEntityMapper.toProfileEntity(profile));
        profileRepository.save(profileEntity);
    }

    @Transactional
    @Override
    public void updateAllInformation(User user, Profile profile, Long id) {
        try {
            UserEntity userEntity = userEntityMapper.toUserEntity(user);
            ProfileEntity profileEntity = profileEntityMapper.toProfileEntity(profile);

            userEntity.setUpdated_at(LocalDate.now());
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
            profileEntity.setId(id);

            userRepository.save(userEntity);
            profileRepository.save(profileEntity);
        }   catch (Exception e){
            throw new FailedToUpdateUserAndProfileException();
        }

    }

    @Override
    public User getAnUserInformation(Long idUser) {
        UserEntity userEntity = userRepository.findById(idUser).orElseThrow();

        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User findByIdUser(Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        return userEntityMapper.toUser(userEntity);
    }
    @Override
    public Profile findByIdProfile(Long id){
        ProfileEntity profileEntity = profileRepository.findById(id).orElseThrow();
        return profileEntityMapper.toProfile(profileEntity);
    }

    @Override
    public void enabledAndDisableAccount() {

    }


    // -------

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        userEntity.setUpdated_at(LocalDate.now());
        userRepository.save(userEntity);
    }
//
//    @Override
//    public void saveProfile(Profile profile, Long id) {
//        ProfileEntity profileEntity = profileEntityMapper.toProfileEntity(profile);
//        profileEntity.setId(id);
//        profileRepository.save(profileEntity);
//    }

    // -----

    public CountryEntity findByIdCountry(Long id){
        CountryEntity countryEntity = countryRepository.findById(id).orElseThrow();
        return countryEntity;
    }
}
