package br.com.goncalvesaco.ecommerce.infra.controllers;

import br.com.goncalvesaco.ecommerce.core.entities.User;
import br.com.goncalvesaco.ecommerce.core.usecases.UserUseCase;
import br.com.goncalvesaco.ecommerce.infra.dtos.UserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserUseCase userUseCase;
    private final UserDTOMapper userDTOMapper;

    public UserController(UserUseCase userUseCase, UserDTOMapper userDTOMapper) {
        this.userUseCase = userUseCase;
        this.userDTOMapper = userDTOMapper;
    }

    @GetMapping
    List<UserResponse> findAllUsers(){
        return userUseCase.findAllUsers().stream().map(userDTOMapper::toResponse).toList();
    }
}
