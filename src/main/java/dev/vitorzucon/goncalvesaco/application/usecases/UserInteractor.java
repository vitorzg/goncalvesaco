package dev.vitorzucon.goncalvesaco.application.usecases;

import dev.vitorzucon.goncalvesaco.application.gateways.IUserGateway;
import dev.vitorzucon.goncalvesaco.domain.entities.User;

public class UserInteractor {

    private IUserGateway userGateway;

    public UserInteractor(IUserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(User user){
        return userGateway.createUser(user);
    }
    
}
