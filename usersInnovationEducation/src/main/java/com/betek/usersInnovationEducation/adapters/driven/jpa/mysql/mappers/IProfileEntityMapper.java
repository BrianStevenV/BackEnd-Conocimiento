package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.mappers;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.ProfileEntity;
import com.betek.usersInnovationEducation.domain.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProfileEntityMapper {
    ProfileEntity toProfileEntity(Profile profile);
    Profile toProfile(ProfileEntity profileEntity);
}
