package dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos;

import dev.vitorzucon.goncalvesaco.domain.entities.User;

public class UserDTOMapper {

    public UserDTOResponse toResponse(User user) {
        return new UserDTOResponse(user.fullName(), user.login(), user.email());
    }

    public User toUser(UserDTORequest request) {
        return new User(request.fullName(), request.login(), request.pwd(), request.email());
    }

}
