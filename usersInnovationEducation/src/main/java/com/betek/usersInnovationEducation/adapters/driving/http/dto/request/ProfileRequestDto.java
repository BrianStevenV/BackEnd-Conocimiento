package com.betek.usersInnovationEducation.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProfileRequestDto {
    @NotBlank
    private String description;
    @NotBlank
    private String profile_image;
    @NotBlank
    private String link_website;
}
