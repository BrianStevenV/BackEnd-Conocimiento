package com.betek.usersInnovationEducation.adapters.driving.http.controller;

import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.ProfileRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.ChooseUserResponseDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.UserProfileResponseDto;
import com.betek.usersInnovationEducation.adapters.driving.http.handlers.IUserHandler;
import com.betek.usersInnovationEducation.configuration.Constants;
import com.betek.usersInnovationEducation.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Add a new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "User already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("profile/")
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        userHandler.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.USER_CREATED_MESSAGE));
    }

    @Operation(summary = "Add an information profile",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Profile terminated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Profile fail",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @SecurityRequirement(name = "jwt")
    @PreAuthorize("hasAuthority('MEMBER_ROLE')")
    @PostMapping("profile/setup/")
    public ResponseEntity<Map<String, String>> createProfile(@Valid @RequestBody ProfileRequestDto profileRequestDto){
        userHandler.createProfile(profileRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PROFILE_CREATED_MESSAGE));
    }

    @Operation(summary = "Update all information",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Update terminated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Update fail",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @SecurityRequirement(name = "jwt")
    @PreAuthorize("hasAnyAuthority('MEMBER_ROLE', 'ADMINISTRATOR_ROLE'")
    @PutMapping("profile/update/")
    public ResponseEntity<Map<String, String>> updateAllInformation(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        userHandler.updateAllInformation(userUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.UPDATE_ALL_INFORMATION_MESSAGE));
    }


    @Operation(summary = "Get information to choose type user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Get data user",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Get fail",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @SecurityRequirement(name = "jwt")
    @PreAuthorize("hasAnyAuthority('MEMBER_ROLE', 'ADMINISTRATOR_ROLE'")
    @GetMapping("validate/")
    public ResponseEntity<ChooseUserResponseDto> getAnUserInformation(){
        ChooseUserResponseDto user = userHandler.getAnUserInformation();
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Change state of user from Administrator",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Change data user",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Change data fail",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PreAuthorize("hasAuthority('ADMINISTRATOR_ROLE)")
    @PatchMapping("/account/enabled&disabled/")
    public ResponseEntity<Map<String, String>> activateUserProfiles(){
        userHandler.enabledAndDisableAccount();
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ACCOUNT_ENABLED_MESSAGE));
    }
    //TODO: ACCOUNT DISABLE

}
