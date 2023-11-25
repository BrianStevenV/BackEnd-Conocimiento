package com.betek.usersInnovationEducation.adapters.driving.http.handlers.impl;

import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.ProfileRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.ChooseUserResponseDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.UserProfileResponseDto;
import com.betek.usersInnovationEducation.adapters.driving.http.handlers.IUserHandler;
import com.betek.usersInnovationEducation.adapters.driving.http.mapper.IProfileRequestMapper;
import com.betek.usersInnovationEducation.adapters.driving.http.mapper.IUserRequestMapper;
import com.betek.usersInnovationEducation.domain.api.IUserServicePort;
import com.betek.usersInnovationEducation.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IProfileRequestMapper profileRequestMapper;
    @Override
    public void createUser(UserRequestDto userRequestDto) {
        userServicePort.createUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void createProfile(ProfileRequestDto profileRequestDto) {
        userServicePort.createProfile(profileRequestMapper.toProfile(profileRequestDto));
    }

    @Override
    public void updateAllInformation(UserUpdateRequestDto userUpdateRequestDto) {
        userServicePort.updateAllInformation(userUpdateRequestDto);
    }

    @Override
    public ChooseUserResponseDto getAnUserInformation() {
        return userServicePort.getAnUserInformation();

    }

    @Override
    public void enabledAndDisableAccount() {
        userServicePort.enabledAndDisableAccount();
    }
}
