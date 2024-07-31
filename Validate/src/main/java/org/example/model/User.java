package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @NotBlank(message = "Name not blank")
    private String name;

//    @NotBlank(message = "Email is mandatory")
    private String email;

//    @NotBlank(message = "Password not null")
    private String password;

    private String roles;

    private String loginSource;

    private String idProvider;
}
