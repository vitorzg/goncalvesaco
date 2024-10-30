package dev.vitorzucon.goncalvesaco.infrastructure.gateways;

import java.util.List;

import dev.vitorzucon.goncalvesaco.application.gateways.IUserGateway;
import dev.vitorzucon.goncalvesaco.domain.entities.User;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.UserEntity;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.UserRepository;
import jakarta.transaction.Transactional;

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

    @Override
    public User findUser(String id) {
        UserEntity objEntity = userRepository.findById(id).get();
        return userEntityMapper.toDomain(objEntity);
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User newUser) {
        UserEntity user = userRepository.findById(newUser.id()).get();
        user.setFullName(newUser.fullName());
        user.setEmail(newUser.email());
        user.setLogin(newUser.login());
        user.setPwd(newUser.pwd());
        userRepository.save(user);
    }

}
