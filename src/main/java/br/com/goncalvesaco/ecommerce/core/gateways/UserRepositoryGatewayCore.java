package br.com.goncalvesaco.ecommerce.core.gateways;

import br.com.goncalvesaco.ecommerce.core.entities.User;

import java.util.List;
import java.util.Map;

public interface UserRepositoryGatewayCore {

    User findUser(String id);
    List<User> findAllUsers();
    User addUser(User newUser);
    void deleteUser(String userId);
    User updateUser(String id, Map<String, Object> updates);
}
