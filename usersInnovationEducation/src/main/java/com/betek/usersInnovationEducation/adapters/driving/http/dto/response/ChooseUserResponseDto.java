package com.betek.usersInnovationEducation.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChooseUserResponseDto {
    private Long id;
    private Boolean is_admin;
    private Boolean state;
}
