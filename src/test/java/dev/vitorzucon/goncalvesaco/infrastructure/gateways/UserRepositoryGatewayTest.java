package dev.vitorzucon.goncalvesaco.infrastructure.gateways;

import dev.vitorzucon.goncalvesaco.application.gateways.IUserGateway;
import dev.vitorzucon.goncalvesaco.domain.entities.User;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.UserEntity;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserRepositoryGatewayTest {

    private UserRepository userRepository;
    private UserEntityMapper userEntityMapper;
    private IUserGateway userGateway;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userEntityMapper = Mockito.mock(UserEntityMapper.class);
        userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User("Teste User", "login", "password", "email@example.com");
        UserEntity userEntity = new UserEntity(); // Preencha com dados necessários
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityMapper.toDomain(userEntity)).thenReturn(user);

        // Act
        User createdUser = userGateway.createUser(user);

        // Assert
        assertEquals(user, createdUser);
        verify(userEntityMapper).toEntity(user);
        verify(userRepository).save(userEntity);
        verify(userEntityMapper).toDomain(userEntity);
    }

    @Test
    void testFindUser() {
        // Arrange
        String userId = "123";
        UserEntity userEntity = new UserEntity(); // Preencha com dados necessários
        User user = new User("Teste User", "login", "password", "email@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toDomain(userEntity)).thenReturn(user);

        // Act
        User foundUser = userGateway.findUser(userId);

        // Assert
        assertEquals(user, foundUser);
        verify(userRepository).findById(userId);
        verify(userEntityMapper).toDomain(userEntity);
    }

    @Test
    void testFindAllUsers() {
        // Arrange
        UserEntity userEntity1 = new UserEntity("User 1", "login1", "password1", "email1@example.com");
        UserEntity userEntity2 = new UserEntity("User 2", "login2", "password2", "email2@example.com");
        User user1 = new User("User 1", "login1", "password1", "email1@example.com");
        User user2 = new User("User 2", "login2", "password2", "email2@example.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(userEntity1, userEntity2));
        when(userEntityMapper.toDomain(userEntity1)).thenReturn(user1);
        when(userEntityMapper.toDomain(userEntity2)).thenReturn(user2);

        // Act
        List<User> foundUsers = userGateway.findAllUsers();

        // Assert
        assertEquals(2, foundUsers.size());
        assertEquals(user1, foundUsers.get(0));
        assertEquals(user2, foundUsers.get(1));
        verify(userRepository).findAll();
    }

    @Test
    void testDeleteUser() {
        // Arrange
        String userId = "123";

        // Act
        userGateway.deleteUser(userId);

        // Assert
        verify(userRepository).deleteById(userId);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        String id = "123";
        User newUser = new User("Updated User", "login", "newPassword", "newEmail@example.com");
        UserEntity oldUserEntity = new UserEntity("Old User", "oldLogin", "oldPassword", "oldEmail@example.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(oldUserEntity));
        when(userEntityMapper.toEntity(newUser)).thenReturn(new UserEntity());

        // Act
        userGateway.updateUser(id, newUser);

        // Assert
        assertEquals("Updated User", oldUserEntity.getFullName());
        assertEquals("newEmail@example.com", oldUserEntity.getEmail());
        assertEquals("login", oldUserEntity.getLogin());
        assertEquals("newPassword", oldUserEntity.getPwd());

        verify(userRepository).findById(id);
        verify(userRepository).save(oldUserEntity);
    }

}
