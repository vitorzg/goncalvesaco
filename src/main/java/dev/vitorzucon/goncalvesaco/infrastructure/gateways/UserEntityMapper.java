package dev.vitorzucon.goncalvesaco.infrastructure.gateways;

import dev.vitorzucon.goncalvesaco.domain.entities.User;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.UserEntity;

public class UserEntityMapper {

    UserEntity toEntity(User userDomainObj) {
        return new UserEntity(
                userDomainObj.email(),
                userDomainObj.fullName(),
                userDomainObj.login(),
                userDomainObj.pwd());
    }

    User toDomain(UserEntity userEntity) {
        return new User(userEntity.getFullName(), userEntity.getLogin(), userEntity.getPwd(), userEntity.getEmail());
    }
}
