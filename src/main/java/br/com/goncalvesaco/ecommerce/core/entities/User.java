package br.com.goncalvesaco.ecommerce.core.entities;

import br.com.goncalvesaco.ecommerce.infra.persistence.models.UserEntity;

import java.util.List;

public record User(
        String full_name,
        String username,
        String email,
        String password
) {

    public static User toUserDomain(UserEntity user) {
        return new User(user.getFull_name(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    public static List<User> toUsersDomain(List<UserEntity> usersList){
        return usersList.stream().map(User::toUserDomain).toList();
    }
}
