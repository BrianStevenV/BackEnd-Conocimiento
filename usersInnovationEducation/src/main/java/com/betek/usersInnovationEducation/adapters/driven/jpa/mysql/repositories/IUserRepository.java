package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.repositories;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM user  WHERE email = :email AND password = :password", nativeQuery = true)
    Optional<UserEntity> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
