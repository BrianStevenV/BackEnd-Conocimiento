package com.betek.usersInnovationEducation.adapters.driving.http.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserUpdateRequestDto {
    @Valid
    private UserRequestDto userRequestDto;
    @Valid
    private ProfileRequestDto profileRequestDto;
}
