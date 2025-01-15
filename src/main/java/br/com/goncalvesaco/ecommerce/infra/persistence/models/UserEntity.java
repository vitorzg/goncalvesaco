package br.com.goncalvesaco.ecommerce.infra.persistence.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String full_name;

    private String username;

    private String email;

    private String password;

    public UserEntity(String password, String email, String username, String full_name) {
        this.password = password;
        this.email = email;
        this.username = username;
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
