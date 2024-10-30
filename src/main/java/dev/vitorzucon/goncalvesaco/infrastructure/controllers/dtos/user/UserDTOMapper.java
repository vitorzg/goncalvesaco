package dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.user;

import dev.vitorzucon.goncalvesaco.domain.entities.User;

public class UserDTOMapper {

    public UserDTOResponse toResponse(User user) {
        return new UserDTOResponse(user.id(), user.fullName(), user.login(), user.email());
    }

    public User toDomain(UserDTORequest request) {
        return new User(null, request.fullName(), request.login(), request.pwd(), request.email());
    }

}
