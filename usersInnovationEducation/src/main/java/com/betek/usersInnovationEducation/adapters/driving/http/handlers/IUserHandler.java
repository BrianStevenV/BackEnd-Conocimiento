package com.betek.usersInnovationEducation.adapters.driving.http.handlers;

import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.ProfileRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.ChooseUserResponseDto;


public interface IUserHandler {
    void createUser(UserRequestDto userRequestDto);
    void createProfile(ProfileRequestDto profileRequestDto);
    void updateAllInformation(UserUpdateRequestDto userUpdateRequestDto);
    ChooseUserResponseDto getAnUserInformation();

    void enabledAndDisableAccount();
}
