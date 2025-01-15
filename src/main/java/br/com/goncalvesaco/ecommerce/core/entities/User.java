package br.com.goncalvesaco.ecommerce.core.entities;

public record User(
        String full_name,
        String username,
        String email,
        String password
) {
}
