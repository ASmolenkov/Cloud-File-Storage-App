package io.github.asmolenkov.cloudfilestorage.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 128, message = "Username must be between 2 and 128 characters long")
    private String username;

    @NotBlank(message = "The password cannot be empty")
    @Size(min = 6, max = 100, message = "Password length from 6 to 100 characters")
    private String password;
}
