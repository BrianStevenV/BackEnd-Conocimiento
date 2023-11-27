package com.betek.usersInnovationEducation.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDto {
    @NotBlank
    private String member_name;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @Pattern(regexp = "^[0-9+]{1,13}$")
    @NotBlank
    private String phone;

    @NotNull
    private Long idCountry;

}
