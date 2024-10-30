package dev.vitorzucon.goncalvesaco;

import dev.vitorzucon.goncalvesaco.application.gateways.IUserGateway;
import dev.vitorzucon.goncalvesaco.application.usecases.UserInteractor;
import dev.vitorzucon.goncalvesaco.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class UserInteractorTest {

    private IUserGateway userGateway;
    private UserInteractor userInteractor;

    @BeforeEach
    void setUp() {
        userGateway = Mockito.mock(IUserGateway.class);
        userInteractor = new UserInteractor(userGateway);
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User("Teste User", "login", "password", "email@example.com");
        Mockito.when(userGateway.createUser(any(User.class))).thenReturn(user);

        // Act
        User createdUser = userInteractor.createUser(user);

        // Assert
        assertEquals(user, createdUser);
        verify(userGateway).createUser(user);
    }

    @Test
    void testFindUser() {
        // Arrange
        String userId = "123";
        User user = new User("Teste User", "login", "password", "email@example.com");
        Mockito.when(userGateway.findUser(userId)).thenReturn(user);

        // Act
        User foundUser = userInteractor.findUser(userId);

        // Assert
        assertEquals(user, foundUser);
        verify(userGateway).findUser(userId);
    }

    @Test
    void testFindAllUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User("User 1", "login1", "password1", "email1@example.com"));
        users.add(new User("User 2", "login2", "password2", "email2@example.com"));
        Mockito.when(userGateway.findAllUsers()).thenReturn(users);

        // Act
        List<User> foundUsers = userInteractor.findAllUsers();

        // Assert
        assertEquals(users, foundUsers);
        verify(userGateway).findAllUsers();
    }

    @Test
    void testDeleteUser() {
        // Arrange
        String userId = "123";

        userInteractor.deleteUser(userId);

        // Assert
        verify(userGateway).deleteUser(userId);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        String id = "123";
        User user = new User("Updated User", "login", "newPassword", "newEmail@example.com");

        // Act
        userInteractor.updateUser(id,user);

        // Assert
        verify(userGateway).updateUser(id,user);
    }
}
