package dev.vitorzucon.goncalvesaco.infrastructure.gateways;

import dev.vitorzucon.goncalvesaco.domain.entities.User;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.UserEntity;

public class UserEntityMapper {

    public UserEntity toEntity(User userDomainObj) {
        return new UserEntity(
                userDomainObj.email(),
                userDomainObj.fullName(),
                userDomainObj.login(),
                userDomainObj.pwd());
    }

    public User toDomain(UserEntity userEntity) {
        return new User(userEntity.getId(),userEntity.getFullName(), userEntity.getLogin(), userEntity.getPwd(), userEntity.getEmail());
    }
}
