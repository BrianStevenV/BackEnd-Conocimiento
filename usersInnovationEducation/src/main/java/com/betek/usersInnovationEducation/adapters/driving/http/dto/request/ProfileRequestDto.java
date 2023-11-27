package com.betek.usersInnovationEducation.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProfileRequestDto {
    @NotBlank
    private String description;
    @NotBlank
    private String profile_image;
    @NotBlank
    private String link_website;
}
