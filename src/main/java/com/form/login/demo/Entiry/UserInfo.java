package com.form.login.demo.Entiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @NotBlank(message = "Username can not be blank")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,15}$", message = "Username can not start with a number")
    private String username;
    @NotEmpty(message = "Passord can not be empty")
//    @Size(min = 6 , max = 15 ,message = "Password must be between 6 to 16 characters long")
    private String password;
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please enter a valid email address")
    @NotBlank(message = "Email can not be blank")
    private String email;
    private String roles;

}
