package dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.user;

public record UserDTORequest(
                String fullName,
                String login,
                String pwd,
                String email) {

}
