package org.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test {
    @NotBlank(message = "email not blank")
    @Size(min = 4, max = 15, message = ">3 and <16")
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "invalid email form")
    private String email;
    @NotBlank(message = "Name not blank")
    @Pattern(regexp = "^\\+61\\d{7,14}$", message = "invalid phone number")
    private String phoneNumber;
}
