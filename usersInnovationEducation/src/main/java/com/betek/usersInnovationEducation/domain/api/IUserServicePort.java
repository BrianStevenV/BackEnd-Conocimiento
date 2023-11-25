package com.betek.usersInnovationEducation.domain.api;

import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.ChooseUserResponseDto;
import com.betek.usersInnovationEducation.domain.model.Profile;
import com.betek.usersInnovationEducation.domain.model.User;

public interface IUserServicePort {
    void createUser(User user);
    void createProfile(Profile profile);
    void updateAllInformation(UserUpdateRequestDto userUpdateRequestDto);
    ChooseUserResponseDto getAnUserInformation();
    void enabledAndDisableAccount();
}
