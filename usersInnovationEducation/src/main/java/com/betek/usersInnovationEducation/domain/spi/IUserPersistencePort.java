package com.betek.usersInnovationEducation.domain.spi;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.UserEntity;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.ChooseUserResponseDto;
import com.betek.usersInnovationEducation.domain.model.Profile;
import com.betek.usersInnovationEducation.domain.model.User;

public interface IUserPersistencePort {
    void createUser(User user);
    void createProfile(Profile profile);
    void updateAllInformation(User user, Profile profile, Long id);

    User getAnUserInformation(Long idUser);

    User findByIdUser(Long id);
    Profile findByIdProfile(Long id);

    void enabledAndDisableAccount();


    void saveUser(User user);
//    void saveProfile(Profile profile, Long id);
}
