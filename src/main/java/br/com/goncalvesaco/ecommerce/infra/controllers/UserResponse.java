package br.com.goncalvesaco.ecommerce.infra.controllers;

public record UserResponse(
        String full_name,
        String username,
        String email
) {
}
