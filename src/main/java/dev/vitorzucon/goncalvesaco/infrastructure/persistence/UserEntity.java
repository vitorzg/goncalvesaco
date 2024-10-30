package dev.vitorzucon.goncalvesaco.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    public UserEntity(String fullName, String login, String pwd, String email) {
        this.fullName = fullName;
        this.login = login;
        this.pwd = pwd;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fullName;
    private String login;
    private String pwd;
    private String email;

}
