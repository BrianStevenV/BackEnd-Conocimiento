package com.betek.usersInnovationEducation.domain.usecase;

import com.betek.usersInnovationEducation.domain.api.IAuthenticationUserInfoServicePort;
import com.betek.usersInnovationEducation.domain.model.Profile;
import com.betek.usersInnovationEducation.domain.model.User;
import com.betek.usersInnovationEducation.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void updateAllInformation() {
    }

    @Test
    void getAnUserInformation() {
    }

    @Test
    void enabledAndDisableAccount() {
    }
}