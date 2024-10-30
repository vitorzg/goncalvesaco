package dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos;

public record UserDTORequest(
                String fullName,
                String login,
                String pwd,
                String email) {

}
