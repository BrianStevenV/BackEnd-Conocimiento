package com.betek.usersInnovationEducation.domain.usecase;

import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.ProfileRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserUpdateRequestDto;
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
    public void testCreateProfile() {
        // Arramge
        Profile profile = new Profile();
        when(authenticationUserInfoServicePort.getIdentifierUserFromToken()).thenReturn(123L); // Simulamos un ID de usuario obtenido del token

        // Act
        userUseCase.createProfile(profile);

        // Assert
        assertEquals(123L, profile.getId());

        verify(userPersistencePort).createProfile(profile);
    }

    @Test
    public void testUpdateAllInformation() {
        // Crear un objeto UserUpdateRequestDto con datos de prueba
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
        UserRequestDto userRequestDto = new UserRequestDto();
        ProfileRequestDto profileRequestDto = new ProfileRequestDto();
        userRequestDto.setMember_name("John Doe");
        userRequestDto.setEmail("john@example.com");
        userRequestDto.setPassword("newpassword");
        userRequestDto.setPhone("123456789");
        userRequestDto.setIdCountry(1L);
        profileRequestDto.setDescription("Updated description");
        profileRequestDto.setProfile_image("image_url");
        profileRequestDto.setLink_website("website_url");
        userUpdateRequestDto.setUserRequestDto(userRequestDto);
        userUpdateRequestDto.setProfileRequestDto(profileRequestDto);

        // Simular la obtención del ID de usuario desde el token
        when(authenticationUserInfoServicePort.getIdentifierUserFromToken()).thenReturn(123L);

        // Simular la búsqueda de usuario y perfil en userPersistencePort
        User mockedUser = new User();
        Profile mockedProfile = new Profile();
        when(userPersistencePort.findByIdUser(123L)).thenReturn(mockedUser);
        when(userPersistencePort.findByIdProfile(123L)).thenReturn(mockedProfile);

        // Ejecutar el método updateAllInformation
        userUseCase.updateAllInformation(userUpdateRequestDto);

        // Verificar que se actualizan correctamente los valores en el usuario y el perfil
        assertEquals("John Doe", mockedUser.getMember_name());
        assertEquals("john@example.com", mockedUser.getEmail());
        assertEquals("newpassword", mockedUser.getPassword());
        assertFalse(mockedUser.getIs_admin());
        assertTrue(mockedUser.getState());
        assertEquals(LocalDate.now(), mockedUser.getUpdated_at());
        assertEquals("123456789", mockedUser.getPhone());
        assertEquals(1L, mockedUser.getIdCountry());

        assertEquals("Updated description", mockedProfile.getDescription());
        assertEquals("image_url", mockedProfile.getProfile_image());
        assertEquals("website_url", mockedProfile.getLink_website());

        // Verificar que se llama al método updateAllInformation en userPersistencePort
        verify(userPersistencePort).updateAllInformation(mockedUser, mockedProfile, 123L);
    }
    @Test
    void getAnUserInformation() {
    }

    @Test
    void enabledAndDisableAccount() {
    }
}