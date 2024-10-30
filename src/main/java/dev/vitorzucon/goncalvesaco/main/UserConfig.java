package dev.vitorzucon.goncalvesaco.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.vitorzucon.goncalvesaco.application.gateways.IUserGateway;
import dev.vitorzucon.goncalvesaco.application.usecases.UserInteractor;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.UserDTOMapper;
import dev.vitorzucon.goncalvesaco.infrastructure.gateways.UserEntityMapper;
import dev.vitorzucon.goncalvesaco.infrastructure.gateways.UserRepositoryGateway;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.UserRepository;

@Configuration
public class UserConfig {

    @Bean
    UserInteractor userCase(IUserGateway userGateway) {
        return new UserInteractor(userGateway);
    }

    @Bean
    IUserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, userEntityMapper);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }
}
