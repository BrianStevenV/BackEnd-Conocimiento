package com.betek.usersInnovationEducation.domain.usecase;

import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.ProfileRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.ChooseUserResponseDto;
import com.betek.usersInnovationEducation.domain.api.IAuthenticationUserInfoServicePort;
import com.betek.usersInnovationEducation.domain.model.Profile;
import com.betek.usersInnovationEducation.domain.model.User;
import com.betek.usersInnovationEducation.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    private IUserPersistencePort userPersistencePort;
    private IAuthenticationUserInfoServicePort authenticationUserInfoServicePort;
    private UserUseCase userUseCase;


    @BeforeEach
    void setUp() {
        userPersistencePort = mock(IUserPersistencePort.class);
        authenticationUserInfoServicePort = mock(IAuthenticationUserInfoServicePort.class);
        userUseCase = new UserUseCase(userPersistencePort, authenticationUserInfoServicePort);
    }

    @Test
    public void testCreateUser() {
        // Arrange
        User user = new User();

        //Act
        userUseCase.createUser(user);

        //Assert
        assertTrue(user.getState()); // Verifica que el estado del usuario sea true
        assertFalse(user.getIs_admin()); // Verifica que is_admin del usuario sea false

        // Verificación de que se llama al método createUser en userPersistencePort con el usuario creado
        verify(userPersistencePort).createUser(user);
    }

    @Test
    public void testUpdateAllInformation() {
        // Arrange
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
        UserRequestDto userRequestDto = new UserRequestDto();
        ProfileRequestDto profileRequestDto = new ProfileRequestDto();
        userRequestDto.setMember_name("Juancho");
        userRequestDto.setEmail("john@example.com");
        userRequestDto.setPassword("newpassword");
        userRequestDto.setPhone("123456789");
        userRequestDto.setIdCountry(1L);
        profileRequestDto.setDescription("Updated description");
        profileRequestDto.setProfile_image("image_url");
        profileRequestDto.setLink_website("website_url");
        userUpdateRequestDto.setUserRequestDto(userRequestDto);
        userUpdateRequestDto.setProfileRequestDto(profileRequestDto);

        // Act
        when(authenticationUserInfoServicePort.getIdentifierUserFromToken()).thenReturn(123L);

        User mockedUser = new User();
        mockedUser.setMember_name("Juancho");
        mockedUser.setEmail("john@example.com");
        mockedUser.setPassword("newpassword");
        mockedUser.setPhone("123456789");
        mockedUser.setIdCountry(1L);

        Profile mockedProfile = new Profile();
        mockedProfile.setDescription("Updated description");
        mockedProfile.setProfile_image("image_url");
        mockedProfile.setLink_website("website_url");
        when(userPersistencePort.findByIdUser(123L)).thenReturn(mockedUser);
        when(userPersistencePort.findByIdProfile(123L)).thenReturn(mockedProfile);

        userUseCase.updateAllInformation(userUpdateRequestDto);

        // Assert
        assertEquals("Juancho", mockedUser.getMember_name());
        assertEquals("john@example.com", mockedUser.getEmail());
        assertEquals("newpassword", mockedUser.getPassword());
        assertEquals("123456789", mockedUser.getPhone());
        assertEquals(1L, mockedUser.getIdCountry());

        assertEquals("Updated description", mockedProfile.getDescription());
        assertEquals("image_url", mockedProfile.getProfile_image());
        assertEquals("website_url", mockedProfile.getLink_website());

    }

    @Test
    public void testGetAnUserInformation() {
        // Arrange
        when(authenticationUserInfoServicePort.getIdentifierUserFromToken()).thenReturn(123L);

        User existingUser = new User();
        existingUser.setId(123L);
        existingUser.setIs_admin(true);
        existingUser.setState(true);
        when(userPersistencePort.getAnUserInformation(123L)).thenReturn(existingUser);

        // Act
        ChooseUserResponseDto result = userUseCase.getAnUserInformation();

        // Assert
        assertEquals(123L, result.getId());
        assertTrue(result.getIs_admin());
        assertTrue(result.getState());

        verify(userPersistencePort).getAnUserInformation(123L);
    }

    @Test
    public void testEnabledAndDisableAccount_whenUserIsActive() {
        // Arrange
        when(authenticationUserInfoServicePort.getIdentifierUserFromToken()).thenReturn(123L);

        User activeUser = new User();
        activeUser.setId(123L);
        activeUser.setState(true); // Usuario activo
        when(userPersistencePort.findByIdUser(123L)).thenReturn(activeUser);

        // Arrange
        userUseCase.enabledAndDisableAccount();

        // Assert
        assertFalse(activeUser.getState());
        assertEquals(LocalDate.now(), activeUser.getUpdated_at());

        verify(userPersistencePort).saveUser(activeUser);
    }

    @Test
    public void testEnabledAndDisableAccount_whenUserIsInactive() {
        // Arrange
        when(authenticationUserInfoServicePort.getIdentifierUserFromToken()).thenReturn(123L);

        User inactiveUser = new User();
        inactiveUser.setId(123L);
        inactiveUser.setState(false); // Usuario inactivo
        when(userPersistencePort.findByIdUser(123L)).thenReturn(inactiveUser);

        // Act
        userUseCase.enabledAndDisableAccount();

        // Assert
        assertTrue(inactiveUser.getState());
        assertEquals(LocalDate.now(), inactiveUser.getUpdated_at());

        verify(userPersistencePort).saveUser(inactiveUser);
    }
}