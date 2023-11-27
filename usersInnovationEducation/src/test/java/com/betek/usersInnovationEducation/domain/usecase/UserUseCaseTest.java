package com.betek.usersInnovationEducation.domain.usecase;

import com.betek.usersInnovationEducation.domain.api.IAuthenticationUserInfoServicePort;
import com.betek.usersInnovationEducation.domain.model.User;
import com.betek.usersInnovationEducation.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
    void createProfile() {
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