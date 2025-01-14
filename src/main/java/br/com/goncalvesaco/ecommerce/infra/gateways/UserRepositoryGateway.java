package br.com.goncalvesaco.ecommerce.infra.gateways;

import br.com.goncalvesaco.ecommerce.core.entities.User;
import br.com.goncalvesaco.ecommerce.core.gateways.UserRepositoryGatewayCore;
import br.com.goncalvesaco.ecommerce.infra.exceptions.UserNotFoundException;
import br.com.goncalvesaco.ecommerce.infra.persistence.models.UserEntity;
import br.com.goncalvesaco.ecommerce.infra.persistence.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryGateway implements UserRepositoryGatewayCore {

    private final UserRepository userRepository;

    public UserRepositoryGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUser(String id) {
        return userRepository.findById(id)
                .map(User::toUserDomain)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Override
    public List<User> findAllUsers() {
        return User.toUsersDomain((List<UserEntity>) userRepository.findAll());
    }

    @Override
    public void addUser(User newUser) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User updateUser(User userUpdated) {
        return null;
    }
}
