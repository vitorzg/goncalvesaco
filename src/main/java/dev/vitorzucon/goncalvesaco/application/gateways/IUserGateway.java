package dev.vitorzucon.goncalvesaco.application.gateways;

import dev.vitorzucon.goncalvesaco.domain.entities.User;

public interface IUserGateway {
    User createUser(User user);

}
