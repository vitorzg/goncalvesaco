package dev.vitorzucon.goncalvesaco.infrastructure.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import dev.vitorzucon.goncalvesaco.application.usecases.UserInteractor;
import dev.vitorzucon.goncalvesaco.domain.entities.User;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.UserDTOMapper;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.UserDTORequest;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.UserDTOResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserInteractor userInteractor;
    private UserDTOMapper userDTOMapper;

    public UserController(UserInteractor userInteractor, UserDTOMapper userDTOMapper) {
        this.userInteractor = userInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping
    public ResponseEntity<UserDTOResponse> create(@RequestBody UserDTORequest requestObj) {
        User newUser = userDTOMapper.toUser(requestObj);
        User savedUser = userInteractor.createUser(newUser);
        UserDTOResponse response = userDTOMapper.toResponse(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTOResponse> findUser(@PathVariable String id) {
        User user = userInteractor.findUser(id);
        if (user != null) {
            UserDTOResponse response = userDTOMapper.toResponse(user);
            return ResponseEntity.ok(response); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> findAllUsers() {
        List<User> users = userInteractor.findAllUsers();
        List<UserDTOResponse> responses = users.stream()
                .map(userDTOMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        try {
            userInteractor.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> updateUser(@PathVariable String id, @RequestBody UserDTORequest requestObj) {
        User updatedUser = userDTOMapper.toUser(requestObj);

        try {
            userInteractor.updateUser(id, updatedUser);
            UserDTOResponse response = userDTOMapper.toResponse(updatedUser);
            return ResponseEntity.ok(response); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
