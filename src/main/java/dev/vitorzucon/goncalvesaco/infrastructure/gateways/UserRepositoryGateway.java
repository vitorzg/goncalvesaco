package dev.vitorzucon.goncalvesaco.infrastructure.gateways;

import dev.vitorzucon.goncalvesaco.application.gateways.IUserGateway;
import dev.vitorzucon.goncalvesaco.domain.entities.User;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.UserEntity;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.UserRepository;

public class UserRepositoryGateway implements IUserGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User createUser(User userDomainObj) {
        UserEntity userEntity = userEntityMapper.toEntity(userDomainObj);
        UserEntity savedUser = userRepository.save(userEntity);
        return userEntityMapper.toDomain(savedUser);

    }

}
