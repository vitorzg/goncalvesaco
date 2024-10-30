package dev.vitorzucon.goncalvesaco.application.gateways;

import java.util.List;
import dev.vitorzucon.goncalvesaco.domain.entities.User;

public interface IUserGateway {
    User createUser(User user);

    User findUser(String id);

    List<User> findAllUsers();

    void deleteUser(String id);

    void updateUser(String id,User user);
}
