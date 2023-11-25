package com.betek.usersInnovationEducation.adapters.driving.http.handlers;

import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.LoginRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.JwtResponseDto;

import java.text.ParseException;

public interface IAuthHandler {
    JwtResponseDto login(LoginRequestDto loginRequestDto);

    JwtResponseDto refresh(JwtResponseDto jwtResponseDto) throws ParseException;
}
