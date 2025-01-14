package br.com.goncalvesaco.ecommerce.core.gateways;

import br.com.goncalvesaco.ecommerce.core.entities.User;

import java.util.List;

public interface UserRepositoryGatewayCore {

    User findUser(String id);
    List<User> findAllUsers();
    void addUser(User newUser);
    void deleteUser(String userId);
    User updateUser(User userUpdated);
}
