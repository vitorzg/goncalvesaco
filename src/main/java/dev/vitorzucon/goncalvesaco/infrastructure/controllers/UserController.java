package dev.vitorzucon.goncalvesaco.infrastructure.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vitorzucon.goncalvesaco.application.usecases.UserInteractor;
import dev.vitorzucon.goncalvesaco.domain.entities.User;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.UserDTOMapper;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.UserDTORequest;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.UserDTOResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserInteractor createUserInteractor;
    private UserDTOMapper userDTOMapper;

    public UserController(UserInteractor createUserInteractor, UserDTOMapper userDTOMapper) {
        this.createUserInteractor = createUserInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping
    public UserDTOResponse create(@RequestBody UserDTORequest requestObj) {
        User newUser = userDTOMapper.toUser(requestObj);
        User savedUser = createUserInteractor.createUser(newUser);
        return userDTOMapper.toResponse(savedUser);
    }

}
