package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<CountryEntity, Long> {
}
