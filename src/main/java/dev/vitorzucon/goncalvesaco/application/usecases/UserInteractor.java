package dev.vitorzucon.goncalvesaco.application.usecases;

import java.util.List;
import dev.vitorzucon.goncalvesaco.application.gateways.IUserGateway;
import dev.vitorzucon.goncalvesaco.domain.entities.User;

public class UserInteractor {

    private final IUserGateway userGateway;

    public UserInteractor(IUserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(User user) {
        return userGateway.createUser(user);
    }

    public User findUser(String id) {
        return userGateway.findUser(id);
    }

    public List<User> findAllUsers(){
        return userGateway.findAllUsers();
    }

    public void deleteUser(String id){
        userGateway.deleteUser(id);
    }

    public void updateUser(String id,User user){
        userGateway.updateUser(id, user);
    }

}
