package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepository extends JpaRepository<ProfileEntity, Long> {
}
