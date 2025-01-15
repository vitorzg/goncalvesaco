package br.com.goncalvesaco.ecommerce.infra.controllers;

public record UserRequest(
        String full_name,
        String username,
        String email,
        String password
) {
}
