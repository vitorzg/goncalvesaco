package br.com.goncalvesaco.ecommerce.core.entities;

public record User(
        String id,
        String full_name,
        String username,
        String email,
        String password
) {
}
