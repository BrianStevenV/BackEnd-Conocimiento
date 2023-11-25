package com.betek.usersInnovationEducation.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter
public class UserProfileResponseDto {
    private String member_name;
    private String email;
    private String password;
    private String phone;
    private Boolean state;
    private LocalDate created_at;
    private LocalDate updated_at;
    private Boolean is_admin;

    private Long idCountry;

    private String description;
    private String profile_image;
    private String link_website;

}
