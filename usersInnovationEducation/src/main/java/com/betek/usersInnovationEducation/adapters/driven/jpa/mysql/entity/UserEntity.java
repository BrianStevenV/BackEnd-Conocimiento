package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String member_name;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private Boolean state = true;
    private LocalDate created_at = LocalDate.now();
    private LocalDate updated_at;
    private Boolean is_admin = false;
    @Column(name = "id_country")
    private Long idCountry;

}
