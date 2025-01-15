package br.com.goncalvesaco.ecommerce.infra.dtos;

import br.com.goncalvesaco.ecommerce.core.entities.User;
import br.com.goncalvesaco.ecommerce.infra.controllers.UserRequest;
import br.com.goncalvesaco.ecommerce.infra.controllers.UserResponse;

public class UserDTOMapper {

    public UserResponse toResponse(User userDomain){
        return new UserResponse(userDomain.full_name(),userDomain.username(),userDomain.email());
    }

    public User toDomain(UserRequest userRequest){
        return new User(userRequest.full_name(), userRequest.username(), userRequest.email(), userRequest.password());
    }
}
