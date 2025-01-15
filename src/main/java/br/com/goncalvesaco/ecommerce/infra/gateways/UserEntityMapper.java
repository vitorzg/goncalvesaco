package br.com.goncalvesaco.ecommerce.infra.gateways;

import br.com.goncalvesaco.ecommerce.core.entities.User;
import br.com.goncalvesaco.ecommerce.infra.persistence.models.UserEntity;

public class UserEntityMapper {
    UserEntity toEntity(User userDomainObj){
        return new UserEntity(userDomainObj.password(), userDomainObj.email(), userDomainObj.username(), userDomainObj.full_name());
    }

    User toDomain(UserEntity userEntityObj){
        return new User(userEntityObj.getFull_name(), userEntityObj.getUsername(), userEntityObj.getEmail(), userEntityObj.getPassword());
    }
}
