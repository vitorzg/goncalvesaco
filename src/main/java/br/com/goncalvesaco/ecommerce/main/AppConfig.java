package br.com.goncalvesaco.ecommerce.main;

import br.com.goncalvesaco.ecommerce.core.gateways.UserRepositoryGatewayCore;
import br.com.goncalvesaco.ecommerce.core.usecases.UserUseCase;
import br.com.goncalvesaco.ecommerce.infra.dtos.UserDTOMapper;
import br.com.goncalvesaco.ecommerce.infra.gateways.UserRepositoryGateway;
import br.com.goncalvesaco.ecommerce.infra.persistence.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    UserUseCase userCase(UserRepositoryGateway userRepositoryGateway){
        return new UserUseCase(userRepositoryGateway);
    }

    @Bean
    UserRepositoryGateway userGateway(UserRepository userRepository){
        return new UserRepositoryGateway(userRepository);
    }

    @Bean
    UserDTOMapper userDTOMapper(){
        return new UserDTOMapper();
    }
}
