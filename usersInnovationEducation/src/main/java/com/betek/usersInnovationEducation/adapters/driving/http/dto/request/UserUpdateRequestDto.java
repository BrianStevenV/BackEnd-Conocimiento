package com.betek.usersInnovationEducation.adapters.driving.http.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserUpdateRequestDto {
    @Valid
    private UserRequestDto userRequestDto;
    @Valid
    private ProfileRequestDto profileRequestDto;
}
