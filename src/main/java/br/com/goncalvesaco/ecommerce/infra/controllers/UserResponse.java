package br.com.goncalvesaco.ecommerce.infra.controllers;

public record UserResponse(
        String id,
        String full_name,
        String username,
        String email
) {
}
