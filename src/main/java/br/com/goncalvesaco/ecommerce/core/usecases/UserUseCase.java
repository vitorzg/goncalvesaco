package br.com.goncalvesaco.ecommerce.core.usecases;

import br.com.goncalvesaco.ecommerce.core.entities.User;
import br.com.goncalvesaco.ecommerce.core.gateways.UserRepositoryGatewayCore;

import java.util.List;

public class UserUseCase {

    private final UserRepositoryGatewayCore userRepositoryGatewayCore;

    public UserUseCase(UserRepositoryGatewayCore userRepositoryGatewayCore) {
        this.userRepositoryGatewayCore = userRepositoryGatewayCore;
    }

    public List<User> findAllUsers(){
        return userRepositoryGatewayCore.findAllUsers();
    }

    public User findUser(String id){
        return userRepositoryGatewayCore.findUser(id);
    }

    public void addUser(User newUser){
        userRepositoryGatewayCore.addUser(newUser);
    }

    public void deleteUser(String userId){
        userRepositoryGatewayCore.deleteUser(userId);
    }

    public User updateUser(User userUpdated){
        return userRepositoryGatewayCore.updateUser(userUpdated);
    }
}
