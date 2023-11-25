package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.mappers;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.UserEntity;
import com.betek.usersInnovationEducation.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    UserEntity toUserEntity(User user);
    User toUser(UserEntity userEntity);
}
