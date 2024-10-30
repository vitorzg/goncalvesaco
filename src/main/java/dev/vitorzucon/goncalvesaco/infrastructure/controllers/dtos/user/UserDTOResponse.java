package dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.user;

public record UserDTOResponse(
        String id,
        String fullName,
        String login,
        String email) {

}
