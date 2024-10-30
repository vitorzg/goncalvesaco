package dev.vitorzucon.goncalvesaco.infrastructure.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import dev.vitorzucon.goncalvesaco.application.usecases.UserInteractor;
import dev.vitorzucon.goncalvesaco.domain.entities.User;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.user.UserDTOMapper;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.user.UserDTORequest;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.user.UserDTOResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserInteractor userInteractor;

    @Mock
    private UserDTOMapper userDTOMapper;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testCreateUser() throws Exception {
        // Arrange
        UserDTORequest request = new UserDTORequest("New User", "login", "password", "email@example.com");
        User user = new User("123", "New User", "login", "password", "email@example.com");
        User savedUser = new User("123", "New User", "login", "password", "email@example.com");
        UserDTOResponse response = new UserDTOResponse("123", "New User", "login", "email@example.com");

        when(userDTOMapper.toDomain(request)).thenReturn(user);
        when(userInteractor.createUser(user)).thenReturn(savedUser);
        when(userDTOMapper.toResponse(savedUser)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(((MockHttpServletRequestBuilder) post("/users"))
                .contentType("application/json")
                .content(
                        "{\"fullName\":\"New User\",\"login\":\"login\",\"pwd\":\"password\",\"email\":\"email@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value("New User"));
    }

    @Test
    void testFindUser() throws Exception {
        // Arrange
        String id = "123";
        User user = new User("123", "Test User", "login", "password", "email@example.com");
        UserDTOResponse response = new UserDTOResponse("123", "Test User", "login", "email@example.com");

        when(userInteractor.findUser(id)).thenReturn(user);
        when(userDTOMapper.toResponse(user)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Test User"));
    }

    @Test
    void testFindAllUsers() throws Exception {
        // Arrange
        User user1 = new User("123", "User One", "login1", "password1", "email1@example.com");
        User user2 = new User("122", "User Two", "login2", "password2", "email2@example.com");
        UserDTOResponse response1 = new UserDTOResponse("123", "User One", "login1", "email1@example.com");
        UserDTOResponse response2 = new UserDTOResponse("122", "User Two", "login2", "email2@example.com");
        List<User> users = Arrays.asList(user1, user2);

        when(userInteractor.findAllUsers()).thenReturn(users);
        when(userDTOMapper.toResponse(user1)).thenReturn(response1);
        when(userDTOMapper.toResponse(user2)).thenReturn(response2);

        // Act & Assert
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("User One"))
                .andExpect(jsonPath("$[1].fullName").value("User Two"));
    }

    @Test
    void testDeleteUser() throws Exception {
        // Arrange
        String id = "123";
        doNothing().when(userInteractor).deleteUser(id);

        // Act & Assert
        mockMvc.perform(delete("/users/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateUser() throws Exception {
        // Cenário 1: Atualização bem-sucedida
        String id = "123";
        UserDTORequest request = new UserDTORequest("Updated User", "login", "newPassword", "newEmail@example.com");
        User updatedUser = new User(id, "Updated User", "login", "newPassword", "newEmail@example.com");
        UserDTOResponse response = new UserDTOResponse(id, "Updated User", "login", "newEmail@example.com");

        // Simula o comportamento esperado
        when(userDTOMapper.toDomain(request)).thenReturn(updatedUser);
        when(userDTOMapper.toResponse(updatedUser)).thenReturn(response);

        // Executa a requisição PUT e verifica a resposta
        mockMvc.perform(put("/users/update/{id}", id)
                .contentType("application/json")
                .content(
                        "{\"fullName\":\"Updated User\",\"login\":\"login\",\"pwd\":\"newPassword\",\"email\":\"newEmail@example.com\"}"))
                .andExpect(status().isOk()) // Verifica o status 200 OK
                .andExpect(jsonPath("$.fullName").value("Updated User")) // Verifica o valor retornado no JSON
                .andExpect(jsonPath("$.email").value("newEmail@example.com")); // Verifica se o email foi atualizado

        // Cenário 2: Erro interno no servidor
        String errorId = "456";
        UserDTORequest errorRequest = new UserDTORequest("Error User", "login", "errorPassword",
                "errorEmail@example.com");
        User errorUser = new User(errorId, "Error User", "login", "errorPassword", "errorEmail@example.com");

        when(userDTOMapper.toDomain(errorRequest)).thenReturn(errorUser);
        doThrow(new RuntimeException("Update error")).when(userInteractor).updateUser(errorUser);

        mockMvc.perform(put("/users/update/{id}", errorId)
                .contentType("application/json")
                .content(
                        "{\"fullName\":\"Error User\",\"login\":\"login\",\"pwd\":\"errorPassword\",\"email\":\"errorEmail@example.com\"}"))
                .andExpect(status().isInternalServerError()); // Verifica o status 500 Internal Server Error
    }

}