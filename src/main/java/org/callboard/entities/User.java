package org.callboard.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.callboard.annotations.NameValidation;
import org.callboard.annotations.StringFormatValidation;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @StringFormatValidation(groups = NameValidation.class)
    private String firstName;

    @NotBlank
    @StringFormatValidation(groups = NameValidation.class)
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    @Size(min = 5, max = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "users_roles")
    @JsonManagedReference
    private Set<Role> roles;
}
