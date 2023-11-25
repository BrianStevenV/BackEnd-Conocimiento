package com.betek.usersInnovationEducation.adapters.driving.http.mapper;

import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserRequestDto;
import com.betek.usersInnovationEducation.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    User toUser(UserRequestDto userRequestDto);
}
