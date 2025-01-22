package br.com.goncalvesaco.ecommerce.infra.gateways;

import br.com.goncalvesaco.ecommerce.core.entities.User;
import br.com.goncalvesaco.ecommerce.infra.persistence.models.UserEntity;
import br.com.goncalvesaco.ecommerce.infra.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserRepositoryGatewayTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserEntityMapper userEntityMapper = Mockito.mock(UserEntityMapper.class);
    private final UserRepositoryGateway userRepositoryGateway = new UserRepositoryGateway(userRepository,userEntityMapper);

    @Test
    void findUser_shouldReturnUser_whenUserExists() {
        // Arrange
        String userId = "123";
        UserEntity userEntity = new UserEntity(); // Mock UserEntity instance
        User expectedUser = new User("testid","testeName","testeLogin","testeEmail","testesenha"); // Mock User instance

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(userEntity));
        when(userEntityMapper.toDomain(userEntity)).thenReturn(expectedUser);

        // Act
        User result = userRepositoryGateway.findUser(userId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedUser, result);
        verify(userRepository).findById(userId);
        verify(userEntityMapper).toDomain(userEntity);
    }

    @Test
    void findUser_shouldThrowException_whenUserDoesNotExist() {
        // Arrange
        String userId = "123";
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userRepositoryGateway.findUser(userId));
        assertEquals("User not found with id: 123", exception.getMessage());
        verify(userRepository).findById(userId);
        verifyNoInteractions(userEntityMapper);
    }
}