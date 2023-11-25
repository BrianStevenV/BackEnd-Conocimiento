package com.betek.usersInnovationEducation.adapters.driving.http.mapper;

import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.ProfileRequestDto;
import com.betek.usersInnovationEducation.domain.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProfileRequestMapper {
    Profile toProfile(ProfileRequestDto profileRequestDto);
}
